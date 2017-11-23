package com.skeleton.model.calendar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alex on 22/7/17.
 */
public class WeekTitle implements Parcelable {
    /**
     *
     */
    public static final Creator<WeekTitle> CREATOR = new Creator<WeekTitle>() {
        @Override
        public WeekTitle createFromParcel(final Parcel source) {
            return new WeekTitle(source);
        }

        @Override
        public WeekTitle[] newArray(final int size) {
            return new WeekTitle[size];
        }
    };
    private int date;
    private int month;
    private int year;

    /**
     * @param date  Int date
     * @param month Int month
     * @param year  Int year
     */
    public WeekTitle(final int date, final int month, final int year) {
        this.date = date;
        this.month = month;
        this.year = year;
    }

    /**
     * @param in Parcel
     */
    protected WeekTitle(final Parcel in) {
        this.date = in.readInt();
        this.month = in.readInt();
        this.year = in.readInt();
    }

    /**
     * @return Int date
     */
    public int getDate() {

        return date;
    }

    /**
     * @param date setDate Int
     */
    public void setDate(final int date) {
        this.date = date;
    }

    /**
     * @return Month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month Month
     */
    public void setMonth(final int month) {
        this.month = month;
    }

    /**
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year year
     */
    public void setYear(final int year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(this.date);
        dest.writeInt(this.month);
        dest.writeInt(this.year);
    }

}
