package com.treebricks.dokuter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.treebricks.dokuter.utils.AppPreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    public final static int RC_SIGN_IN = 2;
    AppPreferenceManager appPreferenceManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appPreferenceManager = new AppPreferenceManager(LoginActivity.this);

        List<String> whitelistedCountries = new ArrayList<>();
        whitelistedCountries.add("+880");
        whitelistedCountries.add("+62");


        AuthUI.IdpConfig googleLoginProvider = new AuthUI.IdpConfig.GoogleBuilder().build();

        AuthUI.IdpConfig facebookLoginProvider = new AuthUI.IdpConfig.FacebookBuilder().build();

        AuthUI.IdpConfig emailLoginProvider = new AuthUI.IdpConfig.EmailBuilder().build();

        AuthUI.IdpConfig mobileLoginProvider = new AuthUI.IdpConfig.PhoneBuilder()
                .setWhitelistedCountries(whitelistedCountries)
                .build();

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                googleLoginProvider,
                facebookLoginProvider,
                emailLoginProvider,
                mobileLoginProvider);

        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setTheme(R.style.GreenTheme)
                .setLogo(R.drawable.dokuter_logo)
                .setAvailableProviders(providers)
                .build(), RC_SIGN_IN);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                appPreferenceManager.setLoggedInStatus(true);
                startActivity(MainActivity.createIntent(this, response));
                finish();
            } else {
                // Sign in failed
                if (response == null) {
                    // Back Button Pressed
                    //showSnackbar(R.string.sign_in_cancelled);
                    Toast.makeText(this, "Sign In Canceled", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
//                    showSnackbar(R.string.no_internet_connection);

                    Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show();

                LogUtils.d(response.getError());
            }
        }
    }
}
