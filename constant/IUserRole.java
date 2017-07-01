package com.techmahindra.smartparking.constant;

/**
 * EAddressType.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

public enum IUserRole {
	SUPERADMIN(1), SOLUTION_USER_ACCESS(2), SOLUTION_ORGANIZATION_ACCESS(3), SERVICEADMIN(4), SERVICEREVENUE(5), SERVICEREPORT(6), SERVICE_USER_ACCESS(7), PARKINGADMIN(8), PARKINGREVENUE(9), PARKINGREPORT(10), PARKING_USER_ACCESS(11), DEVICE_ACCESS(12);

    private int value;

    /**
     * Parameterized constructor
     * 
     * @param value
     */
    private IUserRole(int value) {
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
