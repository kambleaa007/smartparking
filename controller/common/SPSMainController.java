package com.techmahindra.smartparking.controller.common;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
@RequestMapping(value = "/")
public class SPSMainController {
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView viewDashboard(Map<String, Object> model) {
        
		 return new ModelAndView("dashboard");
    }
	
	@RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public ModelAndView getAuthorization(Map<String, Object> model) {
        
		 return new ModelAndView("authorization");
    }
	
	
}
