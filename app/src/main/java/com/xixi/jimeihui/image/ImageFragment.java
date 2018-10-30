package com.xixi.jimeihui.image;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xixi.jimeihui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {
    private View rootView;
    private ImageFragmentPagerAdapter adapter;
    public ImageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.image_fragment, container, false);
        initView(rootView, container);
        return rootView;
    }

    public void initView(View rootView, ViewGroup container) {
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        adapter = new ImageFragmentPagerAdapter(getChildFragmentManager(), container, getContext());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(7);


        //TabLayout
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tablayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //显示当前那个标签页
        viewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewPager);
    }
}
