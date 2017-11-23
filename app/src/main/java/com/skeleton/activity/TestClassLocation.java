package com.skeleton.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.skeleton.R;
import com.skeleton.fragment.TestLocationFragment;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep +++++++++++
 * +++++++++++++++++++++++++++++++
 */

public class TestClassLocation extends BaseActivity {

    private TestLocationFragment testLocationFragment;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_test);

        // PathAndTimeGoogleMaps pathAndTimeGoogleMaps=new PathAndTimeGoogleMaps("SAd","asdas",new GoogleMap(),this);

        testLocationFragment = (TestLocationFragment) getSupportFragmentManager()
                .findFragmentById(R.id.testLocationFragment);
    }
/*
    @Override
    public void checkStatus(final String mValue) {

    }

    @Override
    public void checkDistance(final String mDistace) {

    }

    @Override
    public void checkTime(final String mTime) {

    }
    */
}
