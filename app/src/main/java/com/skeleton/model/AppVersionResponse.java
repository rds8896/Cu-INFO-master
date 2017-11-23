package com.skeleton.model;

import com.google.gson.annotations.SerializedName;

/**
 *  +++++++++++++++++++++++++++++++
 *  +++++++++Click labs +++++++++++
 *  +++++++++++++++++++++++++++++++
 */
public class AppVersionResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Data data;
    @SerializedName("statusCode")
    private int statusCode;

    /**
     * @return message from the server
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message set message for the calling object
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return object of the Data Class
     */
    public Data getData() {
        return data;
    }

    /**
     * @param data set object of the Data Class
     */
    public void setData(final Data data) {
        this.data = data;
    }

    /**
     * @return get status code of the request
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode set status code of the request
     */
    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }
}
