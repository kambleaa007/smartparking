package com.techmahindra.smartparking.constant;

/**
 * EAddressType.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public enum IAddressType {
    ORGANIZATION(1), ZONE(2), ZONE_SUPPORT(3);

    private int value;

    /**
     * Parameterized constructor
     * 
     * @param value
     */
    private IAddressType(int value) {
        this.value = value;
    }

    /**
     * Fetch Value
     * 
     * @return
     */
    public int getValue() {
        return this.value;
    }

}
