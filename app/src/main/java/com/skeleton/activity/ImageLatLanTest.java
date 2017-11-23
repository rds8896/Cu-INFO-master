package com.skeleton.activity;

import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;

import com.skeleton.util.Log;

import java.io.IOException;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class ImageLatLanTest extends BaseActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/Download/bhavya.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        float[] latLong = new float[2];
        boolean hasLatLong = exif.getLatLong(latLong);
        if (hasLatLong) {
            Log.e("Error", "Latitude: " + latLong[0]);
            Log.e("Error", "Longitude: " + latLong[1]);
        } else {
            Log.e("Error", "Not found Lat-Lan");
        }


    }
}
