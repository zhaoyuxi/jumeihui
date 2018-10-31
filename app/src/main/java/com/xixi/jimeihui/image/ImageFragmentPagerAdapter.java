package com.xixi.jimeihui.image;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.xixi.jimeihui.definition.Category;


public class ImageFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private Context context;
    private ViewGroup mContainer;

    public ImageFragmentPagerAdapter(FragmentManager fm, ViewGroup container, Context context) {
        super(fm);
        titles = Category.getImageCategory();
        mContainer = container;
        this.context = context;
    }



    @Override
    public Fragment getItem(int position) {
        return ImagePageFragmentPage.newInstance(mContainer);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
