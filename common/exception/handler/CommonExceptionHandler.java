/**
 * @author Vinay Sumani
 * Date 
 * Description
 */
package com.techmahindra.smartparking.common.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonExceptionHandler {

    final static Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);
    
    private CommonExceptionHandler(){}

    public static boolean validateHeaderParameters(String mdnNumber, String appNonce,
            boolean mdnRequired) {

        if (appNonce == null || appNonce.trim().isEmpty()) {
            return false;
        } else
            if (mdnRequired && (mdnNumber == null || mdnNumber.trim().isEmpty())) {
                return false;
            } else {

                return true;
            }
    }

}
