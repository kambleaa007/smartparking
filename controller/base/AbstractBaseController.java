package com.techmahindra.smartparking.controller.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.techmahindra.smartparking.pojo.dto.error.response.APPErrorResponse;
import com.techmahindra.smartparking.pojo.dto.error.response.APPOtherErrorResponse;
import com.techmahindra.smartparking.pojo.dto.error.response.APPValidationErrorResponse;



/**
 * AbstractBaseController.java Base controller for all the REST Controllers
 * exposed for FE integration.
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public abstract class AbstractBaseController {
    protected static final String RESPONSE_SUCCESS = "200";
    protected static final String RESPONSE_MESSAGE_SUCCESS = "Operation was successful";
    protected static final String RESPONSE_ERROR = "1";
    protected static final String RESPONSE_MESSAGE_ERROR = "Error performing operation";
    protected static final String DEFAULT_JSON_RESPONSE_TAG = "serviceResponse";

   protected  APPErrorResponse populateError(BindingResult result) {
        APPErrorResponse appErrorResponse = new APPErrorResponse();

        List<APPValidationErrorResponse> validationErrList = new ArrayList<>();

        if (result.hasErrors()) {

            List<FieldError> fieldErrors = result.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {

                APPValidationErrorResponse appValidationErrorResponse = new APPValidationErrorResponse();

                appValidationErrorResponse.setMsg(fieldError
                        .getDefaultMessage());
                appValidationErrorResponse.setParam(fieldError.getField());
                validationErrList.add(appValidationErrorResponse);
            }

            appErrorResponse.setValidationErrors(validationErrList);

        }
        return appErrorResponse;
    }
}