package com.treebricks.dokuter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treebricks.dokuter.FAQActivity;
import com.treebricks.dokuter.SearchAppointment;
import com.treebricks.dokuter.adapters.TwoSideDrawableListAdapter;
import com.treebricks.dokuter.R;
import com.treebricks.dokuter.models.RecyclerItem;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;

    public static HomeFragment newInstance(int index) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_with_list, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();

        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(toolbar);
        }

        if (toolbar != null) {
            toolbar.setTitle("Home");
        }
        initList(view);
        return view;
    }

    private void initList(View view) {
        recyclerView = view.findViewById(R.id.fragment_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<RecyclerItem> itemData = new ArrayList<>();
        itemData.add(new RecyclerItem("Book an appointment", "Find doctors, clinics, hospitals and more", R.drawable.ic_add_appointment, SearchAppointment.class));
        itemData.add(new RecyclerItem("Ask a free question", "Get answers from doctors and experts", R.drawable.question, FAQActivity.class));

        TwoSideDrawableListAdapter twoSideDrawableListAdapter = new TwoSideDrawableListAdapter(itemData, getContext());
        recyclerView.setAdapter(twoSideDrawableListAdapter);
    }
}