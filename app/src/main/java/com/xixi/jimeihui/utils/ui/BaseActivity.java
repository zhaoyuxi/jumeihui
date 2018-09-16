package com.xixi.jimeihui.utils.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xixi.jimeihui.allfragment.HomeFragment;
import com.xixi.jimeihui.allfragment.PhotoFragment;
import com.xixi.jimeihui.allfragment.UserFragment;
import com.xixi.jimeihui.allfragment.VideoFragment;
import com.xixi.jimeihui.allfragment.JokeFragment;
import com.xixi.jimeihui.R;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.ResponseCallback;
import com.xixi.jimeihui.image.ImageActivity;
import com.xixi.jimeihui.utils.permission.BaseAppCompatActivity;

import org.json.JSONObject;
import de.hdodenhof.circleimageview.CircleImageView;
import me.shaohui.shareutil.ShareConfig;
import me.shaohui.shareutil.ShareManager;
import me.shaohui.shareutil.ShareUtil;
import me.shaohui.shareutil.share.ShareListener;
import me.shaohui.shareutil.share.SharePlatform;
import okhttp3.Call;
import okhttp3.ResponseBody;
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
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home2, "主页").setInActiveColor(R.color.colorbttonfont).setActiveColorResource(R.color.colorRed))
                .addItem(new BottomNavigationItem(R.mipmap.photo, "图册").setInActiveColor(R.color.colorbttonfont).setActiveColorResource(R.color.colorRed))
                .addItem(new BottomNavigationItem(R.mipmap.play, "视频").setInActiveColor(R.color.colorbttonfont).setActiveColorResource(R.color.colorRed))
                .addItem(new BottomNavigationItem(R.mipmap.user, "我的").setInActiveColor(R.color.colorbttonfont).setActiveColorResource(R.color.colorRed))
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
