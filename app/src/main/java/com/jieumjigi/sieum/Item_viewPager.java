package com.jieumjigi.sieum;


import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;


/**
 * Created by cmtyx on 2017-01-21.
 */

public class Item_viewPager extends PagerAdapter {
    private  LayoutInflater mInflater;

    public Item_viewPager(Context c){
        super();
        mInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount(){
        return 2;
    }

    @Override
    public Object instantiateItem(View pager, int position){
        View v = null;
        v = mInflater.inflate(R.layout.activity_main,null);

        //원하는 기능 추가하기

        ((ViewPager)pager).addView(v,0);

        return v;
    }

    @Override
    public void destroyItem(View pager, int position, Object view){
        ((ViewPager)pager).removeView((View)view);
    }

    @Override
    public boolean isViewFromObject(View pager,Object obj){
        return pager == obj;
    }

    @Override public void restoreState(Parcelable arg0, ClassLoader arg1){}
    @Override public Parcelable saveState(){return null;}
    @Override public void startUpdate(View arg0){}
    @Override public void finishUpdate(View arg0){}
}
