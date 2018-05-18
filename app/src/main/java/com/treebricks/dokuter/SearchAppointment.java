package com.treebricks.dokuter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.treebricks.dokuter.fragments.SearchAppointmentMainFragment;

public class SearchAppointment extends AppCompatActivity {

    EditText searchKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_appointment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchKey = findViewById(R.id.etSearchKey);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Book an Appointment");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.appointment_fragment_placeholder, new SearchAppointmentMainFragment()).commit();

    }
}
