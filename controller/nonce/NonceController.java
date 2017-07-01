package com.techmahindra.smartparking.controller.nonce;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmahindra.smartparking.common.exception.application.GenericDBException;
import com.techmahindra.smartparking.constant.IResponse;
import com.techmahindra.smartparking.controller.base.AbstractBaseController;
import com.techmahindra.smartparking.pojo.dto.error.response.APPErrorResponse;
import com.techmahindra.smartparking.pojo.dto.request.nonce.NonceCreationRequestDTO;
import com.techmahindra.smartparking.pojo.dto.request.nonce.NonceVerificationRequestDTO;
import com.techmahindra.smartparking.pojo.dto.response.nonce.NonceCreationResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.nonce.NonceVerificationResponseDTO;
import com.techmahindra.smartparking.service.nonce.INonceService;
import com.techmahindra.smartparking.utility.CommonUtility;

import io.swagger.annotations.ApiOperation;

/**
 * HomePageController.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@RestController
@RequestMapping("/api/v1/mobile")
public class NonceController extends AbstractBaseController {

    @Autowired
    private INonceService nonceService;
    
   /* @Autowired
    private EmailSenderService mailService;
    
    @Autowired
    private SmsConfigBean smsConfigBean;*/

    
    /**
     *  User Verification Code 
     * 
     * @param requestBody
     * @param request
     * @return responseBody - successful response  object
     */
    @ApiOperation(value = "", notes ="Verify Nonce.", produces="application/json" )
    @RequestMapping(value = "/nonce/verification", method = RequestMethod.POST)
     public NonceVerificationResponseDTO nonceVerification(@Valid @RequestBody final NonceVerificationRequestDTO requestBody,BindingResult result)
    
    {
        NonceVerificationResponseDTO nonceVerificationResponseDTO = new NonceVerificationResponseDTO();
        
    
                if(result.hasErrors())
                {
                        
                        APPErrorResponse appErrorResponse = populateError(result);
                        nonceVerificationResponseDTO.setErrors(appErrorResponse);
                        
                        nonceVerificationResponseDTO.setAppHeader(CommonUtility.getAppResponseHeader(IResponse.ERROR_CODE_VALIDATION,
                                        IResponse.ERROR_MESSAGE_VALIDATION));
                        
                        return nonceVerificationResponseDTO;
                        
                        
                }
        
        try {
            nonceVerificationResponseDTO=nonceService.verifyNonceCode(requestBody);
                
                if(nonceVerificationResponseDTO==null){
                        
                    nonceVerificationResponseDTO=new NonceVerificationResponseDTO();
                    nonceVerificationResponseDTO.setAppHeader(CommonUtility.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                                        IResponse.ERROR_MESSAGE_INVALID_EMAILID));
                        return nonceVerificationResponseDTO;
                        
                }
                if(nonceVerificationResponseDTO.getCustomerId()!=null){
                        
                    nonceVerificationResponseDTO.setAppHeader(CommonUtility.getAppResponseHeader(IResponse.SUCCESS_CODE,
                                        IResponse.SUCCESS_MESSAGE_NONCE_VERIFICATION));
                         return nonceVerificationResponseDTO;
                }
                
                return nonceVerificationResponseDTO;
                
                } catch (GenericDBException e) {
                        // TODO Auto-generated catch block
                    nonceVerificationResponseDTO=new NonceVerificationResponseDTO();
                    nonceVerificationResponseDTO.setAppHeader(CommonUtility.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                                        e.getMessage()));
                        return nonceVerificationResponseDTO;
                }
        catch (final Exception ex) {
            nonceVerificationResponseDTO=new NonceVerificationResponseDTO();
            nonceVerificationResponseDTO.setAppHeader(CommonUtility.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                                        ex.getMessage()));
                        return nonceVerificationResponseDTO;
         
        }
 
         
         
     }
    
    @ApiOperation(value = "", notes ="Create Nonce.", produces="application/json" )
    @RequestMapping(value = "/nonce/creation", method = RequestMethod.POST)
     public NonceCreationResponseDTO nonceCreation(@Valid @RequestBody final NonceCreationRequestDTO requestBody,BindingResult result)
    
    {
        NonceCreationResponseDTO nonceCreationResponseDTO = new NonceCreationResponseDTO();
        
     
       
                if(result.hasErrors())
                {
                        
                        APPErrorResponse appErrorResponse = populateError(result);
                        nonceCreationResponseDTO.setErrors(appErrorResponse);
                        
                        nonceCreationResponseDTO.setAppHeader(CommonUtility.getAppResponseHeader(IResponse.ERROR_CODE_VALIDATION,
                                        IResponse.ERROR_MESSAGE_VALIDATION));
                        
                        return nonceCreationResponseDTO;
                        
                        
                }
        
        try {
            nonceCreationResponseDTO=nonceService.nonceCreation(requestBody);
                
                if(nonceCreationResponseDTO==null){
                        
                    nonceCreationResponseDTO=new NonceCreationResponseDTO();
                    nonceCreationResponseDTO.setAppHeader(CommonUtility.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                            IResponse.ERROR_MESSAGE_INVALID_EMAILID));
                        return nonceCreationResponseDTO;
                        
                }
                if(nonceCreationResponseDTO.getVerificationCode()!=null){
                        
                    nonceCreationResponseDTO.setAppHeader(CommonUtility.getAppResponseHeader(IResponse.SUCCESS_CODE,
                                        IResponse.SUCCESS_MESSAGE_VERIFICATION_CODE_CREATED));
                   /* //Test the mail.
                    mailService.sendMail("1708.amit@gmail.com", "devnokiasmparking@gmail.com", "verifciation code", "verifcation code for registration is "+nonceCreationResponseDTO.getVerificationCode());
                    // Test the SMS
                    CommonUtility.sendSmS(smsConfigBean.getAuthToken(), smsConfigBean.getAccountSid(), smsConfigBean.getTwilioNumber(), "verifcation code for registration is "+nonceCreationResponseDTO.getVerificationCode(), "+917838686871");*/
                    
                    return nonceCreationResponseDTO;
                }
                
                return nonceCreationResponseDTO;
                } catch (GenericDBException e) {
                        // TODO Auto-generated catch block
                    nonceCreationResponseDTO=new NonceCreationResponseDTO();
                    nonceCreationResponseDTO.setAppHeader(CommonUtility.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                                        e.getMessage()));
                        return nonceCreationResponseDTO;
                }
        catch (final Exception ex) {
            nonceCreationResponseDTO=new NonceCreationResponseDTO();
            nonceCreationResponseDTO.setAppHeader(CommonUtility.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                                        ex.getMessage()));
                        return nonceCreationResponseDTO;
         
        }
 
       
         
     }
     

   
}