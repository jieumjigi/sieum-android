package com.jieumjigi.sieum;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wjddk on 2017-06-07.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> listFragments;

    public TabPagerAdapter(FragmentManager fm, List<Fragment> listFragments){
        super(fm);
        this.listFragments = listFragments;
    }

    @Override
    public Fragment getItem(int position){
        return listFragments.get(position);
    }

    @Override
    public int getCount(){
        return listFragments.size();
    }
}
