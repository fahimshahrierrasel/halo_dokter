package com.treebricks.dokuter.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by TRI-MATRIK on 3/3/2018.
 */

public class AppPreferenceManager {
    // Shared preferences file name
    private static final String PREF_NAME = "dokuter";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_LOGGED_ID = "IsLoggedIn";
    private static final String PATIENT_UID = "PatientUID";
    private static final String PATIENT_ID = "PATIENT_ID";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public AppPreferenceManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.apply();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
        editor.apply();
    }

    public void setLoggedInStatus(boolean loggedIn) {
        editor.putBoolean(IS_LOGGED_ID, loggedIn);
        editor.commit();
        editor.apply();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGGED_ID, false);
    }

    public void setPatientUID(String uid) {
        editor.putString(PATIENT_UID, uid);
        editor.commit();
        editor.apply();
    }

    public String getPatientUID() {
        return pref.getString(PATIENT_UID, null);
    }

    public void setPatientId(int id){
        editor.putInt(PATIENT_ID, id);
        editor.commit();
        editor.apply();
    }

    public int getPatientId() {
        return pref.getInt(PATIENT_ID, 0);
    }
}
