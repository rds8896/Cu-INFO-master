package com.skeleton.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.facebook.CallbackManager;
import com.skeleton.R;
import com.skeleton.fragment.TutorialFragment;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Raman Deep++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class TutorialActivity extends BaseActivity {
    private static final long ANIM_VIEWPAGER_DELAY = 3000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 6000;
    private Runnable animateSlider;
    private android.os.Handler handler;
    private boolean stopSliding = false;
    private CallbackManager mCallbackManager;
    private AppCompatButton btnRegister, btnLogin;


    //private TextView tvSkip;
    private ViewPager viewPager;
    private ArrayList<Fragment> arrayListFragment = new ArrayList<>();
    private PagerAdapter myPagerAdapter;
    private CircleIndicator circleIndicator;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_tutorial);
        animateLayout();
        init();
    }


    /**
     * On SignIn/SignUp Button Pressed
     *
     * @param view view
     */
    @Override
    public void onClick(final View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        switch (view.getId()) {
            case R.id.btn_login:
                intent.putExtra(KEY, LOG_IN);
                startActivity(intent);
                break;
            case R.id.btn_register:
                intent.putExtra(KEY, SIGN_UP);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * multiple instances of the same fragment;
     */
    private void initPaging() {
        //add fragment
        arrayListFragment.add(TutorialFragment.newInstance(TUTORIAL_BUNDLE_FIRST));
        arrayListFragment.add(TutorialFragment.newInstance(TUTORIAL_BUNDLE_SECOND));
        arrayListFragment.add(TutorialFragment.newInstance(TUTORIAL_BUNDLE_THIRD));
        setSlider();
    }


    /**
     * initialised
     */
    private void init() {
        btnLogin = (AppCompatButton) findViewById(R.id.btn_login);
        btnRegister = (AppCompatButton) findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        mCallbackManager = CallbackManager.Factory.create();
        viewPager = (ViewPager) findViewById(R.id.view_pager_tutorial);
        circleIndicator = (CircleIndicator) findViewById(R.id.dot_indicator);
        initPaging();
        //set sliders for splash screen
    }


    /**
     * Method to set splash slider
     */
    private void setSlider() {
        myPagerAdapter = new com.skeleton.adapter.PagerAdapter(getSupportFragmentManager(), arrayListFragment);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction()) {

                    case MotionEvent.ACTION_CANCEL:
                        break;

                    case MotionEvent.ACTION_UP:
                        // calls when touch release on ViewPager
                        if (arrayListFragment != null && arrayListFragment.size() != 0) {
                            stopSliding = false;
                            runnable(arrayListFragment.size());
                            handler.postDelayed(animateSlider, ANIM_VIEWPAGER_DELAY_USER_VIEW);
                        }
                        break;

                    case MotionEvent.ACTION_MOVE:
                        // calls when ViewPager touch
                        if (handler != null && !stopSliding) {
                            stopSliding = true;
                            handler.removeCallbacks(animateSlider);
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        //mIndicator.setOnPageChangeListener(new PageChangeListener());
        circleIndicator.setViewPager(viewPager);
    }


    /**
     * Runnable.
     *
     * @param size the size
     */
    public void runnable(final int size) {
        handler = new android.os.Handler();
        animateSlider = new Runnable() {
            public void run() {
                if (!stopSliding) {
                    if (viewPager.getCurrentItem() == size - 1) {
                        viewPager.setCurrentItem(0, true);
                    } else {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    }
                    handler.postDelayed(animateSlider, ANIM_VIEWPAGER_DELAY);

                }
            }
        };

    }


    @Override
    public void onResume() {
        runnable(arrayListFragment.size());
        //Re-run callback
        handler.postDelayed(animateSlider, ANIM_VIEWPAGER_DELAY);
        super.onResume();
    }

    @Override
    public void onPause() {
        if (handler != null) {
            //Remove callback
            handler.removeCallbacks(animateSlider);
        }
        super.onPause();
    }

    /**
     * animate Layout
     */
    private void animateLayout() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_animate);
                Animation slideUp = AnimationUtils.loadAnimation(TutorialActivity.this, R.animator.slide_up);
                relativeLayout.startAnimation(slideUp);
            }
        });
    }
}
