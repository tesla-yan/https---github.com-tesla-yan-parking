package com.smart.parking.config;

import com.smart.parking.adapter.jpa.JpaAdapterListener;
import com.smart.parking.adapter.jpa.JpaAdapterService;
import com.smart.parking.adapter.jpa.JpaRepositoryFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
@Getter
@Slf4j
public class AppConfig {

    @Bean
    public JpaRepositoryFactory getJpaRepositoryFactory() {
        return new JpaRepositoryFactory();
    }

    @Bean
    public JpaAdapterService getJpaAdapterService(JpaRepositoryFactory jpaRepositoryFactory) {
        JpaAdapterService jpaAdapterService = new JpaAdapterService();
        jpaAdapterService.setJpaRepositoryFactory(jpaRepositoryFactory);
        return jpaAdapterService;
    }

    @Bean
    public JpaAdapterListener getJpaAdapterListener(JpaAdapterService jpaAdapterService) {
        JpaAdapterListener jpaAdapterListener = new JpaAdapterListener();
        jpaAdapterListener.setJpaAdapterService(jpaAdapterService);
        return jpaAdapterListener;
    }
}
