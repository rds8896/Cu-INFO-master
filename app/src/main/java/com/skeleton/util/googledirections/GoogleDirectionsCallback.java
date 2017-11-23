package com.skeleton.util.googledirections;

import com.skeleton.util.googledirections.googledirectionmodel.DirectionsResponse;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Click labs +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public interface GoogleDirectionsCallback {
    /**
     * @param response success with response body
     */
    void onSuccess(final DirectionsResponse response);

    /**
     * @param errorMsgApiError failiure with appropriate message
     */
    void onFailure(final String errorMsgApiError);
}

