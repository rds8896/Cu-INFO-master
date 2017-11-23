
package com.skeleton.model.modelsignup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

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

}
