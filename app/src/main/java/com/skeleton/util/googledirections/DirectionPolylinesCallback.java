package com.skeleton.util.googledirections;

import com.skeleton.util.googledirections.googledirectionmodel.PolylineResponse;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Click labs +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public interface DirectionPolylinesCallback {

    /**
     * @param polylineResponse polyline Directions response
     */
    void polylineDirections(final PolylineResponse polylineResponse);
}
