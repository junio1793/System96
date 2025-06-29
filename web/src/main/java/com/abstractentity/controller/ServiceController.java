package com.abstractentity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/generic/{serviceName}")
public class ServiceController {

    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping("/{methodName}")
    public <T> T ivokeMethod(
            @PathVariable String serviceName,
            @PathVariable String methodName, // ex: "save(IntegradorEndpoint,Object,Map)"
            @RequestBody Map<String, Object> data) throws Exception {
        return this.invoke(serviceName, methodName, data);
    }

    private <T> T invoke(String serviceName, String methodName, Object data) throws Exception {
        Object serviceBean = applicationContext.getBean(serviceName);

        String baseMethod = "";
        String[] paramTypeNames = new String[0];

        if (methodName.contains("(")) {
            int start = methodName.indexOf('(');
            int end = methodName.indexOf(')');

            baseMethod = methodName.substring(0, start);
            String paramsString = methodName.substring(start + 1, end);
            paramTypeNames = paramsString.split(",");
        }

        Method targetMethod = null;
        Class<?> clazz = serviceBean.getClass();

        outerLoop:
        while (clazz != null) {

            for (Method method : clazz.getDeclaredMethods()) {
                if (!method.getName().equals(baseMethod)) continue;

                Class<?>[] paramTypes = method.getParameterTypes();
                if (paramTypes.length != paramTypeNames.length) continue ;

                for (int idx = 0; idx < paramTypes.length; idx++) {
                    if (!paramTypes[idx].getSimpleName().equals(paramTypeNames[idx])) {
                        continue outerLoop;
                    }
                }
                targetMethod = method;
                break outerLoop;
            }
            clazz = clazz.getSuperclass();
        }

        if (targetMethod == null) {
            throw new NoSuchMethodException("Método " + methodName + " não encontrado no bean " + serviceName);
        }

        Object[] params = new Object[paramTypeNames.length];
        for (int i = 0; i < paramTypeNames.length; i++) {
            String paramType = paramTypeNames[i];

            if ("Object".equals(paramType)) {
                params[i] = data;
            }
        }

        targetMethod.setAccessible(true);
        Object result = targetMethod.invoke(serviceBean, params);

        return (T) result;
    }
}