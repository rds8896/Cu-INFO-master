package com.skeleton.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.skeleton.R;
import com.skeleton.adapter.TimeSlotAdapter;
import com.skeleton.adapter.WeekFragmentPagerAdapter;
import com.skeleton.model.calendar.WeekTitle;
import com.skeleton.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import io.paperdb.Paper;

/**
 *
 */
public class WeekFragment extends BaseFragment implements WeekViewPagerFragment.WeekChildFragmentListener {
    private static final int DATE_GAP = 1;
    private static final int APPEND_DATES = 2;
    private RecyclerView recyclerViewTimeSlot;
    private ViewPager viewPagerWeek;
    private ArrayList<WeekTitle> weekDaysList;
    private ArrayList<String> timeList;
    private int currentPagePosition, yScrollOffSet;
    private RecyclerView.OnScrollListener onScrollListener;
    private int duration, timeStart, timeEnd, weekLength;
    private WeekFragmentPagerAdapter weekFragmentPagerAdapter;
    private LinearLayout llInstruction;
    private ImageView ivShowInstruction;
    private MaterialCalendarView materialCalendarView;
    private TextView tvDates;
    private ImageView ivCalendarDropDown;

    /**
     *
     */
    public WeekFragment() {
        // Required empty public constructor
    }

    /**
     * @return WeekFragment instance
     */
    public static WeekFragment newInstance() {
        return new WeekFragment();
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragmnet_status_week, container, false);
        init(v);
        return v;
    }

    /**
     * @param view RootLayout
     */
    private void init(final View view) {
        recyclerViewTimeSlot = (RecyclerView) view.findViewById(R.id.week_time_recycler);
        viewPagerWeek = (ViewPager) view.findViewById(R.id.week_viewpager);
        llInstruction = (LinearLayout) view.findViewById(R.id.ll_instruction);
        ivShowInstruction = (ImageView) view.findViewById(R.id.iv_instruction);
        ivCalendarDropDown = (ImageView) view.findViewById(R.id.iv_status_dropdown);
        materialCalendarView = (MaterialCalendarView) view.findViewById(R.id.status_calendar);
        tvDates = (TextView) view.findViewById(R.id.tv_status_date);
        ivShowInstruction.setOnClickListener(this);
        ivCalendarDropDown.setOnClickListener(this);
        setUserSettings();
        dateListGenerator();
        generateTimeRange();
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            currentPagePosition = savedInstanceState.getInt(ARG_PARAM1);
            yScrollOffSet = savedInstanceState.getInt(ARG_PARAM2);
            weekDaysList = savedInstanceState.getParcelableArrayList(ARG_PARAM3);
        }
        recyclerViewTimeSlot.setHasFixedSize(true);
        recyclerViewTimeSlot.addItemDecoration(new TimeSlotDecor(getResources().getDimensionPixelOffset(R.dimen.cell_spacing)));
        recyclerViewTimeSlot.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewTimeSlot.setAdapter(new TimeSlotAdapter(timeStart, timeEnd, duration, timeList));
        recyclerViewTimeSlot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                if (onScrollListener == null) {
                    onScrollListener = new TimeSlotScrollListener();
                    recyclerViewTimeSlot.addOnScrollListener(onScrollListener);
                }
                return false;
            }
        });
        weekFragmentPagerAdapter = new WeekFragmentPagerAdapter(getChildFragmentManager(),
                yScrollOffSet, duration, weekLength
                , timeStart, timeEnd, weekDaysList);
        viewPagerWeek.addOnPageChangeListener(new WeekPageChangeListener());
        viewPagerWeek.setAdapter(weekFragmentPagerAdapter);
        viewPagerWeek.setCurrentItem(DEFAULT_INITIAL_PAGES / 2);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_PARAM1, currentPagePosition);
        outState.putInt(ARG_PARAM2, yScrollOffSet);
        outState.putParcelableArrayList(ARG_PARAM3, weekDaysList);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.iv_instruction:
                setIvShowInstruction(llInstruction);
                break;
            case R.id.iv_status_dropdown:
                setIvShowInstruction(materialCalendarView);
                break;
            default:
        }
    }

    /**
     * Animate Change in Visibility of View
     *
     * @param view View
     */
    private void setIvShowInstruction(final View view) {
        if (view.getVisibility() == View.VISIBLE) {
            Animation aUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up_linear);
            if (aUp != null) {
                aUp.reset();
                view.clearAnimation();
                view.startAnimation(aUp);
                view.setVisibility(View.GONE);
            }
        } else {
            materialCalendarView.setSelectedDate(new Date(System.currentTimeMillis()));
            Animation adown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down_linear);
            if (adown != null) {
                adown.reset();
                view.clearAnimation();
                view.startAnimation(adown);
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * initialise all user settings in member variable for use
     */
    private void setUserSettings() {
        duration = Paper.book().read(DURATION, DEFAULT_DURATION);
        timeStart = Paper.book().read(TIME_RANGE_START, DEFAULT_START_TIME);
        timeEnd = Paper.book().read(TIME_RANGE_END, DEFAULT_END_TIME);
        weekLength = Paper.book().read(DAY_LENGTH, DEFAULT_NO_DAYS);
    }

    /**
     * Generate a WeekTitle list of dates
     */
    private void dateListGenerator() {
        weekDaysList = new ArrayList<>();
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -(weekLength * DEFAULT_PREVIOUS_DATES));
        for (int i = 0; i < DEFAULT_INITIAL_PAGES * weekLength; i++) {
            weekDaysList.add(new WeekTitle(calendar.get(Calendar.DATE),
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR)));
            calendar.add(Calendar.DATE, DATE_GAP);
        }
