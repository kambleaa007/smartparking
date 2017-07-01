package com.techmahindra.smartparking.controller.customer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techmahindra.smartparking.common.exception.application.GenericDBException;
import com.techmahindra.smartparking.constant.IResponse;
import com.techmahindra.smartparking.controller.base.AbstractBaseController;
import com.techmahindra.smartparking.pojo.dto.AbstractBaseDTO;
import com.techmahindra.smartparking.pojo.dto.error.response.APPErrorResponse;
import com.techmahindra.smartparking.pojo.dto.request.customer.CustomerChangePasswordRequestDTO;
import com.techmahindra.smartparking.pojo.dto.request.customer.CustomerProfileEditRequestDTO;
import com.techmahindra.smartparking.pojo.dto.request.customer.CustomerProfileLoginRequestDTO;
import com.techmahindra.smartparking.pojo.dto.request.customer.CustomerProfileRegistrationRequestDTO;
import com.techmahindra.smartparking.pojo.dto.request.customer.CustomerResetPasswordRequestDTO;
import com.techmahindra.smartparking.pojo.dto.response.customer.CustomerChangePasswordResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.customer.CustomerProfileEditResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.customer.CustomerProfileLoginResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.customer.CustomerProfileRegistrationResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.customer.CustomerProfileResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.customer.CustomerResetPasswordResponseDTO;
import com.techmahindra.smartparking.service.customer.ICustomerService;
import com.techmahindra.smartparking.utility.CommonUtility;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * CustomerProfileController.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@RestController
@RequestMapping("/api/v1/mobile")
public class CustomerProfileController extends AbstractBaseController {

    @Autowired
    private ICustomerService customerService;


    /**
     * Create User Registration profile
     * 
     * @param requestBody
     * @param request
     * @return responseBody - successful response object
     */
    @ApiOperation(value = "", notes = "create the customer profile.", produces = "application/json")
    @RequestMapping(value = "/customer/registartion", method = RequestMethod.POST)
    public CustomerProfileRegistrationResponseDTO userRegistration(
            @Valid @RequestBody final CustomerProfileRegistrationRequestDTO requestBody,
            BindingResult result)

    {
        CustomerProfileRegistrationResponseDTO customerProfileRegistrationResponseDTO = new CustomerProfileRegistrationResponseDTO();

        if (result.hasErrors()) {
            APPErrorResponse appErrorResponse = populateError(result);

            customerProfileRegistrationResponseDTO.setErrors(appErrorResponse);

            customerProfileRegistrationResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_VALIDATION,
                            IResponse.ERROR_MESSAGE_VALIDATION));

