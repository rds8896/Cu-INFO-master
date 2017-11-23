package com.skeleton.util.googledirections.googledirectionmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Click labs +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class Route {

    @SerializedName("bounds")
    @Expose
    private Bounds bounds;
    @SerializedName("copyrights")
    @Expose
    private String copyrights;
    @SerializedName("legs")
    @Expose
    private List<Leg> legs = null;
    @SerializedName("overview_polyline")
    @Expose
    private OverviewPolyline overviewPolyline;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("warnings")
    @Expose
    private List<Object> warnings = null;
    @SerializedName("waypoint_order")
    @Expose
    private List<Object> waypointOrder = null;

    /**
     * @return get Bounds
     */
    public Bounds getBounds() {
        return bounds;
    }

    /**
     * @param bounds set bounds
     */
    public void setBounds(final Bounds bounds) {
        this.bounds = bounds;
    }

    /**
     * @return get Copy rights
     */
    public String getCopyrights() {
        return copyrights;
    }

    /**
     * @param copyrights set Copy rights
     */
    public void setCopyrights(final String copyrights) {
        this.copyrights = copyrights;
    }

    /**
     * @return legs
     */
    public List<Leg> getLegs() {
        return legs;
    }

    /**
     * @param legs set legs
     */
    public void setLegs(final List<Leg> legs) {
        this.legs = legs;
    }

    /**
     * @return overViewPolyline
     */
    public OverviewPolyline getOverviewPolyline() {
        return overviewPolyline;
    }

    /**
     * @param overviewPolyline set overview Polylines
     */
    public void setOverviewPolyline(final OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    /**
     * @return get Summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary set Summary
     */
    public void setSummary(final String summary) {
        this.summary = summary;
    }

    /**
     * @return get Warning
     */
    public List<Object> getWarnings() {
        return warnings;
    }

    /**
     * @param warnings set warnings
     */
    public void setWarnings(final List<Object> warnings) {
        this.warnings = warnings;
    }

    /**
     * @return get way point order
     */
    public List<Object> getWaypointOrder() {
        return waypointOrder;
    }

    /**
     * @param waypointOrder set way points order
     */
    public void setWaypointOrder(final List<Object> waypointOrder) {
        this.waypointOrder = waypointOrder;
    }

}
