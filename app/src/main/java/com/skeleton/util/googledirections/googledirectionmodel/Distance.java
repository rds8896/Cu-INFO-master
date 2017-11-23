package com.skeleton.util.googledirections.googledirectionmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**  * +++++++++++++++++++++++++++++++  * +++++++++Click labs +++++++++++  * +++++++++++++++++++++++++++++++  */
public class Distance {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("value")
    @Expose
    private Integer value;

    /**
     * @return get text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text set text
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * @return get Value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @param value set Value
     */
    public void setValue(final Integer value) {
        this.value = value;
    }

}
