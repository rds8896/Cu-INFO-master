package com.skeleton.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skeleton.R;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class TutorialFragment extends BaseFragment {

    private static final String COUNT = "COUNT";
    private TextView tvHead;
    private TextView tvHeader;
    private ImageView ivMainImage;
    private int count;


    /**
     * @param val argument for the Fragment
     * @return Instance of Fragment
     */
    public static TutorialFragment newInstance(final String val) {
        TutorialFragment tutorialFragment = new TutorialFragment();
        Bundle bundle = new Bundle();
        bundle.putString(COUNT, val);
        tutorialFragment.setArguments(bundle);
        return tutorialFragment;
    }

    /**
     * @param inflater           View to be inflate into the Fragment
     * @param container          container of views
     * @param savedInstanceState savedInstanceState
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        switch (getArguments().getString(COUNT)) {
            case TUTORIAL_BUNDLE_FIRST:
                ivMainImage.setImageResource(R.drawable.phone);
                tvHead.setText("TIMETABLE");
                tvHeader.setText("Get to know the classes, only a click away \n and get rid of fussy image timetable");
                break;
            case TUTORIAL_BUNDLE_SECOND:
                ivMainImage.setImageResource(R.drawable.todo);
                tvHead.setText("CALENDER OF EVENTS");
                tvHeader.setText(" Schedule your time for exams! \n Plan your vacations! Be in the know!!");
                break;
            case TUTORIAL_BUNDLE_THIRD:
                ivMainImage.setImageResource(R.drawable.chlkpd);
                tvHead.setText("CHALKPAD");
                tvHeader.setText(" Now access chalkpad on the go,\n anytime, anywhere!");
                break;
            default:
                break;
        }
    }


    /**
     * initialised;
     *
     * @param view root view;
     */
    private void init(final View view) {
        tvHead= (TextView) view.findViewById(R.id.tv_head);
        tvHeader = (TextView) view.findViewById(R.id.tv_header);
        ivMainImage = (ImageView) view.findViewById(R.id.iv_main_image);
    }
}
