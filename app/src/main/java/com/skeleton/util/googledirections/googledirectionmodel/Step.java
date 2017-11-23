package com.skeleton.util.googledirections.googledirectionmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Click labs +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class Step {

    @SerializedName("distance")
    @Expose
    private Distance distance;
    @SerializedName("duration")
    @Expose
    private Duration duration;
    @SerializedName("end_location")
    @Expose
    private EndLocation endLocation;
    @SerializedName("html_instructions")
    @Expose
    private String htmlInstructions;
    @SerializedName("polyline")
    @Expose
    private Polyline polyline;
    @SerializedName("start_location")
    @Expose
    private StartLocation startLocation;
    @SerializedName("travel_mode")
    @Expose
    private String travelMode;
    @SerializedName("maneuver")
    @Expose
    private String maneuver;

    /**
     * @return get Distance
     */
    public Distance getDistance() {
        return distance;
    }

    /**
     * @param distance set Distance
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
     * @param duration set Duration
     */
    public void setDuration(final Duration duration) {
        this.duration = duration;
    }

    /**
     * @return get End Location
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
     * @return get Html Instruction
     */
    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    /**
     * @param htmlInstructions set html Instruction
     */
    public void setHtmlInstructions(final String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    /**
     * @return get Polyline
     */
    public Polyline getPolyline() {
        return polyline;
    }

    /**
     * @param polyline set Polyline
     */
    public void setPolyline(final Polyline polyline) {
        this.polyline = polyline;
    }

    /**
     * @return get Start location
     */
    public StartLocation getStartLocation() {
        return startLocation;
    }

    /**
     * @param startLocation set start location
     */
    public void setStartLocation(final StartLocation startLocation) {
        this.startLocation = startLocation;
    }

    /**
     * @return get Travel Mode
     */
    public String getTravelMode() {
        return travelMode;
    }

    /**
     * @param travelMode set Travel Mode
     */
    public void setTravelMode(final String travelMode) {
        this.travelMode = travelMode;
    }

    /**
     * @return get Maneuver
     */
    public String getManeuver() {
        return maneuver;
    }

    /**
     * @param maneuver set Maneuver
     */
    public void setManeuver(final String maneuver) {
        this.maneuver = maneuver;
    }

}
