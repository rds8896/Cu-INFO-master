package com.skeleton.activity;

import android.accounts.Account;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookException;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.skeleton.MyApplication;
import com.skeleton.R;
import com.skeleton.constant.ApiKeyConstant;
import com.skeleton.constant.AppConstant;
import com.skeleton.database.CommonData;
import com.skeleton.fragment.LoginFragment;
import com.skeleton.fragment.SignUpFragment;
import com.skeleton.model.modelsociallogin.ResponseSocialLogin;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.CommonParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.Util;
import com.skeleton.util.dialog.CustomAlertDialog;
import com.skeleton.util.facebookutil.FacebookManager;
import com.skeleton.util.facebookutil.FacebookResponseHandler;
import com.skeleton.util.facebookutil.SocialUserDetails;

import java.io.IOException;
import java.util.ArrayList;

import io.paperdb.Paper;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep+++++++++++
 * +++++++++++++++++++++++++++++++
 **/
public class WelcomeActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener,
        GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 1001;
    private static final String TAG = "WelcomeActivity";
    private static final int REQ_CODE_REGISTER = 1002;
    private static final int TOKEN_REQUEST = 5001;
    private static final int PLACE_PICKER_REQUEST = 101;
    private static final int GOOGLE_LOGIN = 1;
    private static final int FB_LOGIN = 2;
    private FacebookManager facebookManager;
    private CallbackManager callbackManager;
    private AppCompatButton btnFacebookLogin;
    private SignInButton btnGoogleSignIn;
    private GoogleApiClient mGoogleApiClient;
    private boolean isFacebookSignIn = false;
    private GoogleSignInResult googleSignInResult;
    private GoogleSignInOptions gso;
    private RadioButton rbLogin, rbSignUp;
    private FrameLayout fl1, fl2;
    private String checkOptionSelect;
    private RadioGroup rgLoginSignUp;
    private AppCompatImageView ivCenterLogo;
    private String token;
    private AsyncGetAuthToken asyncTokenRequest;
    private GoogleSignInAccount googleSignInAccount;
    private SocialUserDetails fbUserDetails;
    private ArrayList<Drawable> list = new ArrayList<>();
    private LatLng mLocation;
    private int loginby;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        init();
    }


    /**
     * @param fragment   fragment
     * @param replaceTry replace
     */
    private void replaceFragment(final Fragment fragment, final int replaceTry) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(replaceTry, fragment);
        fragmentTransaction.commit();
    }

    /**
     * initilization
     */
    private void init() {
        ivCenterLogo = (AppCompatImageView) findViewById(R.id.iv_logo_welcome);
        ivCenterLogo.setImageResource(R.mipmap.juspayicon);
        callbackManager = CallbackManager.Factory.create();
        facebookManager = new FacebookManager(this);
       // btnGoogleSignIn = (SignInButton) findViewById(R.id.btnSignInwithGoogle);
       // btnFacebookLogin = (AppCompatButton) findViewById(R.id.btnLoginWithFacebook);
        googleLoginSetUp();
        //btnFacebookLogin.setOnClickListener(this);
        //btnGoogleSignIn.setOnClickListener(this);
        rbLogin = (RadioButton) findViewById(R.id.rb_login);
        rbSignUp = (RadioButton) findViewById(R.id.rb_create_account);
        fl1 = (FrameLayout) findViewById(R.id.fl_create_account);
        fl2 = (FrameLayout) findViewById(R.id.fl_login);
        rbLogin.setOnCheckedChangeListener(this);
        rbSignUp.setOnCheckedChangeListener(this);
        String s = getIntent().getStringExtra(KEY);
        if (s.equals(SIGN_UP)) {
            showSignUp();
            rbSignUp.setChecked(true);
        } else {
            rbLogin.setChecked(true);
            showLogin();
        }
    }


    /**
     * @param requestCode request code
     * @param resultCode  result code
     * @param data        intent;
     */
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //result returned after launching the google+ signin intent;
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_REGISTER
                && resultCode == RESULT_OK) {
            this.setResult(RESULT_OK, this.getIntent());
            this.finish();
        }

        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "onActivityResult :");
            //to get result from intent;
            googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d(TAG, "status : " + googleSignInResult.getStatus() + "/ issuccess :" + googleSignInResult.isSuccess());
            signinIntentHandling(googleSignInResult);
        }

        if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {
            Place place = PlacePicker.getPlace(this, data);
            mLocation = place.getLatLng();
            socialLogIn(loginby);
        }

        if (requestCode == TOKEN_REQUEST && resultCode == RESULT_OK) {
            asyncTokenRequest = new AsyncGetAuthToken();
            asyncTokenRequest.execute(googleSignInAccount.getAccount());
        }
    }

    /**
     * handling google signin result
     *
     * @param result result from intent;
     */
    private void signinIntentHandling(final GoogleSignInResult result) {
        if (result.isSuccess()) {
            //signed in with success;
            googleSignInAccount = result.getSignInAccount();
            Log.e("Name", googleSignInAccount.getDisplayName() + " ");
            Log.e("Email", googleSignInAccount.getEmail() + " ");
            Log.e("Error", googleSignInAccount.getIdToken() + " ");
            Log.e("Error", googleSignInAccount.getId() + " ");
            asyncTokenRequest = new AsyncGetAuthToken();
            asyncTokenRequest.execute(googleSignInAccount.getAccount());
//            if (googleSignInAccount.getPhotoUrl() != null) {
//                Log.e("Error", googleSignInAccount.getPhotoUrl().toString());
//
        } else {
            Log.d(TAG, "google sign in fail :");
        }

    }

    /**
     *
     */
    private class AsyncGetAuthToken extends AsyncTask<Account, Void, String> {

        @Override
        protected String doInBackground(final Account... params) {
            String scopes = "oauth2:https://www.googleapis.com/auth/plus.login";
            try {
                token = GoogleAuthUtil.getToken(WelcomeActivity.this, params[0], scopes);
            } catch (IOException e) {
                Log.d(TAG, e.toString());
            } catch (UserRecoverableAuthException e) {
                startActivityForResult(e.getIntent(), TOKEN_REQUEST);
            } catch (GoogleAuthException e) {
                Log.d(TAG, e.toString());
            }
            return token;

        }

        @Override
        protected void onPostExecute(final String s) {
            if (token != null) {
                Log.d(TAG, "token :" + token);
                openPlacePicker(GOOGLE_LOGIN);
            }
        }
    }

    @Override
    protected void onPause() {
        if (asyncTokenRequest != null && asyncTokenRequest.getStatus().equals(AsyncTask.Status.RUNNING)) {
            asyncTokenRequest.cancel(true);
        }
        super.onPause();
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
           /*// case R.id.btnLoginWithFacebook:
                if (AccessToken.getCurrentAccessToken() != null) {
                    LoginManager.getInstance().logOut();
                }
                facebookLogin();
                break;
           // case R.id.btnSignInwithGoogle:
                googleLogin();
                break;*/
            default:
                break;
        }
    }


    /**
     * LoginWith Google+
     */

    private void googleLogin() {
        if (mGoogleApiClient != null) {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }

    /**
     */
    private void openPlacePicker(final int calledBy) {
        this.loginby = calledBy;
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Log.d(TAG, "GooglePlayServicesRepairableException exception");
        }
    }


    /**
     * facebook login
     */
    private void facebookLogin() {
        if (Util.isNetworkAvailable(this)) {
            facebookManager.getFbUserDetails(callbackManager, new FacebookResponseHandler() {

                @Override
                public void onSuccess(final SocialUserDetails userDetails) {
                    fbUserDetails = userDetails;
                    Log.e("Error", fbUserDetails.getId());
                    Log.e("Error", AccessToken.getCurrentAccessToken().getToken());
                    openPlacePicker(FB_LOGIN);
                }

                @Override
                public void onCancel(final String msg) {
                    Log.e("Error", "cancel");
                }

                @Override
                public void onError(final FacebookException e) {
                    Log.e("Error", "Exception");
                }
            });
        } else {
            Log.e("Error", "Network Connection Failure");
        }
    }

    private void socialLogIn(final int thirdParty) {
        if (thirdParty == FB_LOGIN) {
            CommonParams commonParams = new CommonParams.Builder()
                    .add(ROLE, ROLE_TYPE)
                    .add(APP_VERSION, VERSION)
                    .add(DEVICE_TOKEN, DEVICE_TOKEN)
                    .add(ApiKeyConstant.DEVICE_TYPE, AppConstant.DEVICE_TYPE)
                    .add(SOCIAL_TYPE, FACEBOOK)
//                    .add(LATITUDE, mLocation.latitude)
//                    .add(LONGITUDE, mLocation.longitude)
                    .add(SOCIAL_ID, fbUserDetails.getId())
                    .add(ACCESS_TOKEN, AccessToken.getCurrentAccessToken().getToken())
                    .build();
            RestClient.getApiInterface().socialLogin(DEFAULT_LANGUAGE, commonParams.getMap())
                    .enqueue(new ResponseResolver<ResponseSocialLogin>(WelcomeActivity.this, true, false) {
                        @Override
                        public void success(final ResponseSocialLogin responseSocialLogin) {
                            CommonData.saveAccessToken("bearer "
                                    + responseSocialLogin.getData().getToken());
                            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                        }

                        @Override
                        public void failure(final APIError error) {
                            new CustomAlertDialog.Builder(WelcomeActivity.this).setMessage(error.getMessage()).show();
                        }
                    });
        }
        if (thirdParty == GOOGLE_LOGIN) {
            CommonParams commonParams = new CommonParams.Builder()
                    .add(ROLE, ROLE_TYPE)
                    .add(APP_VERSION, VERSION)
                    .add(DEVICE_TOKEN, DEVICE_TOKEN)
                    .add(ApiKeyConstant.DEVICE_TYPE, AppConstant.DEVICE_TYPE)
                    .add(SOCIAL_TYPE, GOOGLE)
//                    .add(LATITUDE, mLocation.latitude)
//                    .add(LONGITUDE, mLocation.longitude)
                    .add(SOCIAL_ID, googleSignInAccount.getId())
                    .add(ACCESS_TOKEN, token)
                    .build();
            RestClient.getApiInterface().socialLogin(DEFAULT_LANGUAGE, commonParams.getMap())
                    .enqueue(new ResponseResolver<ResponseSocialLogin>(WelcomeActivity.this, true, false) {
                        @Override
                        public void success(final ResponseSocialLogin responseSocialLogin) {
                            CommonData.saveAccessToken("bearer "
                                    + responseSocialLogin.getData().getToken());
                            Paper.book().write(SOCIAL_LOGIN_BY, GOOGLE);
                            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                        }

                        @Override
                        public void failure(final APIError error) {
                            new CustomAlertDialog.Builder(WelcomeActivity.this).setMessage(error.getMessage()).show();

                        }
                    });
        }
    }

    /**
     * check for users cached credentials;
     * if valid google signinresult is made available instantly;
     */
    @Override
    protected void onStart() {
        super.onStart();
//        if (mGoogleApiClient != null) {
//            OptionalPendingResult<GoogleSignInResult> optionalPendingResult = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
//            //for silent signin ;
//            if (optionalPendingResult.isDone()) {
//                Toast.makeText(this, "Cached Signin", Toast.LENGTH_SHORT).show();
//                GoogleSignInResult result = optionalPendingResult.get();
//                signinIntentHandling(result);
//            } else {
//                optionalPendingResult.setResultCallback(new ResultCallback<GoogleSignInResult>() {
//                    @Override
//                    public void onResult(@NonNull final GoogleSignInResult result) {
//                        signinIntentHandling(result);
//                    }
//                });
//            }
//        }
    }


    /**
     * Login with
     */
    private void googleLoginSetUp() {
// Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
//
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                // .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .requestIdToken(KEY_REQUEST_TOKEN_GOOGLE)
                .requestEmail()
                .build();
        buildGoogleApiClient();
    }


    /**
     * google Api client
     */
    private void buildGoogleApiClient() {
        //enable auto manage requires context of activity or frag, on connection failed listener;
        mGoogleApiClient = new GoogleApiClient.Builder(MyApplication.getAppContext())
                .addOnConnectionFailedListener(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }


    @Override
    public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.rb_login:
                if (rbLogin.isChecked()) {
                    rbSignUp.setChecked(false);
                }
                showLogin();
                break;
            case R.id.rb_create_account:
                if (rbSignUp.isChecked()) {
                    rbLogin.setChecked(false);
                }
                showSignUp();
                break;
            default:
                break;
        }
    }


    /**
     * show Login
     */
    private void showLogin() {
        fl1.setVisibility(View.GONE);
        fl2.setVisibility(View.VISIBLE);
        replaceFragment(LoginFragment.newInstance(), R.id.fl_login);
    }


    /**
     * show sign Up
     */
    private void showSignUp() {
        fl2.setVisibility(View.GONE);
        fl1.setVisibility(View.VISIBLE);
        replaceFragment(SignUpFragment.newInstance(), R.id.fl_create_account);
    }


    @Override
    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.stopAutoManage(this);
        mGoogleApiClient.disconnect();
    }

}
