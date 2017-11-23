package com.skeleton.util.googledirections.geocodingmodel;

import com.google.gson.annotations.SerializedName;

/**
 *  +++++++++++++++++++++++++++++++
 *  +++++++++Click labs +++++++++++
 *  +++++++++++++++++++++++++++++++
 */
public class Geometry {
    @SerializedName("location")
    private Location location;
    @SerializedName("location_type")
    private String locationType;
    @SerializedName("viewport")
    private Viewport viewport;

    /**
     * @return get location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location set location
     */
    public void setLocation(final Location location) {
        this.location = location;
    }

    /**
     * @return get laction type
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * @param mLocationType set location type
     */
    public void setLocationType(final String mLocationType) {
        this.locationType = mLocationType;
    }

    /**
     * @return get view ports
     */
    public Viewport getViewport() {
        return viewport;
    }

    /**
     * @param viewport set view ports
     */
    public void setViewport(final Viewport viewport) {
        this.viewport = viewport;
    }
}
