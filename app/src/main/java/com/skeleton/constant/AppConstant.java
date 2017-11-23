package com.skeleton.constant;


/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep+++++++++++
 * +++++++++++++++++++++++++++++++
 */
public interface AppConstant {
    String DEVICE_TYPE = "ANDROID";

    //Intent Filter
    String NOTIFICATION_RECEIVED = "notification_received";

    //Error
    String ERROR_VALUE_MISSING = "Either start or Destination Missing";

    String CHAT_SERVER_URL = "https://socketio-chat.now.sh/";


    String HOUR = "hr";
    String MINUTES = "min";
    String NO_VALUE_STRING = "";
    int MAX_IMAGE_UPLOAD = 10;

    //
    int SESSION_EXPIRED = 401;

    int REQUEST_CODE_PLAY_STORE = 500;
    int SDK_MIN = 23;

    //Request code
    int REQ_CODE_DEFAULT_SETTINGS = 16061;
    int REQ_CODE_PLAY_SERVICES_RESOLUTION = 16061;
    int REQ_CODE_SCREEN_OVERLAY = 10101;


    float DEFAULT_POLYLINE_WIDTH = 15;
    int CONVERT_TIME_MIN = 60;
    int CONVERT_KM = 1000;
    /*
   PolyLine decoding
    */
    int POLYDECODING_1 = 63;
    int POLYDECODING_2 = 0x1f;
    int POLYDECODING_3 = 0x20;
    double POLYDECODING_4 = 1E5;

    //------- TABS
     int TAB_COUNT = 4;
     int TAB_HOME = 0;
     int TAB_BOOKING = 1;
     int TAB_AVAILABILITY = 2;
      int TAB_PROFILE = 3;

    //Geo Model Data
    String STREET_NAME = "street_number";
    String ROUTE = "route";
    String LOCALITY = "locality";
    String COUNTRY = "country";
    String POSTAL_CODE = "postal_code";
    String POLITICAL = "political";
    String FORMATTED_ADDRESS = "formatted_address";


    float DIM_AMOUNT = 0.6f;

    String LIVE = "LIVE";
    String DEV = "DEV";
    String QA = "QA";

    //-----------RegEx----------
    String REGEX_NUMERIC_STRING = "(^.\\d*$)";
    String REGEX_MOBILE_NUMBER = "(^[0-9]{10}$)";


    //Save logs Address
    String ADD_LOGS = "/logcat-base.txt";
    /**
     * GetLocationconstants
     */
    int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    int SET_TIME_INTERVAL = 101;
    int MIN_PASSWORD_LENGTH = 8;


    String CLICK_LABS_EMAIL = "contact@click-labs.com";
    String CLICK_LABS_PHONE_NUMBER = "tel:01722659902";
    String CLICK_LABS_SUPPORT = "CLICKLABS SUPPORT";


    int YEAR = 1995;
    int DAY = 28;
    int MONTH = 7;

    //Date
    String DATE_PICKER = "mDatePicker";
    String DIALOG_FORGOT_PASSWORD = "Reset password link is sent on your email";
    String REFER_CODE_SEND = "Send Refral:";
    String REFERAL_CODE = "ClickLabs100";


    String TUTORIAL_BUNDLE_FIRST = "first";
    String TUTORIAL_BUNDLE_SECOND = "second";
    String TUTORIAL_BUNDLE_THIRD = "third";

    // sharedpreference Names
    String LOGIN_CREDENTIALS = "logininfo";
    // login details
    String SOCIAL_LOGIN_BY = "social_login";

    //--------------Broadcast Key
    String BROADCAST_SCROLL = "broadcastscroll";
    String BROADCAST_TIME_SCROLL = "broadcasttimescroll";

    //------WeekView Settings-----
    String DURATION = "duration";
    String TIME_RANGE_START = "s_time";
    String TIME_RANGE_END = "e_time";
    String DAY_LENGTH = "days";
    int DEFAULT_DURATION = 2;
    int DEFAULT_START_TIME = 0;
    int DEFAULT_END_TIME = 23;
    int DEFAULT_NO_DAYS = 7;
    int DEFAULT_INITIAL_PAGES = 5;
    int DEFAULT_PREVIOUS_DATES = 2;
    String ARG_PARAM1 = "param1";
    String ARG_PARAM2 = "param2";
    String ARG_PARAM3 = "param3";
    String ARG_PARAM4 = "param4";
    String INTENT_KEY_SCROLL = "newy";
    String INTENT_KEY_POSITION = "position";
    int[] DURATION_ARRAY = {4, 2, 1};
    int[] DURATION_INDEX = {0, 1, 2};
    int[] TIME_DIFFERENCE = {15, 30, 60};
    String[] AM_PM = {"am", "pm"};
    String[] HOURS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    String[] WEEK_DAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    String[] MONTH_NAME = {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};

}
