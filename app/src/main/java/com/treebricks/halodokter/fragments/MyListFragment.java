package com.treebricks.halodokter.fragments;

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

import com.treebricks.halodokter.R;
import com.treebricks.halodokter.adapters.DetailsItemListAdapter;
import com.treebricks.halodokter.models.DetailsListItem;

import java.util.ArrayList;

public class MyListFragment extends Fragment {
    private RecyclerView recyclerView;

    public static MyListFragment newInstance(int index) {
        MyListFragment homeFragment = new MyListFragment();
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

            toolbar.setTitle("Your List");
        }
        initList(view);
        return view;
    }

    private void initList(View view) {
        recyclerView = view.findViewById(R.id.fragment_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<DetailsListItem> itemData = new ArrayList<>();
        itemData.add(new DetailsListItem("Your health profile is only 20% complete", getResources().getString(R.string.lorem_ipsum), 0, "Add Details", null));
        itemData.add(new DetailsListItem("Use 'HaloDoc' code to get 25% off on medicines", getResources().getString(R.string.lorem_ipsum), 0, "Order Now", null));
        itemData.add(new DetailsListItem("Personalise your reading experience", getResources().getString(R.string.lorem_ipsum), 0, "Add Interests", null));
        itemData.add(new DetailsListItem("Refer and save money on medicine order", getResources().getString(R.string.lorem_ipsum), 0, "Share Code", null));

        DetailsItemListAdapter twoSideDrawableListAdapter = new DetailsItemListAdapter(itemData, getContext());
        recyclerView.setAdapter(twoSideDrawableListAdapter);
    }
}
