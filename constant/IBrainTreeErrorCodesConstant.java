package com.techmahindra.smartparking.constant;

/**
 * IBrainTreeErrorCodesConstant.java contains constants related to BrainTree
 * error codes
 *
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public interface IBrainTreeErrorCodesConstant {
    String ERROR_CODE_1000_KEY = "errorCode.1000";
    String ERROR_CODE_1001_KEY = "errorCode.1001";
    String ERROR_CODE_1002_KEY = "errorCode.1002";
    String ERROR_CODE_2005_KEY = "errorCode.2005";
    String ERROR_CODE_2006_KEY = "errorCode.2006";
    String ERROR_CODE_2008_KEY = "errorCode.2008";
    String ERROR_CODE_2010_KEY = "errorCode.2010";
    String ERROR_CODE_2016_KEY = "errorCode.2016";
    String ERROR_CODE_2060_KEY = "errorCode.2060";
    String ERROR_CODE_OTHER_KEY = "errorCode.Other";
    String ERROR_CODE_CUSTOM_MSG = "Server side processing error";
    int ERROR_CODE_UPPER_LIMIT = 3000; // BrainTree upper limit
    // for error codes
    int ERROR_CODE_LOWER_LIMIT = 1000; // BrainTree lower limit
    // for error codes
    String CUSTOM_ERROR_CODE = "9999"; // Customized error code
    // in case when no
    // BrainTree error code
    // is generated
    String GATEWAY_REJECTED_CODE = "Gateway_Rejected";
}
