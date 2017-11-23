package com.skeleton.util.googledirections;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.skeleton.R;
import com.skeleton.constant.ApiKeyConstant;
import com.skeleton.constant.AppConstant;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.CommonParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.googledirections.googledirectionmodel.DirectionApiResponse;
import com.skeleton.util.googledirections.googledirectionmodel.DirectionsResponse;
import com.skeleton.util.googledirections.googledirectionmodel.Leg;
import com.skeleton.util.googledirections.googledirectionmodel.PolylineResponse;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Click labs +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public final class GoogleDirectionsMap implements ApiKeyConstant, AppConstant {

    /**
     * @param builder builder object
     */
    private GoogleDirectionsMap(final Builder builder) {
        if (builder.googleDirectionsCallback != null || builder.directionPolylinesCallback != null) {
            getDirectionsFromGoogleApi(builder.mSourceLatLng,
                    builder.mDesLatLng,
                    builder.mWayPoints,
                    builder.googleDirectionsCallback,
                    builder.directionPolylinesCallback);
        } else {
            Log.e("Error", "call back error both null ");
        }
    }


    /**
     * @param sourceLatLng               source latlng
     * @param destinationLatLng          destination latlng
     * @param mWayPoints                 waypoints
     * @param googleDirectionsCallback   google directions call back
     * @param directionPolylinesCallback directions polylines callback
     */
    private void getDirectionsFromGoogleApi(final String sourceLatLng,
                                            final String destinationLatLng,
                                            final String mWayPoints,
                                            final GoogleDirectionsCallback googleDirectionsCallback,
                                            final DirectionPolylinesCallback directionPolylinesCallback) {

        CommonParams.Builder builder = new CommonParams.Builder();
        builder.add(SOURCE, sourceLatLng)
                .add(DESTINATION, destinationLatLng);

        if (mWayPoints != null) {
            builder.add(WAYPOINTS, mWayPoints);
        }
        CommonParams commonParams = builder.build();

        RestClient.getGoogleApiInterface().getDirections(commonParams.getMap())
                .enqueue(new ResponseResolver<DirectionApiResponse>() {
                    @Override
                    public void success(final DirectionApiResponse directionResponse) {
                        Log.e("Error", directionResponse.getStatus());
                        if (directionResponse.getStatus().equals(RESPONSE_GOOGLE)) {
                            // Response parser function
                            if (googleDirectionsCallback != null) {
                                setDirectionsResponse(directionResponse, googleDirectionsCallback);
                            }
                            if (directionPolylinesCallback != null) {
                                setdirectionPolylinesCallback(directionResponse, directionPolylinesCallback);
                            }
                        } else {
                            if (directionResponse.getStatus().equals(RESPONSE_GOOGLE_ERROR)) {
                                googleDirectionsCallback.onFailure(ERROR_MSG_PATH_NOT_FOUND);

                            } else {
                                googleDirectionsCallback.onFailure(ERROR_MSG_REQUEST_DENIED);
                            }
                        }
                    }

                    @Override
                    public void failure(final APIError error) {
                        googleDirectionsCallback.onFailure(ERROR_MSG_API_ERROR);
                    }
                });
    }


    /**
     * @param response                   DirectionApiResponse response
     * @param directionPolylinesCallback direction polyline callback
     */
    private void setdirectionPolylinesCallback(final DirectionApiResponse response,
                                               final DirectionPolylinesCallback directionPolylinesCallback) {
        PolylineResponse polylineResponse = new PolylineResponse(response
                .getRoutes()
                .get(0)
                .getOverviewPolyline()
                .getPoints());
        if (directionPolylinesCallback != null) {
            directionPolylinesCallback.polylineDirections(polylineResponse);
        }
    }


    /**
     * @param response                 set the Response to the user
     * @param googleDirectionsCallback google directions Call backs
     */
    private void setDirectionsResponse(final DirectionApiResponse response,
                                       final GoogleDirectionsCallback googleDirectionsCallback) {
        String mTimes, mDestinationAddress, mStartingAddress;
        double distance;
        double mDestLat, mDestLan, mStartLat, mStartLan;
        Leg mLeg = response.getRoutes().get(0).getLegs().get(0);
        mTimes = formatHoursAndMinutes(mLeg.getDuration().getValue() / CONVERT_TIME_MIN);
        distance = mLeg.getDistance().getValue() / CONVERT_KM;
        mDestinationAddress = mLeg.getEndAddress();
        mStartingAddress = mLeg.getStartAddress();
        mDestLat = mLeg.getEndLocation().getLat();
        mDestLan = mLeg.getEndLocation().getLng();
        mStartLat = mLeg.getStartLocation().getLat();
        mStartLan = mLeg.getStartLocation().getLng();

        DirectionsResponse responseData = new DirectionsResponse(distance,
                mDestinationAddress,
                mStartingAddress,
                mDestLat,
                mDestLan,
                mStartLat,
                mStartLan);
        responseData.setTime(mTimes);
        googleDirectionsCallback.onSuccess(responseData);
    }


    /**
     * @param totalMinutes total minutes
     * @return return the time in format order
     */
    private String formatHoursAndMinutes(final int totalMinutes) {
        String minutes = Integer.toString(totalMinutes % CONVERT_TIME_MIN);
        minutes = minutes.length() == 1 ? "0" + minutes : minutes;
        return (totalMinutes / CONVERT_TIME_MIN) + HOUR + minutes + MINUTES;
    }

    /**
     * Builder class DrawPath
     */
    public static class Builder {
        private Context context;
        private String mWayPoints;
        private AlertDialog mAlertDialog;
        private String mSourceLatLng, mDesLatLng;
        private GoogleDirectionsCallback googleDirectionsCallback;
        private DirectionPolylinesCallback directionPolylinesCallback;

        /**
         * @param mContext context
         */
        public Builder(final Context mContext) {
            context = mContext;
        }


        /**
         * @param msgError Error Msg
         */
        private void dialogMsg(final String msgError) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(msgError)
                    .setPositiveButton(context.getString(R.string.text_ok), new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            mAlertDialog.dismiss();
                        }
                    });
            mAlertDialog = builder.create();
            mAlertDialog.show();
        }

        /**
         * @param sourceLatLan source lat lan with comma sepration (lat,lan)
         * @return this
         */
        public Builder setSourceLatLan(final String sourceLatLan) {
            this.mSourceLatLng = sourceLatLan;
            return this;
        }

        /**
         * @param destinationLatLan destination lat lan with comma sepration (lat,lan)
         * @return this
         */
        public Builder setDestinationLatLan(final String destinationLatLan) {
            this.mDesLatLng = destinationLatLan;
            return this;
        }


        /**
         * @param mGoogleDirectionsCallback google directions call backs
         * @return this
         */
        public Builder setGoogleDirectionsCallback(final GoogleDirectionsCallback mGoogleDirectionsCallback) {
            this.googleDirectionsCallback = mGoogleDirectionsCallback;
            return this;
        }

        /**
         * @param mDirectionPolylinesCallback for Polylines callback
         * @return return this
         */
        public Builder setDirectionPolylinesCallback(final DirectionPolylinesCallback mDirectionPolylinesCallback) {
            this.directionPolylinesCallback = mDirectionPolylinesCallback;
            return this;
        }


        /**
         * @return check boolean value true or false
         */
        private boolean checkGoogleDirections() {
            if (mSourceLatLng == null || mDesLatLng == null) {
                return false;
            }
            return true;
        }

        /**
         * @param wayPoints way points string
         * @return return context
         */
        public Builder setWayPoints(final String wayPoints) {
            this.mWayPoints = wayPoints;
            return this;
        }

        /**
         * @return drawpath
         */
        public GoogleDirectionsMap build() {

            if (checkGoogleDirections()) {
                return new GoogleDirectionsMap(this);
            } else {
                //change need
                dialogMsg(ERROR_VALUE_MISSING);
                return null;
            }

        }

    }


}
