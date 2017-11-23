package com.skeleton.model.modellogin;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
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
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("utcoffset")
    @Expose
    private Integer utcoffset;
    @SerializedName("isEmailVerified")
    @Expose
    private Boolean isEmailVerified;
    @SerializedName("isAdminVerified")
    @Expose
    private Boolean isAdminVerified;
    @SerializedName("isDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("isBlocked")
    @Expose
    private Boolean isBlocked;
    @SerializedName("socialAccounts")
    @Expose
    private List<Object> socialAccounts = null;
    @SerializedName("driverAddressID")
    @Expose
    private Object driverAddressID;
    @SerializedName("serviceProviderAddressID")
    @Expose
    private Object serviceProviderAddressID;
    @SerializedName("customerAddressID")
    @Expose
    private CustomerAddressID customerAddressID;
    @SerializedName("driverID")
    @Expose
    private Object driverID;
    @SerializedName("serviceProviderID")
    @Expose
    private Object serviceProviderID;
    @SerializedName("customerID")
    @Expose
    private CustomerID customerID;
    @SerializedName("role")
    @Expose
    private List<String> role = null;
    @SerializedName("ratedByUserCount")
    @Expose
    private Integer ratedByUserCount;
    @SerializedName("totalRatingPoints")
    @Expose
    private Integer totalRatingPoints;
    @SerializedName("passwordResetToken")
    @Expose
    private Object passwordResetToken;
    @SerializedName("cronHardDeleteCount")
    @Expose
    private Integer cronHardDeleteCount;
    @SerializedName("rememberMe")
    @Expose
    private Boolean rememberMe;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("contacts")
    @Expose
    private List<Contact> contacts = null;
    @SerializedName("token")
    @Expose
    private String token;
    public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            Data instance = new Data();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
            instance.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            instance.userName = ((String) in.readValue((String.class.getClassLoader())));
            instance.email = ((String) in.readValue((String.class.getClassLoader())));
            instance.utcoffset = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.isEmailVerified = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.isAdminVerified = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.isDeleted = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.isBlocked = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            in.readList(instance.socialAccounts, (java.lang.Object.class.getClassLoader()));
            instance.driverAddressID = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.serviceProviderAddressID = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.customerAddressID = ((CustomerAddressID) in.readValue((CustomerAddressID.class.getClassLoader())));
            instance.driverID = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.serviceProviderID = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.customerID = ((CustomerID) in.readValue((CustomerID.class.getClassLoader())));
            in.readList(instance.role, (java.lang.String.class.getClassLoader()));
            instance.ratedByUserCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalRatingPoints = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.passwordResetToken = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.cronHardDeleteCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.rememberMe = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.lastName = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.contacts, (com.skeleton.model.modellogin.Contact.class.getClassLoader()));
            instance.token = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUtcoffset() {
        return utcoffset;
    }

    public void setUtcoffset(Integer utcoffset) {
        this.utcoffset = utcoffset;
    }

    public Boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public Boolean getIsAdminVerified() {
        return isAdminVerified;
    }

    public void setIsAdminVerified(Boolean isAdminVerified) {
        this.isAdminVerified = isAdminVerified;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public List<Object> getSocialAccounts() {
        return socialAccounts;
    }

    public void setSocialAccounts(List<Object> socialAccounts) {
        this.socialAccounts = socialAccounts;
    }

    public Object getDriverAddressID() {
        return driverAddressID;
    }

    public void setDriverAddressID(Object driverAddressID) {
        this.driverAddressID = driverAddressID;
    }

    public Object getServiceProviderAddressID() {
        return serviceProviderAddressID;
    }

    public void setServiceProviderAddressID(Object serviceProviderAddressID) {
        this.serviceProviderAddressID = serviceProviderAddressID;
    }

    public CustomerAddressID getCustomerAddressID() {
        return customerAddressID;
    }

    public void setCustomerAddressID(CustomerAddressID customerAddressID) {
        this.customerAddressID = customerAddressID;
    }

    public Object getDriverID() {
        return driverID;
    }

    public void setDriverID(Object driverID) {
        this.driverID = driverID;
    }

    public Object getServiceProviderID() {
        return serviceProviderID;
    }

    public void setServiceProviderID(Object serviceProviderID) {
        this.serviceProviderID = serviceProviderID;
    }

    public CustomerID getCustomerID() {
        return customerID;
    }

    public void setCustomerID(CustomerID customerID) {
        this.customerID = customerID;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public Integer getRatedByUserCount() {
        return ratedByUserCount;
    }

    public void setRatedByUserCount(Integer ratedByUserCount) {
        this.ratedByUserCount = ratedByUserCount;
    }

    public Integer getTotalRatingPoints() {
        return totalRatingPoints;
    }

    public void setTotalRatingPoints(Integer totalRatingPoints) {
        this.totalRatingPoints = totalRatingPoints;
    }

    public Object getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(Object passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public Integer getCronHardDeleteCount() {
        return cronHardDeleteCount;
    }

    public void setCronHardDeleteCount(Integer cronHardDeleteCount) {
        this.cronHardDeleteCount = cronHardDeleteCount;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(updatedAt);
        dest.writeValue(createdAt);
        dest.writeValue(userName);
        dest.writeValue(email);
        dest.writeValue(utcoffset);
        dest.writeValue(isEmailVerified);
        dest.writeValue(isAdminVerified);
        dest.writeValue(isDeleted);
        dest.writeValue(isBlocked);
        dest.writeList(socialAccounts);
        dest.writeValue(driverAddressID);
        dest.writeValue(serviceProviderAddressID);
        dest.writeValue(customerAddressID);
        dest.writeValue(driverID);
        dest.writeValue(serviceProviderID);
        dest.writeValue(customerID);
        dest.writeList(role);
        dest.writeValue(ratedByUserCount);
        dest.writeValue(totalRatingPoints);
        dest.writeValue(passwordResetToken);
        dest.writeValue(cronHardDeleteCount);
        dest.writeValue(rememberMe);
        dest.writeValue(lastName);
        dest.writeValue(name);
        dest.writeList(contacts);
        dest.writeValue(token);
    }

    public int describeContents() {
        return 0;
    }

}
