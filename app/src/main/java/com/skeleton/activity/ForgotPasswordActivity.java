package com.skeleton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.CommonParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.ValidateEditText;
import com.skeleton.util.dialog.CustomAlertDialog;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class ForgotPasswordActivity extends BaseActivity {
    private static final int REQ_MAIL_CLIENT = 201;
    private Toolbar toolbar;
    private AppCompatEditText metEmail;
    private AppCompatButton btnResendOTP;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);
        init();
    }

    /**
     * initialization
     */
    private void init() {
        metEmail = (AppCompatEditText) findViewById(R.id.met_email);
        btnResendOTP = (AppCompatButton) findViewById(R.id.btn_forgot_password);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar);

        btnResendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (ValidateEditText.checkEmail(metEmail)) {
                    RestClient.getApiInterface().forgotPassword(DEFAULT_LANGUAGE, metEmail.getText().toString())
                            .enqueue(new ResponseResolver<CommonParams>(ForgotPasswordActivity.this, true, false) {
                                @Override
                                public void success(final CommonParams commonParams) {
                                    new CustomAlertDialog.Builder(ForgotPasswordActivity.this)
                                            .setMessage(DIALOG_FORGOT_PASSWORD)
                                            .setPositiveButton(R.string.text_ok, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick() {
                                                    Intent intent = new Intent(Intent.ACTION_MAIN);
                                                    intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                                                    if (intent.resolveActivity(getPackageManager()) != null) {
                                                        startActivity(Intent.createChooser(intent, "Select one"));
                                                    } else {
                                                        Toast.makeText(ForgotPasswordActivity.this, "No Mail Client", Toast.LENGTH_SHORT).show();
                                                    }
                                                    finish();
                                                }
                                            })
                                            .show();
                                }

                                @Override
                                public void failure(final APIError error) {
                                    Log.e("Error", "onFailure in Forgot Password");
                                    new CustomAlertDialog.Builder(ForgotPasswordActivity.this).setMessage(error.getMessage())
                                            .show();

                                }
                            });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        Log.d("alex", "" + requestCode);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
