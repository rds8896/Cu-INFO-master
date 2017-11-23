package com.skeleton.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.phlox.datepick.CalendarNumbersView;
import com.phlox.datepick.CalendarPickerView;
import com.skeleton.R;
import com.skeleton.activity.GetSetTaskActivity;
import com.skeleton.database.CommonData;
import com.skeleton.model.raman.DataObj;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.DateTimeUtil;

import java.util.Calendar;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep+++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class HomeFragment extends BaseFragment {
    private CalendarPickerView calendarPickerView;
    private String m_Text = "";
    private Context contextt;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        calendarPickerView = (CalendarPickerView) view.findViewById(R.id.datePicker);
        calendarPickerView.setListener(new CalendarNumbersView.DateSelectionListener() {
            @Override
            public void onDateSelected(final Calendar selectedDate) {
                RestClient.getApiInterface().getData(DateTimeUtil.getDateToDisplay(selectedDate.getTime()), m_Text).enqueue(new ResponseResolver<DataObj>() {
                    @Override
                    public void success(final DataObj commonParams) {
                        CommonData.savaData(commonParams);
                        if (commonParams.getData().size() > 0) {
                            Toast.makeText(contextt, "Information Available", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(contextt, GetSetTaskActivity.class)
                                    .putExtra("a", DateTimeUtil.getDateToDisplay(selectedDate.getTime()))
                                    .putExtra("b", false).putExtra("c", m_Text));
                        } else {
                            Toast.makeText(contextt, "No Information Available for the selected Date", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(contextt, GetSetTaskActivity.class)
                                    .putExtra("a", DateTimeUtil.getDateToDisplay(selectedDate.getTime()))
                                    .putExtra("b", true).putExtra("c", m_Text));

                        }
                    }

                    @Override
                    public void failure(final APIError error) {
                        Toast.makeText(contextt, error.getMessage(), Toast.LENGTH_SHORT).show();
                        showAlert();
                    }
                });

            }


        });
        return view;
    }

    private void showAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(contextt);
        builder.setTitle("Your College Id");

        final EditText input = new EditText(contextt);
        input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()

        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
            }
        });
        builder.show();
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);

    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        contextt = context;

    }
}
