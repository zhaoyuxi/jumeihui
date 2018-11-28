package com.xixi.jimeihui.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.xixi.jimeihui.allfragment.PageFragment;
import com.xixi.jimeihui.definition.Category;


public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private Context context;
    private AppCompatActivity activity;

    public HomeFragmentPagerAdapter(FragmentManager fm, Context context, AppCompatActivity activity) {
        super(fm);
        titles = Category.getHomeCategory();
        this.context = context;
        this.activity = activity;
    }



    @Override
    public Fragment getItem(int position) {
        return HomeTabFragment.newInstance(activity);
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
