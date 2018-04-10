package com.halodokter.andromeda.halodokter.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.halodokter.andromeda.halodokter.fragments.HomeFragment;
import com.halodokter.andromeda.halodokter.fragments.ProfileFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment currentFragment;
    private static int NUM_TAB_ITEMS = 3;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                currentFragment = HomeFragment.newInstance(0);
                break;
            case 1:
                currentFragment = HomeFragment.newInstance(1);
                break;
            case 2:
                currentFragment = ProfileFragment.newInstance(0);
                break;
            default:
                currentFragment = null;
                break;
        }
        return currentFragment;
    }

    @Override
    public int getCount() {
        return NUM_TAB_ITEMS;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * Get the current fragment
     */
    public Fragment getCurrentFragment() {
        return currentFragment;
    }
}
