package com.techmahindra.smartparking.constant;

/**
 * EPaymentOption.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public enum EPaymentOption {

    CARD_TYPE("cardType"), PAYMENT_METHOD_ID("paymentMethodId"), ZIPCODE("zipCode"), LAST4DIGIT(
            "last4Digit");

    String value;

    /**
     * Parameterized constructor to initialize its value
     * 
     * @param value
     */
    private EPaymentOption(String value) {
        this.value = value;
    }

    /**
     * Get PaymentOption value
     * 
     * @return
     */
    public String getValue() {
        return this.value;
    }

}
