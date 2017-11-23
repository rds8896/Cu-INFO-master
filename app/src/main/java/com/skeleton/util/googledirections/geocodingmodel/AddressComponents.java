package com.skeleton.util.googledirections.geocodingmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *  +++++++++++++++++++++++++++++++
 *  +++++++++Click labs +++++++++++
 *  +++++++++++++++++++++++++++++++
 */
public class AddressComponents {
    @SerializedName("long_name")
    private String longName;
    @SerializedName("short_name")
    private String shortName;
    @SerializedName("types")
    private List<String> types;

    /**
     * @return Address  long
     */
    public String getLongName() {
        return longName;
    }

    /**
     * @param mLongName set long name
     */
    public void setLongName(final String mLongName) {
        this.longName = mLongName;
    }

    /**
     * @return get short name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param mShortName set short name
     */
    public void setShortName(final String mShortName) {
        this.shortName = mShortName;
    }

    /**
     * @return List of types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * @param types set list of types
     */
    public void setTypes(final List<String> types) {
        this.types = types;
    }
}
