package com.skeleton.model.modelsociallogin;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialAccount implements Parcelable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("socialID")
    @Expose
    private String socialID;
    public final static Parcelable.Creator<SocialAccount> CREATOR = new Creator<SocialAccount>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SocialAccount createFromParcel(Parcel in) {
            SocialAccount instance = new SocialAccount();
            instance.type = ((String) in.readValue((String.class.getClassLoader())));
            instance.socialID = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public SocialAccount[] newArray(int size) {
            return (new SocialAccount[size]);
        }

    }
            ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSocialID() {
        return socialID;
    }

    public void setSocialID(String socialID) {
        this.socialID = socialID;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(socialID);
    }

    public int describeContents() {
        return 0;
    }

}

