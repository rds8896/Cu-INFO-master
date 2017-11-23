
package com.skeleton.model.calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */
public class SingleDate {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("hours")
    @Expose
    private List<Integer> hours = null;

    /**
     * @return String
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date String
     */
    public void setDate(final String date) {
        this.date = date;
    }

    /**
     * @return hours set
     */
    public List<Integer> getHours() {
        return hours;
    }

    /**
     * @param hours hour set
     */
    public void setHours(final List<Integer> hours) {
        this.hours = hours;
    }

}
