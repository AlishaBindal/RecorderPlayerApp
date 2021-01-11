package com.example.recorderplayersampleapp.base.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * BasePagerAdapter
 */
public class BasePagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private FragmentManager fm;

    /**
     * Constructor
     *
     * @param fm        instance of fragment manager
     * @param fragments list for fragments
     */
    public BasePagerAdapter(final FragmentManager fm, final List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        this.fm = fm;
    }


    @Override
    public Fragment getItem(final int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }


}