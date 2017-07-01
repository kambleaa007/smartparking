package com.techmahindra.smartparking.constant;

/**
 * EAddressType.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public enum IOrganizationType {
	SOLUTION_OWNER(1), SERVICE_PROVIDER(2), PARKING_OWNER(3);

    private int value;

    /**
     * Parameterized constructor
     * 
     * @param value
     */
    private IOrganizationType(int value) {
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
