package com.skeleton.util.googledirections;

import com.skeleton.constant.ApiKeyConstant;
import com.skeleton.constant.AppConstant;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.CommonParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.googledirections.geocodingmodel.AddressComponents;
import com.skeleton.util.googledirections.geocodingmodel.GeoCodedApiResponse;
import com.skeleton.util.googledirections.geocodingmodel.GeoCodedResponse;

import java.util.List;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Click labs +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public final class GeoCodedAddress implements ApiKeyConstant, AppConstant {


    /**
     * @param builder builder object
     */
    private GeoCodedAddress(final Builder builder) {
        if (builder.geoCodedCallBack != null) {
            getGeoCodedAdderssFormGoogleApi(builder.mAddressLatLng, builder.geoCodedCallBack);
        } else {
            Log.e("Error", "call back error. ");
        }
    }


    /**
     * @param geoAddressLatLng lat lng string i.e (lat,lng)
     * @param geoCodedCallBack geoCodedCallBack
     */
    private void getGeoCodedAdderssFormGoogleApi(final String geoAddressLatLng,
                                                 final GeoCodedCallBack geoCodedCallBack) {
        CommonParams.Builder builder = new CommonParams.Builder();
        builder.add(LATLNG, geoAddressLatLng);
        CommonParams commonParams = builder.build();
        RestClient.getGoogleApiInterface().getGeoAddress(commonParams.getMap())
                .enqueue(new ResponseResolver<GeoCodedApiResponse>() {
                    @Override
                    public void success(final GeoCodedApiResponse geoCodedApiResponse) {
                        if (geoCodedApiResponse.getStatus().equals(RESPONSE_GOOGLE)) {
                            setGeoCodedResponse(geoCodedApiResponse, geoCodedCallBack);
                        } else {
                            if (geoCodedApiResponse.getStatus().equals(RESPONSE_GOOGLE_ERROR)) {
                                geoCodedCallBack.onFailureGeo(ERROR_MSG_ZERO_RESULTS);
                            } else {
                                geoCodedCallBack.onFailureGeo(ERROR_MSG_UNKNOWN_ERROR);
                            }
                        }
                    }

                    @Override
                    public void failure(final APIError error) {
                        Log.e("Error", "Error");
                        geoCodedCallBack.onFailureGeo(ERROR_MSG_REQUEST_DENIED);
                    }
                });
    }


    /**
     * @param geoCodedResponse response GeoCodedApiResponse
     * @param geoCodedCallBack call back GeoCodedCallBack
     */
    private void setGeoCodedResponse(final GeoCodedApiResponse geoCodedResponse
            , final GeoCodedCallBack geoCodedCallBack) {
        List<AddressComponents> addressComponents = geoCodedResponse.getResults().get(0).getAddressComponents();
        int size = addressComponents.size(), i = 0, j = 0;
        String mStreetName = null, mRoute = null,
                mLocality = null, mCountry = null,
                mShortFormCountry = null, mPostalCode = null,
                mPolotical = null, mFormattedAddress;
        do {
            while (j < size) {
                List<String> temp = addressComponents.get(j).getTypes();
                String mSetData = addressComponents.get(j).getLongName();
                switch (temp.get(0)) {
                    case STREET_NAME:
                        mStreetName = mSetData;
                        break;
                    case ROUTE:
                        mRoute = mSetData;
                        break;
                    case LOCALITY:
                        mLocality = mSetData;
                        break;
                    case COUNTRY:
                        mCountry = mSetData;
                        mShortFormCountry = addressComponents.get(j).getShortName();
                        break;
                    case POSTAL_CODE:
                        mPostalCode = mSetData;
                        break;
                    case POLITICAL:
                        mPolotical = mSetData;
                        break;
                    default:
                        break;
                }
                j++;
            }
            mFormattedAddress = geoCodedResponse.getResults().get(0).getFormattedAddress();
            i++;
        } while (i < 1);

        GeoCodedResponse codedResponse = new GeoCodedResponse(mStreetName, mRoute,
                mLocality, mShortFormCountry,
                mCountry, mPostalCode
                , mPolotical, mFormattedAddress);
        geoCodedCallBack.onSuccessGeo(codedResponse);
    }


    /**
     * Geo Coded Address Builder
     */
    public static class Builder {
        private String mAddressLatLng;
        private GeoCodedCallBack geoCodedCallBack;

        /**
         * @param mGeoCodedCallBacks mGeoCodedCallBack
         * @return return this
         */
        public Builder setGeoCodedCallBacks(final GeoCodedCallBack mGeoCodedCallBacks) {
            this.geoCodedCallBack = mGeoCodedCallBacks;
            return this;
        }

        /**
         * @param mLat lat source
         * @param mLan lan Source
         * @return return this
         */
        public Builder setGeoLatLan(final double mLat, final double mLan) {
            mAddressLatLng = mLat + "," + mLan;
            return this;
        }

        /**
         * @return return builder
         */
        public GeoCodedAddress build() {
            return new GeoCodedAddress(this);
        }

    }

}
