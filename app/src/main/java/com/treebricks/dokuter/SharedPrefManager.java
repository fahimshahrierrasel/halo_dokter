package com.treebricks.dokuter;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by TRI-MATRIK on 3/3/2018.
 */

public class SharedPrefManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    // Shared preferences file name
    private static final String PREF_NAME = "dokuter";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_LOGGED_ID = "IsLoggedIn";

    SharedPrefManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.apply();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
        editor.apply();
    }
    public void setLoggedInStatus(boolean loggedIn)
    {
        editor.putBoolean(IS_LOGGED_ID, loggedIn);
        editor.commit();
        editor.apply();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public boolean isLoggedIn()
    {
        return pref.getBoolean(IS_LOGGED_ID, false);
    }
}
