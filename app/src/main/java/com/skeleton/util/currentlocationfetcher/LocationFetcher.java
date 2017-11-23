package com.skeleton.util.currentlocationfetcher;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.skeleton.constant.AppConstant;
import com.skeleton.util.Log;
import com.skeleton.util.permissionhelper.MPermissionHelper;

/**
 * fetch current location
 */
public final class LocationFetcher extends Thread implements AppConstant {
    /**
     * Stores the types of location services the client is interested in using. Used for checking
     * settings to determine if the device has optimal location settings.
     */
    private LocationSettingsRequest locationSettingsRequest;
    /**
     * Stores parameters for requests to the FusedLocationProviderApi.
     */

    private LocationRequest locationRequest;
    /**
     * Callback for Location events.
     */
    private LocationCallback locationCallback;
    /**
     * callback to handle location and time events
     */
    private LocationFetcherCallBack locationFetcherCallBack;
    /**
     * the status of the location updates request
     */
    private boolean mRequestingLocationUpdates = true;
    /**
     * Provides access to the Fused Location Provider API.
     */
    private FusedLocationProviderClient fusedLocationClient;
    /**
     * Provides access to the Location Settings API.
     */
    private SettingsClient settingsClient;


    /**
     * @param builder object of Builder
     */
    public LocationFetcher(final Builder builder) {
        locationFetcherCallBack = builder.mLocationFetcherCallBack;
        /**
         * Provides the entry point to the Fused Location Provider API.
         */
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(builder.mContext);
        settingsClient = LocationServices.getSettingsClient(builder.mContext);

        /**
         * Creates a callback for receiving location.
         */
        locationCallback = new LocationCallback() {

            @Override
            public void onLocationResult(final LocationResult locationResult) {
                android.util.Log.e("76", "success");
                super.onLocationResult(locationResult);
                Location mCurrentLocation = locationResult.getLastLocation();
                if (mCurrentLocation != null) {
                    android.util.Log.e("success", String.valueOf(mCurrentLocation.getLatitude()));
                    locationFetcherCallBack.onSuccess(mCurrentLocation);
                } else {
                    locationFetcherCallBack.onFailure("error!");
                }
                //Log.e("79", "success");

            }

        };

        /**
         * Sets up the location request.Android has two location request settings:
         * {@code ACCESS_COARSE_LOCATION} and {@code ACCESS_FINE_LOCATION}. These settings control
         * the accuracy of the current location.
         */

        locationRequest = new LocationRequest();
        // Sets the desired interval for active location updates.
        locationRequest.setInterval(builder.mTimeInterval);
        /**
         * used for checking if a device has the needed location settings.
         */
        LocationSettingsRequest.Builder builder1 = new LocationSettingsRequest.Builder();
        builder1.addLocationRequest(locationRequest);
        locationSettingsRequest = builder1.build();
        // getLastLocation(mFusedLocationClient, builder.mContext, builder.mLocationFetcherCallBack);
        if (checkPermissions(builder.mContext)) {
            startLocationUpdates(settingsClient, locationSettingsRequest, fusedLocationClient
                    , locationRequest);
        } else {
            requestPermissions(builder.mContext);
        }

    }

    /**
     * Requests location updates from the FusedLocationApi.
     *
     * @param mSettingsClient          Provides access to the Location Settings API.
     * @param mLocationSettingsRequest Stores the types of location services the client is interested in using.
     * @param mFusedLocationClient     Provides access to the Fused Location Provider API.
     * @param mlocationrequest         Stores parameters for requests to the FusedLocationProviderApi.
     */

    private void startLocationUpdates(final SettingsClient mSettingsClient,
                                      final LocationSettingsRequest mLocationSettingsRequest,
                                      final FusedLocationProviderClient mFusedLocationClient,
                                      final LocationRequest mlocationrequest) {
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest).addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull final Task<LocationSettingsResponse> task) {
                if (task.isSuccessful()) {
                    //noinspection MissingPermission
                    mFusedLocationClient.requestLocationUpdates(mlocationrequest,
                            locationCallback, Looper.myLooper());

                } else {
                    // Task failed with an exception
                    Exception exception = task.getException();

                }
            }
        });
