package com.skeleton.util.googledirections.geocodingmodel;

import com.google.gson.annotations.SerializedName;

/**
 *  +++++++++++++++++++++++++++++++
 *  +++++++++Click labs +++++++++++
 *  +++++++++++++++++++++++++++++++
 */
public class Viewport {
    @SerializedName("northeast")
    private Northeast northeast;
    @SerializedName("southwest")
    private Southwest southwest;

    /**
     * @return get north east
     */
    public Northeast getNortheast() {
        return northeast;
    }

    /**
     * @param northeast set north east
     */
    public void setNortheast(final Northeast northeast) {
        this.northeast = northeast;
    }

    /**
     * @return get south west
     */
    public Southwest getSouthwest() {
        return southwest;
    }

    /**
     * @param southwest set south west
     */
    public void setSouthwest(final Southwest southwest) {
        this.southwest = southwest;
    }
}
