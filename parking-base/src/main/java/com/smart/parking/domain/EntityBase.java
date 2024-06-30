package com.smart.parking.domain;

import com.google.common.collect.Lists;
import com.smart.parking.adapter.jpa.EntityInvokeEvent;
import com.smart.parking.adapter.jpa.EntityUpdateEvent;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@Data
public abstract class EntityBase implements Comparable<EntityBase>, Serializable {

    protected abstract Integer getEntityId();

    protected abstract String getEntityName();

    private Integer active;

    private static EntityInvokeEvent publishRepoEvent(String entityName, String invokeMethod, Object [] parameters) {
        EntityInvokeEvent<? extends EntityBase> event = new EntityInvokeEvent<>("");
        event.setCmd(invokeMethod);
        event.setEntityName(entityName);

        if (null != parameters) {
            for (int i = 0; i < parameters.length; i++) {
                event.putParameterData(i + "", parameters[i]);
            }
        }
        event.publish();
        return event;
    }

    public static <T> List<T> invokeFind(String entityName, String invokeMethod, Object... parameters) {
        EntityInvokeEvent event = publishRepoEvent(entityName, invokeMethod, parameters);
        return event.getEntityList();
    }

    public void save() {
        List<EntityBase> list = Lists.newArrayList();
        this.setActive(1);
        list.add(this);
        saveAll(list);
    }

    public static <T> List<T> findByIds(String entityName, List<Integer> ids) {
        List<T> resultList = invokeFind(entityName, "findAllById", ids);
        return resultList;
    }

    public static <T extends EntityBase> T get(String entityName, Integer id) {
        List<Integer> ids = Lists.newArrayList(id);
        List<T> resultList = findByIds(entityName, ids);
        if (!CollectionUtils.isEmpty(resultList)) {
            return resultList.get(0);
        }
        return null;
    }

    public EntityBase delete() {
        EntityBase entity = get(this.getEntityName(), this.getEntityId());
        entity.setActive(0);
        entity.save();
        return entity;
    }

    public static <T extends EntityBase> List<T> saveAll(List<T> entityList) {
        EntityUpdateEvent entityUpdateEvent = new EntityUpdateEvent("");
        entityUpdateEvent.setEntityList(entityList);
        entityUpdateEvent.publish();
        return entityUpdateEvent.getEntityList();
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
