package com.skeleton.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skeleton.MyApplication;
import com.skeleton.R;
import com.skeleton.model.calendar.WeekTitle;
import com.skeleton.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import io.paperdb.Paper;

import static com.skeleton.constant.AppConstant.AM_PM;
import static com.skeleton.constant.AppConstant.DURATION_ARRAY;
import static com.skeleton.constant.AppConstant.HOURS;
import static com.skeleton.constant.AppConstant.MONTH_NAME;
import static com.skeleton.constant.AppConstant.TIME_DIFFERENCE;

/**
 * Created by Raman Deep .
 */

public class RecyclerGridViewAdapter extends RecyclerView.Adapter<RecyclerGridViewAdapter.VHolder> {

    /**
     *
     */
    public interface Listener {
        /**
         * @param view     view
         * @param position pos
         */
        void onClick(View view, int position);

        /**
         * @param view     view
         * @param position pos
         */
        void onLongClick(View view, int position);
    }

    private static final int TEN = 10;
    private static final int TWENTY_FOUR = 24;
    private int timeDuration, timeStart, timeEnd, weekLen, totalRow;
    private ViewGroup viewGroup;
    private ArrayList<WeekTitle> weekDayList;
    private Calendar tempCalendar;
    private Integer[] indexValue;
    private Listener mListener;
    private List<List<Integer>> listStatus = new ArrayList<>();
    private int longPressColumn = -1;

    /**
     * @param timeDuration time duration
     * @param timeStart    start time
     * @param timeEnd      end time
     * @param weekLen      number of days
     * @param viewGroup    ViewGroup to Show SnackBar
     * @param weekDayList  ArrayList of WeekTitle object
     */
    public RecyclerGridViewAdapter(final int timeDuration
            , final int timeStart
            , final int timeEnd
            , final int weekLen
            , final ViewGroup viewGroup
            , final ArrayList<WeekTitle> weekDayList) {

        this.timeDuration = timeDuration;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.weekLen = weekLen;
        this.viewGroup = viewGroup;
        this.weekDayList = weekDayList;
        tempCalendar = Calendar.getInstance();
        totalRow = timeDuration == 2 ? TWENTY_FOUR : (timeDuration == 1 ? (TWENTY_FOUR * 2) : (TWENTY_FOUR * 4));
        for (WeekTitle week : weekDayList) {
            listStatus.add(Paper.book().read(getKey(week), initArrayList(totalRow)));
        }
    }

