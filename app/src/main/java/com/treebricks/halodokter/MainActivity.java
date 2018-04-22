package com.treebricks.halodokter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.treebricks.halodokter.adapters.ViewPagerAdapter;
import com.treebricks.halodokter.R;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Fragment currentFragment;
    ViewPagerAdapter viewPagerAdapter;

    AHBottomNavigation bottomNavigation;
    AHBottomNavigationViewPager bottomNavigationViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(MainActivity.this, LoginScreen.class));
                }
            }
        };

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigationViewPager = findViewById(R.id.view_pager);


        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        bottomNavigationViewPager.setAdapter(viewPagerAdapter);
        currentFragment = viewPagerAdapter.getCurrentFragment();


        bottomNavigation.setColored(true);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home, R.drawable.ic_home_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.list, R.drawable.ic_list_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.profile, R.drawable.ic_person_24dp, R.color.colorPrimary);
        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (currentFragment == null) {
                    currentFragment = viewPagerAdapter.getCurrentFragment();
                }

//                if (wasSelected) {
//                    currentFragment.refresh();
//                    return true;
//                }

//                if (currentFragment != null) {
//                    currentFragment.willBeHidden();
//                }

                bottomNavigationViewPager.setCurrentItem(position, false);

                if (currentFragment == null) {
                    return true;
                }

                currentFragment = viewPagerAdapter.getCurrentFragment();
                //currentFragment.willBeDisplayed();
//                switch (position)
//                {
//                    case 0:
//                        if(getSupportActionBar() != null)
//                            getSupportActionBar().setTitle("Home");
//                        break;
//                    case 1:
//                        if(getSupportActionBar() != null)
//                            getSupportActionBar().setTitle("Your List");
//                        break;
//                    case 2:
//                        if(getSupportActionBar() != null)
//                            getSupportActionBar().setTitle(mAuth.getCurrentUser().getDisplayName());
//                        break;
//                    default:
//                        break;
//                }
                return true;
            }
        });
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                // Manage the new y position

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
