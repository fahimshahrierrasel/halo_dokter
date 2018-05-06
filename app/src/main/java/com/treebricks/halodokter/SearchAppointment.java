package com.treebricks.halodokter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.treebricks.halodokter.adapters.LeftSideDrawableListAdapter;
import com.treebricks.halodokter.R;
import com.treebricks.halodokter.fragments.BookAppointmentMainFragment;
import com.treebricks.halodokter.models.RecyclerItem;

import java.util.ArrayList;

public class SearchAppointment extends AppCompatActivity {

    EditText searchKey;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_appointment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchKey = findViewById(R.id.etSearchKey);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.appointmentFragmentPlaceholder, new BookAppointmentMainFragment()).commit();

    }

}
