package com.techmahindra.smartparking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * RootConfig.java The root / parent class for bootstrapping spring java
 * configuration classes
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@Configuration
@Import(value = {ShutDownHookConfig.class, WebConfig.class, PersistanceConfig.class,EmailConfig.class,QuartzConfig.class,
        ServiceConfig.class,SecurityConfig.class, SwaggerConfig.class, CassandraConfig.class })
@EnableAspectJAutoProxy
@EnableAsync
@EnableScheduling
public interface RootConfig {
}