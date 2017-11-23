
package com.skeleton.model.calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */
public class JsonModel {

    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<SingleDate> data = null;

    /**
     * @return int
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode HTTP Status code
     */
    public void setStatusCode(final Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return List
     */
    public List<SingleDate> getData() {
        return data;
    }

    /**
     * @param data Data
     */
    public void setData(final List<SingleDate> data) {
        this.data = data;
    }

}