    /**
     * function return count length -1 initialised list
     *
     * @param count Number to iterate
     * @return ArrayList
     */
    private ArrayList<Integer> initArrayList(final int count) {
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tempList.add(-1);
        }
        return tempList;
    }

    /**
     * @param weekTitle WeekTitle Object
     * @return String Key with combination [year-month-date-duration]
     */
    private String getKey(final WeekTitle weekTitle) {
        return String.valueOf(weekTitle.getYear())
                + "-" + getZeroAppended(weekTitle.getMonth() + 1)
                + "-" + getZeroAppended(weekTitle.getDate())
                + timeDuration;
    }

    /**
     * Save Change Status Data if any
     */
    public void saveStatusData() {
        for (int i = 0; i < weekDayList.size(); i++) {
            String key = getKey(weekDayList.get(i));
            Paper.book().write(key, listStatus.get(i));
        }
    }

    @Override
    public RecyclerGridViewAdapter.VHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new RecyclerGridViewAdapter.VHolder(LayoutInflater.from(MyApplication.getAppContext())
                .inflate(R.layout.layout_week_box, parent, false));
    }

    /**
     * return 0 appended at prefix if  indexValue < 10
     * otherwise return indexValue a string without any modification
     *
     * @param value any int indexValue
     * @return string  of indexValue
     */
    private String getZeroAppended(final int value) {
        if (value < TEN) {
            return String.valueOf("0" + value);
        }
        return String.valueOf(value);
    }

    /**
     * @param position posiotion
     */
    public void setSingleItem(final int position) {
        Log.e("alex", "pos " + position);
        setStatusAtPosition(position, 1);
        notifyItemChanged(position);
    }

    /**
     * @param start start index
     * @param end   end index
     */
    public void setRange(final int start, final int end) {
        // Log.e("alex", "range " + start + "-" + end);
        for (int i = start; i <= end; i++) {
            if ((i % weekLen) == longPressColumn) {
                updateStatus(i);
            }
        }

    }

    private void updateStatus(final int position) {
        int status = getStatusAtPosition(position);
        if (status == -1 || status == 1) {
            setStatusAtPosition(position, status == 1 ? -1 : 1);
            notifyItemChanged(position);
        }
    }

    /**
     * @param listener ClickListener
     */
    public void setListener(final Listener listener) {
        this.mListener = listener;
    }

    /**
     * Get Status of box at Position in GridView
     *
     * @param position int adapter position
     * @return int value at position
     */
    private int getStatusAtPosition(final int position) {
        int column = position % weekLen;
        int row = position / weekLen;
        return listStatus.get(column).get(row + timeStart * DURATION_ARRAY[timeDuration]);
    }

    /**
     * @param position int Adapter Position
     * @param value    int status value at position
     */
    private void setStatusAtPosition(final int position, final int value) {
        int column = position % weekLen;
        int row = position / weekLen;
        listStatus.get(column).set(row + timeStart * DURATION_ARRAY[timeDuration], value);
    }

    @Override
    public void onBindViewHolder(final RecyclerGridViewAdapter.VHolder holder, final int position) {
        int value = getStatusAtPosition(position);
        switch (value) {
            case 1:
                holder.view.setBackgroundResource(R.color.color_available);
                break;
            case 2:
                holder.view.setBackgroundResource(R.color.color_busy);
                break;
            default:
                holder.view.setBackgroundResource(R.color.color_unavailable);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return (Math.abs(timeEnd - timeStart) + 1) * weekLen * DURATION_ARRAY[timeDuration];
    }

    /**
     *
     */
    class VHolder extends RecyclerView.ViewHolder implements View.OnClickListener
            , View.OnLongClickListener {
        private View view;

        /**
         * @param itemView View
         */
        public VHolder(final View itemView) {
            super(itemView);
            view = itemView;
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            updateStatus(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(final View v) {
            if (mListener != null) {
                mListener.onLongClick(v, getAdapterPosition());
            }
            longPressColumn = getAdapterPosition() % weekLen;
            updateStatus(getAdapterPosition());
            return false;
        }
    }

    /**
     * @param adapterPosition ViewPager Current Page Position
     * @return Time Range of Cell
     */
    private String getClickTimeRange(final int adapterPosition) {
        final Calendar calendar = new GregorianCalendar(tempCalendar.get(Calendar.YEAR)
                , tempCalendar.get(Calendar.MONTH)
                , tempCalendar.get(Calendar.DATE), timeStart, 0);
        final StringBuilder builder = new StringBuilder();
        calendar.add(Calendar.MINUTE, TIME_DIFFERENCE[timeDuration] * (adapterPosition / weekLen - 1));
        builder.append(weekDayList.get(adapterPosition % weekLen).getDate() + "-"
                + MONTH_NAME[weekDayList.get(adapterPosition % weekLen).getMonth()]
                + "\n");
        builder.append("[" + HOURS[calendar.get(Calendar.HOUR)]);
        final int flag = calendar.get(Calendar.MINUTE);
        if (flag != 0) {
            builder.append("." + calendar.get(Calendar.MINUTE));
        }
        builder.append(AM_PM[calendar.get(Calendar.AM_PM)]);
        calendar.add(Calendar.MINUTE, TIME_DIFFERENCE[timeDuration]);
        builder.append("-" + HOURS[calendar.get(Calendar.HOUR)]);
        final int flag2 = calendar.get(Calendar.MINUTE);
        if (flag2 != 0) {
            builder.append("." + calendar.get(Calendar.MINUTE));
        }
        builder.append(AM_PM[calendar.get(Calendar.AM_PM)] + "]");
        return builder.toString();
    }

}