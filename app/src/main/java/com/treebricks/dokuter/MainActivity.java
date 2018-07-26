package com.treebricks.dokuter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.blankj.utilcode.util.LogUtils;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.util.ExtraConstants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.treebricks.dokuter.adapters.ViewPagerAdapter;
import com.treebricks.dokuter.api.ApiUtils;
import com.treebricks.dokuter.api.UserService;
import com.treebricks.dokuter.models.Patient;
import com.treebricks.dokuter.utils.AppPreferenceManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    boolean backToExitPressedOnce = false;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Fragment currentFragment;
    ViewPagerAdapter viewPagerAdapter;
    AppPreferenceManager appPreferenceManager;

    AHBottomNavigation bottomNavigation;
    AHBottomNavigationViewPager bottomNavigationViewPager;


    public static Intent createIntent(Context context, IdpResponse idpResponse) {
        return new Intent().setClass(context, MainActivity.class)
                .putExtra(ExtraConstants.IDP_RESPONSE, idpResponse);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);

        appPreferenceManager = new AppPreferenceManager(this);

        mAuthListener = firebaseAuth -> {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user == null) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        };

        IdpResponse response = getIntent().getParcelableExtra(ExtraConstants.IDP_RESPONSE);
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            if (response != null) {
                if (response.isNewUser()) {
                    savePatientToDB(user);
                } else {
                    getPatientFromDB(user.getUid());
                }
            }
        }


        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigationViewPager = findViewById(R.id.view_pager);


        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        bottomNavigationViewPager.setAdapter(viewPagerAdapter);
        currentFragment = viewPagerAdapter.getCurrentFragment();


        bottomNavigation.setColored(true);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home, R.drawable.ic_home_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.list, R.drawable.ic_list_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.profile, R.drawable.ic_person_24dp, R.color.colorPrimary);
        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        // Set listeners
        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            if (currentFragment == null) {
                currentFragment = viewPagerAdapter.getCurrentFragment();
            }

            bottomNavigationViewPager.setCurrentItem(position, false);

            if (currentFragment == null) {
                return true;
            }

            currentFragment = viewPagerAdapter.getCurrentFragment();
            return true;
        });
        bottomNavigation.setOnNavigationPositionListener(y -> {
            // Manage the new y position
        });
    }

    private void getPatientFromDB(String uid) {
        UserService userService = ApiUtils.getUserService();

        Call<Patient> apiCall = userService.getPatient(uid);
        apiCall.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(@NonNull Call<Patient> call, @NonNull Response<Patient> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    Patient patient = response.body();
                    if (patient != null) {
                        savePatientInfoToPreference(patient);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Patient> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Something Went Wrong on Server.", Toast.LENGTH_SHORT).show();
                LogUtils.d(t.getMessage());
            }
        });
    }

    private void savePatientToDB(FirebaseUser user) {
        UserService userService = ApiUtils.getUserService();

        Map<String, String> fields = new HashMap<>();
        fields.put("patientUUID", user.getUid());
        fields.put("patient_name", user.getDisplayName());

        Call<Patient> apiCall = userService.savePatient(fields);
        apiCall.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(@NonNull Call<Patient> call, @NonNull Response<Patient> response) {
                int statusCode = response.code();
                if (statusCode == 201) {
                    Patient patient = response.body();
                    if (patient != null) {
                        savePatientInfoToPreference(patient);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Patient> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Something Went Wrong on Server.", Toast.LENGTH_SHORT).show();
                LogUtils.d(t.getMessage());
            }
        });

    }

    private void savePatientInfoToPreference(Patient patient) {
        appPreferenceManager.setPatientUID(patient.getPatientUUID());
        appPreferenceManager.setPatientId(patient.getId());
    }

    @Override
    public void onBackPressed() {
        if (backToExitPressedOnce) {
            super.onBackPressed();
            finish();
        }

        backToExitPressedOnce = true;
        Toast.makeText(MainActivity.this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> backToExitPressedOnce = false, 2000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
