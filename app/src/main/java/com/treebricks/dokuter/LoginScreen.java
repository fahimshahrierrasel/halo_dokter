package com.treebricks.dokuter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.treebricks.dokuter.models.User;

public class LoginScreen extends AppCompatActivity {

    //edit text login criteria
    EditText etUserName, etPassword;
    private String TAG = "Authentication";

    private FirebaseAuth mAuth;
    public final static int RC_SIGN_IN = 2;
    private GoogleApiClient mGoogleApiClient;
    FirebaseAuth.AuthStateListener mAuthListener;
    Button loginbtn;
    SharedPrefManager sharedPrefManager;

    //facebook
    private CallbackManager mCallbackManager;

    //with input disable the button
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues();
        }
    };

    //edit text login criteria
    void checkFieldsForEmptyValues() {
        loginbtn = findViewById(R.id.btn_login);
        String s1 = etUserName.getText().toString();
        String s2 = etPassword.getText().toString();
        if (s1.equals("") || s2.equals("")) {
            loginbtn.setEnabled(false);
        } else {
            loginbtn.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // Making notification bar transparent
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPrefManager = new SharedPrefManager(LoginScreen.this);

        etUserName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        // set listeners
        etUserName.addTextChangedListener(mTextWatcher);
        etPassword.addTextChangedListener(mTextWatcher);

        // run once to disable if empty
        checkFieldsForEmptyValues();

        SignInButton bGoogleSignIn = findViewById(R.id.google_sign_in_button);
        mAuth = FirebaseAuth.getInstance();

        // redirect to another activity
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
                if (loggedIn) {
                    Log.d(TAG, "Facebook Logged In");
                } else {
                    Log.d(TAG, "Facebook not Logged In");
                }
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginScreen.this, MainActivity.class));
                    Log.d(TAG, "onAuthStateChanged: User Found Redirect to MainActivity");
                } else {
                    Log.d(TAG, "onAuthStateChanged: User Not Found !!");

                }
            }
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.d(TAG, "onConnectionFailed: " + connectionResult.getErrorMessage());
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        bGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Google sign in button clicked");
                signIn();
            }
        });

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        final LoginButton loginButton = findViewById(R.id.facebook_sign_in_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook register callbase:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook register callbase:onCancel");
                handleFacebookAccessToken(null);
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook register callbase:onError", error);
                handleFacebookAccessToken(null);
            }
        });

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook  :onSuccess: ");
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    public void saveData(View view) {
        boolean error = false;
        User user = new User();
        String username = etUserName.getText().toString().trim();

        if (username.isEmpty()) {
            error = true;
            etUserName.setError("User Name is empty!");
        } else {
            if (username.length() < 6) {
                error = true;
                etUserName.setError("UserName is too short!");
            } else {
                user.setUsername(username);
            }
        }

        String password = etPassword.getText().toString();

        if (password.isEmpty()) {
            error = true;
            etPassword.setError("Password is empty!");
        } else {
            user.setPassword(password);
        }

        //model class integration
        /*Student std = new Student();
        std.setUsername(etUserName.getText().toString().trim());
        std.setPassword(etPassword.getText().toString());
        std.setCgpa(Float.parseFloat(eTCgpa.getText().toString()));
        std.setPhoneno(eTPhoneno.getText().toString().trim());*/

        //get value from ui
        /*String username = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString();
        Float cgpa = Float.parseFloat(eTCgpa.getText().toString());
        String phoneno = eTPhoneno.getText().toString().trim();*/
        if (error) {
            Toast.makeText(LoginScreen.this, "Data not saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(LoginScreen.this, user.toString(), Toast.LENGTH_LONG).show();
        }

    }

    //clear the entered data
    public void clearData(View view) {
        etUserName.setText(null);
        etPassword.setText(null);
    }

    //Called when the user taps the Send button
    public void sendMessage(View view) {
        Intent intent = new Intent(this, UserRegistration.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: Application on Start");
        FirebaseUser currentUser = mAuth.getCurrentUser();
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

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            sharedPrefManager.setLoggedInStatus(true);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            sharedPrefManager.setLoggedInStatus(true);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "signInWithCredential:Failure");
                        }
                    }
                });
    }
}
