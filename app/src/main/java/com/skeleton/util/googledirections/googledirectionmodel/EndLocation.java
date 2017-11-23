package com.skeleton.util.googledirections.googledirectionmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**  * +++++++++++++++++++++++++++++++  * +++++++++Click labs +++++++++++  * +++++++++++++++++++++++++++++++  */
public class EndLocation {

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;

    /**
     * @return get latitude
     */
    public Double getLat() {
        return lat;
    }

    /**
     * @param lat set latitude
     */
    public void setLat(final Double lat) {
        this.lat = lat;
    }

    /**
     * @return get lng
     */
    public Double getLng() {
        return lng;
    }

    /**
     * @param lng set lan
     */
    public void setLng(final Double lng) {
        this.lng = lng;
    }

}
