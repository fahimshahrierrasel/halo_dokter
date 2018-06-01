package com.treebricks.dokuter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.treebricks.dokuter.utils.SharedPrefManager;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();

    private FirebaseAuth mAuth;
    public final static int RC_SIGN_IN = 2;
    private GoogleApiClient mGoogleApiClient;
    FirebaseAuth.AuthStateListener mAuthListener;
    SharedPrefManager sharedPrefManager;

    //facebook
    private CallbackManager mCallbackManager;
    private LoginManager facebookLoginManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // Making notification bar transparent
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPrefManager = new SharedPrefManager(LoginActivity.this);

        Button bGoogleSignIn = findViewById(R.id.google_sign_in_button_wrapper);
        mAuth = FirebaseAuth.getInstance();

        // redirect to another activity
        mAuthListener = firebaseAuth -> {
            boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
            if (loggedIn) {
                Log.d(TAG, "Facebook Logged In");
            } else {
                Log.d(TAG, "Facebook not Logged In");
            }
            if (firebaseAuth.getCurrentUser() != null) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                Log.d(TAG, "onAuthStateChanged: User Found Redirect to MainActivity");
            } else {
                Log.d(TAG, "onAuthStateChanged: User Not Found !!");

            }
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, connectionResult ->
                        Log.d(TAG, "onConnectionFailed: " + connectionResult.getErrorMessage())
                )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        bGoogleSignIn.setOnClickListener(v -> {
            Log.d(TAG, "onClick: Google sign in button clicked");
            signIn();
        });

        // Initialize Facebook Login
        facebookLoginManger = LoginManager.getInstance();
        mCallbackManager = CallbackManager.Factory.create();

        Button btnFacebookLogin = findViewById(R.id.facebook_sign_in_button_wrapper);

        btnFacebookLogin.setOnClickListener(view ->
                facebookLoginManger.logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile", "user_birthday"))
        );

        facebookLoginManger.registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook register callbase:onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                        setResult(RESULT_OK);
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook register callbase:onCancel");
                        handleFacebookAccessToken(null);
                        setResult(RESULT_CANCELED);
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d(TAG, "facebook register callbase:onError", exception);
                        handleFacebookAccessToken(null);
                        setResult(RESULT_CANCELED);
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: Application on Start");
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Log.d(TAG, "signIn: Google sign in button clicked wait for result");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.d(TAG, "Google sign in failed" + e.getMessage());
            }
        }
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        if (token != null) {

            AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            sharedPrefManager.setLoggedInStatus(true);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "signInWithCredential:failure", task.getException());
                        }
                    });
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's
                        Log.d(TAG, "signInWithCredential:success");
                        sharedPrefManager.setLoggedInStatus(true);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d(TAG, "signInWithCredential:Failure");
                    }
                });
    }

    public void loginWithEmail(View view) {
        startActivity(new Intent(LoginActivity.this, EmailLoginActivity.class));
    }
}
