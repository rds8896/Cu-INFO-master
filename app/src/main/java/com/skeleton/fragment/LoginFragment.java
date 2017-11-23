package com.skeleton.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.activity.ForgotPasswordActivity;
import com.skeleton.activity.HomeActivity;
import com.skeleton.util.ValidateEditText;

import io.paperdb.Paper;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep+++++++++++
 * +++++++++++++++++++++++++++++++
 **/
public class LoginFragment extends BaseFragment {

    private static final String USER_ID = "id";
    private static final String USER_PASS = "pass";
    private TextView tvForgotPassword;
    private AppCompatButton btnLogin;
    private AppCompatCheckBox checkBox;
    private AppCompatEditText etPassword;
    private RelativeLayout relativeLayout;
    private AppCompatAutoCompleteTextView acEmail;
    private Context context;
    private AppCompatImageView ivPasswordShowHide;
    private boolean isLoginByMail;

    /**
     * Creating Instance of Fragment
     *
     * @return Instance of Fragment
     */
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        context = getContext();
        init(view);
        return view;
    }

    /**
     * initialization
     *
     * @param view
     */
    private void init(final View view) {
        ivPasswordShowHide = (AppCompatImageView) view.findViewById(R.id.iv_eye_active_inactive);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_eye_visible);
        tvForgotPassword = (TextView) view.findViewById(R.id.tvForgot_password);
        btnLogin = (AppCompatButton) view.findViewById(R.id.btnLogin);
        acEmail = (AppCompatAutoCompleteTextView) view.findViewById(R.id.etEmail);
        etPassword = (AppCompatEditText) view.findViewById(R.id.etPassword);
        checkBox = (AppCompatCheckBox) view.findViewById(R.id.cb_rememberme);
        attachSuggestionToEmail(acEmail);
        btnLogin.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);
    }

    /**
     * Check weather login by email or Mobile
     *
     * @return boolean
     */
    private boolean checkEmailNumber() {
        if (!acEmail.getText().toString().trim().matches(REGEX_NUMERIC_STRING)) {
            isLoginByMail = true;
            return ValidateEditText.checkEmail(acEmail);
        }
        isLoginByMail = false;
        return ValidateEditText.checkPhoneNumber(acEmail);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (checkEmailNumber()
                        && ValidateEditText.checkPassword(etPassword, false)) {
                    login();
                }
                break;
            case R.id.tvForgot_password:
                forgotPassword();
                break;
            case R.id.rl_eye_visible:
                if (ivPasswordShowHide.getDrawable().getConstantState()
                        == (ContextCompat.getDrawable(getContext(), R.drawable.ic_action_visible_off).getConstantState())) {
                    // show password
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivPasswordShowHide.setImageResource(R.drawable.ic_action_visible);
                } else {
                    // hide password
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivPasswordShowHide.setImageResource(R.drawable.ic_action_visible_off);
                }
                break;
            default:
                break;
        }
    }

    private void attachSuggestionToEmail(final AppCompatAutoCompleteTextView textView) {
        final String[] id = {Paper.book(LOGIN_CREDENTIALS).read(USER_ID, "")};
        if (id.length > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, id);
            textView.setAdapter(adapter);
            textView.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
                @Override
                public void onDismiss() {
                    etPassword.setText(Paper.book(LOGIN_CREDENTIALS).read(USER_PASS, ""));
                    etPassword.invalidate();
                }
            });
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * method to save user credential when check Remember_Me
     */
    private void saveLoginInfo() {
        String email = acEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        Paper.book(LOGIN_CREDENTIALS).write(USER_ID, email);
        Paper.book(LOGIN_CREDENTIALS).write(USER_PASS, password);
    }

    /**
     * forgot password
     */
    private void forgotPassword() {
        startActivity(new Intent(getActivity(), ForgotPasswordActivity.class));
    }

    /**
     * login With Email and Password
     */
    private void login() {
//        Log.d("clab", "utc offset :" + DateTimeUtil.getCurrentZoneOffset());
//        CommonParams.Builder commonBuild = new CommonParams.Builder()
//                .add(PASSWORD, etPassword.getText().toString().trim())
//                .add(DEVICE_TOKEN, CommonData.getFCMToken())
//                .add(ROLE, ROLE_TYPE)
//                .add(APP_VERSION, BuildConfig.VERSION_NAME)
//                .add(ApiKeyConstant.DEVICE_TYPE, APP_TYPE)
//                .add(isLoginByMail ? EMAIL : MOBILE_NUMBER, acEmail.getText().toString().trim());
//        RestClient.getApiInterface().login(RetrofitUtils.getHeaderMap(false), commonBuild.build().getMap())
//                .enqueue(new ResponseResolver<ResponseLogin>(getActivity(), true, false) {
//                    @Override
//                    public void success(final ResponseLogin responseLogin) {
//                        CommonData.saveAccessToken("bearer " + responseLogin.getData().getToken());
//                        Log.e("Error", "bearer " + responseLogin.getData().getToken());
//                        if (checkBox.isChecked()) {
//                            saveLoginInfo();
//                        }
        startActivity(new Intent(context, HomeActivity.class));
//                    }
//
//                    @Override
//                    public void failure(final APIError error) {
//                        new CustomAlertDialog.Builder(context).setMessage(error.getMessage()).show();
//                    }
//                });
//    }
    }
}
