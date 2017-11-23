package com.skeleton.util.googledirections.googledirectionmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Click labs +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class ViaWaypoint {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("step_index")
    @Expose
    private Integer stepIndex;
    @SerializedName("step_interpolation")
    @Expose
    private Double stepInterpolation;

    /**
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location et location
     */
    public void setLocation(final Location location) {
        this.location = location;
    }

    /**
     * @return get step index
     */
    public Integer getStepIndex() {
        return stepIndex;
    }

    /**
     * @param stepIndex set Step index
     */
    public void setStepIndex(final Integer stepIndex) {
        this.stepIndex = stepIndex;
    }

    /**
     * @return get step interpolation
     */
    public Double getStepInterpolation() {
        return stepInterpolation;
    }

    /**
     * @param stepInterpolation set the step inter polation
     */
    public void setStepInterpolation(final Double stepInterpolation) {
        this.stepInterpolation = stepInterpolation;
    }

}