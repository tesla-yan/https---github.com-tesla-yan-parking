package com.smart.parking.adapter.jpa;

import com.smart.parking.adapter.AdapterEvent;
import com.smart.parking.domain.EntityBase;

import java.util.ArrayList;
import java.util.List;

public class JpaEvent<T extends EntityBase> extends AdapterEvent {

    private List<T> entityList = new ArrayList<>();

    public JpaEvent(Object source) {
        super(source);
    }

    public List<T> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
    }
}
