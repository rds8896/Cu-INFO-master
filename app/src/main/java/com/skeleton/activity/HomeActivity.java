package com.skeleton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.fragment.AboutDev;
import com.skeleton.fragment.Chalkpad;
import com.skeleton.fragment.HomeFragment;
import com.skeleton.fragment.MapFragment;
import com.skeleton.fragment.TimeTable;
import com.skeleton.fragment.VideoLec;
import com.skeleton.util.Util;
import com.skeleton.util.dialog.CustomAlertDialog;

public class HomeActivity extends BaseActivity {
    private TextView tvHeading;
    DrawerLayout drawerLayout;
    private RelativeLayout rlBack;
    private boolean onBackPressedClicked = false;
    private android.support.v4.app.FragmentManager fragmentManager;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        tvHeading = (TextView) findViewById(R.id.tvHeading);
        fragmentManager = getSupportFragmentManager();
        rlBack = (RelativeLayout) findViewById(R.id.rlBackButton);
        rlBack.setOnClickListener(this);
        setHome();

    }

    @Override
    public void onClick(final View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rlBackButton:
                drawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.tvHome:
                HomeFragment homeFragment = new HomeFragment();
                setFragment(homeFragment, "Home");
                break;
            case R.id.tvWayToc:
                MapFragment mapFragment = new MapFragment();
                setFragment(mapFragment, "Track");
                break;
            case R.id.tvChkp:
                Chalkpad chalkpad = new Chalkpad();
                setFragment(chalkpad, "Chalkpad");
                break;
            case R.id.TvVideoLec:
                VideoLec videoLec = new VideoLec();
                setFragment(videoLec, "Tutorials");
                break;
            case R.id.tvTimeTable:
                TimeTable timeTable = new TimeTable();
                setFragment(timeTable, "Time Table");
                break;
            case R.id.tvAboutUs:
                AboutDev  aboutDev = new AboutDev();
                setFragment(aboutDev, "Developers");
                break;
            case R.id.tvLogout:
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                Intent home = new Intent(HomeActivity.this,SplashActivity.class);

                System.exit(0);
                startActivity(home);


                break;
            default:
        }
    }

    /**
     * set home fragment
     */
    private void setHome() {
        tvHeading.setText("Home");
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ll, homeFragment);
        fragmentTransaction.commit();


    }

    private void setFragment(final Fragment fragment, final String title) {

        if (Util.isNetworkAvailable(this)) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.ll, fragment);
            fragmentTransaction.commit();
            tvHeading.setText(title);
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            showAlertDialog(getString(R.string.error_internet_not_connected));
        }
    }


    /**
     * @param msg of alert dialog
     */
    private void showAlertDialog(final String msg) {
        new CustomAlertDialog.Builder(HomeActivity.this)
                .setMessage(msg)
                .setPositiveButton(R.string.text_ok, null)
                .show();
    }


    @Override
    public void onBackPressed() {
        onBackClicked();
    }

    /**
     * show alert on back press
     */
    private void onBackClicked() {
        if (onBackPressedClicked) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);

        } else {
            Toast.makeText(HomeActivity.this, R.string.press_again_to_exit, Toast.LENGTH_SHORT).show();
        }

        onBackPressedClicked = true;

        Handler handlerTimer = new Handler();
        handlerTimer.postDelayed(new Runnable() {
            public void run() {
                onBackPressedClicked = false;
            }
        }, 1000);

    }
}
