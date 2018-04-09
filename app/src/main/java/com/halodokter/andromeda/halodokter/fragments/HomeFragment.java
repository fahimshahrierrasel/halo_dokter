package com.halodokter.andromeda.halodokter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.halodokter.andromeda.halodokter.adapters.HomeListAdapter;
import com.halodokter.andromeda.halodokter.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FrameLayout fragmentContainer;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

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
        initList(view);
        return view;
    }

    private void initList(View view){
        fragmentContainer = view.findViewById(R.id.fragment_container);
        recyclerView = view.findViewById(R.id.fragment_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> itemData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            itemData.add("Fragment " + getArguments().getInt("index", -1) + " / Item" + i);
        }

        HomeListAdapter homeListAdapter = new HomeListAdapter(itemData);
        recyclerView.setAdapter(homeListAdapter);
    }

    public void refresh()
    {
        if(recyclerView != null)
        {
            recyclerView.smoothScrollToPosition(0);
        }
    }

//    /**
//     * Called when a fragment will be displayed
//     */
//    public void willBeDisplayed() {
//        // Do what you want here, for example animate the content
//        if (fragmentContainer != null) {
//            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
//            fragmentContainer.startAnimation(fadeIn);
//        }
//    }
//
//    /**
//     * Called when a fragment will be hidden
//     */
//    public void willBeHidden() {
//        if (fragmentContainer != null) {
//            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
//            fragmentContainer.startAnimation(fadeOut);
//        }
//    }
}
