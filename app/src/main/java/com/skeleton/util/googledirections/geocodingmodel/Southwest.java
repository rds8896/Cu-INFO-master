package com.skeleton.util.googledirections.geocodingmodel;

import com.google.gson.annotations.SerializedName;

/**
 *  +++++++++++++++++++++++++++++++
 *  +++++++++Click labs +++++++++++
 *  +++++++++++++++++++++++++++++++
 */
public class Southwest {
    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;

    /**
     * @return get lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat set lat
     */
    public void setLat(final double lat) {
        this.lat = lat;
    }

    /**
     * @return get lng
     */
    public double getLng() {
        return lng;
    }

    /**
     * @param lng set lng
     */
    public void setLng(final double lng) {
        this.lng = lng;
    }
}
