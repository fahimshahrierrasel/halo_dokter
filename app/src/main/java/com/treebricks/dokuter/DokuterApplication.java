package com.treebricks.dokuter;

import android.app.Application;
import android.content.res.Resources;

public class DokuterApplication extends Application {
    private static final String TAG = "DokuterApplication";

    private static DokuterApplication mInstance;
    private static Resources res;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        res = getResources();
    }

    public static DokuterApplication getInstance() {
        return mInstance;
    }

    public static Resources getResourses() {
        return res;
    }

}
