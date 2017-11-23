package com.skeleton;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.skeleton.util.Foreground;

import io.paperdb.Paper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

//import com.squareup.leakcanary.LeakCanary;

/**
 * +++++++++++++++++++++++++++++++
 * +++++++++Click labs +++++++++++
 * +++++++++++++++++++++++++++++++
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;
//    private Socket mSocket;
//
//    {
//        try {
//            mSocket = IO.socket(BuildConfig.BASE_URL + "chat");
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }


    /**
     * @return instance of MyApplication
     */
    public static MyApplication getApplication() {
        return myApplication;
    }

    /**
     * Getter to access Singleton instance
     *
     * @return instance of MyApplication
     */
    public static Context getAppContext() {
        return getApplication().getApplicationContext();
    }

//    /**
//     * @return mSocket
//     */
//    public Socket getSocket() {
//        return mSocket;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(this);
        Foreground.init(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Avenir.ttc")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        // Setup singleton instance
        myApplication = this;

        //leak canary
        /*if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
        */
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d("clab", "<<<<<<<<<<<-----------on low memory called -------------->>>>>>>>>>>");
    }

}
