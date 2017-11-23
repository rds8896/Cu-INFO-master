package com.skeleton.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.adapter.RamanAdapter;
import com.skeleton.database.CommonData;
import com.skeleton.model.raman.DataObj;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.CommonParams;
import com.skeleton.retrofit.CommonResponse;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.StringUtil;
import com.skeleton.util.customview.MaterialEditText;
import com.skeleton.util.dialog.CustomAlertDialog;

public class GetSetTaskActivity extends BaseActivity {

    RamanAdapter ramanAdapter;
    private TextView tvHeading;
    private MaterialEditText tvId, name;
    private EditText task;
    private LinearLayout llScreen1, llScreen2;
    private String date, id;
    private boolean isNew;
    private DataObj dataObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_set_task);
        init();
        getData();
    }

    private void getData() {
        date = getIntent().getStringExtra("a");
        isNew = getIntent().getBooleanExtra("b", false);
        id = getIntent().getStringExtra("C");
        if (isNew) {
            llScreen1.setVisibility(View.GONE);
            llScreen2.setVisibility(View.VISIBLE);
            tvHeading.setText("Welcome");
            tvId.setText(id);
        } else {
            hit();
        }

    }

    /**
     * hit to get data
     */
    private void hit() {
        try {
            id = String.valueOf(CommonData.getData().getData().get(0).getUniqueId());
        } catch (Exception ignored) {

        }

        RestClient.getApiInterface().getData(date, id).enqueue(new ResponseResolver<DataObj>() {
            @Override
            public void success(DataObj dataObj) {
                CommonData.savaData(dataObj);
                dataObj = CommonData.getData();
                tvHeading.setText(StringUtil.toCamelCase(dataObj.getData().get(0).getName()));
                ramanAdapter.setData(dataObj.getData());
                llScreen1.setVisibility(View.VISIBLE);
                llScreen2.setVisibility(View.GONE);
            }

            @Override
            public void failure(final APIError error) {

            }
        });
    }

    private void init() {
        ImageView ivHamburger = (ImageView) findViewById(R.id.ivHamburger);
        ivHamburger.setVisibility(View.GONE);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvRaman);
        tvHeading = (TextView) findViewById(R.id.tvHeading);
        llScreen1 = (LinearLayout) findViewById(R.id.llScreen1);
        llScreen2 = (LinearLayout) findViewById(R.id.llScreen2);
        tvId = (MaterialEditText) findViewById(R.id.id);
        name = (MaterialEditText) findViewById(R.id.name);
        task = (EditText) findViewById(R.id.task);
        /*
         * detailed Options Adapter
         */
        ramanAdapter = new RamanAdapter(this);
        recyclerView.setAdapter(ramanAdapter);
        final LinearLayoutManager linearLayoutManagerNormal = new LinearLayoutManager(GetSetTaskActivity.this);
        recyclerView.setLayoutManager(linearLayoutManagerNormal);
    }

    @Override
    public void onClick(final View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.save:
                addTaskApiHit();
                break;
            case R.id.Add:
                setScreenData();
                llScreen2.setVisibility(View.VISIBLE);
                break;
            default:
        }
    }

    private void setScreenData() {
        dataObj = CommonData.getData();
        name.setText(StringUtil.toCamelCase(dataObj.getData().get(0).getName()));
        name.setKeyListener(null);
        tvId.setText(String.valueOf(dataObj.getData().get(0).getUniqueId()));
        tvId.setKeyListener(null);
    }

    /**
     * Api hit to add task for the selected date
     */
    private void addTaskApiHit() {
        id = tvId.getText().toString();
        CommonParams params = new CommonParams.Builder()
                .add("startDate", date)
                .add("name", name.getText().toString())
                .add("text", task.getText().toString())
                .add("uniqueId", tvId.getText().toString()).build();
        RestClient.getApiInterface().addData("en", params.getMap())
                .enqueue(new ResponseResolver<CommonResponse>(this, true, true) {
                    @Override
                    public void success(final CommonResponse commonResponse) {
                        DataObj dataObj = commonResponse.toResponseModel(DataObj.class);
                        Toast.makeText(GetSetTaskActivity.this, "Added", Toast.LENGTH_SHORT).show();
                        hit();

                    }

                    @Override
                    public void failure(final APIError error) {
                        showAlertDialog(error.getMessage());

                    }
                });
    }

    /**
     * @param msg of alert dialog
     */
    private void showAlertDialog(final String msg) {
        new CustomAlertDialog.Builder(GetSetTaskActivity.this)
                .setMessage(msg)
                .setPositiveButton(R.string.text_ok, null)
                .show();

    }
}
