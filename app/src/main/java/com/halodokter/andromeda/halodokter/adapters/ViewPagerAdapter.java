package com.halodokter.andromeda.halodokter.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.halodokter.andromeda.halodokter.fragments.HomeFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<HomeFragment> fragments = new ArrayList<>();

    private HomeFragment currentFragment;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments.clear();
        fragments.add(HomeFragment.newInstance(0));
        fragments.add(HomeFragment.newInstance(1));
        fragments.add(HomeFragment.newInstance(2));
        fragments.add(HomeFragment.newInstance(3));
        fragments.add(HomeFragment.newInstance(4));
    }

    @Override
    public HomeFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((HomeFragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * Get the current fragment
     */
    public HomeFragment getCurrentFragment() {
        return currentFragment;
    }
}
