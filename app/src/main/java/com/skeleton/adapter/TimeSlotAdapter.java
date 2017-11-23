package com.skeleton.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skeleton.MyApplication;
import com.skeleton.R;

import java.util.ArrayList;

import static com.skeleton.constant.AppConstant.DURATION_ARRAY;

/**
 * Created by Raman Deep
 */

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.TimeVHolder> {

    private int timeStart, timeEnd, duration;
    private ArrayList<String> timeList;

    /**
     * @param timeStart timeStart
     * @param timeEnd   timeEnd
     * @param duration  duration
     * @param timeList  ArrayList of Dates
     */
    public TimeSlotAdapter(final int timeStart, final int timeEnd
            , final int duration, final ArrayList<String> timeList) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.duration = duration;
        this.timeList = timeList;
    }

    @Override
    public TimeSlotAdapter.TimeVHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new TimeSlotAdapter.TimeVHolder(LayoutInflater.from(MyApplication.getAppContext())
                .inflate(R.layout.layout_time_slot, parent, false));
    }

    @Override
    public void onBindViewHolder(final TimeSlotAdapter.TimeVHolder holder, final int position) {
        holder.textView.setText(timeList.get(position));
    }

    @Override
    public int getItemCount() {
        return ((timeEnd - timeStart) + 1) * DURATION_ARRAY[duration];
    }

    /**
     *
     */
    class TimeVHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        /**
         * @param itemView view
         */
        public TimeVHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_time_slot);
        }
    }
}