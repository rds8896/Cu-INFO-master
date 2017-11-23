package com.skeleton.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.skeleton.R;
import com.skeleton.locationLib.location.LocationConfig;
import com.skeleton.util.Log;
import com.skeleton.util.customview.ProgressDialog;
import com.skeleton.util.googledirections.DirectionPolylinesCallback;
import com.skeleton.util.googledirections.GoogleDirectionsCallback;
import com.skeleton.util.googledirections.GoogleDirectionsMap;
import com.skeleton.util.googledirections.googledirectionmodel.DirectionsResponse;
import com.skeleton.util.googledirections.googledirectionmodel.PolylineResponse;

import java.util.List;

/**
 * Created by Raman on 4/11/17.
 */

public class MapFragment extends LocationBaseFragment implements
        OnMapReadyCallback, GoogleDirectionsCallback, DirectionPolylinesCallback {
    private LatLng src, des, currentLoc;
    private Double cLAt = 30.5176;
    private Double cLng = 76.6595;
    Location locations;
    private GoogleMap mgoogleMap;
    private com.google.android.gms.maps.model.Polyline currentPolyline;
    private Context contextt;
    private TextView tvName, tvETA;
    private BitmapDescriptor valetIcon, userIcon, locIcon;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        init(view);
        return view;
    }

    private void init(final View view) {
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        supportMapFragment.getMapAsync(this);
        valetIcon = BitmapDescriptorFactory.fromResource(R.drawable.valet_loc);
        userIcon = BitmapDescriptorFactory.fromResource(R.drawable.meeting_loc);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvETA = (TextView) view.findViewById(R.id.tvETA);
    }

    @Override
    public void onLocationUpdate(final Location location) {
        locations = location;
        currentLoc = new LatLng(location.getLatitude(), location.getLongitude());
        setupMap();
    }

    @Override
    public LocationConfig setLocationConfig() {
        return new LocationConfig().setRepeated(false).setTimeInterval(100);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mgoogleMap = googleMap;
        if (locations != null) {
            if (ActivityCompat.checkSelfPermission(contextt, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(contextt, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mgoogleMap.setMyLocationEnabled(true);
            setupMap();

        }

    }

    private void setupMap() {
        src = new LatLng(locations.getLatitude(), locations.getLongitude());
        des = new LatLng(cLAt, cLng);
        GoogleDirectionsMap googleDirectionsCallback = new GoogleDirectionsMap.Builder(contextt)
                .setSourceLatLan(getS(locations.getLatitude()) + " " + getS(locations.getLongitude())).setDestinationLatLan(getS(cLAt) + " " + getS(cLng))
                .setGoogleDirectionsCallback(this)
                .setDirectionPolylinesCallback(this)
                .build();
        mgoogleMap.addMarker(new MarkerOptions().position(des).icon(userIcon));
        mgoogleMap.addMarker(new MarkerOptions().position(currentLoc).icon(locIcon));
    }


    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        contextt = context;

    }

    private String getS(Double x) {
        return String.valueOf(x);
    }

    @Override
    public void onSuccess(final DirectionsResponse response) {
        tvName.setText("Your destination is " + String.valueOf(response.getDistance()) + " Kms");
        tvETA.setText("Your ETA is " + response.getTime());

    }

    @Override
    public void onFailure(final String errorMsgApiError) {

    }

    @Override
    public void polylineDirections(final PolylineResponse polylineResponse) {
        plotPolyLines(polylineResponse.getPolylineDirectionLatLngs(), 6, String.valueOf(R.color.colorPrimaryDark));
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(src);
        builder.include(des);
        for (int i = 0; i < polylineResponse.getPolylineDirectionLatLngs().size(); i++) {
            builder.include(polylineResponse.getPolylineDirectionLatLngs().get(i));
        }

        final LatLngBounds bounds = builder.build();
        bounds.getCenter();
        mgoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
    }

    /**
     * @param latLngs       latlngs
     * @param polylineWidth width
     * @param colorPoly     color
     */
    private void plotPolyLines(final List<LatLng> latLngs, final double polylineWidth, final String colorPoly) {
        if (currentPolyline != null) {
            currentPolyline.remove();
        }
        Log.e("Error", colorPoly);
        currentPolyline = mgoogleMap.addPolyline(new PolylineOptions()
                .addAll(latLngs)
                .width((float) polylineWidth)
                .color(R.color.colorAccent));
        currentPolyline.setVisible(true);
        ProgressDialog.dismissProgressDialog();

    }


}
