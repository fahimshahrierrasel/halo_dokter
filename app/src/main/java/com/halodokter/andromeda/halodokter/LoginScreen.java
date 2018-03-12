package com.halodokter.andromeda.halodokter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toolbar;

public class LoginScreen extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "login to register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);


    }
    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, UserRegistration.class);
        startActivity(intent);
    }

}
