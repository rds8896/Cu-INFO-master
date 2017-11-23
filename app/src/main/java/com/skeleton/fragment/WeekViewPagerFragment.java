package com.skeleton.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.michaelflisar.dragselectrecyclerview.DragSelectTouchListener;
import com.skeleton.R;
import com.skeleton.adapter.RecyclerGridViewAdapter;
import com.skeleton.adapter.RecyclerWeekDaysAdapter;
import com.skeleton.model.calendar.WeekTitle;

import java.util.ArrayList;

import static com.skeleton.constant.AppConstant.ARG_PARAM1;
import static com.skeleton.constant.AppConstant.ARG_PARAM2;
import static com.skeleton.constant.AppConstant.ARG_PARAM3;
import static com.skeleton.constant.AppConstant.ARG_PARAM4;
import static com.skeleton.constant.AppConstant.BROADCAST_SCROLL;
import static com.skeleton.constant.AppConstant.BROADCAST_TIME_SCROLL;
import static com.skeleton.constant.AppConstant.INTENT_KEY_POSITION;
import static com.skeleton.constant.AppConstant.INTENT_KEY_SCROLL;

/**
 *
 */
public class WeekViewPagerFragment extends Fragment {

    private BroadcastReceiver receiverScroll;
    private int yScroll = 0;
    private int paramPosition;
    private ArrayList<WeekTitle> list;
    private BroadcastReceiver receiverForTimeScroll;
    private GridScrollListener scrollListenerCell;
    private int cMonth;
    private int cYear;
    private int duration;
    private int timeStart;
    private int timeEnd;
    private int weekLen;
    private short[] settingsValues;
    private LinearLayout linearLayout;
    private RecyclerView recyclerGridView;
    private RecyclerView recyclerViewWeekTitle;
    private RecyclerGridViewAdapter recyclerAdapter;
    private DragSelectTouchListener dragSelectTouchListener;
    private WeekChildFragmentListener mListener;

    /**
     * WeekViewPagerFragment constructor
     */
    public WeekViewPagerFragment() {
        // Required empty public constructor
    }

