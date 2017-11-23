package com.skeleton.util.googledirections.googledirectionmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Click labs +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class OverviewPolyline {

    @SerializedName("points")
    @Expose
    private String points;

    /**
     * @return get points
     */
    public String getPoints() {
        return points;
    }

    /**
     * @param points set points
     */
    public void setPoints(final String points) {
        this.points = points;
    }

}
