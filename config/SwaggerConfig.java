package com.techmahindra.smartparking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@Configuration
// Enables Swagger
@EnableSwagger2
// Mandatory scan for components in the swagger package
//      @ComponentScan(basePackages = "com.mangofactory.swagger")
// I have strapped in properties here which contains the application contextpath
// and version
@PropertySource({ "classpath:app_config.properties" })
public class SwaggerConfig {

    
    @Bean
       public Docket api() {                
        return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
          .select()   
          .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
      .pathMapping("/");
    }
    
    

private ApiInfo apiInfo() {
    return new ApiInfo(
      "SMART PARKING",
      "Smart Parking By TechM.",
      "API TOS",
      "Terms of service",
      "myeaddress@company.com",
      "License of API",
      "API license URL");
}

}