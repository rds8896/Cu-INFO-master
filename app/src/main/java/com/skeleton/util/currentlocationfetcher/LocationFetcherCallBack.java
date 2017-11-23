package com.skeleton.util.currentlocationfetcher;

/**
 *  +++++++++++++++++++++++++++++++
 *  +++++++++Click labs +++++++++++
 *  +++++++++++++++++++++++++++++++
 */

public interface LocationFetcherCallBack {
    /**
     *
     * @param location set location
     */
    void onSuccess(android.location.Location location);

    /**
     *
     * @param errorMsg prints error mesg in case location is not set
     */
    void onFailure(String errorMsg);


}
