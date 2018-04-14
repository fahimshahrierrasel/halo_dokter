package com.halodokter.andromeda.halodokter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.halodokter.andromeda.halodokter.adapters.LeftSideDrawableListAdapter;
import com.halodokter.andromeda.halodokter.models.RecyclerItem;

import java.util.ArrayList;

public class SearchAppointment extends AppCompatActivity {

    EditText searchKey;

    RecyclerView searchRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_appointment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchKey = findViewById(R.id.etSearchKey);
        searchRecyclerView = findViewById(R.id.rvSearchedItem);
        searchRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        searchRecyclerView.setLayoutManager(layoutManager);

        ArrayList<RecyclerItem> itemData = new ArrayList<>();
        itemData.add(new RecyclerItem("Doctors", "Cardiologist, Dermatologist, etc.", R.drawable.ic_add_appointment, null));
        itemData.add(new RecyclerItem("Dentists", "Dentists, Prosthodontist, etc.", R.drawable.ic_add_appointment, null));
        itemData.add(new RecyclerItem("Alternative Medicine Doctors(AYUSH)", "Ayurveda, Homeopath, etc.", R.drawable.ic_add_appointment, null));
        itemData.add(new RecyclerItem("Therapists & Nutritionists", "Acupuncturist, Physiotherapist, etc.", R.drawable.ic_add_appointment, null));

        LeftSideDrawableListAdapter leftSideDrawableListAdapter = new LeftSideDrawableListAdapter(itemData, this);
        searchRecyclerView.setAdapter(leftSideDrawableListAdapter);

    }

}
