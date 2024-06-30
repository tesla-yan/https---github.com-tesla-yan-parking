package com.smart.parking.adapter.jpa;

import com.smart.parking.domain.EntityBase;
import org.apache.commons.lang.reflect.MethodUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class JpaAdapterService {

    private JpaRepositoryFactory jpaRepositoryFactory;

    public void invokeJpaCmd(EntityInvokeEvent<? extends EntityBase> event) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        JpaRepository jpaRepository = jpaRepositoryFactory.getRepositoryForEntity(Class.forName(event.getEntityName()));

        String methodName = event.getCmd();
        Map<String, ?> parameters = event.getParameterMap();

        Object result = MethodUtils.invokeMethod(jpaRepository, methodName, parameters.values().toArray());

        if (result instanceof List) {
            event.setEntityList((List<?>) result);
        }
    }

    public <S extends EntityBase> List<S> saveAll(List<S> entityList) {
        JpaRepository jpaRepository = jpaRepositoryFactory.getRepositoryForEntity(entityList.get(0).getClass());

        jpaRepository.saveAll(entityList);

        return entityList;
    }

    public JpaRepositoryFactory getJpaRepositoryFactory() {
        return jpaRepositoryFactory;
    }

    public void setJpaRepositoryFactory(JpaRepositoryFactory jpaRepositoryFactory) {
        this.jpaRepositoryFactory = jpaRepositoryFactory;
    }
}
