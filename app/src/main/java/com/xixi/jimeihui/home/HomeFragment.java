package com.xixi.jimeihui.home;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xixi.jimeihui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivAddFriend)
    ImageView ivAddFriend;
    Unbinder unbinder;
    private View rootView;
    private HomeFragmentPagerAdapter adapter;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initView(rootView);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void initView(View rootView) {
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        adapter = new HomeFragmentPagerAdapter(getChildFragmentManager(), getContext());
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
