package com.smart.parking.adapter.jpa;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

public class JpaRepositoryFactory {

    @Autowired
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T, ID> JpaRepository<T, ID> getRepositoryForEntity(Class<T> entityClass) {
        String repositoryBeanName = getRepositoryBeanName(entityClass);
        return applicationContext.getBean(repositoryBeanName, JpaRepository.class);
    }

    private String getRepositoryBeanName(Class<?> entityClass) {
        return Character.toLowerCase(entityClass.getSimpleName().charAt(0)) + entityClass.getSimpleName().substring(1) + "Repository";
    }
}
