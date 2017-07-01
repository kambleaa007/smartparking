package com.techmahindra.smartparking.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ServiceConfig.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@Configuration
public class ServiceConfig {

    /**
     * Generate the dozer
     * 
     * @return dozer - DozerBeanMapper
     */
    @Bean
    public DozerBeanMapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        return dozer;
    }
}