    /**
     * static method to get WeekViewPagerFragment object
     *
     * @param param1   yScrollOffSet
     * @param param2   Fragment Position in ViewPager
     * @param list     List of WeekTitle object
     * @param settings shortArray of setting Values
     * @return WeekViewPagerFragment instance
     */
    public static WeekViewPagerFragment newInstance(final int param1, final int param2
            , final ArrayList<WeekTitle> list
            , final short[] settings) {
        final WeekViewPagerFragment fragment = new WeekViewPagerFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        args.putParcelableArrayList(ARG_PARAM3, list);
        args.putShortArray(ARG_PARAM4, settings);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            yScroll = getArguments().getInt(ARG_PARAM1);
            paramPosition = getArguments().getInt(ARG_PARAM2);
            this.list = getArguments().getParcelableArrayList(ARG_PARAM3);
            this.settingsValues = getArguments().getShortArray(ARG_PARAM4);
            duration = settingsValues[0];
            weekLen = settingsValues[1];
            timeStart = settingsValues[2];
            timeEnd = settingsValues[3];
        }
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            list = savedInstanceState.getParcelableArrayList(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_status_calendar, container, false);
        init(v);
        return v;
    }

    /**
     * @param view view fragment
     */
    private void init(final View view) {
        recyclerViewWeekTitle = (RecyclerView) view.findViewById(R.id.recycler_week_day);
        recyclerGridView = (RecyclerView) view.findViewById(R.id.recycler);
        linearLayout = (LinearLayout) view.findViewById(R.id.ll_fragment);
    }


    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof WeekChildFragmentListener) {
            mListener = (WeekChildFragmentListener) getParentFragment();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement WeekChildFragmentListener");
        }
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        recyclerGridView.setHasFixedSize(true);
        recyclerGridView.addItemDecoration(new GridDecorator(getActivity().getResources().getDimensionPixelOffset(R.dimen.cell_spacing)));
        recyclerGridView.setLayoutManager(new GridLayoutManager(getActivity(), weekLen));
        recyclerAdapter = new RecyclerGridViewAdapter(duration, timeStart, timeEnd, weekLen, linearLayout, list);
        dragSelectTouchListener = new DragSelectTouchListener()
                .withSelectListener(new DragSelectTouchListener.OnDragSelectListener() {
                    @Override
                    public void onSelectChange(final int i, final int i1, final boolean b) {
                        recyclerAdapter.setRange(i, i1);
                    }
                });
        recyclerAdapter.setListener(new RecyclerGridViewAdapter.Listener() {
            @Override
            public void onClick(final View view, final int position) {

            }

            @Override
            public void onLongClick(final View view, final int position) {
                dragSelectTouchListener.startDragSelection(position);
            }
        });
        recyclerGridView.setAdapter(recyclerAdapter);
        recyclerGridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                if (scrollListenerCell == null) {
                    scrollListenerCell = new GridScrollListener();
                    recyclerGridView.addOnScrollListener(scrollListenerCell);
                }
                return false;
            }
        });
        recyclerGridView.addOnItemTouchListener(dragSelectTouchListener);
        recyclerViewWeekTitle.addItemDecoration(new TitleDecorator(getActivity().getResources().getDimensionPixelOffset(R.dimen.cell_spacing)));
        recyclerViewWeekTitle.setLayoutManager(new GridLayoutManager(getActivity(), weekLen));
        recyclerViewWeekTitle.setAdapter(new RecyclerWeekDaysAdapter(weekLen, list));
        cMonth = list.get(0).getMonth();
        cYear = list.get(0).getYear();
    }

    @Override
    public void onResume() {
        super.onResume();

        // BroadCast Receiver for WeekViewPagerFragment  RecyclerView scroll
        receiverScroll = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                if (paramPosition != intent.getIntExtra(INTENT_KEY_POSITION, 0)) {
                    // Log.d("clab","scroll point 1 of Frg :"+paramPosition+"----by :"+intent.getIntExtra("newy",0));
                    recyclerGridView.scrollBy(0, intent.getIntExtra("newy", 0));
                    recyclerGridView.invalidate();
                    //recyclerGridView.smoothScrollBy(0,intent.getIntExtra("newy",0),null);
                    // Log.d("clab","receiver :"+paramPosition+" ,"+intent.getIntExtra("newy",0));
                }
            }
        };

        // BroadCast Receiver for TimeRange RecyclerView Scroll
        receiverForTimeScroll = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
                if (scrollListenerCell != null && recyclerGridView != null) {
                    recyclerGridView.removeOnScrollListener(scrollListenerCell);
                    scrollListenerCell = null;
                }
                // Log.d("clab","scroll point 2 of Frg :"+paramPosition+"----by :"+intent.getIntExtra("newy",0));
                if (recyclerGridView != null) {
                    recyclerGridView.scrollBy(0, intent.getIntExtra(INTENT_KEY_SCROLL, 0));
                    recyclerGridView.invalidate();
                }
            }
        };

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(receiverScroll
                , new IntentFilter(BROADCAST_SCROLL));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(receiverForTimeScroll
                , new IntentFilter(BROADCAST_TIME_SCROLL));

        //scroll RecyclerView after it created in ViewTree
        recyclerGridView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(final View v, final int left
                    , final int top, final int right
                    , final int bottom, final int oldLeft
                    , final int oldTop, final int oldRight
                    , final int oldBottom) {
                final int initial = recyclerGridView.computeVerticalScrollOffset();
                recyclerGridView.scrollBy(0, yScroll - initial);
                recyclerGridView.removeOnLayoutChangeListener(this);
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(receiverScroll);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(receiverForTimeScroll);
        if (recyclerAdapter != null) {
            recyclerAdapter.saveStatusData();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ARG_PARAM3, list);
    }

    /**
     * Decorator  For GridViews
     */
    private class GridDecorator extends RecyclerView.ItemDecoration {

        private int unit;

        /**
         * @param val Margin Value in DP
         */
        public GridDecorator(final int val) {
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

    /**
     * Calendar Day Decorator
     */
    private class TitleDecorator extends RecyclerView.ItemDecoration {

        private int unit;

        /**
         * @param val Margin Value in DP
         */
        public TitleDecorator(final int val) {
            this.unit = val;
        }

        @Override
        public void getItemOffsets(final Rect outRect, final View view, final RecyclerView parent
                , final RecyclerView.State state) {
            outRect.right = unit;
            //outRect.bottom = unit;
            outRect.left = unit;

        }
    }

    /**
     * GridView scroll Listener
     */
    private class GridScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
            super.onScrolled(recyclerView, dx, dy);
            /*
             * if this activity is scrolled by User
             * then inform Parent Fragment about amount of scroll
             * (mListener.currentPageIndex() == paramPosition) ensure that this is visible activity
             */
            if (mListener.currentPageIndex() == paramPosition) {
                mListener.onViewScrolled(dy, paramPosition, recyclerView.computeVerticalScrollOffset());
            }
        }

        @Override
        public void onScrollStateChanged(final RecyclerView recyclerView, final int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }
    }

    /**
     * Fragment Communicator
     */
    public interface WeekChildFragmentListener {

        /**
         * @param y              yScroll
         * @param position       Pager Position
         * @param verticalOffSet RecyclerView Current YOffSet
         */
        void onViewScrolled(int y, int position, int verticalOffSet);

        /**
         * @return index of visible fragment in ViewPager
         */
        int currentPageIndex();
    }

}
