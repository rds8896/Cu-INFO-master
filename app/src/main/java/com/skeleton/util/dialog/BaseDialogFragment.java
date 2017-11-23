package com.skeleton.util.dialog;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by alex on 26/7/17.
 */

public class BaseDialogFragment extends DialogFragment implements View.OnClickListener {

    private static final double DIALOG_WIDTH_PREF = 0.95;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        final Window window = getDialog().getWindow();
        final Point point = new Point();
        final Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(point);
        window.setLayout((int) (point.x * DIALOG_WIDTH_PREF), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        super.onResume();
    }

    @Override
    public void onClick(final View v) {

    }
}
