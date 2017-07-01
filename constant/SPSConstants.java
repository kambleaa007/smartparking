package com.techmahindra.smartparking.constant;


public final class SPSConstants {
	
	private SPSConstants(){}
	
	/*
	 * constants for SCREEN NAMES
	 * */

	public static final String LOGIN = "login";
	public static final String DASHBOARD = "dashboard";
	public static final String ZONE_HOME = "myzone";
	
	/*
	 * constants for url's
	 **/
	
	public static final String OCCUPANCY_STATUS_URL = "http://85.190.176.142:8080/smartparking-middleware/api/middleware/sensor/readings?deviceIds=";
	public static final String DEVICE_REGISTER_URL = "http://85.190.176.142:8080/smartparking-middleware/api/middleware/register/";
	
	/*
	 * constants for Messages
	 * */

	public static final String USER_MSG = "User does not exist";
	public static final String PASS_MSG = "Incorrect Password";
	public static final String ADD_ZONE_SUCCESS = "Zone added successfully";
	public static final String ADD_ZONE_FAIL = "Error while adding zone";
	public static final String EDIT_ZONE_SUCCESS = "Zone updated successfully";
	public static final String EDIT_ZONE_FAIL = "Error while updating zone";
	public static final String DELETE_ZONE_SUCCESS = "Zone deleted successfully";
	public static final String DELETE_ZONE_FAIL = "Error while deleting zone";
	public static final String DELETE_LEVEL_SUCCESS = "level removed successfully";
	public static final String DELETE_LEVEL_FAIL = "Error while removing zone";
	public static final String ADD_LEVEL_FAIL = "Error while adding Levels";
	public static final String ADD_LEVEL_SUCCESS = "Levels added successfully";
	public static final String UPDATE_LEVEL_FAIL = "Error while updating Level";
	public static final String UPDATE_LEVEL_SUCCESS = "Level updated successfully";
	public static final String BOOK_TWO_WHEELER_PARKING_SUCCESS = "Parking for two wheeler booked successfully";
	public static final String BOOK_FOUR_WHEELER_PARKING_SUCCESS = "Parking for four wheeler booked successfully";
	public static final String BOOK_TWO_WHEELER_PARKING_FAIL = "Error while booking parking for the two wheeler";
	public static final String BOOK_FOUR_WHEELER_PARKING_FAIL = "Error while booking parking for the four wheeler";
	public static final String PARKING_NOT_AVAILABLE = "No parking slot available for booking";
	public static final String TWO_WHEELER_PARKING_RELEASED = "A slot for two wheeler parking is available for booking";
	public static final String FOUR_WHEELER_PARKING_RELEASED = "A slot for four wheeler parking is available for booking";
	public static final String TWO_WHEELER_PARKING_RELEASE_FAIL = "Error while releasing two wheeler parking";
	public static final String FOUR_WHEELER_PARKING_RELEASE_FAIL = "Errorwhile releasing four wheeler booking parking";
	public static final String ADD_USER_SUCCESS = "User added successfully";
	public static final String ADD_USER_FAIL = "Error while adding user";
	public static final String EDIT_USER_SUCCESS = "User updated successfully";
	public static final String EDIT_USER_FAIL = "Error while updating user";
	public static final String DELETE_USER_SUCCESS = "User deleted successfully";
	public static final String DELETE_USER_FAIL = "Error while deleting user";
	public static final String ADD_ORG_SUCCESS = "Organization added successfully";
	public static final String ADD_ORG_FAIL = "Error while adding organization";
	public static final String EDIT_ORGANIZATION_SUCCESS = "Organization updated successfully";
	public static final String EDIT_ORGANIZATION_FAIL = "Error while updating Organization";
	public static final String ADD_SP_SUCCESS = "Service Provider added successfully";
	public static final String ADD_SP_FAIL = "Error while adding Service Provider";
	public static final String EDIT_SP_SUCCESS = "Service Provider updated successfully";
	public static final String EDIT_SP_FAIL = "Error while updating Service Provider";
	public static final String ADD_PO_SUCCESS = "Parking Owner added successfully";
	public static final String ADD_PO_FAIL = "Error while adding Parking Owner";
	public static final String EDIT_PO_SUCCESS = "Parking Owner updated successfully";
	public static final String EDIT_PO_FAIL = "Error while updating Parking Owner";
	public static final String DEREGISTER_ORGANIZATION_SUCCESS = "Parking Owner deregistered successfully";
	public static final String DEREGISTER_ORGANIZATION_FAIL = "Error while Parking Owner deregisteration";
	public static final String REGISTER_ORGANIZATION_SUCCESS = "Parking Owner registered successfully";
	public static final String REGISTER_ORGANIZATION_FAIL = "Error while Parking Owner registeration";
	public static final String INCORRECT_PASSWORD_MESSAGE = "Old Pasword is incorrect";
	public static final String UPDATE_PASSWORD_SUCCESS = "User password updated successfully";
	public static final String UPDATE_PASSWORD_FAIL = "Error while updating user password";
	public static final String UPDATE_SECURITY_SUCCESS = "User security question updated successfully";
	public static final String UPDATE_SECURITY_FAIL = "Error while updating user security question";
	public static final String USER_NOT_fOUND = "The entered user name does not exist";
	public static final String USER_NOT_ACTIVE = "The entered username is not an active user";
	public static final String SECURITY_QUESTION_NOT_FOUND = "You have not set security options yet. Please contact admin for assistance";
	public static final String EDIT_SLOT_SUCCESS = "Parking slot updated successfully";
	public static final String EDIT_SLOT_FAIL = "Error while updating parking slot";
	public static final String ADD_DEVICE_SUCCESS = "Device added successfully";
	public static final String ADD_DEVICE_FAIL = "Error while adding device";
	public static final String EDIT_DEVICE_SUCCESS = "Device updated successfully";
	public static final String EDIT_DEVICE_FAIL = "Error while updating device";
	
	public static final int INCORRECT_USER = 1;
	public static final int INCORRECT_PASSWORD = 2;
	public static final int CORRECT_CREDENTIALS = 3;
		

}
