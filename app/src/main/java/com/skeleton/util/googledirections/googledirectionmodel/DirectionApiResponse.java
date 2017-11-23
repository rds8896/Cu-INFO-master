package com.skeleton.util.googledirections.googledirectionmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**  * +++++++++++++++++++++++++++++++  * +++++++++Click labs +++++++++++  * +++++++++++++++++++++++++++++++  */
public class DirectionApiResponse {

    @SerializedName("geocoded_waypoints")
    @Expose
    private List<GeocodedWaypoint> geocodedWaypoints = null;
    @SerializedName("routes")
    @Expose
    private List<Route> routes = null;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * @return List
     */
    public List<GeocodedWaypoint> getGeocodedWaypoints() {
        return geocodedWaypoints;
    }

    /**
     * @param geocodedWaypoints set List of geocoded way points
     */
    public void setGeocodedWaypoints(final List<GeocodedWaypoint> geocodedWaypoints) {
        this.geocodedWaypoints = geocodedWaypoints;
    }

    /**
     * @return List of Routesl
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     * @param routes set list Routes
     */
    public void setRoutes(final List<Route> routes) {
        this.routes = routes;
    }

    /**
     * @return get Status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status set Status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

}
