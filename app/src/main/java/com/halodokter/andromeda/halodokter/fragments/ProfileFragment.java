package com.halodokter.andromeda.halodokter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.halodokter.andromeda.halodokter.R;

public class ProfileFragment extends Fragment {
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    Toolbar toolbar;

    public static ProfileFragment newInstance(int index) {
        ProfileFragment homeFragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.profile_fragment, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        appBarLayout = view.findViewById(R.id.appbar);
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();

        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(toolbar);
        }

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange == -1)
                {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if(scrollRange + verticalOffset == 0)
                {
                    collapsingToolbarLayout.setTitle("Profile Name");
                    isShow = true;
                }else if(isShow)
                {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

        return view;
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
