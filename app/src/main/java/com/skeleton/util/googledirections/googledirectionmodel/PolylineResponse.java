package com.skeleton.util.googledirections.googledirectionmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.skeleton.constant.AppConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Sumit Thakur
 * Dated: 15/06/17.
 */
public class PolylineResponse implements AppConstant, Parcelable {

    public static final Creator<PolylineResponse> CREATOR = new Creator<PolylineResponse>() {
        @Override
        public PolylineResponse createFromParcel(final Parcel in) {
            return new PolylineResponse(in);
        }

        @Override
        public PolylineResponse[] newArray(final int size) {
            return new PolylineResponse[size];
        }
    };

    private List<LatLng> latLngs;

    /**
     * @param points way points strings
     */
    public PolylineResponse(final String points) {
        latLngs = decodePoly(points);
    }

    /**
     * @param in in
     */
    protected PolylineResponse(final Parcel in) {
        latLngs = in.createTypedArrayList(LatLng.CREATOR);
    }

    /**
     * @param encoded Method to decode polyline pointsfrom encoded string
     * @return return
     */
    private List<LatLng> decodePoly(final String encoded) {

        List<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - POLYDECODING_1;
                result |= (b & POLYDECODING_2) << shift;
                shift += 5;
            } while (b >= POLYDECODING_3);
            int dlat = (result & 1) != 0 ? ~(result >> 1) : (result >> 1);
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - POLYDECODING_1;
                result |= (b & POLYDECODING_2) << shift;
                shift += 5;
            } while (b >= POLYDECODING_3);
            int dlng = (result & 1) != 0 ? ~(result >> 1) : (result >> 1);
            lng += dlng;

            LatLng p = new LatLng((double) lat / POLYDECODING_4,
                    (double) lng / POLYDECODING_4);
            poly.add(p);
        }
        return poly;
    }

    /**
     * @return list of latlng
     */
    public List<LatLng> getPolylineDirectionLatLngs() {
        return latLngs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeTypedList(latLngs);
    }
}
