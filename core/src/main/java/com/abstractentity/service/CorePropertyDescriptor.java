package com.abstractentity.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class CorePropertyDescriptor {

    private final Field field;

    private final Method readMethod;

    private final Method writeMethod;

    CorePropertyDescriptor(Field field, Method readMethod, Method writeMethod) {
        this.field = field;
        this.readMethod = readMethod;
        this.writeMethod = writeMethod;
    }

    public Field getField() {
        return field;
    }

    public Method getReadMethod() {
        return readMethod;
    }

    public Method getWriteMethod() {
        return writeMethod;
    }

    public Class<?> getPropertyType() {
        return field.getType();
    }

    public Class<?> getGenericType() {
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType) {
            Type argType = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (argType instanceof Class<?>) {
                return (Class<?>) argType;
            } else {
                return Object.class;
            }
        }
        return field.getType();
    }

    public String getName() {
        return field.getName();
    }
}
