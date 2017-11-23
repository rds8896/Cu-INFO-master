package com.skeleton.retrofit;


import com.google.gson.JsonObject;
import com.skeleton.model.modellogin.ResponseLogin;
import com.skeleton.model.modelsignup.ResponseSignUp;
import com.skeleton.model.modelsociallogin.ResponseSocialLogin;
import com.skeleton.model.raman.DataObj;
import com.skeleton.util.googledirections.geocodingmodel.GeoCodedApiResponse;
import com.skeleton.util.googledirections.googledirectionmodel.DirectionApiResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import static com.skeleton.constant.ApiKeyConstant.AUTHORIZATION;
import static com.skeleton.constant.ApiKeyConstant.EMAIL;
import static com.skeleton.constant.ApiKeyConstant.LANGUAGE_CONTENT;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public interface ApiInterface {

    /*SERVER_DOMAIN PORT*/
    /*Development server*/
    String DEV_SERVER = "DEV_SERVER";
    /*Test server*/
    String TEST_SERVER = "TEST_SERVER";
    /*Live server*/
    String LIVE_SERVER = "LIVE_SERVER";
    /*Live Pre server*/
    String LIVE_PRE_SERVER = "LIVE_PRE_SERVER";

    //   String UNIVERSAL_BASE_URL = null;
    String UPDATE_LOCATION = "api/v1/user/updateLocation";
    String GOOGLE_DIRECTION = "maps/api/directions/json";
    String GEO_CODING = "maps/api/geocode/json";
//    String GOOGLE_AUTO_COMPLETE_ADDRESS = "maps/api/place/autocomplete/json";


    //Api Key Constants
    String API_USER = "user";
    String API_CUSTOMER = "customer";
    String API_SOCIAL = "social";
    String API_DRIVER = "driver";


    String LOGIN_API = API_USER + "/login";
    String VERIFY_OTP = API_USER + "/verifyMobileOTP";
    String RESEND_OTP = API_USER + "/resendOTP";
    String UPLOAD_FILE = API_USER + "/uploadFile";
    String FORGOT_PASSWORD = API_USER + "/getResetPasswordToken";
    String SIGN_UP = API_CUSTOMER + "/registerFromEmail";
    String RESET_PASSWORD = API_USER + "/resetPassword";
    String LOG_OUT = API_USER + "/logout";
    String SOCIAL_LOGIN = API_SOCIAL + "/login";
    String CHANGE_PASSWORD = API_USER + "/changePassword";
    String ADD_VEHICLE_DETAIL = API_DRIVER + "/addVehicle";
    String SIGN_UP_DRIVER = API_DRIVER + "/registerFromEmail";
    String GET_DATA = "customer/getData";
    String UPDATE_DATA = "customer/updateData";
    String ADD_DATA = "customer/addData";


    /**
     * @param language language
     * @param map      map
     * @return response
     */
    @FormUrlEncoded
    @POST(SOCIAL_LOGIN)
    Call<ResponseSocialLogin> socialLogin(@Header(LANGUAGE_CONTENT) String language, @FieldMap HashMap<String, String> map);


    /**
     * @param headerMap header map
     * @return return the response
     */
    @POST(LOG_OUT)
    Call<CommonParams> logout(@HeaderMap HashMap<String, String> headerMap);

    /**
     * @param language language
     * @param map      map
     * @return return the response
     */
    @FormUrlEncoded
    @POST(SIGN_UP)
    Call<ResponseSignUp> signUp(@Header(LANGUAGE_CONTENT) String language, @FieldMap HashMap<String, String> map);

    /**
     * @param headerMap language and Authorization
     * @param map       SignUp from Data
     * @return response
     */
    @FormUrlEncoded
    @POST(SIGN_UP_DRIVER)
    Call<ResponseSignUp> signUpDriver(@HeaderMap HashMap<String, String> headerMap, @FieldMap HashMap<String, String> map);

    /**
     * @param language language
     * @param email    email of user
     * @return return the response
     */
    @GET(FORGOT_PASSWORD)
    Call<CommonParams> forgotPassword(@Header(LANGUAGE_CONTENT) String language, @Query(EMAIL) String email);

    /**
     * @param headerMap language and Authorization
     * @param map       old password , new Password
     * @return response
     */
    @FormUrlEncoded
    @PUT(CHANGE_PASSWORD)
    Call<CommonParams> changePassword(@HeaderMap HashMap<String, String> headerMap, @FieldMap HashMap<String, String> map);

    /**
     * @param headerMap headers of hash map
     * @param map       Body Map
     * @return return the response
     */
    @PUT(RESEND_OTP)
    Call<CommonResponse> resendOTP(@HeaderMap HashMap<String, String> headerMap, @Body HashMap<String, String> map);


    /**
     * @param language language
     * @param map      map
     * @return retunr reset password
     */
    @FormUrlEncoded
    @PUT(RESET_PASSWORD)
    Call<CommonParams> resetPassword(@Header(LANGUAGE_CONTENT) String language, @FieldMap HashMap<String, String> map);


    /**
     * @param headerMap headers Map
     * @param map       otp
     * @return the response or failure
     */
    @PUT(VERIFY_OTP)
    Call<CommonResponse> verifyOTP(@HeaderMap HashMap<String, String> headerMap, @Body JsonObject map);

    /**
     * @param map map of string, string
     * @return Response in format DirectionApiResponse
     */
    @GET(GOOGLE_DIRECTION)
    Call<DirectionApiResponse> getDirections(@QueryMap Map<String, String> map);

    /**
     * @param map map
     * @return Response in GeoCodedApiResponse
     */
    @GET(GEO_CODING)
    Call<GeoCodedApiResponse> getGeoAddress(@QueryMap Map<String, String> map);

    /**
     * @param headerMap Headers on API
     * @param map       Data to be send
     * @return return the response
     */
    @FormUrlEncoded
    @POST(LOGIN_API)
    Call<ResponseLogin> login(@HeaderMap HashMap<String, String> headerMap, @FieldMap HashMap<String, String> map);

    /**
     * Update location call.
     *
     * @param authorization the authorization
     * @param map           the map
     * @return the call
     */
    @FormUrlEncoded
    @POST(UPDATE_LOCATION)
    Call<CommonParams> updateLocation(@Header(AUTHORIZATION) String authorization,
                                      @FieldMap HashMap<String, String> map);

    /**
     * @param headerMap Headers on API
     * @param map       Data to be send
     * @return return the response
     */
    @FormUrlEncoded
    @POST(ADD_VEHICLE_DETAIL)
    Call<CommonParams> addCarDetails(@HeaderMap HashMap<String, String> headerMap
            , @FieldMap HashMap<String, String> map);


    /**
     * @return return the response
     */
    @GET(GET_DATA)
    Call<DataObj> getData(@Query("startDate") String date, @Query("uniqueId") String id);


    /**
     * @return return the response
     */
    @FormUrlEncoded
    @PUT(UPDATE_DATA)
    Call<CommonResponse> updateData(@Header("content-language") String date, @FieldMap HashMap<String, String> stringHashMap);

    /**
     * @return return the response
     */
    @FormUrlEncoded
    @POST(ADD_DATA)
    Call<CommonResponse> addData(@Header("content-language") String date, @FieldMap HashMap<String, String> stringHashMap);

}

