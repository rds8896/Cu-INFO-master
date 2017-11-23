package com.skeleton.util.googledirections.googledirectionmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *  +++++++++++++++++++++++++++++++
 *  +++++++++Click labs +++++++++++
 *  +++++++++++++++++++++++++++++++
 */
public class Leg {

    @SerializedName("distance")
    @Expose
    private Distance distance;
    @SerializedName("duration")
    @Expose
    private Duration duration;
    @SerializedName("end_address")
    @Expose
    private String endAddress;
    @SerializedName("end_location")
    @Expose
    private EndLocation endLocation;
    @SerializedName("start_address")
    @Expose
    private String startAddress;
    @SerializedName("start_location")
    @Expose
    private StartLocation startLocation;
    @SerializedName("steps")
    @Expose
    private List<Step> steps = null;
    @SerializedName("traffic_speed_entry")
    @Expose
    private List<Object> trafficSpeedEntry = null;
    @SerializedName("via_waypoint")
    @Expose
    private List<ViaWaypoint> viaWaypoint = null;

    /**
     * @return distance
     */
    public Distance getDistance() {
        return distance;
    }

    /**
     * @param distance set distance
     */
    public void setDistance(final Distance distance) {
        this.distance = distance;
    }

    /**
     * @return get Duration
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * @param duration set duration
     */
    public void setDuration(final Duration duration) {
        this.duration = duration;
    }

    /**
     * @return get End address
     */
    public String getEndAddress() {
        return endAddress;
    }

    /**
     * @param endAddress set End Address
     */
    public void setEndAddress(final String endAddress) {
        this.endAddress = endAddress;
    }

    /**
     * @return get End location
     */
    public EndLocation getEndLocation() {
        return endLocation;
    }

    /**
     * @param endLocation set End Location
     */
    public void setEndLocation(final EndLocation endLocation) {
        this.endLocation = endLocation;
    }

    /**
     * @return get start Address
     */
    public String getStartAddress() {
        return startAddress;
    }

    /**
     * @param startAddress set start Address
     */
    public void setStartAddress(final String startAddress) {
        this.startAddress = startAddress;
    }

    /**
     * @return get Start Location
     */
    public StartLocation getStartLocation() {
        return startLocation;
    }

    /**
     * @param startLocation set Start Location
     */
    public void setStartLocation(final StartLocation startLocation) {
        this.startLocation = startLocation;
    }

    /**
     * @return return steps
     */
    public List<Step> getSteps() {
        return steps;
    }

    /**
     * @param steps set list steps
     */
    public void setSteps(final List<Step> steps) {
        this.steps = steps;
    }

    /**
     * @return get Traffic speed entry
     */
    public List<Object> getTrafficSpeedEntry() {
        return trafficSpeedEntry;
    }

    /**
     * @param trafficSpeedEntry set Traffic speed Entry
     */
    public void setTrafficSpeedEntry(final List<Object> trafficSpeedEntry) {
        this.trafficSpeedEntry = trafficSpeedEntry;
    }

    /**
     * @return get Via way point
     */
    public List<ViaWaypoint> getViaWaypoint() {
        return viaWaypoint;
    }

    /**
     * @param viaWaypoint set Via way points
     */
    public void setViaWaypoint(final List<ViaWaypoint> viaWaypoint) {
        this.viaWaypoint = viaWaypoint;
    }

}