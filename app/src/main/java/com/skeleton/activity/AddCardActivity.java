package com.skeleton.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.util.Log;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep+++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class AddCardActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, TextWatcher {

    private static final char SPACE = '-';
    private EditText etCardNumber, etCardDatePicker, etCVV;
    private TextView tvTitle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        init();

    }

    /**
     * initialization
     */
    private void init() {

        etCardDatePicker = (EditText) findViewById(R.id.et_card_date_picker);
        tvTitle = (TextView) findViewById(R.id.toolbar_title);
        etCVV = (EditText) findViewById(R.id.et_cvv);
        tvTitle.setText(R.string.toolbar_title_add_card);
        etCardNumber = (EditText) findViewById(R.id.et_card_details);
        etCardNumber.addTextChangedListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar);
        etCardDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                datePicker();
            }
        });
    }

    @Override
    public void onDateSet(final DatePicker view, final int year, final int month, final int dayOfMonth) {

    }

    @Override
    public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

    }

    @Override
    public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {

    }

    @Override
    public void afterTextChanged(final Editable s) {
        // Remove spacing char
        if (s.length() > 0 && (s.length() % 5) == 0) {
            final char c = s.charAt(s.length() - 1);
            if (SPACE == c) {
                s.delete(s.length() - 1, s.length());
            }
        }
        // Insert char where needed.
        if (s.length() > 0 && (s.length() % 5) == 0) {
            final char c = s.charAt(s.length() - 1);

            // Only if its a digit where there should be a space ,we insert a space
            if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(SPACE)).length <= 3) {
                s.insert(s.length() - 1, String.valueOf(SPACE));
            }
        }

//        // setting card type image
//        if (s.length() > 0 && s.charAt(0) == 4) {
//            // for visa card
//            etCardNumber.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.visa_logo, 0);
//        } else if (s.length() > 0 && s.charAt(0) == 5) {
//            // for master card
//            etCardNumber.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.visa_logo, 0);
//
//        } else if (s.length() == 0) {
//            etCardNumber.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
//        }
    }


    private DatePickerDialog createDialogWithoutDateField() {
        DatePickerDialog dpd = new DatePickerDialog(this, null, YEAR, MONTH, DAY);
        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals(DATE_PICKER)) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.e("Error", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
        return dpd;
    }

    private void datePicker() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(final DatePicker view, final int year, final int month, final int dayOfMonth) {
                String expire = String.valueOf(month) + "/" + String.valueOf(year);
                etCardDatePicker.setText(expire);
            }
        }, YEAR, MONTH, DAY);
        dialog.show();
    }

}
