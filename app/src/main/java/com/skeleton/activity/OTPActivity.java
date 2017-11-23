package com.skeleton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.util.EditTextUtil;

import java.util.Objects;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class OTPActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatButton btnVerfiy, btnResendOTP;
    private AppCompatEditText metOTP1, metOTP2, metOTP3, metOTP4;
    private AppCompatTextView tvPhoneNumber;
    private String mobileNumber;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_activity);
        init();
    }


    /**
     * initialization
     */
    private void init() {
        mobileNumber = getIntent().getStringExtra(MOBILE);
        btnVerfiy = (AppCompatButton) findViewById(R.id.btn_verify);
        btnResendOTP = (AppCompatButton) findViewById(R.id.btn_resendOTP);
        metOTP1 = (AppCompatEditText) findViewById(R.id.etOTP1);
        metOTP2 = (AppCompatEditText) findViewById(R.id.etOTP2);
        metOTP3 = (AppCompatEditText) findViewById(R.id.etOTP3);
        metOTP4 = (AppCompatEditText) findViewById(R.id.etOTP4);
        tvPhoneNumber = (AppCompatTextView) findViewById(R.id.tv_phone_number);
        btnResendOTP.setOnClickListener(this);
        btnVerfiy.setOnClickListener(this);
        EditTextUtil.moveFrontAndBack(1, metOTP1, metOTP2, metOTP3, metOTP4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar);
        tvPhoneNumber.setText(getString(R.string.text_otp).concat(getIntent().getStringExtra(MOBILE)));
        metOTP4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                if (s.length() > 1) {
                    metOTP4.setText(String.valueOf(s.charAt(0)));
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {

            }
        });
    }


    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn_verify:
                verifyOTP();
                break;
            case R.id.btn_resendOTP:
                resendOTP();
                break;
            default:
                break;
        }
    }

    /**
     * server call to resend OTP
     */
    private void resendOTP() {
        Toast.makeText(this, "OTP has been sent to your registered number", Toast.LENGTH_LONG).show();
    }


    /**
     * server call verify OTP
     */
    private void verifyOTP() {
        String otp = metOTP1.getText().toString() + metOTP2.getText().toString()
                + metOTP3.getText().toString()
                + metOTP4.getText().toString();
        if (otp.length() < 4 || !Objects.equals(otp, "1111")) {
            Toast.makeText(this, "Please enter correct otp", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(OTPActivity.this, HomeActivity.class));
        }
    }
}

//    int optConvert = Integer.parseInt(otp);
//        Log.e("Error",mobileNumber);
//    JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty(MOBILE,mobileNumber);
//        jsonObject.addProperty(OTPCODE,optConvert);
//
////        CommonParams commonParams = new CommonParams.Builder().add("body", jsonObject)
////                .build();
//
//        RestClient.getApiInterface().
//
//    verifyOTP(RetrofitUtils.getHeaderMap(true),jsonObject)
//            .
//
//    enqueue(new ResponseResolver<CommonResponse>(this, true,false) {
//        @Override
//        public void success ( final CommonResponse commonResponse){
//            startActivity(new Intent(OTPActivity.this, HomeActivity.class));
//
//        }
//
//        @Override
//        public void failure ( final APIError error){
//            new CustomAlertDialog.Builder(OTPActivity.this).setMessage(error.getMessage()).show();
//
//        }
//    });
//}
//
//
//}
