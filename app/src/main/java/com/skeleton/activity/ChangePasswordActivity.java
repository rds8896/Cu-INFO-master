package com.skeleton.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.CommonParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.retrofit.RetrofitUtils;
import com.skeleton.util.Util;
import com.skeleton.util.ValidateEditText;
import com.skeleton.util.dialog.CustomAlertDialog;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class ChangePasswordActivity extends BaseActivity {

    private static final String TAG = "ChangePasswordActivity";
    private AppCompatEditText etOldPassword, etNewPassword, etToken;
    private AppCompatButton btnSave;
    private TextView tvWarning;
    private RelativeLayout relativeLayout;
    private AppCompatImageView ivPasswordShowHide;
    private boolean flag;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();
    }

    /**
     * initialization
     */
    private void init() {
        ivPasswordShowHide = (AppCompatImageView) findViewById(R.id.iv_eye_active_inactive);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar);
        btnSave = (AppCompatButton) findViewById(R.id.btn_save);
        etNewPassword = (AppCompatEditText) findViewById(R.id.met_new_password);
        etToken = (AppCompatEditText) findViewById(R.id.met_token);
        tvWarning = (TextView) findViewById(R.id.met_warning);
        etOldPassword = (AppCompatEditText) findViewById(R.id.met_oldpassword);
        btnSave.setOnClickListener(this);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl_eye_visible);
        relativeLayout.setOnClickListener(this);
        etToken.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (s.toString().trim().equals(etNewPassword.getText().toString().trim())) {
                    tvWarning.setVisibility(View.VISIBLE);
                    tvWarning.setText(R.string.password_matched);
                    flag = true;
                } else {
                    tvWarning.setText(R.string.error_password_mismatch);
                }
            }
        });
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                if (ValidateEditText.checkPassword(etNewPassword, false)
                        && !ValidateEditText.genericEmpty(etToken) && flag) {
                    changePassword();
                }
                break;
            case R.id.rl_eye_visible:
                if (ivPasswordShowHide.getDrawable().getConstantState()
                        == (ContextCompat.getDrawable(this, R.drawable.ic_action_visible_off).getConstantState())) {
                    // show password
                    etNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivPasswordShowHide.setImageResource(R.drawable.ic_action_visible);
                } else {
                    // hide password
                    etNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivPasswordShowHide.setImageResource(R.drawable.ic_action_visible_off);
                }
                break;
            default:
                break;
        }
    }


    /**
     * change Password API hit
     */
    private void changePassword() {
//        CommonParams commonParams = new CommonParams.Builder()
//                .add(TOKEN, etToken.getText().toString().trim())
//                .add(NEW_PASSWORD, etNewPassword.getText().toString().trim())
//                .build();
//        RestClient.getApiInterface().resetPassword(DEFAULT_LANGUAGE, commonParams.getMap())
//                .enqueue(new ResponseResolver<CommonParams>(ChangePasswordActivity.this, true) {
//                    @Override
//                    public void success(final CommonParams commonParams) {
//                        Util.showToast(ChangePasswordActivity.this,
//                                ChangePasswordActivity.this.getString(R.string.text_password_updated));
//                    }
//
//                    @Override
//                    public void failure(final APIError error) {
//                        new CustomAlertDialog.Builder(ChangePasswordActivity.this)
//                                .setMessage(error.getMessage())
//                                .setCancelable(true)
//                                .show();
//                    }
//                });
        CommonParams commonParams = new CommonParams.Builder()
                .add(OLD_PASSWORD, etOldPassword.getText().toString().trim())
                .add(NEW_PASSWORD, etNewPassword.getText().toString().trim())
                .build();
        RestClient.getApiInterface().changePassword(RetrofitUtils.getHeaderMap(true), commonParams.getMap())
                .enqueue(new ResponseResolver<CommonParams>(ChangePasswordActivity.this, true) {
                    @Override
                    public void success(final CommonParams commonParams) {
                        Util.showToast(ChangePasswordActivity.this, getString(R.string.text_password_updated));
                    }

                    @Override
                    public void failure(final APIError error) {
                        new CustomAlertDialog.Builder(ChangePasswordActivity.this)
                                .setMessage(error.getMessage())
                                .setCancelable(true)
                                .show();
                    }
                });

    }

}
