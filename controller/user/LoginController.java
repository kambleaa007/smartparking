package com.techmahindra.smartparking.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

import com.techmahindra.smartparking.controller.AbstractController;
import com.techmahindra.smartparking.pojo.dto.response.user.SpUserAssistanceResponseDto;
import com.techmahindra.smartparking.service.common.CommonService;
import com.techmahindra.smartparking.service.user.IUserService;

@RestController
@ApiIgnore
@RequestMapping(value = "/auth")
public class LoginController extends AbstractController {

	@Autowired
	private UserDetailsService userDetailServiceCustom;
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/{user}", method = RequestMethod.GET)
	public ModelAndView login(@PathVariable("user") String username) {

		try {
			UserDetails userDetails = userDetailServiceCustom.loadUserByUsername(username);
			Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			SpUserAssistanceResponseDto dto = userService.getUserByName(userDetails.getUsername());
			if (dto.getAnswer() != null) {
				return new ModelAndView("dashboard");
			} else {
				return new ModelAndView("set_security");
			}
		} catch (UsernameNotFoundException e) {
			ModelAndView model = new ModelAndView("loginfailed");
			model.addObject("Error", "Invalid user credentials");
			return model;
		}
	}

	@RequestMapping(value = "/forgetpassword", method = RequestMethod.GET)
	public String forgetPassword(Model model) {
		return "forgetpassword";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("relogin");

	}

}
