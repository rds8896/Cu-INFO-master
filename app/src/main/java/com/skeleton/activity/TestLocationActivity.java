package com.skeleton.activity;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.skeleton.util.currentlocationfetcher.LocationFetcher;
import com.skeleton.util.currentlocationfetcher.LocationFetcherCallBack;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep+++++++++++
 * +++++++++++++++++++++++++++++++
 */

public class TestLocationActivity extends BaseActivity implements LocationFetcherCallBack {
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocationFetcher getLocation = new LocationFetcher.Builder(this)
                .setTimeInterval(SET_TIME_INTERVAL)
                .setLocationFetcherCallBack(this)
                .build();

    }


    @Override
    public void onSuccess(final Location location) {
        Log.e("Success", String.valueOf(location.getLatitude()));
        Log.e("Success", String.valueOf(location.getLongitude()));

    }

    @Override
    public void onFailure(final String errorMsg) {
        Log.e("Error", "failure");
    }
}