//                .addOnSuccessListener((Activity) mContext,
//                        new OnSuccessListener<LocationSettingsResponse>() {
//                            @Override
//                            public void onSuccess(final LocationSettingsResponse locationSettingsResponse) {
//
//                                //noinspection MissingPermission
//                                mFusedLocationClient.requestLocationUpdates(mlocationrequest,
//                                        locationCallback, Looper.myLooper());
//
//
//                            }
//                        })
//                .addOnFailureListener((Activity) mContext, new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull final Exception e) {
//                        Log.i("148", "");
//
//                    }
//                });
    }

    /**
     * Removes location updates from the FusedLocationApi.
     *
     * @param context context
     */
    private void stopLocationUpdates(final Context context) {
        if (!mRequestingLocationUpdates) {
            return;
        }
        fusedLocationClient.removeLocationUpdates(locationCallback)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull final Task<Void> task) {
                        mRequestingLocationUpdates = false;

                    }
                });
    }

    /**
     * @param context context
     * @return permission state
     */
    private boolean checkPermissions(final Context context) {
        int permissionState = ActivityCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * @param context context
     */

    private void requestPermissions(final Context context) {
        final String stringRationale = " necessary to show location ";

//        boolean shouldProvideRationale =
//                ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
//                        Manifest.permission.ACCESS_FINE_LOCATION);
//        if (shouldProvideRationale) {
        Log.e("success", "Displaying permission rationale to provide additional context.");

        MPermissionHelper.requestPermissions((Activity) context, stringRationale, REQUEST_PERMISSIONS_REQUEST_CODE
                , new String[]{Manifest.permission.ACCESS_FINE_LOCATION});

        if (MPermissionHelper.hasPermissions(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION})) {
            startLocationUpdates(settingsClient, locationSettingsRequest, fusedLocationClient
                    , locationRequest);
//
//            }
//            ActivityCompat.requestPermissions((Activity) context,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    REQUEST_PERMISSIONS_REQUEST_CODE);

        }
    }


    /**
     * Called when a permissions request has been completed.
     *
     * @param permissions  permissions argument to permission result
     * @param requestCode  requestCode argument to permission result
     * @param grantResults grantResults argument to permission result
    //     */
//    @Override
//    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions,
//                                           @NonNull final int[] grantResults) {
//        Log.i("141", "onRequestPermissionResult");
//        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
//            if (grantResults.length <= 0) {
//                // If user interaction was interrupted, the permission request is cancelled and you
//                // receive empty arrays.
//                Log.i("146", "User interaction was cancelled.");
//            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                if (mRequestingLocationUpdates) {
//                    Log.i("150", "Permission granted, updates requested, starting location updates");
//                    startLocationUpdates(settingsClient, locationSettingsRequest, fusedLocationClient
//                            , locationRequest);
//
//                } else {
//                    Log.i("222", "display app setting");
//                 // displays the App settings screen.
//                }
//            }
//        }
//    }


    /**
     * Builder Location Fetcher
     */
    public static class Builder {
        private int mTimeInterval;
        private LocationFetcherCallBack mLocationFetcherCallBack;
        private Context mContext;


        /**
         * @param context Context
         */
        public Builder(final Context context) {
            this.mContext = context;
        }

        /**
         * @param mTime time interval
         * @return this
         */
        public Builder setTimeInterval(final int mTime) {
            this.mTimeInterval = mTime;
            return this;
        }

        /**
         * @param locationFetcherCallBack callback LocationFetcherCallBack
         * @return this
         */
        public Builder setLocationFetcherCallBack(final LocationFetcherCallBack locationFetcherCallBack) {
            this.mLocationFetcherCallBack = locationFetcherCallBack;
            return this;
        }


        /**
         * @return return the builder context
         */
        public LocationFetcher build() {
            return new LocationFetcher(this);
        }


    }

}
