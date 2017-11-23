package com.skeleton.locationLib.locationroute;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep+++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class Route {
    private List<RoutePoint> routePoints = new ArrayList<>();
    private Boolean hasEnded = false;
    private Boolean hasStarted = false;

    private Boolean locationUpdatorOn = false;


    /**
     * Gets location updator on.
     *
     * @return the location updator on
     */
    public Boolean getLocationUpdatorOn() {
        return this.locationUpdatorOn;
    }

    /**
     * Sets location updator on.
     *
     * @param locationUpdatorOn the location updator on
     */
    public void setLocationUpdatorOn(final Boolean locationUpdatorOn) {
        this.locationUpdatorOn = locationUpdatorOn;
    }

    /**
     * Gets last lat lng.
     *
     * @return the last lat lng
     */
    public LatLng getLastLatLng() {
        if (routePoints.size() == 0) {
            return null;
        } else if (routePoints.size() == 1) {
            return new LatLng(routePoints.get(0).getLatitude(), routePoints.get(0).getLongitude());
        } else {
            return new LatLng(routePoints.get(routePoints.size() - 2).getLatitude(), routePoints.get(routePoints.size() - 2).getLongitude());
        }
    }

//    public void setLastLatLng(LatLng lastLatLng) {
//        this.lastLatLng = lastLatLng;
//    }

    /**
     * Gets current lat lng.
     *
     * @return the current lat lng
     */
    public LatLng getCurrentLatLng() {

        if (routePoints.size() == 0) {
            return null;
        }
        return new LatLng(routePoints.get(routePoints.size() - 1).getLatitude(), routePoints.get(routePoints.size() - 1).getLongitude());
    }

//    public void setCurrentLatLng(LatLng currentLatLng) {
//        this.currentLatLng = currentLatLng;
//    }

    /**
     * Gets has started.
     *
     * @return the has started
     */
    public Boolean getHasStarted() {
        return hasStarted;
    }

    /**
     * Sets has started.
     *
     * @param hasStarted the has started
     */
    public void setHasStarted(final Boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    /**
     * Gets has ended.
     *
     * @return the has ended
     */
    public Boolean getHasEnded() {
        return hasEnded;
    }

    /**
     * Sets has ended.
     *
     * @param hasEnded the has ended
     */
    public void setHasEnded(final Boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    /**
     * Gets starting lat lng.
     *
     * @return the starting lat lng
     */
    public LatLng getStartingLatLng() {

        if (routePoints.size() == 0) {
            return null;
        }

        return new LatLng(routePoints.get(0).getLatitude(), routePoints.get(0).getLongitude());
    }


    /**
     * Gets route distance.
     *
     * @return the route distance
     */
    public Double getRouteDistance() {

        return RouteUtil.getRouteDistance(routePoints);
    }


    /**
     * Gets route time.
     *
     * @return the route time
     */
    public Long getRouteTime() {


        return RouteUtil.getRouteTime(this);
    }


    /**
     * Gets route points.
     *
     * @return the route points
     */
    public List<RoutePoint> getRoutePoints() {
        return routePoints;
    }

    /**
     * Sets route points.
     *
     * @param routePoints the route points
     */
    public void setRoutePoints(final List<RoutePoint> routePoints) {
        this.routePoints = routePoints;
    }

}
