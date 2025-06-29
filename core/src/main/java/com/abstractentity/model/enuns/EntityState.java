package com.abstractentity.model.enuns;

public enum EntityState {
    NEW,
    DELETED,
    MODIFIED,
    UNMODIFIED;

    public static EntityState valueOfName(String name) {
        EntityState[] values = EntityState.values();
        for (EntityState entityState : values) {
            if (entityState.name().equals(name)) {
                return entityState;
            }
        }
        return null;
    }
}
