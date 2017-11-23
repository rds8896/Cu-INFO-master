package com.skeleton.model.modellogin;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerAddressID implements Parcelable
{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("zipCode")
    @Expose
    private Object zipCode;
    @SerializedName("state")
    @Expose
    private Object state;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("longitude")
    @Expose
    private Object longitude;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    public final static Parcelable.Creator<CustomerAddressID> CREATOR = new Creator<CustomerAddressID>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CustomerAddressID createFromParcel(Parcel in) {
            CustomerAddressID instance = new CustomerAddressID();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
            instance.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            instance.v = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.zipCode = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.state = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.city = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.longitude = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.latitude = ((Object) in.readValue((Object.class.getClassLoader())));
            return instance;
        }

        public CustomerAddressID[] newArray(int size) {
            return (new CustomerAddressID[size]);
        }

    }
            ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Object getZipCode() {
        return zipCode;
    }

    public void setZipCode(Object zipCode) {
        this.zipCode = zipCode;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(updatedAt);
        dest.writeValue(createdAt);
        dest.writeValue(v);
        dest.writeValue(zipCode);
        dest.writeValue(state);
        dest.writeValue(city);
        dest.writeValue(longitude);
        dest.writeValue(latitude);
    }

    public int describeContents() {
        return 0;
    }

}