package com.xixi.jimeihui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.xixi.jimeihui.utils.ui.FragmentController;
import com.xixi.jimeihui.utils.ui.SwiftAdaptor;
import com.xixi.jimeihui.video.VideoManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.shaohui.shareutil.ShareConfig;
import me.shaohui.shareutil.ShareManager;
import me.shaohui.shareutil.ShareUtil;
import me.shaohui.shareutil.share.ShareListener;
import me.shaohui.shareutil.share.SharePlatform;

public class


MainActivity extends BaseAppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener {
    @BindView(R.id.ivIconHome)
    ImageView ivIconHome;
    @BindView(R.id.tvTextHome)
    TextView tvTextHome;
    @BindView(R.id.tvBadgeHome)
    TextView tvBadgeHome;
    @BindView(R.id.ivIconPicture)
    ImageView ivIconPicture;
    @BindView(R.id.tvTextPicture)
    TextView tvTextPicture;
    @BindView(R.id.tvBadgePicture)
    TextView tvBadgePicture;
    @BindView(R.id.ivIconPublish)
    ImageView ivIconPublish;
    @BindView(R.id.tvTextPublish)
    TextView tvTextPublish;
    @BindView(R.id.tvBadgePublish)
    TextView tvBadgePublish;
    @BindView(R.id.ivIconVideo)
    ImageView ivIconVideo;
    @BindView(R.id.tvTextVideo)
    TextView tvTextVideo;
    @BindView(R.id.tvBadgeVideo)
    TextView tvBadgeVideo;
    @BindView(R.id.ivIconMine)
    ImageView ivIconMine;
    @BindView(R.id.tvTextMine)
    TextView tvTextMine;
    @BindView(R.id.tvBadgeMine)
    TextView tvBadgeMine;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;
    private View lastSelectedIcon;
    private View lastSelectedText;
    private Toolbar mtoolbar;
    private HomeFragment homeFragment;
    private VideoFragment videoFragment;
    private FragmentController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        homeFragment = new HomeFragment();
        videoFragment = new VideoFragment();

        //初始化
        initView();

        ImageManager.getInstace().initialize(R.id.maindfragment);
        VideoManager.getInstace().initialize(R.id.maindfragment);
        SwiftAdaptor adaptor = SwiftAdaptor.getInstace();
        FragmentManager fm = getSupportFragmentManager();
        adaptor.intialize(fm, R.id.maindfragment, homeFragment, SuperClass.Home);
        permission();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mController.onDestroy();
    }

    private void permission() {
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


    protected void initNavigator() {
        // mController = FragmentController.getInstance(this, R.id.maindfragment, true);
        //setEnableSwipe(false);
        //mController.showFragment(0);

        for (int i = 0; i < llBottom.getChildCount(); i++) {
            if (i == 0) {
                //默认选中首页
                setSelectIcon(ivIconHome, tvTextHome);
            }
            final int position = i;
            llBottom.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != 2) {
                        if (lastSelectedIcon != null) {
                            lastSelectedIcon.setSelected(false);
                        }
                        if (lastSelectedText != null) {
                            lastSelectedText.setSelected(false);
                        }
                        RelativeLayout rl = (RelativeLayout) v;
                        ImageView icon = (ImageView) rl.getChildAt(0);
                        TextView text = (TextView) rl.getChildAt(1);
                        setSelectIcon(icon, text);
                    }
                    onNavigatorTabSelected(position);
                }
            });
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.maindfragment, homeFragment).commit();
    }

    private void onNavigatorTabSelected(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                ft.replace(R.id.maindfragment, homeFragment).commit();
                break;
            case 1:
                ImageManager.getInstace().enter();
                break;
            case 2:
                //startActivity(new Intent(this, AlbumActivity.class));
                startActivity(new Intent(this, NewActivity.class));
                break;
            case 3:
                VideoManager.getInstace().enter();
                break;
            case 4:
                ft.replace(R.id.maindfragment, videoFragment).commit();
                break;
        }
    }

    private void setSelectIcon(ImageView iv, TextView tv) {
        iv.setSelected(true);
        tv.setSelected(true);
        lastSelectedIcon = iv;
        lastSelectedText = tv;
    }

    public void initView() {
        initNavigator();
        //显示toolbar
        //mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        //mtoolbar.setTitle(R.string.home_title);
        int color = ContextCompat.getColor(getBaseContext(), R.color.colorToolbarTitle);
        //mtoolbar.setTitleTextColor(color);
        //setSupportActionBar(mtoolbar);
        setDefaultFragment();

        //底部导航监听事件
        //bottomNavigationBar.setTabSelectedListener(this);
    }

    //设置启动页
    private void setDefaultFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.maindfragment, homeFragment).commit();
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
