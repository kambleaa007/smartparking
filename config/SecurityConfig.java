package com.techmahindra.smartparking.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.techmahindra.smartparking.security.AccessDeniedHandler;
import com.techmahindra.smartparking.security.XSSFilter;

/**
 * SecurityConfig.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement(proxyTargetClass=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    final static Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class); // Initializing
                                                                                // logger

	@Autowired
	private UserDetailsService userDetailServiceCustom;
	@Autowired
	private XSSFilter xssFilter;
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	
    
    /**
     * Configure HttpSecurity
     * 
     * @param http
     */
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder registry)
			throws Exception {

		registry.userDetailsService(userDetailServiceCustom).passwordEncoder(passwordEncoder());
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.csrf().disable();
		http.authorizeRequests().antMatchers("/resources/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/user/forgotPasswordScreen").permitAll()
				.antMatchers("/user/name/**").permitAll()
				.antMatchers("/user/resetPassword/**").permitAll()
				.antMatchers("/user/setSecurityScreen/**").permitAll()
				.antMatchers("/user/**").hasAnyRole("SUPERADMIN","SOLUTION_USER_ACCESS","SERVICEADMIN","SERVICE_USER_ACCESS","PARKING_USER_ACCESS","SERVICEREPORT","PARKINGREPORT","PARKINGADMIN")
				.antMatchers("/zone/**").hasAnyRole("SUPERADMIN","PARKINGADMIN","SERVICEADMIN","PARKINGREVENUE","PARKINGREPORT","DEVICE_ACCESS","PARKINGREPORT","PARKINGADMIN")
				.antMatchers("/organization/**").hasAnyRole("SUPERADMIN","SOLUTION_ORGANIZATION_ACCESS","SERVICEADMIN")
				.antMatchers("/device/**").hasAnyRole("SUPERADMIN","DEVICE_ACCESS","PARKINGADMIN")
				.antMatchers("/reports/**").hasAnyRole("SUPERADMIN","SERVICEADMIN","SERVICEREPORT","PARKINGREPORT","PARKINGADMIN")
				.and().formLogin().loginPage("/").defaultSuccessUrl("/").failureUrl("/");
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler).accessDeniedPage("/403");
		http.addFilterBefore(xssFilter,WebAsyncManagerIntegrationFilter.class);


	}
    
    @Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
    
}
