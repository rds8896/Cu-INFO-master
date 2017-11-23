package com.skeleton.model.modelsociallogin;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSocialLogin implements Parcelable
{

    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    public final static Parcelable.Creator<ResponseSocialLogin> CREATOR = new Creator<ResponseSocialLogin>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ResponseSocialLogin createFromParcel(Parcel in) {
            ResponseSocialLogin instance = new ResponseSocialLogin();
            instance.statusCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            instance.data = ((Data) in.readValue((Data.class.getClassLoader())));
            return instance;
        }

        public ResponseSocialLogin[] newArray(int size) {
            return (new ResponseSocialLogin[size]);
        }

    }
            ;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(statusCode);
        dest.writeValue(message);
        dest.writeValue(data);
    }

    public int describeContents() {
        return 0;
    }

}