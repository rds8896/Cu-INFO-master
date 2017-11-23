package com.skeleton.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.skeleton.fragment.WeekViewPagerFragment;
import com.skeleton.model.calendar.WeekTitle;

import java.util.ArrayList;

/**
 * Created by Raman Deep
 */

public class WeekFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private int yScrollValue;
    private int duration;
    private int weekLength;
    private int timeStart;
    private int timeEnd;
    private ArrayList<WeekTitle> weekDaysList;

    /**
     * @param fm         FragmentManager
     * @param yScroll    current yScroll of visible RecyclerViews
     * @param duration   duration in setting
     * @param weekLength weekLength in setting
     * @param timeStart  timeStart in setting
     * @param timeEnd    timeEnd in setting
     * @param list       ArrayList of WeekTitle dates
     */

    public WeekFragmentPagerAdapter(final FragmentManager fm, final int yScroll
            , final int duration, final int weekLength
            , final int timeStart, final int timeEnd
            , final ArrayList<WeekTitle> list) {
        super(fm);
        this.yScrollValue = yScroll;
        this.duration = duration;
        this.weekLength = weekLength;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.weekDaysList = list;
    }

    @Override
    public Fragment getItem(final int position) {
        return WeekViewPagerFragment.newInstance(yScrollValue, position
                , new ArrayList<>(weekDaysList.subList(weekLength * position
                        , weekLength * position + weekLength))
                , new short[]{(short) duration
                        , (short) weekLength
                        , (short) timeStart
                        , (short) timeEnd});
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    /**
     * @param yScrollValue set YScrollOffset
     */
    public void setyScrollValue(final int yScrollValue) {
        this.yScrollValue = yScrollValue;
    }
}