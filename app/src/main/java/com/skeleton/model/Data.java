package com.skeleton.model;

import com.google.gson.annotations.SerializedName;

/**
 *  +++++++++++++++++++++++++++++++
 *  +++++++++Click labs +++++++++++
 *  +++++++++++++++++++++++++++++++
 */
public class Data {
    @SerializedName("appVersionDetails")
    private AppVersionDetails appVersionDetails;

    /**
     * @return object of the app version details class
     */
    public AppVersionDetails getAppVersionDetails() {
        return appVersionDetails;
    }

    /**
     * @param appVersionDetails set object of the app version details class
     */
    public void setAppVersionDetails(final AppVersionDetails appVersionDetails) {
        this.appVersionDetails = appVersionDetails;
    }
}
