package com.skeleton.model.raman;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mcgreen on 7/10/17.
 */

public class DataObj {
    @SerializedName("statusCode")
    @Expose
    public int statusCode;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public List<Detail> data = null;

    public List<Detail> getData() {
        return data;
    }

    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setData(final List<Detail> data) {
        this.data = data;
    }
}


