/**
 * @author Vinay Sumani
 * Date 
 * Description
 */
package com.techmahindra.smartparking.common.exception.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.techmahindra.smartparking.pojo.dto.AbstractBaseDTO;
import com.techmahindra.smartparking.pojo.dto.error.response.APPErrorResponse;
import com.techmahindra.smartparking.pojo.dto.error.response.APPOtherErrorResponse;
import com.techmahindra.smartparking.pojo.dto.error.response.APPValidationErrorResponse;
import com.techmahindra.smartparking.pojo.dto.response.ErrorResponseDTO;

/**
 * @author VS00419238
 *
 */
@ControllerAdvice
public class DefaultExceptionHandler {
    
  
    
    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AbstractBaseDTO handleAnyUncaughtException(ServletRequestBindingException e) {
        List<APPValidationErrorResponse> validationErrList = new ArrayList<>();
        List<APPOtherErrorResponse> otherErrList = new ArrayList<>();

        otherErrList.add(new APPOtherErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Invalid Request."));

        APPErrorResponse errorRes = new APPErrorResponse();

        errorRes.setValidationErrors(validationErrList);
        errorRes.setOtherErrors(otherErrList);

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setSuccess("false");
        errorResponseDTO.setErrors(errorRes);

        return errorResponseDTO;
    
    }
    
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AbstractBaseDTO handleException1(final MethodArgumentNotValidException ex) {
 
        List<APPValidationErrorResponse> validationErrList = new ArrayList<>();
        List<APPOtherErrorResponse> otherErrList = new ArrayList<>();

        otherErrList.add(new APPOtherErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Invalid Request."));

        APPErrorResponse errorRes = new APPErrorResponse();

        errorRes.setValidationErrors(validationErrList);
        errorRes.setOtherErrors(otherErrList);

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setSuccess("false");
        errorResponseDTO.setErrors(errorRes);

        return errorResponseDTO;

    }
    
}



