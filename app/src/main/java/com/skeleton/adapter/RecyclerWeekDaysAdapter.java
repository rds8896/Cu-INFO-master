package com.skeleton.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skeleton.MyApplication;
import com.skeleton.R;
import com.skeleton.model.calendar.WeekTitle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.skeleton.constant.AppConstant.WEEK_DAYS;

/**
 *Raman Deep
 */

public class RecyclerWeekDaysAdapter extends RecyclerView.Adapter<RecyclerWeekDaysAdapter.VHolderWeek> {

    private int weekLen;
    private ArrayList<WeekTitle> list;

    /**
     * @param weekLen DayLength in setting
     * @param list    ArrayList of WeekTitle object for Title RecyclerView
     */
    public RecyclerWeekDaysAdapter(final int weekLen, final ArrayList<WeekTitle> list) {
        this.weekLen = weekLen;
        this.list = list;
    }

    @Override
    public RecyclerWeekDaysAdapter.VHolderWeek onCreateViewHolder(final ViewGroup parent
            , final int viewType) {
        LayoutInflater inflater = LayoutInflater.from(MyApplication.getAppContext());
        return new RecyclerWeekDaysAdapter.VHolderWeek(inflater.inflate(R.layout.layout_calendar_title
                , parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerWeekDaysAdapter.VHolderWeek holder
            , final int position) {
        holder.monthday.setText(String.valueOf(list.get(position).getDate()));
        final Calendar cal = new GregorianCalendar(list.get(position).getYear()
                , list.get(position).getMonth()
                , list.get(position).getDate());
        holder.weekDay.setText(WEEK_DAYS[cal.get(Calendar.DAY_OF_WEEK) - 1]);
    }

    @Override
    public int getItemCount() {
        return weekLen;
    }

    /**
     * ViewHolder
     */
    class VHolderWeek extends RecyclerView.ViewHolder {

        private TextView monthday;
        private TextView weekDay;

        /**
         * @param itemView ViewHolder
         */
        public VHolderWeek(final View itemView) {
            super(itemView);
            monthday = (TextView) itemView.findViewById(R.id.tv_month_day);
            weekDay = (TextView) itemView.findViewById(R.id.tv_week_day);
        }
    }
}
