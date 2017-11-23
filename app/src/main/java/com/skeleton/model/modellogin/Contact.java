package com.skeleton.model.modellogin;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact implements Parcelable
{

    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("isPrimary")
    @Expose
    private Boolean isPrimary;
    @SerializedName("isVerified")
    @Expose
    private Boolean isVerified;
    public final static Parcelable.Creator<Contact> CREATOR = new Creator<Contact>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Contact createFromParcel(Parcel in) {
            Contact instance = new Contact();
            instance.mobile = ((String) in.readValue((String.class.getClassLoader())));
            instance.countryCode = ((String) in.readValue((String.class.getClassLoader())));
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.isPrimary = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.isVerified = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            return instance;
        }

        public Contact[] newArray(int size) {
            return (new Contact[size]);
        }

    }
            ;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mobile);
        dest.writeValue(countryCode);
        dest.writeValue(id);
        dest.writeValue(isPrimary);
        dest.writeValue(isVerified);
    }

    public int describeContents() {
        return 0;
    }

}