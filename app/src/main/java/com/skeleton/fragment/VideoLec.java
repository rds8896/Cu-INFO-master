package com.skeleton.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skeleton.R;

/**
 * Created by Raman on 7/11/17.
 */

public class VideoLec extends BaseFragment {
    private LinearLayout tut1;
    private Context context;
    private TextView C_Lang;
    private TextView Cpp_Lang;
    private TextView DS;
    private TextView Java;

    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        init(view);
        return view;
    }

    public void play_Video(String url)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + url)));
    }

    /**
     * init
     * @param view view
     */
    private void init(View view) {
        C_Lang = (TextView) view.findViewById(R.id.clang);
        C_Lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                play_Video("-CpG3oATGIs");
            }
        });
        Cpp_Lang = (TextView) view.findViewById(R.id.cpp);
        Cpp_Lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                play_Video("MhYECGUzdA4");
            }
        });
        Java = (TextView) view.findViewById(R.id.java);
        Java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                play_Video("qQVqfvs3p48");
            }
        });
        DS = (TextView) view.findViewById(R.id.ds);
        DS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                play_Video("92S4zgXN17o");
            }
        });
    }

    @Override
    public void onAttach(final Context contextt) {
        super.onAttach(context);
        context = contextt;
    }


}
