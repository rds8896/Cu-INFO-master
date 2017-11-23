package com.skeleton.util.googledirections;

import com.skeleton.util.googledirections.geocodingmodel.GeoCodedResponse;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Click labs +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public interface GeoCodedCallBack {

    /**
     * @param response success with response body
     */
    void onSuccessGeo(final GeoCodedResponse response);

    /**
     * @param errorMsgGeoApiError failiure with appropriate message
     */
    void onFailureGeo(final String errorMsgGeoApiError);
}