//        Log.d("clab","array len :"+weekDaysList.size());
//        for (WeekTitle a:weekDaysList){
//            Log.d("clabs",a.getDate()+"-"+a.getMonth()+"-"+a.getYear());
//        }
    }

    /**
     * add new dates to left of list when position is 1 or add to right
     *
     * @param position flag
     */
    private void appendMoreDate(final int position) {
        final Calendar calendar = new GregorianCalendar(weekDaysList.get(weekDaysList.size() - 1).getYear()
                , weekDaysList.get(weekDaysList.size() - 1).getMonth()
                , weekDaysList.get(weekDaysList.size() - 1).getDate());
        for (int i = 0; i < (APPEND_DATES * weekLength); i++) {
            calendar.add(Calendar.DATE, 1);
            weekDaysList.add(weekDaysList.size()
                    , new WeekTitle(calendar.get(Calendar.DATE)
                            , calendar.get(Calendar.MONTH)
                            , calendar.get(Calendar.YEAR)));
        }
        if (weekFragmentPagerAdapter != null) {
            weekFragmentPagerAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Generate a List of String for time range on basis of Duration
     */

    private void generateTimeRange() {
        timeList = new ArrayList<>();
        final Calendar clc = Calendar.getInstance();
        final Calendar calendar = new GregorianCalendar(clc.get(Calendar.YEAR), clc.get(Calendar.MONTH)
                , clc.get(Calendar.DATE), timeStart, 0);
        // calculate total number of TimeSlots
        final int len = ((timeEnd - timeStart) + 1) * DURATION_ARRAY[duration];

        for (int i = 0; i < len; i++) {
            final StringBuffer buffer = new StringBuffer();
            buffer.append(HOURS[calendar.get(Calendar.HOUR)]);
            final int x = calendar.get(Calendar.MINUTE);
            if (x != 0) {
                final String temp = "." + x;
                buffer.append(temp);
            }
            buffer.append(AM_PM[calendar.get(Calendar.AM_PM)]);
            timeList.add(buffer.toString());
            calendar.add(Calendar.MINUTE, TIME_DIFFERENCE[duration]);
        }
//        int x = 0;
//        for (String s : timeList) {
//            Log.d("clab", "time[" + (x++) + "] :" + s);
//        }
    }

    /**
     * listener method to listen for RecyclerView scroll in viewPager Fragment
     *
     * @param y              amount of yScroll
     * @param position       indexOf Fragment whose RecyclerView Scrolled
     * @param verticalOffSet total verticalOffSet of RecyclerView
     */
    @Override
    public void onViewScrolled(final int y, final int position, final int verticalOffSet) {
        yScrollOffSet = verticalOffSet;
        weekFragmentPagerAdapter.setyScrollValue(verticalOffSet);
        if (onScrollListener != null) {
            recyclerViewTimeSlot.removeOnScrollListener(onScrollListener);
            onScrollListener = null;
        }
        recyclerViewTimeSlot.scrollBy(0, y);
        final Intent intent = new Intent(BROADCAST_SCROLL);
        intent.putExtra(INTENT_KEY_SCROLL, y);
        intent.putExtra(INTENT_KEY_POSITION, position);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    /**
     * @return position of current visible Fragment in ViewPager
     */
    @Override
    public int currentPageIndex() {
        return currentPagePosition;
    }

    /**
     * PageChangeListener for ViewPager
     */
    private class WeekPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(final int position) {

            /*
             * append More dates when second Last page is loaded
             * to make scroll infinite
             */
            currentPagePosition = position;
            if (position == (weekDaysList.size() / weekLength) - 2) {
                appendMoreDate(2);
            }
            WeekTitle start = weekDaysList.get(weekLength * position);
            WeekTitle end = weekDaysList.get(weekLength * position + (weekLength - 1));
            String dateRange = String.valueOf(start.getDate())
                    + MONTH_NAME[start.getMonth()]
                    + "-" + String.valueOf(end.getDate())
                    + MONTH_NAME[end.getMonth()];
//            SpannableStringBuilder span = new SpannableStringBuilder(dateRange);
//            StyleSpan span1 = new StyleSpan(Typeface.BOLD);
//            span.setSpan(span1,dateRange.indexOf(String.valueOf(start.getDate())),);
            tvDates.setText(dateRange);
        }

        @Override
        public void onPageScrollStateChanged(final int state) {

        }
    }

    /**
     * Scroll listener For TimeRange Dialog
     */
    private class TimeSlotScrollListener extends RecyclerView.OnScrollListener {

        /**
         * LocalBroadCast a intent with amount of y scroll
         * to synchronise all live Fragment scroll position
         *
         * @param recyclerView rv
         * @param dx           x
         * @param dy           y
         */
        @Override
        public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
            final Intent intent = new Intent(BROADCAST_TIME_SCROLL);
            intent.putExtra(INTENT_KEY_SCROLL, dy);
            yScrollOffSet = recyclerView.computeVerticalScrollOffset();
            weekFragmentPagerAdapter.setyScrollValue(yScrollOffSet);
            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
            Log.d("CLAB", "Time Scrolled");
        }
    }

    /**
     */
    private class TimeSlotDecor extends RecyclerView.ItemDecoration {

        private int unit;

        /**
         * @param val Margin Value in DP
         */
        public TimeSlotDecor(final int val) {
            this.unit = val;
        }

        @Override
        public void getItemOffsets(final Rect outRect, final View view, final RecyclerView parent
                , final RecyclerView.State state) {
            outRect.left = unit;
            outRect.right = unit;
            outRect.bottom = unit;
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = unit;
            } else {
                outRect.top = 0;
            }
        }
    }

}
