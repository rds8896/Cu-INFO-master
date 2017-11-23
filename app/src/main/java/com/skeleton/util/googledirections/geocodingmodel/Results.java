package com.skeleton.util.googledirections.geocodingmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *  +++++++++++++++++++++++++++++++
 *  +++++++++Click labs +++++++++++
 *  +++++++++++++++++++++++++++++++
 */
public class Results {
    @SerializedName("address_components")
    private List<AddressComponents> addressComponents = null;
    @SerializedName("formatted_address")
    private String formattedAddress;
    @SerializedName("geometry")
    private Geometry geometry;
    @SerializedName("place_id")
    private String placeId;
    @SerializedName("types")
    private List<String> types = null;

    /**
     * @return return the list of address
     */
    public List<AddressComponents> getAddressComponents() {
        return addressComponents;
    }

    /**
     * @param mAddressComponents set address comp.
     */
    public void setAddressComponents(final List<AddressComponents> mAddressComponents) {
        this.addressComponents = mAddressComponents;
    }


    /**
     * @return get formatted address
     */
    public String getFormattedAddress() {
        return formattedAddress;
    }

    /**
     * @param mFormattedAddress set formatted address
     */
    public void setFormattedAddress(final String mFormattedAddress) {
        this.formattedAddress = mFormattedAddress;
    }

    /**
     * @return get geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * @param geometry set geometry
     */
    public void setGeometry(final Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * @return get place id
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * @param mPlaceId set place id
     */
    public void setPlaceId(final String mPlaceId) {
        this.placeId = mPlaceId;
    }

    /**
     * @return list of get type
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
