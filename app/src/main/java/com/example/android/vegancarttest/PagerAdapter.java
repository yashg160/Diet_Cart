package com.example.android.vegancarttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumberofTabs;
    public PagerAdapter(FragmentManager fm, int NumberofTabs){
        super(fm);
        this.mNumberofTabs = NumberofTabs;
    }
    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0:
                return new Intro();
            case 1:
                return new IntroFaq();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNumberofTabs;
    }
}