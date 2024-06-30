package com.smart.parking.adapter.jpa;

import com.smart.parking.domain.EntityBase;
import org.springframework.context.event.EventListener;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class JpaAdapterListener {

    private JpaAdapterService jpaAdapterService;

    @EventListener(classes = EntityInvokeEvent.class)
    public void select(EntityInvokeEvent entityInvokeEvent) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        jpaAdapterService.invokeJpaCmd(entityInvokeEvent);
    }

    @EventListener(classes = EntityUpdateEvent.class)
    public void select(EntityUpdateEvent<EntityBase> entityUpdateEvent) {
        List<? extends EntityBase> entityList = entityUpdateEvent.getEntityList();
        jpaAdapterService.saveAll(entityList);
    }

    public JpaAdapterService getJpaAdapterService() {
        return jpaAdapterService;
    }

    public void setJpaAdapterService(JpaAdapterService jpaAdapterService) {
        this.jpaAdapterService = jpaAdapterService;
    }
}
