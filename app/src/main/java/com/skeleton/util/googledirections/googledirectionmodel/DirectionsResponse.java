package com.skeleton.util.googledirections.googledirectionmodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */
public class DirectionsResponse implements Parcelable {

    public static final Creator<DirectionsResponse> CREATOR = new Creator<DirectionsResponse>() {
        @Override
        public DirectionsResponse createFromParcel(final Parcel in) {
            return new DirectionsResponse(in);
        }

        @Override
        public DirectionsResponse[] newArray(final int size) {
            return new DirectionsResponse[size];
        }
    };

    private String mStartAddress, mEndAddress, mEstimatedTime;
    private double mDistance, mDestLat, mDestLan, mStartLat, mStartLan;

    /**
     * @param in Parcel in
     */
    protected DirectionsResponse(final Parcel in) {
        mStartAddress = in.readString();
        mEndAddress = in.readString();
        mEstimatedTime = in.readString();
        mDistance = in.readDouble();
        mDestLat = in.readDouble();
        mDestLan = in.readDouble();
        mStartLat = in.readDouble();
        mStartLan = in.readDouble();
    }

    /**
     * @param distance            distance
     * @param mDestinationAddress Destination Address
     * @param mStartingAddress    starting Address
     * @param mDestLat            Destination Latitude
     * @param mDestLan            Destination Lognitude
     * @param mStartLat           Start Latitude
     * @param mStartLan           Start Lognitude
     */
    public DirectionsResponse(final double distance,
                              final String mDestinationAddress,
                              final String mStartingAddress, final double mDestLat,
                              final double mDestLan, final double mStartLat,
                              final double mStartLan) {

        this.mDistance = distance;
        this.mEndAddress = mDestinationAddress;
        this.mStartAddress = mStartingAddress;
        this.mDestLat = mDestLat;
        this.mDestLan = mDestLan;
        this.mStartLat = mStartLat;
        this.mStartLan = mStartLan;

    }

    /**
     * @param mTime set time
     */
    public void setTime(final String mTime) {
        this.mEstimatedTime = mTime;
    }


    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(mStartAddress);
        dest.writeString(mEndAddress);
        dest.writeString(mEstimatedTime);
        dest.writeDouble(mDistance);
        dest.writeDouble(mDestLat);
        dest.writeDouble(mDestLan);
        dest.writeDouble(mStartLat);
        dest.writeDouble(mStartLan);
    }

    @Override
    public int describeContents() {
        return 0;
    }


    /**
     * @return return the start address
     */
    public String getStartAddress() {
        return mStartAddress;
    }

    /**
     * @return return the end Address
     */
    public String getEndAddress() {
        return mEndAddress;
    }

    /**
     * @return return the time in hours and min in proper format
     */
    public String getTime() {
        return mEstimatedTime;
    }

    /**
     * @return return the distance b/w the points
     */
    public double getDistance() {
        return mDistance;
    }

    /**
     * @return return the Destination Latitude
     */
    public double getDestLat() {
        return mDestLat;
    }

    /**
     * @return return the Destination Longitude
     */
    public double getDestLan() {
        return mDestLan;
    }

    /**
     * @return return the Start Latitude
     */
    public double getStartLat() {
        return mStartLat;
    }

    /**
     * @return return the Start Longitude
     */
    public double getStartLan() {
        return mStartLan;
    }


}
