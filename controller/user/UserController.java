package com.techmahindra.smartparking.controller.user;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmahindra.smartparking.controller.base.AbstractBaseController;
import com.techmahindra.smartparking.pojo.dto.request.user.SpServiceProviderRequestDto;
import com.techmahindra.smartparking.pojo.dto.response.user.SpServiceProviderResponseDto;
import com.techmahindra.smartparking.service.user.IUserService;

import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/v1/user")
public class UserController extends AbstractBaseController {

    @Autowired
    private IUserService userService;
    
   
    
    @ApiOperation(value = "", notes ="add the service provider.", produces="application/json" )
    @RequestMapping(value = "/serviceProvider/add", method = RequestMethod.POST)
     public SpServiceProviderResponseDto userServiceProviderAdd(@Valid @RequestBody final SpServiceProviderRequestDto requestBody,BindingResult result)
    
    {

         /*       SpServiceProviderResponseDto spServiceProviderResponseDto = new SpServiceProviderResponseDto();
                
                APPErrorResponse appErrorResponse=new APPErrorResponse();
                
        List<APPValidationErrorResponse> validationErrList = new ArrayList<APPValidationErrorResponse>();
     
        
                                if(result.hasErrors())
                                {
                                                
                                                List<FieldError> fieldErrors=result.getFieldErrors();
                                                
                                                for (FieldError fieldError : fieldErrors) {
                                                                
                                                                APPValidationErrorResponse appValidationErrorResponse=new APPValidationErrorResponse();
                                                                
                                                                appValidationErrorResponse.setMsg(fieldError.getDefaultMessage());
                                                                appValidationErrorResponse.setParam(fieldError.getField());
                                                                validationErrList.add(appValidationErrorResponse);
                                                }
                                                
                                                appErrorResponse.setValidationErrors(validationErrList);
                                                spServiceProviderResponseDto.setErrors(appErrorResponse);
                                                
                                                spServiceProviderResponseDto.setAppHeader(CommonUtility.getAppResponseHeader(IResponse.ERROR_CODE_VALIDATION,
                                                                                IResponse.ERROR_MESSAGE_VALIDATION));
                                                
                                                return spServiceProviderResponseDto;
                                                
                                                
                                }
                
                                try {
                                                spServiceProviderResponseDto=userService.serviceProviderRegistration(requestBody);
                                } catch (GenericDBException e) {
                                                // TODO Auto-generated catch block
                                
                                                spServiceProviderResponseDto.setAppHeader(CommonUtility.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                                                                                e.getMessage()));
                                                return spServiceProviderResponseDto;
                                }
                                
                                if(spServiceProviderResponseDto.getServiceProviderId()!=null){
                                                
                                                spServiceProviderResponseDto.setAppHeader(CommonUtility.getAppResponseHeader(IResponse.SUCCESS_CODE,
                                                                                IResponse.SUCCESS_MESSAGE_SERVICEPROVIDER_ADDED));
                                                return spServiceProviderResponseDto;
                                }

                                return spServiceProviderResponseDto;
                 

    	
     
    
    	*/ 

    	return null;
     }
  
    
   
}




