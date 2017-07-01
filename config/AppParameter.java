package com.techmahindra.smartparking.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration 
@PropertySource({ "classpath:app_parameters.properties"})
public class AppParameter {
	
	 @Autowired
	 Environment env;

}
