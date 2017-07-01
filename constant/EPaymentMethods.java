package com.techmahindra.smartparking.constant;

import java.util.Arrays;

/**
 * EPaymentMethods.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public enum EPaymentMethods {
    VERIZON, CREDITCARD;

    public static String[] names() {
        String valuesStr = Arrays.toString(EPaymentMethods.values());
        return valuesStr.substring(1, valuesStr.length() - 1).replace(" ", "").split(",");
    }
}
