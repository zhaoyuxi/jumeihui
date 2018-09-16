package com.xixi.jimeihui.alladapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.xixi.jimeihui.allfragment.PageFragment;

/**
 * Created by LaravelChen on 2017/6/8.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{"关注","推荐","论坛"};
    public int COUNT = titles.length;
    private Context context;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }



    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance();
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
