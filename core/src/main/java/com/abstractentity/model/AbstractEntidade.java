package com.abstractentity.model;

import com.abstractentity.model.enuns.EntityState;
import com.abstractentity.utils.UtilsCommonToUse;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Transient
    private final String uuid = UUID.randomUUID().toString();

    @Transient
    private EntityState entityState = EntityState.NEW;

    public AbstractEntidade() {
    }

    public EntityState getEntityState() {
        return entityState;
    }

    public void setEntityState(EntityState entityState) {
        this.entityState = entityState;
    }

    @Transient
    protected Map<String, Map<Object, Object>> changedFields = new LinkedHashMap<>();

    public boolean isNew() {
        return getEntityState() != null && entityState == EntityState.NEW;
    }

    public boolean isModified() {
        return getEntityState() != null && entityState == EntityState.MODIFIED;
    }

    public boolean isDeleted() {
        return getEntityState() != null && entityState == EntityState.DELETED;
    }

    public boolean isUnmodified() {
        return getEntityState() != null && entityState == EntityState.UNMODIFIED;
    }

    public boolean changeEntityState(Object oldValue, Object newValue) {
        if (UtilsCommonToUse.equals(oldValue, newValue)) {
            return false;
        }

        if (isUnmodified()) {
            setEntityState(EntityState.MODIFIED);
        }

        return true;
    }

    @Override
    public AbstractEntidade clone() {
        try {
            return (AbstractEntidade) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e);

            return null;
        }
    }

    @PostLoad
    public void postLoadEntidade() {
        this.setEntityState(EntityState.UNMODIFIED);
    }
}
