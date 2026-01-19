package org.surest.config;

import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class JpaConfig {

    @PostConstruct
    public void init() {
        System.setProperty(
                "hibernate.physical_naming_strategy",
                PhysicalNamingStrategyStandardImpl.class.getName()
        );
    }
}
