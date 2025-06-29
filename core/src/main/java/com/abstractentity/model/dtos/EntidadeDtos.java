package com.abstractentity.model.dtos;

import java.util.LinkedHashMap;
import java.util.Map;

public class EntidadeDtos implements IAbstractDTO {

    @Override
    public boolean containsAttr(String key) {
        return this.fields.containsKey(key) ? true : false;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    private Map<String, Object> fields = new LinkedHashMap<>();

    @Override
    public <T> T getAttr(String key) {
        return this.getAttr(key);
    }
}
