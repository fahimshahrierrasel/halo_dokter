package com.treebricks.dokuter.fragments;

import android.graphics.Color;
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

import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.Animations.DescriptionAnimation;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.SliderTypes.DefaultSliderView;
import com.treebricks.dokuter.FAQActivity;
import com.treebricks.dokuter.SearchAppointment;
import com.treebricks.dokuter.adapters.TwoSideDrawableListAdapter;
import com.treebricks.dokuter.R;
import com.treebricks.dokuter.models.RecyclerItem;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private SliderLayout mDemoSlider;

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

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();

        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(toolbar);
        }

//        if (toolbar != null) {
//            toolbar.setTitle("Home");
//        }


        mDemoSlider = view.findViewById(R.id.slider);

        ArrayList<String> listUrl = new ArrayList<>();

        listUrl.add("https://www.revive-adserver.com/media/GitHub.jpg");
        listUrl.add("https://tctechcrunch2011.files.wordpress.com/2017/02/android-studio-logo.png");
        //RequestOptions requestOptions = new RequestOptions();
        //requestOptions.centerCrop();
        //.diskCacheStrategy(DiskCacheStrategy.NONE)
        //.placeholder(R.drawable.placeholder)
        //.error(R.drawable.placeholder);

        for (int i = 0; i < listUrl.size(); i++) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());
            // if you want show image only / without description text use DefaultSliderView instead

            // initialize SliderLayout
            sliderView
                    .image(listUrl.get(i))
                    .setRequestOption(RequestOptions.centerCropTransform())
                    .setBackgroundColor(Color.WHITE)
                    .setProgressBarVisible(true);

            //add your extra information
            //sliderView.bundle(new Bundle());
            //sliderView.getBundle().putString("extra", listName.get(i));
            mDemoSlider.addSlider(sliderView);
        }

        // set Slider Transition Animation
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        //mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);

        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);



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

    @Override
    public void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }
}