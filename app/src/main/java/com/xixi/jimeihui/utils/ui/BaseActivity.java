package com.xixi.jimeihui.utils.ui;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xixi.jimeihui.R;
import com.xixi.jimeihui.utils.permission.BaseAppCompatActivity;
public class BaseActivity extends BaseAppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener {
    private Toolbar mtoolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private ButtomNavigatorListener buttomNavigatorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setButtomNavigatorListener(ButtomNavigatorListener impl) {
        buttomNavigatorListener = impl;
    }


    public void initBaseView(int pos) {
        //显示toolbar
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        mtoolbar.setTitle("聚美汇");
        setSupportActionBar(mtoolbar);
        //显示底部导航
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.setBarBackgroundColor(R.color.white);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home2, "主页").setInActiveColor(R.color.naviTabUnselectedText).setActiveColorResource(R.color.naviTabSelectedText))
                .addItem(new BottomNavigationItem(R.mipmap.photo, "图册").setInActiveColor(R.color.naviTabUnselectedText).setActiveColorResource(R.color.naviTabSelectedText))
                .addItem(new BottomNavigationItem(R.mipmap.play, "视频").setInActiveColor(R.color.naviTabUnselectedText).setActiveColorResource(R.color.naviTabSelectedText))
                .addItem(new BottomNavigationItem(R.mipmap.user, "我的").setInActiveColor(R.color.naviTabUnselectedText).setActiveColorResource(R.color.naviTabSelectedText))
                .setFirstSelectedPosition(pos)
                .initialise();

        //底部导航监听事件
        bottomNavigationBar.setTabSelectedListener(this);
    }

    //底部导航监听事件
    @Override
    public void onTabSelected(int position) {
        buttomNavigatorListener.onTabSelected(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onClick(View v) {

    }
}
