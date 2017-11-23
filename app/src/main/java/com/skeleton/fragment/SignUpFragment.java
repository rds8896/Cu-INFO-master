package com.skeleton.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.InputFilter;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.CountryPickerListener;
import com.skeleton.R;
import com.skeleton.activity.OTPActivity;
import com.skeleton.activity.TermsConditions;
import com.skeleton.util.EditTextUtil;
import com.skeleton.util.Log;
import com.skeleton.util.Util;
import com.skeleton.util.ValidateEditText;
import com.skeleton.util.dialog.CustomAlertDialog;

import static android.app.Activity.RESULT_OK;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep+++++++++++
 * +++++++++++++++++++++++++++++++
 **/
public class SignUpFragment extends BaseFragment {

    private static final int MAX_NUMBER_LENGTH = 15;
    private static final String TAG = "SignUpFragment";
    private static final int PLACE_PICKER_REQUEST = 999;
    private AppCompatEditText etName, etMobileNumber, etPassword, etPasswordConfirm, etEmail, etUserName, etLastName;
    private AppCompatButton btnContinue;
    private TextView tvCountryCode, tvTermsAndConditions;
    private AppCompatImageView ivPasswordShowHide;
    private RelativeLayout relativeLayout;
    private ImageView ivPlacePicker;
    private boolean isLocationSet;
    private LatLng mLocation;

    /**
     * Creating Instance of Fragment
     *
     * @return Instance of Fragment
     */
    public static Fragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_fragment, container, false);
        init(view);
        return view;
    }

    private void init(final View view) {
        tvTermsAndConditions = (TextView) view.findViewById(R.id.tv_terms_and_conditions);
        ivPasswordShowHide = (AppCompatImageView) view.findViewById(R.id.iv_eye_active_in_active);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_eye_visible_signup);
        etPasswordConfirm = (AppCompatEditText) view.findViewById(R.id.etPassword2);
        etName = (AppCompatEditText) view.findViewById(R.id.et_name);
        //ivPlacePicker = (ImageView) view.findViewById(R.id.iv_add_location);
        etLastName = (AppCompatEditText) view.findViewById(R.id.et_last_name);
        etUserName = (AppCompatEditText) view.findViewById(R.id.et_user_name);
        etMobileNumber = (AppCompatEditText) view.findViewById(R.id.et_phone_number);
        etMobileNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_NUMBER_LENGTH)});
        etEmail = (AppCompatEditText) view.findViewById(R.id.etEmail);
        etPassword = (AppCompatEditText) view.findViewById(R.id.etPassword);
        btnContinue = (AppCompatButton) view.findViewById(R.id.btn_continue);
        tvCountryCode = (TextView) view.findViewById(R.id.tv_country_code);
       // ivPlacePicker.setOnClickListener(this);
        tvCountryCode.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);
        tvTermsAndConditions.setOnClickListener(this);
        EditTextUtil.addPasswordPatternWatcher(etPassword);
        EditTextUtil.addTextMatchWatcher(etPasswordConfirm, etPassword);
    }


    @Override
    public void onClick(final View v) {
        removeSpace(etMobileNumber);
        switch (v.getId()) {
            case R.id.btn_continue:
                if (ValidateEditText.checkName(etName, true)
                        && ValidateEditText.checkName(etLastName, false)
                        && ValidateEditText.checkEmail(etEmail)
                        && ValidateEditText.checkPassword(etPassword, false)
                        && ValidateEditText.genericEmpty(etUserName, Util.getStringRes(R.string.error_name_field_empty))) {
                    if (isValidMobile(etMobileNumber.getText().toString().trim())) {
                        signup();


                    } else {
                        new CustomAlertDialog.Builder(getActivity()).setMessage(R.string.error_invalid_phone_number).show();
                    }
                }
                break;
            case R.id.tv_country_code:
                countryCodePicker();
                break;
            case R.id.rl_eye_visible_signup:
                if (ivPasswordShowHide.getDrawable().getConstantState()
                        == (ContextCompat.getDrawable(getContext(), R.drawable.ic_action_visible_off).getConstantState())) {
                    // show password
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivPasswordShowHide.setImageResource(R.drawable.ic_action_visible);
                    Log.e("Error", "active");
                } else {
                    // hide password
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivPasswordShowHide.setImageResource(R.drawable.ic_action_visible_off);
                    //
                    Log.e("Error", "inactive");
                }
                break;
            case R.id.tv_terms_and_conditions:
                termsAndConditions();
                break;
//            case R.id.iv_add_location:
//                openPlacePicker();
//                break;
            default:
                break;
        }
    }

    /**
     * remove all spaces in Number
     *
     * @param textView TV
     */
    private void removeSpace(final TextView textView) {
        textView.setText(textView.getText().toString().replace(" ", ""));
        textView.invalidate();
    }

    /**
     */
