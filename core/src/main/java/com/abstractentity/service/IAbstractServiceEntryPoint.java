package com.abstractentity.service;

import com.abstractentity.model.AbstractEntidade;
import com.abstractentity.model.dtos.IAbstractDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface IAbstractServiceEntryPoint<ENTIDADE extends AbstractEntidade, DTO extends IAbstractDTO> {

    default boolean canExecute(DTO dto, Map<String, Object> params) {
        return true;
    }

    default void beforeSave(DTO dto, ENTIDADE entidade, Map<String, Object> params) {
        // hook
    }

    default void afterSave(DTO dto, ENTIDADE entidade, Map<String, Object> params) {
        // hook
    }

    default void beforeExecute(DTO dto, Map<String, Object> params) {
        // hook
    }

    default void afterExecute(DTO dto, ENTIDADE entidade, Map<String, Object> params) {
        // hook
    }

    default boolean customEquals(ENTIDADE entidade, DTO dto, boolean currentEqualsStatus) {
        return currentEqualsStatus;
    }

    default boolean readOnly(DTO dto, ENTIDADE entidade) {
        return dto.isReadOnly();
    }

    default Object customField(
            ENTIDADE entidade,
            DTO dto,
            String entityField,
            String dtoField,
            Object attr,
            CorePropertyDescriptor propertyDescriptor,
            boolean create,
            Map<String, Object> params
    ) {
        if (attr == null && propertyDescriptor != null) {
            try {
                return propertyDescriptor.getReadMethod().invoke(entidade);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return attr;
    }

    default Map<String, Object> popularRetorno(DTO dto, ENTIDADE entidade, Map<String, Object> params) {
        return Map.of(
                "entidade", entidade.getClass().getSimpleName(),
                "estado", entidade.getEntityState()
        );
    }
}
