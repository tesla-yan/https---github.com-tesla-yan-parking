package com.smart.parking.adapter.jpa;

import com.smart.parking.domain.EntityBase;

public class EntityInvokeEvent<T extends EntityBase> extends JpaEvent {

    private String entityName;

    public EntityInvokeEvent(Object source) {
        super(source);
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
