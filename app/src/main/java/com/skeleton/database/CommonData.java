package com.skeleton.database;

import com.skeleton.BuildConfig;
import com.skeleton.constant.PaperDbConstant;
import com.skeleton.model.raman.DataObj;

import io.paperdb.Paper;

import static com.skeleton.constant.AppConstant.DEV;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep++++++++++
 * +++++++++++++++++++++++++++++++
 */
public final class CommonData implements PaperDbConstant {
    /**
     * Empty Constructor
     * not called
     */
    private CommonData() {
    }

    /**
     * Update fcm token.
     *
     * @param token the token
     */
//======================================== FCM token ==============================================
    public static void updateFCMToken(final String token) {
        Paper.book().write(PAPER_DEVICE_TOKEN, token);
    }

    /**
     * Gets fcm token.
     *
     * @return the fcm token
     */
    public static String getFCMToken() {
        return Paper.book().read(PAPER_DEVICE_TOKEN);
    }


    //======================================== Base URL ===========================================

    /**
     * retrieving base url as per build flavor
     *
     * @return base url from paper db
     */
    public static String getBaseUrl() {
        return Paper.book().read(BASE_SERVER_URL, BuildConfig.BASE_URL);
    }

    /**
     * settting universal flavor url(based on flavor type selected from universal flavor list) if product flavor selected is URL
     *
     * @param universalFlavorUrl , universal flavor type to be set
     */
    public static void setBaseUrl(final String universalFlavorUrl) {
        Paper.book().write(BASE_SERVER_URL, universalFlavorUrl);
    }

    /**
     * returns type of universal App flavor(dev,test,client) ,if Build Variant is universal
     *
     * @return Universal App Flavor Type (default value, DEV)
     */
    public static String getFlavorType() {
        return Paper.book().read(UNIVERSAL_TYPE, DEV);
    }

    /**
     * sets type of universal App flavor(dev,test,client) ,if Build Variant is universal
     *
     * @param flavorType , which type of flavor (DEV,QA,LIVE) in case of Universal App build variant is selected
     */
    public static void setFlavorType(final String flavorType) {
        Paper.book().write(UNIVERSAL_TYPE, flavorType);
    }

    /**
     * Save access token.
     *
     * @param accessToken the access token
     */
//======================================== Access Token ============================================

    /**
     * Save access token
     *
     * @param accessToken user access token
     */
    public static void saveAccessToken(final String accessToken) {
        Paper.book().write(PAPER_ACCESS_TOKEN, accessToken);
    }

    /**
     * Gets access token.
     *
     * @return the access token
     */
    public static String getAccessToken() {
        return Paper.book().read(PAPER_ACCESS_TOKEN);
    }


    //======================================== Clear Data ===============================================

    /**
     * Delete paper.
     */
    public static void clearData() {
        String deviceToken = getFCMToken();
        Paper.book().destroy();
        updateFCMToken(deviceToken);
    }

    /**
     * Get application server port.
     *
     * @return c
     */
    public static String getApplicationServer() {
        if (BuildConfig.IS_UNIVERSAL) {
            return Paper.book().read(KEY_APP_SERVER, BuildConfig.BASE_URL);
        }

        return BuildConfig.BASE_URL;
    }

    /**
     * Get application server port.
     *
     * @return application server port
     */
    public static String getApplicationType() {
        if (BuildConfig.IS_UNIVERSAL) {
            return Paper.book().read(KEY_APP_TYPE, BuildConfig.BUILD_SERVER_TYPE);
        }

        return BuildConfig.BUILD_SERVER_TYPE;
    }

    /**
     * Save application server.
     *
     * @param serverPort server port
     */
    public static void saveApplicationServer(final String serverPort) {
    /*If there is no default server saved, return default server from configuration.*/
        Paper.book().write(KEY_APP_SERVER, serverPort);
    }

    /**
     * Save application server.
     *
     * @param type type
     */
    public static void saveApplicationType(final String type) {
    /*If there is no default server saved, return default server from configuration.*/
        Paper.book().write(KEY_APP_TYPE, type);
    }

    public static void savaData(final DataObj commonParams) {
        Paper.book().write("ramna", commonParams);
    }

    public static DataObj getData() {
        return Paper.book().read("ramna");
    }
}

