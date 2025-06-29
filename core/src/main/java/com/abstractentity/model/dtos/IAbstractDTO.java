package com.abstractentity.model.dtos;

import java.io.Serializable;

public interface IAbstractDTO extends Serializable {

    boolean containsAttr(String key);
}
