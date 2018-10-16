package com.xixi.jimeihui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xixi.jimeihui.allfragment.VideoFragment;
import com.xixi.jimeihui.definition.SuperClass;
import com.xixi.jimeihui.editor.NewActivity;
import com.xixi.jimeihui.home.HomeFragment;
import com.xixi.jimeihui.image.ImageManager;
import com.xixi.jimeihui.utils.permission.BaseAppCompatActivity;
import com.xixi.jimeihui.utils.permission.PermissionListener;
import com.xixi.jimeihui.utils.ui.SwiftAdaptor;
import com.xixi.jimeihui.video.VideoManager;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.shaohui.shareutil.ShareConfig;
import me.shaohui.shareutil.ShareManager;
import me.shaohui.shareutil.ShareUtil;
import me.shaohui.shareutil.share.ShareListener;
import me.shaohui.shareutil.share.SharePlatform;

public class






MainActivity extends BaseAppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener {

    private Toolbar mtoolbar;
    private HomeFragment homeFragment;
    private VideoFragment videoFragment;
    private NavigationView navigationView;
    private CircleImageView imageView;
    private View headerlayout;
    private Boolean message;
    private SharedPreferences sp;
    private ShareListener mShareListener;
    private BottomSheetDialog dialog;
    private SharedPreferences.Editor editor;
    private int lastSelectedButtomTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeFragment = new HomeFragment();
        videoFragment = new VideoFragment();

        //navgationview
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);


        //绑定headerlayout
        get_info();

        //初始化
        initView();

        ImageManager.getInstace().initialize(R.id.maindfragment);
        VideoManager.getInstace().initialize(R.id.maindfragment);
        SwiftAdaptor adaptor = SwiftAdaptor.getInstace();
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        adaptor.intialize(fm, R.id.maindfragment, homeFragment, SuperClass.Home);
        permission();
    }
    private void permission(){
        requestRunPermisssion(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionListener() {
            @Override
            public void onGranted() {
                System.out.print("Get the READ_EXTERNAL_STORAGE");
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                System.out.print("Deny the READ_EXTERNAL_STORAGE");
                finish();
            }
        });
        requestRunPermisssion(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionListener() {
            @Override
            public void onGranted() {
                System.out.print("Get the WRITE_EXTERNAL_STORAGE");
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                System.out.print("Deny the WRITE_EXTERNAL_STORAGE");
                finish();
            }
        });
    }
    //获取用户信息并且判断是否登录
    private void get_info() {
        sp = getSharedPreferences("user_auth", Activity.MODE_PRIVATE);
        editor = sp.edit();
        message = sp.getBoolean("message", false);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        if (!message) {
            navigationView.addHeaderView(layoutInflater.inflate(R.layout.navigation_header_before, navigationView, false));
            headerlayout = navigationView.getHeaderView(0);
            imageView = (CircleImageView) headerlayout.findViewById(R.id.profile_image_before);
            imageView.setOnClickListener(this);
        } else {
            navigationView.addHeaderView(layoutInflater.inflate(R.layout.navigation_header, navigationView, false));
            headerlayout = navigationView.getHeaderView(0);
            TextView tv_header = (TextView) headerlayout.findViewById(R.id.tv_header);
            TextView followers = (TextView) headerlayout.findViewById(R.id.followers);
            TextView following = (TextView) headerlayout.findViewById(R.id.following);
            tv_header.setText(sp.getString("name", "Laravel"));
            followers.setText(sp.getInt("followers", 0) + "");
            following.setText(sp.getInt("following", 0) + "");
        }
    }


    public void initView() {
        //显示toolbar
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        mtoolbar.setTitle(R.string.home_title);
        int color = ContextCompat.getColor(getBaseContext(),R.color.colorToolbarTitle);
        mtoolbar.setTitleTextColor(color);
        setSupportActionBar(mtoolbar);
        //显示底部导航
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.setBarBackgroundColor(R.color.colorBottomNavigationBarBackground);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home2, "主页").setInActiveColor(R.color.naviTabUnselectedText).setActiveColorResource(R.color.naviTabSelectedText))
                .addItem(new BottomNavigationItem(R.mipmap.photo, "图册").setInActiveColor(R.color.naviTabUnselectedText).setActiveColorResource(R.color.naviTabSelectedText))
                .addItem(new BottomNavigationItem(R.mipmap.add,"发布").setInActiveColor(R.color.naviTabUnselectedText).setActiveColorResource(R.color.naviTabSelectedText))
                .addItem(new BottomNavigationItem(R.mipmap.play, "视频").setInActiveColor(R.color.naviTabUnselectedText).setActiveColorResource(R.color.naviTabSelectedText))
                .addItem(new BottomNavigationItem(R.mipmap.user, "我的").setInActiveColor(R.color.naviTabUnselectedText).setActiveColorResource(R.color.naviTabSelectedText))
                .setFirstSelectedPosition(0)
                .initialise();
        lastSelectedButtomTab = 0;
        setDefaultFragment();

        //初始化Listener
        ShareConfig config = ShareConfig.instance()
                .qqId("101410114")
                .weiboId("10121212")
                .wxId("12224412");
        ShareManager.init(config);

        mShareListener = new ShareListener() {
            @Override
            public void shareSuccess() {
                Toast.makeText(MainActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void shareFailure(Exception e) {
                Toast.makeText(MainActivity.this, "分享失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void shareCancel() {
                Toast.makeText(MainActivity.this, "取消分享", Toast.LENGTH_SHORT).show();
            }
        };

        //底部导航监听事件
        bottomNavigationBar.setTabSelectedListener(this);
    }

    //用户分享
    private void user_share(View view, final BottomSheetDialog dialog) {
        view.findViewById(R.id.share_qq).setOnClickListener(this);
        view.findViewById(R.id.share_weixin).setOnClickListener(this);
        view.findViewById(R.id.share_friend).setOnClickListener(this);
        view.findViewById(R.id.share_weibo).setOnClickListener(this);
        view.findViewById(R.id.share_zone).setOnClickListener(this);
    }

    //设置启动页
    private void setDefaultFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.maindfragment, homeFragment).commit();
    }

    //toolbar的监听


    //请先登录的提示
    public void alert_info() {
        new AlertDialog.Builder(MainActivity.this).setTitle("消息提示").setMessage("请您先登录?").setNegativeButton("确定", null).show();
    }

    //退出登录
    public void logout() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        Intent i = getIntent();
        finish();
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //显示这个的搜索绑定
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                System.out.println("open");
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                System.out.println("close");
                return true;
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }

    //底部导航监听事件
    @Override
    public void onTabSelected(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                ft.replace(R.id.maindfragment, homeFragment).commit();
                lastSelectedButtomTab = position;
                break;
            case 1:
                ImageManager.getInstace().enter();
                lastSelectedButtomTab = position;
                break;
            case 2:
                //startActivity(new Intent(this, AlbumActivity.class));
                startActivity(new Intent(this, NewActivity.class));
                BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
                bottomNavigationBar.setFirstSelectedPosition(lastSelectedButtomTab);
                break;
            case 3:
                VideoManager.getInstace().enter();
                //ft.replace(R.id.maindfragment, videoFragment).commit();
                //mtoolbar.setTitle("聚美汇-视频");
                lastSelectedButtomTab = position;
                break;
            case 4:
                ft.replace(R.id.maindfragment, videoFragment).commit();
                //mtoolbar.setTitle("聚美汇-我的");
                lastSelectedButtomTab = position;
                break;
        }
    }


    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    //头像登录监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_qq:
                ShareUtil.shareImage(MainActivity.this, SharePlatform.QQ,
                        "http://shaohui.me/images/avatar.gif", mShareListener);
                dialog.hide();
                break;
            case R.id.share_weixin:
                ShareUtil.shareText(MainActivity.this, SharePlatform.WX, "分享文字", mShareListener);
                dialog.hide();
                break;
            case R.id.share_weibo:
                ShareUtil.shareImage(MainActivity.this, SharePlatform.WEIBO,
                        "http://shaohui.me/images/avatar.gif", mShareListener);
                dialog.hide();
                break;
            case R.id.share_zone:
                ShareUtil.shareMedia(MainActivity.this, SharePlatform.QZONE, "Title", "summary",
                        "http://www.google.com", "http://shaohui.me/images/avatar.gif",
                        mShareListener);
                dialog.hide();
                break;
            case R.id.share_friend:
                ShareUtil.shareText(MainActivity.this, SharePlatform.WX_TIMELINE, "测试分享文字",
                        mShareListener);
                dialog.hide();
                break;
            case R.id.theme_black:
                //mtoolbar.setBackgroundColor(Color.parseColor("#000000"));
                //editor.putString("theme", "#000000");
                editor.commit();
                break;
            case R.id.theme_blue:
                mtoolbar.setBackgroundColor(Color.parseColor("#3F51B5"));
                editor.putString("theme", "#3F51B5");
                editor.commit();
                break;
            case R.id.theme_pink:
                mtoolbar.setBackgroundColor(Color.parseColor("#d4237a"));
                editor.putString("theme", "#d4237a");
                editor.commit();
                break;
            case R.id.theme_purple:
                mtoolbar.setBackgroundColor(Color.parseColor("#6A5ACD"));
                editor.putString("theme", "#6A5ACD");
                editor.commit();
                break;
            case R.id.theme_yellow:
                mtoolbar.setBackgroundColor(Color.parseColor("#FF7F00"));
                editor.putString("theme", "#FF7F00");
                editor.commit();
                break;
        }
    }
}
