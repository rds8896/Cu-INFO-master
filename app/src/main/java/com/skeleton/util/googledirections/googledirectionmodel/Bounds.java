package com.skeleton.util.googledirections.googledirectionmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**  * +++++++++++++++++++++++++++++++  * +++++++++Click labs +++++++++++  * +++++++++++++++++++++++++++++++  */
public class Bounds {

    @SerializedName("northeast")
    @Expose
    private Northeast northeast;
    @SerializedName("southwest")
    @Expose
    private Southwest southwest;

    /**
     * @return get northeast
     */
    public Northeast getNortheast() {
        return northeast;
    }

    /**
     * @param northeast set northeast
     */
    public void setNortheast(final Northeast northeast) {
        this.northeast = northeast;
    }

    /**
     * @return get  southeast
     */
    public Southwest getSouthwest() {
        return southwest;
    }

    /**
     * @param southwest set Southeast
     */
    public void setSouthwest(final Southwest southwest) {
        this.southwest = southwest;
    }

}