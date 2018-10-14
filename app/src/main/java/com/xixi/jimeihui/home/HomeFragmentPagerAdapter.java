package com.xixi.jimeihui.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.xixi.jimeihui.allfragment.PageFragment;


public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{"关注","推荐","知识","游玩","美食","心灵鸡汤","养生","汽车"};
    private Context context;

    public HomeFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }



    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance();
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
