package com.halodokter.andromeda.halodokter;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.setting_content, new ApplicationPreferenceFragment());
        fragmentTransaction.commit();
    }

    public static class ApplicationPreferenceFragment extends PreferenceFragment{
        private FirebaseAuth mAuth;
        private FirebaseAuth.AuthStateListener mAuthListener;
        PrefManager prefManager;
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mAuth = FirebaseAuth.getInstance();
            prefManager = new PrefManager(getActivity());
            addPreferencesFromResource(R.xml.preferences);


            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user == null) {
                        startActivity(new Intent(getActivity(), LoginScreen.class));
                        prefManager.setLoggedInStatus(false);
                    }
                }
            };

            Preference logoutPreference = findPreference("logout");
            logoutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    mAuth.signOut();
                    LoginManager.getInstance().logOut();
                    prefManager.setLoggedInStatus(false);
                    startActivity(new Intent(getActivity(), LoginScreen.class));

                    getActivity().finish();
                    return true;
                }
            });
        }
        @Override
        public void onStart() {
            super.onStart();
            mAuth.addAuthStateListener(mAuthListener);
        }
    }
}
