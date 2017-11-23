package com.skeleton.constant;

/**
 * +++++++++++++++++++++++++++++++
 * ++++++++Raman Deep +++++++++++
 * +++++++++++++++++++++++++++++++
 */

public interface ApiKeyConstant {

    String APP_TYPE = "ANDROID";
    String DEFAULT_LANGUAGE = "en";
    String ROLE_TYPE = "driver";
    String VERSION = "100";
    //String KEY_REQUEST_TOKEN_GOOGLE = "949898545673-v7bvb01v5bomc3tq2tefp558tiusag6i.apps.googleusercontent.com";
//    String KEY_REQUEST_TOKEN_GOOGLE = "999651919253-2bc5moq66k9p10fckuk0rtkj12b40v6c.apps.googleusercontent.com";
    String KEY_REQUEST_TOKEN_GOOGLE = "554363946466-rotbr7kpklgojam91jpa6iqe4n60gqr8.apps.googleusercontent.com";
    //Response Google
    String RESPONSE_GOOGLE = "OK";
    String RESPONSE_GOOGLE_ERROR = "ZERO_RESULTS";
    String RESPONSE_OVER_QUERY_LIMIT = "OVER_QUERY_LIMIT";
    String RESPONSE_INVALID_REQUEST = "INVALID_REQUEST";

    //Error Messages
    String ERROR_MSG_PATH_NOT_FOUND = "Invalid path or Path not found.";
    String ERROR_MSG_REQUEST_DENIED = "Invalid key parameter or Unknown Error";
    String ERROR_MSG_API_ERROR = "Api Hit Error.";
    String ERROR_MSG_ZERO_RESULTS = "Sucessfull but search was passed a bounds in a remote location";
    String ERROR_MSG_INVALID_REQUEST = "Some input Parameter is Missing.";
    String ERROR_MSG_UNKNOWN_ERROR = "Unknown Error. Try Again.";


    //Google Api parameters
    String SOURCE = "origin";
    String DESTINATION = "destination";
    String WAYPOINTS = "waypoints";
    String LATLNG = "latlng";
    //    String SEARCH = "input";
//    String LANGUAGE = "language";
    String KEY = "key";


    String MESSAGE = "message";
    String DATA = "data";
    String STATUS_CODE = "statusCode";
    String AUTHORIZATION = "authorization";
    String BOOKING_ID = "bookingId";
    String LATITUDE = "latitude";
    String LONGITUDE = "longitude";
    String TIMESTAMP = "timestamp";
    String MCNUMBER = "MCNumber";
    String MOBILE_NUMBER = "primaryMobile";

    //Login API CONSTANTS
    String LANGUAGE_CONTENT = "content-language";
    String OFFSET = "utcoffset";
    String EMAIL = "email";
    String PRIMARY_MOBILE = "primaryMobile";
    String MOBILE = "mobile";
    String OTPCODE = "OTPCode";
    String DEVICE_TOKEN = "deviceToken";
    String PASSWORD = "password";
    String ROLE = "role";
    String APP_VERSION = "appVersion";
    String DEVICE_TYPE = "deviceType";
    String FIRST_NAME = "name";
    String LAST_NAME = "lastName";
    String USER_NAME = "userName";
    String COUNTRY_CODE = "countryCode";
    String TOKEN = "passwordResetToken";
    String NEW_PASSWORD = "newPassword";
    String OLD_PASSWORD = "oldPassword";
    String SOCIAL_TYPE = "socialType";
    String FACEBOOK = "Facebook";
    String GOOGLE = "Google";
    String SOCIAL_ID = "socialID";
    String ACCESS_TOKEN = "accessToken";
    String LOG_IN = "login";
    String SIGN_UP = "signup";
    String IMAGE = "image";
    String VEHICLE_IMAGES = "vehicleImages";
    String IMAGE_ORIGINAL = "original";
    String IMAGE_THUMB = "thumbnail";
    // Vehicle KEY
    /*String VEHICLE_TYPE = "vehicleType";
    String VEHICLE_COMPANY = "vehicleCompany";
    String VEHICLE_YEAR = "vehicleYear";
    String VEHICLE_NUMBER = "vehicleNumber";
    String LICENCE_NUMBER = "licenceNumber";*/


}
