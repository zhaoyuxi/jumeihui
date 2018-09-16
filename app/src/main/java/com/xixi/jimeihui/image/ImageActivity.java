package com.xixi.jimeihui.image;


import android.Manifest;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xixi.jimeihui.PhotoActivity;
import com.xixi.jimeihui.R;
import com.huewu.pla.lib.MultiColumnListView;
import com.xixi.jimeihui.utils.permission.BaseAppCompatActivity;
import com.xixi.jimeihui.utils.permission.PermissionListener;
import com.xixi.jimeihui.utils.ui.BaseActivity;
import com.xixi.jimeihui.utils.ui.ButtomNavigatorListener;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends BaseActivity {
	private ViewPager viewPager;
	ImageFragmentPagerAdapter mImagePagerFragmentAdapter;
    FragmentManager mFragmentManager;
	List<Fragment> mFragmentList = new ArrayList<Fragment>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_activity);
		initBaseView(1);
		permission();
		initFragmentList();
		initViewPager();

        setButtomNavigatorListener(new ButtomNavigatorListener() {
            @Override
            public void onTabSelected(int position) {
                if (position != 1) {
                    finish();
                }
            }
        });
	}
	public void initFragmentList() {
		Fragment image = new ImageFragment();
		mFragmentList.add(image);
	}
	public void initViewPager() {
		viewPager = (ViewPager) findViewById(R.id.image_viewPager);
		viewPager.addOnPageChangeListener(new ViewPagetOnPagerChangedLisenter());
		mFragmentManager = getSupportFragmentManager();
		mImagePagerFragmentAdapter = new ImageFragmentPagerAdapter(mFragmentManager,mFragmentList);
		viewPager.setAdapter(mImagePagerFragmentAdapter);
		viewPager.setCurrentItem(0);
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
	}

	public class ImageFragmentPagerAdapter extends FragmentPagerAdapter {
		private List<Fragment> mList = new ArrayList<Fragment>();
		public ImageFragmentPagerAdapter(FragmentManager fm , List<Fragment> list) {
			super(fm);
			mList = list;
		}

		@Override
		public Fragment getItem(int position) {
			return mList.get(position);
		}

		@Override
		public int getCount() {
			return mList.size();
		}
	}
	class ViewPagetOnPagerChangedLisenter implements ViewPager.OnPageChangeListener {
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		}
		@Override
		public void onPageSelected(int position) {
		}
		@Override
		public void onPageScrollStateChanged(int state) {
		}
	}
}
