package com.techmahindra.smartparking.constant;

public interface IResponse {

    static String SUCCESS_CODE = "200";
    static String ERROR_CODE_APPLICATION = "400";
    static String ERROR_CODE_VALIDATION = "401";
    static String ERROR_CODE_CUSTOMER_DEVERIFIED = "402";
    static String ERROR_CODE_REGISTRATION_VERIFICATION_PENDING = "410";
    static String SUCCESS_MESSAGE_CUSTOMERPROFILE_REGISTERATION = "Customer Profile registered successfully.";
    static String SUCCESS_MESSAGE_CUSTOMER_LOGIN = "Customer Login succesfully.";
    static String SUCCESS_MESSAGE_SERVICEPROVIDER_ADDED = "Service provider added succesfully.";
    static String ERROR_MESSAGE_CUSTOMERPROFILE_REGISTERATION = "Not able to registered Customer Profile.";
    static String ERROR_MESSAGE_CUSTOMERPROFILE_ALREADYEXIST = "Email or mobileNumber is already registered.";
    static String ERROR_MESSAGE_VALIDATION = "validation error";
    static String ERROR_MESSAGE_INVALID_EMAILID = "Email address is not valid.";
    static String ERROR_MESSAGE_INVALID_PASSWORD = "Password is not valid.";
    static String ERROR_MESSAGE_ACCOUNT_LOCK = "your account is locked.Please retry after some time";
    static String ERROR_MESSAGE_CUSTOMER_NOTACTIVATED = "customer is not verified.Please verify using the mobile number";
    static String ERROR_MESSAGE_SERVICE_PROVIDER_EXIST = "service provider with name already exist.";
    static String ERROR_MESSAGE_SOLUTION_PROVIDER_NOTEXIST = "solution provider does not exist for the service provider.";
    static String ERROR_MESSAGE_SERVICE_PROVIDER_NOTEXIST = "service provider does not exist for the parking owner.";
    static String ERROR_MESSAGE_PARKING_OWNER_EXIST = "parking owner with name already exist.";
    static String SUCCESS_MESSAGE_NONCE_VERIFICATION = "Verification code succesfully.";
    static String ERROR_MESSAGE_NONCE_VERIFICATION = "Verification code failed.";
    static String SUCCESS_MESSAGE_CUSTOMER_PASSWORD_RESET_REQUEST = "Password reset successfully.";
    static String ERROR_MESSAGE_REGISTRATION_VERIFICATION_PENDING = "Registration verification pending.";
    static String SUCCESS_MESSAGE_VERIFICATION_CODE_CREATED = "Verification code created successfully.";
    static String SUCCESS_MESSAGE_RESET_PASSWORD = "Passsword reset successfully";
    static String SUCCESS_MESSAGE_CHANGE_PASSWORD = "Passsword changed successfully";
    static String ERROR_MESSAGE_PASSWORD_MISTMATCH = "Password does not match.";
    static String ERROR_MESSAGE_PASSWORD_OLDMATCH = "new password can't be same as old password.";
    static String ERROR_MESSAGE_INVALID_CUSTOMERID = "Customer Id is not valid.";
    static String SUCCESS_MESSAGE_CUSTOMER_PROFILE_SEARCH = "Customer profile found.";
    static String SUCCESS_MESSAGE_CUSTOMERPROFILE_EDIT = "Customer profile updated successfully.";
    static String ERROR_CODE_FETCH_PARKINGZONE = "411";
    static String SUCCESS_MESSAGE_FETCH_PARKINGZONE = "Parking Zone Found Successfully.";
    static String ERROR_MESSAGE_FETCH_PARKINGZONE = "No Parking Zone Found.";
    static String ERROR_MESSAGE_MOBILE_NUMBER_EXIST = "mobile number already registered.";
    static String ERROR_CODE_FETCH_LEVEL = "412";
    static String SUCCESS_MESSAGE_FETCH_LEVEL_DETAIL = "Level Found Successfully.";
    static String SUCCESS_MESSAGE_SLOT_VEHCILE_TYPE_MSG = "Slot and Vehicle Type information found succesfully.";
    static String ERROR_MESSAGE_FETCH_LEVEL_DETAIL = "No Level Found.";
    static String SUCCESS_MESSAGE_BLOCK_PARKING = "Parking Blocked Successfully.";
    static String ERROR_MESSAGE_BLOCK_PARKING= "No parking slot available for this time duration.";
    static String ERROR_CODE_BLOCK_PARKING= "413.";
    static String SUCCESS_MESSAGE_BOOKING_COST = "Booking Cost Calculated Successfully.";
    static String ERROR_MESSAGE_BOOKING_COST= "Booking Cost Could not be calculated";
    static String SUCCESS_MESSAGE_PARKING_PAYMENT = "Parking Payment Status Updated Successfully.";
    static String SUCCESS_MESSAGE_PARKING_ENTRY = "Parking entry is allowed for QR code.";
    static String ERROR_MESSAGE_PARKING_ENTRY_ORCODE = "Not a valid QR code for parking.";
    static String ERROR_MESSAGE_PARKING_ENTRY = "Error at entry for parking.";
    static String SUCCESS_MESSAGE_PARKING_EXIT = "Parking exit is allowed for QR code.";
    static String ERROR_MESSAGE_PARKING_EXIT = "error while fetching the details of booking at exit.";
    static String ERROR_MESSAGE_PARKING_EXIT_NORECORD = "No Record found for the bookingId and customerId.";
    static String ERROR_MESSAGE_PARKING_EXIT_EXTENDED_BOOKING = "Please pay for extneded hours of booking.";
    static String ERROR_MESSAGE_PARKING_EXIT_CONSUMED = "error while updating the details of booking at exit.";

    static String SUCCESS_MESSAGE_BOOKING_HISTORY = "Booking History Found Successfully.";
    static String ERROR_MESSAGE_BOOKING_HISTORY = "No Booking History Found.";
    static String SUCCESS_MESSAGE_VERIFY_EXTENDED_BOOKING = "Extended booking detail fetched Successfully";
    static String ERROR_MESSAGE_VERIFY_EXTENDED_BOOKING = "Extended booking detail could not be fetched";

    static String SUCCESS_MESSAGE_BOOKING_CANCEL = "Booking canceled succesfully.";
    static String ERROR_MESSAGE_BOOKING_CANCEL_NORECORD = "error while cancel the booking. No record found for this booking";
    static String ERROR_MESSAGE_BOOKING_CANCEL_SYSTEM = "error while cancel the booking from system";    

}
