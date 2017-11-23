package com.skeleton.model;

import com.google.gson.annotations.SerializedName;

/**
 *  +++++++++++++++++++++++++++++++
 *  +++++++++Click labs +++++++++++
 *  +++++++++++++++++++++++++++++++
 */
public class AppVersionDetails {
    @SerializedName("updateMessage")
    private String updateMessage;
    @SerializedName("updateTitle")
    private String updateTitle;
    @SerializedName("criticalAndroidVersion")
    private int criticalAndroidVersion;
    @SerializedName("latestAndroidVersion")
    private int latestAndroidVersion;

    /**
     * @return update message from server
     */
    public String getUpdateMessage() {
        return updateMessage;
    }

    /**
     * @param updateMessage set update message
     */
    public void setUpdateMessage(final String updateMessage) {
        this.updateMessage = updateMessage;
    }

    /**
     * @return update title from server
     */
    public String getUpdateTitle() {
        return updateTitle;
    }

    /**
     * @param updateTitle set update title
     */
    public void setUpdateTitle(final String updateTitle) {
        this.updateTitle = updateTitle;
    }

    /**
     * @return critical version from server of the app
     */
    public int getCriticalAndroidVersion() {
        return criticalAndroidVersion;
    }

    /**
     * @param criticalAndroidVersion set critical version from server of the app
     */
    public void setCriticalAndroidVersion(final int criticalAndroidVersion) {
        this.criticalAndroidVersion = criticalAndroidVersion;
    }

    /**
     * @return get the latest version of the app
     */
    public int getLatestAndroidVersion() {
        return latestAndroidVersion;
    }

    /**
     * @param latestAndroidVersion set the latest version of the app
     */
    public void setLatestAndroidVersion(final int latestAndroidVersion) {
        this.latestAndroidVersion = latestAndroidVersion;
    }
}
