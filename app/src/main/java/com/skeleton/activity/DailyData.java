package com.skeleton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.skeleton.R;

public class DailyData extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_data);
        Intent intent = getIntent();
        TextView tvHeading = (TextView) findViewById(R.id.tvHeading);
        tvHeading.setText(intent.getStringExtra(MOBILE));
    }

    @Override
    public void onClick(final View v) {
        super.onClick(v);
    }
}
