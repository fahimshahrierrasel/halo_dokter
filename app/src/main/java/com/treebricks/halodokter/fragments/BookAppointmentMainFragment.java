package com.treebricks.halodokter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.treebricks.halodokter.R;
import com.treebricks.halodokter.adapters.DoctorSpecialityListAdapter;
import com.treebricks.halodokter.adapters.LeftSideDrawableListAdapter;
import com.treebricks.halodokter.models.RecyclerItem;

import java.util.ArrayList;

public class BookAppointmentMainFragment extends Fragment {
    RecyclerView searchRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appointment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchRecyclerView = view.findViewById(R.id.rvSearchedItem);
        searchRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        searchRecyclerView.setLayoutManager(layoutManager);

        ArrayList<RecyclerItem> itemData = new ArrayList<>();
        itemData.add(new RecyclerItem("Doctors", "Cardiologist, Dermatologist, etc.", R.drawable.ic_add_appointment, null));
        itemData.add(new RecyclerItem("Dentists", "Dentists, Prosthodontist, etc.", R.drawable.ic_add_appointment, null));
        itemData.add(new RecyclerItem("Alternative Medicine Doctors(AYUSH)", "Ayurveda, Homeopath, etc.", R.drawable.ic_add_appointment, null));
        itemData.add(new RecyclerItem("Therapists & Nutritionists", "Acupuncturist, Physiotherapist, etc.", R.drawable.ic_add_appointment, null));

        ArrayList<String> items = new ArrayList<>();
        items.add("Ophthalmologist");
        items.add("Dermatologist");
        items.add("Cardiologist");
        items.add("Gastroenterologist");
        items.add("Psychiatrist");
        items.add("Eat-Nose-Throat (ENT) Specialist");
        items.add("Gynecologist/Obstetrician");
        items.add("Neurologist");
        items.add("Urologist");

        final DoctorSpecialityListAdapter adapter = new DoctorSpecialityListAdapter(items, getActivity());

        LeftSideDrawableListAdapter leftSideDrawableListAdapter = new LeftSideDrawableListAdapter(itemData, getActivity());
        searchRecyclerView.setAdapter(leftSideDrawableListAdapter);
        leftSideDrawableListAdapter.setOnItemClickListener(new LeftSideDrawableListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                searchRecyclerView.setAdapter(adapter);
            }
        });
    }
}
