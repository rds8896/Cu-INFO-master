package com.skeleton.model.raman;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mcgreen on 7/10/17.
 */

public class Detail {

    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("updatedAt")
    @Expose
    public String updatedAt;
    @SerializedName("createdAt")
    @Expose
    public String createdAt;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("uniqueId")
    @Expose
    public int uniqueId;
    @SerializedName("startDate")
    @Expose
    public String startDate;
    @SerializedName("__v")
    @Expose
    public int v;

    public String getId() {
        return id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public String getStartDate() {
        return startDate;
    }

    public int getV() {
        return v;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setUpdatedAt(final String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public void setUniqueId(final int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    public void setV(final int v) {
        this.v = v;
    }
}
