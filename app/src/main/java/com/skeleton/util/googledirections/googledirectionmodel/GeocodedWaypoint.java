package com.skeleton.util.googledirections.googledirectionmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**  * +++++++++++++++++++++++++++++++  * +++++++++Click labs +++++++++++  * +++++++++++++++++++++++++++++++  */

public class GeocodedWaypoint {

    @SerializedName("geocoder_status")
    @Expose
    private String geocoderStatus;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("types")
    @Expose
    private List<String> types = null;

    /**
     * @return geturn geocoder status
     */
    public String getGeocoderStatus() {
        return geocoderStatus;
    }

    /**
     * @param geocoderStatus set geocoder status
     */
    public void setGeocoderStatus(final String geocoderStatus) {
        this.geocoderStatus = geocoderStatus;
    }

    /**
     * @return get place Id
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * @param placeId set Place id
     */
    public void setPlaceId(final String placeId) {
        this.placeId = placeId;
    }

    /**
     * @return list types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * @param types set types
     */
    public void setTypes(final List<String> types) {
        this.types = types;
    }

}
