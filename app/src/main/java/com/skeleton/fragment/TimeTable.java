package com.skeleton.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.activity.DailyData;

/**
 * Created by Raman on 17/11/17.
 */

public class TimeTable extends BaseFragment {
    private Context mContext;
    private CardView monday, tuesday, wed, thus, friday, sat, sun;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timetable, container, false);
        init(view);
        return view;
    }

    private void init(final View view) {
        monday = (CardView) view.findViewById(R.id.monday);
        tuesday = (CardView) view.findViewById(R.id.tuesday);
        wed = (CardView) view.findViewById(R.id.wed);
        thus = (CardView) view.findViewById(R.id.thus);
        friday = (CardView) view.findViewById(R.id.friday);
        sat = (CardView) view.findViewById(R.id.sat);
        sun = (CardView) view.findViewById(R.id.sun);
        monday.setOnClickListener(this);
        tuesday.setOnClickListener(this);
        wed.setOnClickListener(this);
        thus.setOnClickListener(this);
        friday.setOnClickListener(this);
        sat.setOnClickListener(this);
        sun.setOnClickListener(this);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @SuppressLint("ShowToast")
    @Override
    public void onClick(final View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.monday:
                startActivity(new Intent(mContext, DailyData.class).putExtra(MOBILE, "Monday"));
                break;
            case R.id.tuesday:
                startActivity(new Intent(mContext, DailyData.class).putExtra(MOBILE, "Tuesday"));
                break;
            case R.id.wed:
                startActivity(new Intent(mContext, DailyData.class).putExtra(MOBILE, "Wednesday"));
                break;
            case R.id.thus:
                startActivity(new Intent(mContext, DailyData.class).putExtra(MOBILE, "Thursday"));
                break;
            case R.id.friday:
                startActivity(new Intent(mContext, DailyData.class).putExtra(MOBILE, "Friday"));
                break;
            case R.id.sat:
                Toast.makeText(mContext, "App is designed considering Saturday as Holiday", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(mContext, DailyData.class).putExtra(MOBILE, "Saturday"));
                break;
            case R.id.sun:
                Toast.makeText(mContext, "App is designed considering Sunday as Holiday", Toast.LENGTH_LONG).show();
                ;

//                startActivity(new Intent(mContext, DailyData.class).putExtra(MOBILE, "Sunday"));
                break;
            default:
        }

    }

    /**
     * @param msg of alert dialog
     */
    private void showAlertDialog(final String msg) {
        new AlertDialog.Builder(mContext)
                .setMessage(msg)
                .setPositiveButton(R.string.text_ok, null);
    }
}
