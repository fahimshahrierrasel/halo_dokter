package com.treebricks.dokuter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.treebricks.dokuter.GlideApp;
import com.treebricks.dokuter.LoginScreen;
import com.treebricks.dokuter.R;
import com.treebricks.dokuter.SettingsActivity;
import com.treebricks.dokuter.adapters.RightSideDrawableNoDescListAdapter;
import com.treebricks.dokuter.models.RecyclerItem;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    public static final String TAG = ProfileFragment.class.getSimpleName();
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    ImageView profileImage;
    TextView profileName;
    String userName;
    RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public static ProfileFragment newInstance(int index) {
        ProfileFragment homeFragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        Log.d(TAG, "onCreate: Profile fragment on create");
        setHasOptionsMenu(true);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(getActivity(), LoginScreen.class));
                }
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        appBarLayout = view.findViewById(R.id.appbar);
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        profileImage = view.findViewById(R.id.profile_image);
        profileName = view.findViewById(R.id.profile_name);

        if (appCompatActivity != null) {
            appCompatActivity.setSupportActionBar(toolbar);
        }

        FirebaseUser user = mAuth.getCurrentUser();


        if(user != null)
        {
            userName =  user.getDisplayName();
            profileName.setText(userName);
            String imageUrl = getUserImageUrl(user);

            GlideApp.with(getActivity())
                    .load(imageUrl)
                    .into(profileImage);
        }

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(userName);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
        initList(view);
        return view;
    }
    private void initList(View view){
        recyclerView = view.findViewById(R.id.rvProfileItems);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<RecyclerItem> itemData = new ArrayList<>();
        itemData.add(new RecyclerItem("My doctors", " ", 0, null));
        itemData.add(new RecyclerItem("Appointments", " ", 0, null));
        itemData.add(new RecyclerItem("Online consultations", " ", 0, null));
        itemData.add(new RecyclerItem("Medical records", " ", 0, null));
        itemData.add(new RecyclerItem("Orders", " ", 0, null));
        itemData.add(new RecyclerItem("Reminders", " ", 0, null));
        itemData.add(new RecyclerItem("Bookmarked articles", " ", 0, null));
        itemData.add(new RecyclerItem("Health interests", " ", 0, null));
        itemData.add(new RecyclerItem("My payments", " ", 0, null));

        RightSideDrawableNoDescListAdapter rightSideDrawableNoDescListAdapter = new RightSideDrawableNoDescListAdapter(itemData, getContext());
        recyclerView.setAdapter(rightSideDrawableNoDescListAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            default:
                break;
        }

        return true;
    }

    private String getUserImageUrl(FirebaseUser user)
    {
        String facebookUserId = "";
        String imageUrl = "";
        // find the Facebook profile and get the user's id
        for(UserInfo profile : user.getProviderData()) {
            // check if the provider id matches "facebook.com"
            if(FacebookAuthProvider.PROVIDER_ID.equals(profile.getProviderId())) {
                facebookUserId = profile.getUid();
                imageUrl = "https://graph.facebook.com/" + facebookUserId + "/picture?type=large";
            }else if(GoogleAuthProvider.PROVIDER_ID.equals(profile.getProviderId()))
            {
                imageUrl = profile.getPhotoUrl().toString();
            }
        }
        // alternatively, use '?type=small|medium|large' instead of ?height=
        return imageUrl;
    }
}
