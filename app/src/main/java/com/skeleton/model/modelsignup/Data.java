
package com.skeleton.model.modelsignup;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("__v")
    @Expose
    private Integer v;
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
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("utcoffset")
    @Expose
    private Integer utcoffset;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("isPhoneVerified")
    @Expose
    private Boolean isPhoneVerified;
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
    private String driverAddressID;
    @SerializedName("serviceProviderAddressID")
    @Expose
    private Object serviceProviderAddressID;
    @SerializedName("customerAddressID")
    @Expose
    private Object customerAddressID;
    @SerializedName("driverID")
    @Expose
    private String driverID;
    @SerializedName("serviceProviderID")
    @Expose
    private Object serviceProviderID;
    @SerializedName("customerID")
    @Expose
    private Object customerID;
    @SerializedName("role")
    @Expose
    private List<String> role = null;
    @SerializedName("totalCreatedUsers")
    @Expose
    private Integer totalCreatedUsers;
    @SerializedName("assignedTo")
    @Expose
    private Object assignedTo;
    @SerializedName("assignedBy")
    @Expose
    private Object assignedBy;
    @SerializedName("createdBy")
    @Expose
    private Object createdBy;
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
    @SerializedName("driver")
    @Expose
    private Driver driver;
    @SerializedName("address")
    @Expose
    private Address address;

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
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

    public Integer getUtcoffset() {
        return utcoffset;
    }

    public void setUtcoffset(Integer utcoffset) {
        this.utcoffset = utcoffset;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsPhoneVerified() {
        return isPhoneVerified;
    }

    public void setIsPhoneVerified(Boolean isPhoneVerified) {
        this.isPhoneVerified = isPhoneVerified;
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

    public String getDriverAddressID() {
        return driverAddressID;
    }

    public void setDriverAddressID(String driverAddressID) {
        this.driverAddressID = driverAddressID;
    }

    public Object getServiceProviderAddressID() {
        return serviceProviderAddressID;
    }

    public void setServiceProviderAddressID(Object serviceProviderAddressID) {
        this.serviceProviderAddressID = serviceProviderAddressID;
    }

    public Object getCustomerAddressID() {
        return customerAddressID;
    }

    public void setCustomerAddressID(Object customerAddressID) {
        this.customerAddressID = customerAddressID;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public Object getServiceProviderID() {
        return serviceProviderID;
    }

    public void setServiceProviderID(Object serviceProviderID) {
        this.serviceProviderID = serviceProviderID;
    }

    public Object getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Object customerID) {
        this.customerID = customerID;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public Integer getTotalCreatedUsers() {
        return totalCreatedUsers;
    }

    public void setTotalCreatedUsers(Integer totalCreatedUsers) {
        this.totalCreatedUsers = totalCreatedUsers;
    }

    public Object getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Object assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Object getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Object assignedBy) {
        this.assignedBy = assignedBy;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