            return customerProfileRegistrationResponseDTO;

        }

        try {
            customerProfileRegistrationResponseDTO = customerService
                    .userRegistration(requestBody);

            if (customerProfileRegistrationResponseDTO == null) {

                customerProfileRegistrationResponseDTO = new CustomerProfileRegistrationResponseDTO();
                customerProfileRegistrationResponseDTO
                        .setAppHeader(CommonUtility
                                .getAppResponseHeader(
                                        IResponse.ERROR_CODE_APPLICATION,
                                        IResponse.ERROR_MESSAGE_CUSTOMERPROFILE_ALREADYEXIST));
                return customerProfileRegistrationResponseDTO;

            }
            if (customerProfileRegistrationResponseDTO.getCustomerId() != null) {

                customerProfileRegistrationResponseDTO
                        .setAppHeader(CommonUtility
                                .getAppResponseHeader(
                                        IResponse.SUCCESS_CODE,
                                        IResponse.SUCCESS_MESSAGE_CUSTOMERPROFILE_REGISTERATION));
                return customerProfileRegistrationResponseDTO;
            }

            else {
                customerProfileRegistrationResponseDTO
                        .setAppHeader(CommonUtility
                                .getAppResponseHeader(
                                        IResponse.ERROR_CODE_APPLICATION,
                                        IResponse.ERROR_MESSAGE_CUSTOMERPROFILE_REGISTERATION));
                return customerProfileRegistrationResponseDTO;

            }
        } catch (GenericDBException e) {
            // TODO Auto-generated catch block
            customerProfileRegistrationResponseDTO = new CustomerProfileRegistrationResponseDTO();
            customerProfileRegistrationResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                            e.getMessage()));
            return customerProfileRegistrationResponseDTO;
        } catch (final Exception ex) {
            customerProfileRegistrationResponseDTO = new CustomerProfileRegistrationResponseDTO();
            customerProfileRegistrationResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                            ex.getMessage()));
            return customerProfileRegistrationResponseDTO;

        }

    }

    /**
     * User Login
     * 
     * @param requestBody
     * @param request
     * @return responseBody - successful response object
     */
    @ApiOperation(value = "", notes = "login the customer into app.", produces = "application/json")
    @RequestMapping(value = "/customer/login", method = RequestMethod.POST)
    public CustomerProfileLoginResponseDTO userLogin(
            @Valid @RequestBody final CustomerProfileLoginRequestDTO requestBody,
            BindingResult result)

    {
        CustomerProfileLoginResponseDTO customerProfileLoginResponseDTO = new CustomerProfileLoginResponseDTO();



        if (result.hasErrors()) {

            APPErrorResponse appErrorResponse = populateError(result);

            customerProfileLoginResponseDTO.setErrors(appErrorResponse);

            customerProfileLoginResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_VALIDATION,
                            IResponse.ERROR_MESSAGE_VALIDATION));

            return customerProfileLoginResponseDTO;

        }

        try {
            customerProfileLoginResponseDTO = customerService
                    .userLogin(requestBody);

            if (customerProfileLoginResponseDTO == null) {

                customerProfileLoginResponseDTO = new CustomerProfileLoginResponseDTO();
                customerProfileLoginResponseDTO.setAppHeader(CommonUtility
                        .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                                IResponse.ERROR_MESSAGE_INVALID_EMAILID));
                return customerProfileLoginResponseDTO;

            }
            if (customerProfileLoginResponseDTO.getCustomerId() != null) {

                customerProfileLoginResponseDTO.setAppHeader(CommonUtility
                        .getAppResponseHeader(IResponse.SUCCESS_CODE,
                                IResponse.SUCCESS_MESSAGE_CUSTOMER_LOGIN));
                return customerProfileLoginResponseDTO;
            }

            return customerProfileLoginResponseDTO;

        } catch (GenericDBException e) {
            // TODO Auto-generated catch block
            customerProfileLoginResponseDTO = new CustomerProfileLoginResponseDTO();
            customerProfileLoginResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                            e.getMessage()));
            return customerProfileLoginResponseDTO;
        } catch (final Exception ex) {
            customerProfileLoginResponseDTO = new CustomerProfileLoginResponseDTO();
            customerProfileLoginResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                            ex.getMessage()));
            return customerProfileLoginResponseDTO;

        }

    }

    @ApiOperation(value = "", notes = "create the customer profile.", produces = "application/json")
    @RequestMapping(value = "/customer/changePassword", method = RequestMethod.POST)
    public CustomerChangePasswordResponseDTO changePassword(
            @Valid @RequestBody final CustomerChangePasswordRequestDTO requestBody,
            BindingResult result) {

        CustomerChangePasswordResponseDTO customerChangePasswordResponseDTO = new CustomerChangePasswordResponseDTO();

       
        if (result.hasErrors()) {

            APPErrorResponse appErrorResponse = populateError(result);
            customerChangePasswordResponseDTO.setErrors(appErrorResponse);

            customerChangePasswordResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_VALIDATION,
                            IResponse.ERROR_MESSAGE_VALIDATION));

            return customerChangePasswordResponseDTO;

        }

        try {
            customerChangePasswordResponseDTO = customerService
                    .changePassword(requestBody);

            if (customerChangePasswordResponseDTO == null) {

                customerChangePasswordResponseDTO = new CustomerChangePasswordResponseDTO();
                customerChangePasswordResponseDTO.setAppHeader(CommonUtility
                        .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                                IResponse.ERROR_MESSAGE_INVALID_CUSTOMERID));
                return customerChangePasswordResponseDTO;

            }

            return customerChangePasswordResponseDTO;

        } catch (GenericDBException e) {
            // TODO Auto-generated catch block
            customerChangePasswordResponseDTO = new CustomerChangePasswordResponseDTO();
            customerChangePasswordResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                            e.getMessage()));
            return customerChangePasswordResponseDTO;
        } catch (final Exception ex) {
            customerChangePasswordResponseDTO = new CustomerChangePasswordResponseDTO();
            customerChangePasswordResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                            ex.getMessage()));
            return customerChangePasswordResponseDTO;

        }

    }

    @ApiOperation(value = "", notes = "create the customer profile.", produces = "application/json")
    @RequestMapping(value = "/customer/resetPassword", method = RequestMethod.POST)
    public CustomerResetPasswordResponseDTO resetPassword(
            @Valid @RequestBody final CustomerResetPasswordRequestDTO requestBody,
            BindingResult result) {

        CustomerResetPasswordResponseDTO customerResetPasswordResponseDTO = new CustomerResetPasswordResponseDTO();

        if (result.hasErrors()) {   
            APPErrorResponse appErrorResponse = populateError(result);          
            customerResetPasswordResponseDTO.setErrors(appErrorResponse);

            customerResetPasswordResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_VALIDATION,
                            IResponse.ERROR_MESSAGE_VALIDATION));

            return customerResetPasswordResponseDTO;

        }

        try {
            customerResetPasswordResponseDTO = customerService
                    .resetPassword(requestBody);

            if (customerResetPasswordResponseDTO == null) {

                customerResetPasswordResponseDTO = new CustomerResetPasswordResponseDTO();
                customerResetPasswordResponseDTO.setAppHeader(CommonUtility
                        .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                                IResponse.ERROR_MESSAGE_INVALID_EMAILID));
                return customerResetPasswordResponseDTO;

            }

            return customerResetPasswordResponseDTO;

        } catch (GenericDBException e) {
            // TODO Auto-generated catch block
            customerResetPasswordResponseDTO = new CustomerResetPasswordResponseDTO();
            customerResetPasswordResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                            e.getMessage()));
            return customerResetPasswordResponseDTO;
        } catch (final Exception ex) {
            customerResetPasswordResponseDTO = new CustomerResetPasswordResponseDTO();
            customerResetPasswordResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                            ex.getMessage()));
            return customerResetPasswordResponseDTO;

        }
    }
    

    @ApiOperation(value = "", notes = "Fetch Customer Profile", produces="application/json" )
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public AbstractBaseDTO fetchCustomerProfile(@RequestParam int customerId) {
            

  
        CustomerProfileResponseDTO customerProfileDTO = new CustomerProfileResponseDTO();

        try {
            customerProfileDTO= customerService.fetchCustomerProfile(customerId);
            
            if(customerProfileDTO==null){
                customerProfileDTO = new CustomerProfileResponseDTO();
                customerProfileDTO.setAppHeader(CommonUtility
                        .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION ,
                                IResponse.ERROR_MESSAGE_INVALID_CUSTOMERID));
            }else{
                
                customerProfileDTO.setAppHeader(CommonUtility
                        .getAppResponseHeader(IResponse.SUCCESS_CODE,
                                IResponse.SUCCESS_MESSAGE_CUSTOMER_PROFILE_SEARCH));
            }
            
            return customerProfileDTO;
            
        } catch (GenericDBException e) {
            // TODO Auto-generated catch block
            customerProfileDTO = new CustomerProfileResponseDTO();
            customerProfileDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                            e.getMessage()));
            return customerProfileDTO;
        } catch (final Exception ex) {
            
            
            customerProfileDTO = new CustomerProfileResponseDTO();
            customerProfileDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                            ex.getMessage()));
            return customerProfileDTO;

        }
        
    

    }

    
    
    @ApiOperation(value = "", notes = "edit the customer profile.", produces = "application/json")
    @RequestMapping(value = "/customer/edit", method = RequestMethod.POST)
    public CustomerProfileEditResponseDTO editCustomerProfile(
            @Valid @RequestBody final CustomerProfileEditRequestDTO requestBody,
            BindingResult result)

    {
        CustomerProfileEditResponseDTO customerProfileEditResponseDTO = new CustomerProfileEditResponseDTO();

        if (result.hasErrors()) {
            APPErrorResponse appErrorResponse = populateError(result);

            customerProfileEditResponseDTO.setErrors(appErrorResponse);

            customerProfileEditResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_VALIDATION,
                            IResponse.ERROR_MESSAGE_VALIDATION));

            return customerProfileEditResponseDTO;

        }

        try {
            customerProfileEditResponseDTO = customerService
                    .editCustomerProfile(requestBody);

            if (customerProfileEditResponseDTO == null) {

                customerProfileEditResponseDTO = new CustomerProfileEditResponseDTO();
                customerProfileEditResponseDTO
                        .setAppHeader(CommonUtility
                                .getAppResponseHeader(
                                        IResponse.ERROR_CODE_APPLICATION,
                                        IResponse.ERROR_MESSAGE_INVALID_CUSTOMERID));
                return customerProfileEditResponseDTO;

            }
            if (customerProfileEditResponseDTO.getCustomerId() != null) {

                customerProfileEditResponseDTO
                        .setAppHeader(CommonUtility
                                .getAppResponseHeader(
                                        IResponse.SUCCESS_CODE,
                                        IResponse.SUCCESS_MESSAGE_CUSTOMERPROFILE_EDIT));
                return customerProfileEditResponseDTO;
            }

            return customerProfileEditResponseDTO;
        } catch (GenericDBException e) {
            // TODO Auto-generated catch block
            customerProfileEditResponseDTO = new CustomerProfileEditResponseDTO();
            customerProfileEditResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                            e.getMessage()));
            return customerProfileEditResponseDTO;
        } catch (final Exception ex) {
            customerProfileEditResponseDTO = new CustomerProfileEditResponseDTO();
            customerProfileEditResponseDTO.setAppHeader(CommonUtility
                    .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                            ex.getMessage()));
            return customerProfileEditResponseDTO;

        }

    }

    
    
    

    /**
     * Mapping for index page
     * 
     * @return index page
     */
    @ApiIgnore
    @RequestMapping(value = "/", produces = "text/html")
    public String getIndexPage() {
        return "Welcome to TechMahindra Smart Parking Application.";
    }

}