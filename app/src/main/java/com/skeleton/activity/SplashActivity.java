package com.skeleton.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.skeleton.BuildConfig;
import com.skeleton.R;
import com.skeleton.database.CommonData;
import com.skeleton.fcm.FCMTokenInterface;
import com.skeleton.fcm.MyFirebaseInstanceIdService;
import com.skeleton.retrofit.ApiInterface;
import com.skeleton.util.Log;
import com.skeleton.util.RootUtil;
import com.skeleton.util.Util;
import com.skeleton.util.dialog.CustomAlertDialog;

import java.io.File;
import java.io.IOException;

/**
 * +++++++++++++++++++++++++++++++
 * ++++++++Raman Deep+++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class SplashActivity extends BaseActivity implements FCMTokenInterface, View.OnClickListener {
    private static final String TAG = SplashActivity.class.getName();
    private Dialog mDialog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        deletedLogFile();
        if (RootUtil.isDeviceRooted()) {
            mDialog = new CustomAlertDialog.Builder(this)
                    .setTitle(getString(R.string.msg_warning))
                    .setMessage(getString(R.string.msg_device_rooted))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.text_ok),
                            new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                                @Override
                                public void onClick() {
                                    mDialog.dismiss();
                                    init();
                                }
                            }).show();
        } else {
            init();

        }
    }

    /**
     * initialization
     */
    private synchronized void init() {

        //finding button view
        if (!Util.isNetworkAvailable(SplashActivity.this)) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mDialog = new CustomAlertDialog.Builder(SplashActivity.this)
                    .setMessage(R.string.error_internet_not_connected)
                    .setPositiveButton(R.string.text_retry, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                        @Override
                        public void onClick() {
                            init();
                        }
                    })
                    .setNegativeButton(getString(R.string.text_exit), new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                        @Override
                        public void onClick() {
                            finish();
                        }
                    })
                    .show();
            return;
        }
        if (!checkPlayServices()) {
            return;
        }
        MyFirebaseInstanceIdService.setCallback(this);

