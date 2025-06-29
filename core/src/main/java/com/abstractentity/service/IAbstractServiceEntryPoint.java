package com.abstractentity.service;

import com.abstractentity.model.AbstractEntidade;
import com.abstractentity.model.dtos.IAbstractDTO;

import java.lang.reflect.InvocationTargetException;

public interface IAbstractServiceEntryPoint<ENTITY extends AbstractEntidade, DTO extends IAbstractDTO> {

    default boolean canExecute(DTO fields) {
        return true;
    }

    default Object findEntityReference(DTO IAbstractDTO) {
        return null;
    }

    default Object customField(ENTITY entity, DTO dto, String entityField, String dtoField, Object attr, CorePropertyDescriptor corePropertyDescriptor) {
        if (attr == null && !dto.containsAttr(dtoField) && corePropertyDescriptor != null) {
            try {
                return corePropertyDescriptor.getReadMethod().invoke(entity);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return attr;
    }
}
