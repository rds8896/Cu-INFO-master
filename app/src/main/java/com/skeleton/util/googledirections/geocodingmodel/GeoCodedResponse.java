package com.skeleton.util.googledirections.geocodingmodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  +++++++++++++++++++++++++++++++
 *  +++++++++Click labs +++++++++++
 *  +++++++++++++++++++++++++++++++
 */
public class GeoCodedResponse implements Parcelable {

    public static final Creator<GeoCodedResponse> CREATOR = new Creator<GeoCodedResponse>() {
        @Override
        public GeoCodedResponse createFromParcel(final Parcel in) {
            return new GeoCodedResponse(in);
        }

        @Override
        public GeoCodedResponse[] newArray(final int size) {
            return new GeoCodedResponse[size];
        }
    };

    private String mStreetNumber, mRoute, mLocality, mShortFormCountry, mCountryName, mPostalCode,
            mPolitical, mFormattedAddress;

    /**
     * @param streetNumber     street number
     * @param route            route
     * @param locality         locality
     * @param shortFormCountry short country name
     * @param country          country name full
     * @param postalCode       postal code
     * @param political        political address name
     * @param formattedAddress full formatted address
     */
    public GeoCodedResponse(final String streetNumber, final String route,
                            final String locality, final String shortFormCountry,
                            final String country, final String postalCode,
                            final String political, final String formattedAddress) {
        this.mStreetNumber = streetNumber;
        this.mRoute = route;
        this.mLocality = locality;
        this.mShortFormCountry = shortFormCountry;
        this.mCountryName = country;
        this.mPostalCode = postalCode;
        this.mPolitical = political;
        this.mFormattedAddress = formattedAddress;
    }

    /**
     * @param in parcel in
     */
    protected GeoCodedResponse(final Parcel in) {
        mStreetNumber = in.readString();
        mRoute = in.readString();
        mLocality = in.readString();
        mShortFormCountry = in.readString();
        mCountryName = in.readString();
        mPostalCode = in.readString();
        mPolitical = in.readString();
        mFormattedAddress = in.readString();
    }


    /**
     * @return get Street number
     */
    public String getStreetNumber() {
        return mStreetNumber;
    }

    /**
     * @return get route
     */
    public String getRoute() {
        return mRoute;
    }

    /**
     * @return get locality
     */
    public String getLocality() {
        return mLocality;
    }

    /**
     * @return get Short form country name
     */
    public String getShortCountryName() {
        return mShortFormCountry;
    }

    /**
     * @return get full country name
     */
    public String getFullCountryName() {
        return mCountryName;
    }

    /**
     * @return get postal code
     */
    public String getPostalCode() {
        return mPostalCode;
    }

    /**
     * @return get political address
     */
    public String getPoltical() {
        return mPolitical;
    }

    /**
     * @return get formatted address
     */
    public String getFormattedAddress() {
        return mFormattedAddress;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(mStreetNumber);
        dest.writeString(mRoute);
        dest.writeString(mLocality);
        dest.writeString(mShortFormCountry);
        dest.writeString(mCountryName);
        dest.writeString(mPostalCode);
        dest.writeString(mPolitical);
        dest.writeString(mFormattedAddress);
    }
}
