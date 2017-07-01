package com.techmahindra.smartparking.config;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShutDownHookConfig {

	@Autowired
	private Scheduler scheduler;
	
	@Bean
	public SpringShutdownHook springShutDownHook() {
		
		SpringShutdownHook springShutDownHook=new SpringShutdownHook();
		
		 springShutDownHook.setScheduler(scheduler);
		
		return  springShutDownHook;
	}
}
