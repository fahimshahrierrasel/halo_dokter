package com.treebricks.halodokter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.treebricks.halodokter.adapters.DoctorListRecyclerViewAdapter;

import java.util.ArrayList;

public class DoctorsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);

        RecyclerView recyclerView = findViewById(R.id.rv_doctor_list);

        ArrayList<String> items = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            items.add(String.valueOf(i));
        }

        DoctorListRecyclerViewAdapter dlrva = new DoctorListRecyclerViewAdapter(items, this);
        recyclerView.setAdapter(dlrva);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
