package com.skeleton.util.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.skeleton.R;

/**
 * +++++++++++++++++++++++++++++++++
 * ++++++++++Click labs ++++++++++++
 * +++++++++++++++++++++++++++++++++
 */

public class BookingRequestDialog extends BaseDialogFragment {

    private Context context;
    private TextView tvTime, tvCustomer, tvAddress, tvInfo;
    private ImageView ivCall, ivLocation;
    private Button btAccept, btDecline;

    /**
     *
     * @return Dialog object
     */
    public static BookingRequestDialog getInstance() {
        BookingRequestDialog dialog = new BookingRequestDialog();
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_booking_request, container, false);
        init(view);
        return view;
    }

    /**
     *
     * @param view Initialise View
     */
    private void init(final View view) {
        tvTime = (TextView) view.findViewById(R.id.tv_booking_dialog_time);
        tvCustomer = (TextView) view.findViewById(R.id.tv_booking_dialog_customer);
        tvAddress = (TextView) view.findViewById(R.id.tv_booking_dialog_address);
        tvInfo = (TextView) view.findViewById(R.id.tv_booking_dialog_info);
        ivCall = (ImageView) view.findViewById(R.id.iv_booking_dialog_call);
        ivLocation = (ImageView) view.findViewById(R.id.iv_booking_dialog_location);
        btAccept = (Button) view.findViewById(R.id.bt_booking_dialog_accept);
        btDecline = (Button) view.findViewById(R.id.bt_booking_dialog_decline);
        ivCall.setOnClickListener(this);
        ivLocation.setOnClickListener(this);
        btAccept.setOnClickListener(this);
        btDecline.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.iv_booking_dialog_call:
                break;
            case R.id.iv_booking_dialog_location:
                break;
            case R.id.bt_booking_dialog_accept:
                break;
            case R.id.bt_booking_dialog_decline:
                break;
            default:
        }
    }
}
