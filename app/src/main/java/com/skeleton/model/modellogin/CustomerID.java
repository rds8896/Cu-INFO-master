
package com.skeleton.model.modellogin;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerID implements Parcelable
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
    @SerializedName("deviceType")
    @Expose
    private String deviceType;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("image")
    @Expose
    private Object image;
    public final static Parcelable.Creator<CustomerID> CREATOR = new Creator<CustomerID>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CustomerID createFromParcel(Parcel in) {
            CustomerID instance = new CustomerID();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
            instance.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            instance.deviceType = ((String) in.readValue((String.class.getClassLoader())));
            instance.v = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.image = ((Object) in.readValue((Object.class.getClassLoader())));
            return instance;
        }

        public CustomerID[] newArray(int size) {
            return (new CustomerID[size]);
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(updatedAt);
        dest.writeValue(createdAt);
        dest.writeValue(deviceType);
        dest.writeValue(v);
        dest.writeValue(image);
    }

    public int describeContents() {
        return 0;
    }

}