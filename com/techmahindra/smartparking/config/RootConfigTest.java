package com.techmahindra.smartparking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
/*@Import(value = {ShutDownHookConfig.class, WebConfig.class, PersistanceConfig.class,EmailConfig.class,QuartzConfig.class,
        ServiceConfig.class,SecurityConfig.class, SwaggerConfig.class, CassandraConfig.class })*/
//@Import(value = {WebConfig.class, PersistanceConfig.class})
@EnableAspectJAutoProxy
//@EnableAsync
//@EnableScheduling
@EnableWebMvc
public class RootConfigTest {

}