//    private void openPlacePicker() {
//        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//        try {
//            startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
//        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
//            Log.d(TAG, "GooglePlayServicesRepairableException exception");
//        }
//    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PLACE_PICKER_REQUEST) {
                Place place = PlacePicker.getPlace(getContext(), data);
                mLocation = place.getLatLng();
                isLocationSet = true;
                ivPlacePicker.setImageResource(R.drawable.ic_done_circle);
            }
        }

    }

    /**
     * terms and Conditions
     */
    private void termsAndConditions() {
        startActivity(new Intent(getActivity(), TermsConditions.class));
    }


    /**
     * country code Picker
     */
    private void countryCodePicker() {
        // dialog title
        final CountryPicker picker = CountryPicker.newInstance("Select Country");
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(final String name,
                                        final String code,
                                        final String dialCode,
                                        final int flagDrawableResID) {
                tvCountryCode.setText(dialCode);
                picker.dismiss();

            }
        });
        picker.show(getFragmentManager(), "COUNTRY_PICKER");
    }


    /**
     * @param phone phone
     * @return boolean value
     */
    private boolean isValidMobile(final String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }


    /**
     * sign up API hit
     */
    private void signup() {
//        CommonParams commonParams = new CommonParams.Builder()
//                .add(EMAIL, etEmail.getText().toString().trim())
//                .add(PASSWORD, etPassword.getText().toString().trim())
//                .add(FIRST_NAME, etName.getText().toString().trim())
//                .add(LAST_NAME, etLastName.getText().toString().trim())
//                .add(USER_NAME, etUserName.getText().toString().trim())
//                .add(MOBILE, etMobileNumber.getText().toString().trim())
//                .add(COUNTRY_CODE, tvCountryCode.getText().toString())
//                .add(DEVICE_TOKEN, AppConstant.DEVICE_TYPE)
//                .add(LATITUDE, "12.972442")
//                .add(LONGITUDE, "77.580643")
//                .add(MCNUMBER, "123456")
//                .add(ApiKeyConstant.DEVICE_TYPE, AppConstant.DEVICE_TYPE)
//                .build();
//        RestClient.getApiInterface().signUpDriver(RetrofitUtils.getHeaderMap(false), commonParams.getMap())
//                .enqueue(new ResponseResolver<ResponseSignUp>(getActivity(), true) {
//                    @Override
//                    public void success(final ResponseSignUp responseSignUp) {
//                        CommonData.saveAccessToken("bearer " + responseSignUp.getData().getToken());
//                        Log.e("Error", "bearer " + responseSignUp.getData().getToken());



        Intent intent = new Intent(getActivity(), OTPActivity.class);
                        intent.putExtra(MOBILE, etMobileNumber.getText().toString().trim());
                        startActivity(intent);
//                    }
//
//                    @Override
//                    public void failure(final APIError error) {
//                        new CustomAlertDialog.Builder(getActivity()).setMessage(error.getMessage()).show();
//                    }
//                });
//        Log.e("Error", "Test2");
    }

}
