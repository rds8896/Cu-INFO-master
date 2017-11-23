package com.skeleton.util.googledirections.geocodingmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *  +++++++++++++++++++++++++++++++
 *  +++++++++Click labs +++++++++++
 *  +++++++++++++++++++++++++++++++
 */
public class GeoCodedApiResponse {


    @SerializedName("results")
    private List<Results> results;
    @SerializedName("status")
    private String status;

    /**
     * @return return list of results
     */
    public List<Results> getResults() {
        return results;
    }

    /**
     * @param results result set
     */
    public void setResults(final List<Results> results) {
        this.results = results;
    }

    /**
     * @return Status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status set status
     */
    public void setStatus(final String status) {
        this.status = status;
    }
}
