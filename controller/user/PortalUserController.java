package com.techmahindra.smartparking.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.techmahindra.smartparking.common.exception.application.GenericDBException;
import com.techmahindra.smartparking.constant.SPSConstants;
import com.techmahindra.smartparking.controller.base.AbstractBaseController;
import com.techmahindra.smartparking.pojo.dto.request.user.SpUserAssistanceRequestDto;
import com.techmahindra.smartparking.pojo.dto.request.user.SpUserEditRequestDto;
import com.techmahindra.smartparking.pojo.dto.request.user.SpUserPasswordEditRequestDto;
import com.techmahindra.smartparking.pojo.dto.request.user.SpUserRequestDto;
import com.techmahindra.smartparking.pojo.dto.response.common.SPSCommonResponseDto;
import com.techmahindra.smartparking.pojo.dto.response.user.SPSUserListResponseDto;
import com.techmahindra.smartparking.pojo.dto.response.user.SpUserAssistanceResponseDto;
import com.techmahindra.smartparking.pojo.dto.response.user.SpUserResponseDto;
import com.techmahindra.smartparking.service.user.IUserService;

import springfox.documentation.annotations.ApiIgnore;

/**
* UserController.java
* 
 * @version version 1.0
* @author Tech Mahindra Limited
*/

/**
 * UserController.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@RestController
@ApiIgnore
@RequestMapping("/user")
public class PortalUserController extends AbstractBaseController {

	@Autowired
	private IUserService userService;

	@Secured({"ROLE_SUPERADMIN","SOLUTION_USER_ACCESS","SERVICEADMIN","SERVICE_USER_ACCESS","PARKING_USER_ACCESS"})
	@RequestMapping(value = "/view/list", method = RequestMethod.GET)
	public ModelAndView viewUserList() {

		return new ModelAndView("user");
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public ModelAndView viewServiceProviders() {

		return new ModelAndView("add_user");
	}

	@RequestMapping(value = "userdetail/{userId}", method = RequestMethod.GET)
	public ModelAndView viewUserDetail(@PathVariable("userId") int id) {
		return new ModelAndView("user_detail");
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public SPSCommonResponseDto addUser(@RequestBody SpUserRequestDto userdto) {

		try {
			return new SPSCommonResponseDto(userService.addUser(userdto));
		} catch (GenericDBException e) {
			return new SPSCommonResponseDto(SPSConstants.ADD_USER_FAIL);
		}
	}

	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public SPSCommonResponseDto updateUser(@RequestBody SpUserEditRequestDto userdto) {

		try {
			return new SPSCommonResponseDto(userService.editUser(userdto));
		} catch (GenericDBException e) {
			return new SPSCommonResponseDto(e.getMessage());
		}
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public SPSCommonResponseDto updateUserPassword(@RequestBody SpUserPasswordEditRequestDto userdto) {

		try {
			return new SPSCommonResponseDto(userService.updateUserPassword(userdto));
		} catch (GenericDBException e) {
			return new SPSCommonResponseDto(SPSConstants.UPDATE_PASSWORD_FAIL);
		}
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public SPSCommonResponseDto resetUserPassword(@RequestBody SpUserPasswordEditRequestDto userdto) {

		try {
			return new SPSCommonResponseDto(userService.resetUserPassword(userdto));
		} catch (GenericDBException e) {
			return new SPSCommonResponseDto(SPSConstants.UPDATE_PASSWORD_FAIL);
		}
	}

	@RequestMapping(value = "/changePasswordScreen", method = RequestMethod.GET)
	public ModelAndView getUserPasswordScreen() {
		return new ModelAndView("change_password");
	}

	@RequestMapping(value = "/setSecurityScreen", method = RequestMethod.GET)
	public ModelAndView getSecurityQuestionScreen() {
		return new ModelAndView("set_security");
	}
	
	@RequestMapping(value = "/setSecurityQuestion", method = RequestMethod.POST)
	public SPSCommonResponseDto setSecurityQuestion(@RequestBody SpUserAssistanceRequestDto userdto) {

		try {
			return new SPSCommonResponseDto(userService.setUserSecurityQuestion(userdto));
		} catch (GenericDBException e) {
			return new SPSCommonResponseDto(SPSConstants.UPDATE_SECURITY_FAIL);
		}
	}


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public SPSUserListResponseDto getUserList() {

		return userService.getUserList();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public SpUserResponseDto getUser(@PathVariable("id") int id) {

		return userService.getUser(id);

	}

	@RequestMapping(value = "/forgotPasswordScreen", method = RequestMethod.GET)
	public ModelAndView getForgotPasswordScreen() {
		return new ModelAndView("resetpassword");
	}
		
	@RequestMapping(value = "name/{name}", method = RequestMethod.GET)
	public SpUserAssistanceResponseDto getUserByName(@PathVariable("name") String name) {

		return userService.getUserByName(name);

	}

	@RequestMapping(value = "/loggedUser", method = RequestMethod.GET)
	public SpUserResponseDto getLoggedUser() {

		return userService.getLoggedUser();

	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public SPSCommonResponseDto deleteUser(@PathVariable("id") int id) {

		try {
			return new SPSCommonResponseDto(userService.deleteUser(id));
		} catch (GenericDBException e) {
			return new SPSCommonResponseDto(SPSConstants.DELETE_USER_FAIL);
		}

	}

}
