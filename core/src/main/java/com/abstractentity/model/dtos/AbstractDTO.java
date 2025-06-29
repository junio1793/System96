package com.abstractentity.model.dtos;

import java.util.HashMap;
import java.util.Map;

public class AbstractDTO implements IAbstractDTO{

    private Map<String, Object> attrs = new HashMap<>();

    @Override
    public boolean containsAttr(String key) {
        return this.attrs.containsKey(key);
    }
}