//        // handling selection of build flavor & selected build specifications
//        if (BuildConfig.IS_UNIVERSAL) {
//            selectBuildFlavor();
//        } else {
//            startActivity(new Intent(SplashActivity.this, TestClassLocation.class));
//        }
        /*Enable dev options if application is universal build.*/
        if (BuildConfig.IS_UNIVERSAL) {
            final AppCompatTextView tvAppServer = (AppCompatTextView) findViewById(R.id.tvAppServer);
            tvAppServer.setVisibility(View.VISIBLE);
            tvAppServer.setText(CommonData.getApplicationType());
            tvAppServer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final AlertDialog.Builder serverOptions = new AlertDialog.Builder(SplashActivity.this);
                    serverOptions.setItems(new String[]{"Dev", "Test", "Live"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialogInterface, final int i) {
                            switch (i) {
                                case 0:
                                    CommonData.saveApplicationServer(ApiInterface.DEV_SERVER);
                                    CommonData.saveApplicationType("Dev");
                                    break;

                                case 1:
                                    CommonData.saveApplicationServer(ApiInterface.TEST_SERVER);
                                    CommonData.saveApplicationType("Test");
                                    break;

                                case 2:
                                    CommonData.saveApplicationServer(ApiInterface.LIVE_SERVER);
                                    CommonData.saveApplicationType("Live");
                                    break;

                                default:
                                    break;

                            }
                            addWaterMark();
                            tvAppServer.setText(CommonData.getApplicationType());
                            //                           invalidateView(((MyApplication) getApplicationContext()).waterView);
//                            tvAppServer.setText(getServerDetails());
                        }
                    });
                    serverOptions.setTitle("Application Server");
                    serverOptions.setNegativeButton("Cancel", null);
                    serverOptions.show();
                }
            });
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_SCREEN_OVERLAY) {
            if (Settings.canDrawOverlays(this)) {
                init();
            }
        } else if (requestCode == REQ_CODE_PLAY_SERVICES_RESOLUTION
                && resultCode == Activity.RESULT_OK) {
            init();
        } else if (requestCode == REQUEST_CODE_PLAY_STORE) {
            /*
             *  Call the app version check method here after api call
             * it accepts two parameters critical version and latest version of the app
             *  for e.g
             *  <code>checkVersion(mAppVersion.getCriticalAndroidVersion(), mAppVersion.getLatestAndroidVersion());</code>
             */
            Log.e("Error", "Checkstyle Error");
        }
    }

    /**
     * @return true if google play service present & updated false if not presented or not updated
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, REQ_CODE_PLAY_SERVICES_RESOLUTION)
                        .show();
            } else {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
                mDialog = new CustomAlertDialog.Builder(SplashActivity.this)
                        .setMessage(R.string.error_device_not_supported)
                        .setPositiveButton(R.string.text_ok, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                            @Override
                            public void onClick() {
                                finish();
                            }
                        })
                        .show();
            }
            return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onTokenReceived(final String token) {
        if (isStoragePermissionGranted()) {

            //"MyAppTAG:V" sets the priority level for all tags to Verbose (lowest priority)
            //"*:S" sets the priority level for all tags to "silent"
            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + ADD_LOGS;
            try {
                Runtime.getRuntime().exec(new String[]{"logcat", "-v", "time", "-f", filePath, "MyAppTAG:V", "*:E"});
                Log.e("Error", "Success Save file");
            } catch (IOException e) {
                Log.e("Error", "Failure save file");
                e.printStackTrace();
            }
            //Runtime.getRuntime().freeMemory();
        }
        startActivity(new Intent(this, TutorialActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onFailure() {
        if (isFinishing()) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                /**
                 * in failure make another attempt to get device token
                 * or finish activity which in turn finish application.
                 */
                MyFirebaseInstanceIdService.retry(SplashActivity.this);
            }
        });
    }

    /**
     * @return boolean value of permission overlay
     */
    public boolean checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQ_CODE_SCREEN_OVERLAY);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Call this function in success of the app version API
     *
     * @param criticalAndroidVersion critical version of the app
     * @param latestAndroidVersion   latest version of the app
     */
    private void checkVersion(final int criticalAndroidVersion, final int latestAndroidVersion) {
        if (criticalAndroidVersion > BuildConfig.VERSION_CODE) {
            new CustomAlertDialog.Builder(SplashActivity.this)
                    .setTitle(R.string.update_found)
                    .setMessage(R.string.critical_update_msg)
                    .setPositiveButton(R.string.update, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                        @Override
                        public void onClick() {
                            openPlayStore();
                        }
                    })
                    .setNegativeButton(R.string.text_exit, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                        @Override
                        public void onClick() {
                            finish();
                        }
                    })
                    .setCancelable(false)
                    .show();

        } else if (latestAndroidVersion > BuildConfig.VERSION_CODE) {
            new CustomAlertDialog.Builder(SplashActivity.this)
                    .setTitle(R.string.update_found)
                    .setMessage(R.string.critical_update_msg)
                    .setPositiveButton(R.string.update, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                        @Override
                        public void onClick() {
                            openPlayStore();
                        }
                    })
                    .setNegativeButton(R.string.cancel, new CustomAlertDialog.CustomDialogInterface.OnClickListener() {
                        @Override
                        public void onClick() {
                            afterAppVersionCheck();
                        }
                    })
                    .setCancelable(false)
                    .show();
        } else {
            afterAppVersionCheck();
        }
    }

    /**
     * App flow starts from here
     */
    private void afterAppVersionCheck() {
        /* Write your code here*/
        startActivity(new Intent(this, TestLocationActivity.class));
    }

    /**
     * Method for opening the app on play store
     */
    private void openPlayStore() {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + getPackageName()));
        try {
            startActivityForResult(intent, REQUEST_CODE_PLAY_STORE);
        } catch (final Exception e) {
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
        }
    }

    /**
     * @return return boolean value true/false permission granted or not
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= SDK_MIN) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG, "Permission Granted");
                return true;
            } else {
                Log.e(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    /**
     * delete the log file
     */
    private void deletedLogFile() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + ADD_LOGS);
        file.delete();
    }

}
