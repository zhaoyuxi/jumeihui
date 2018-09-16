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
import com.xixi.jimeihui.definition.SuperClass;
import com.xixi.jimeihui.utils.permission.BaseAppCompatActivity;
import com.xixi.jimeihui.utils.permission.PermissionListener;
import com.xixi.jimeihui.utils.ui.BaseActivity;
import com.xixi.jimeihui.utils.ui.ButtomNavigatorListener;
import com.xixi.jimeihui.utils.ui.SwiftAdaptor;

import java.util.ArrayList;
import java.util.List;

public class ImageManager {
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private Fragment homeFragment;
	private int containerViewId;
	private static ImageManager instance = new ImageManager();
	private ImageManager() {
	}
	public void initialize(int containerViewId) {
		this.containerViewId = containerViewId;
		homeFragment = new ImageFragment();
		fragments.add(homeFragment);
	}
	public static ImageManager getInstace() {
		return instance;
	}
	public void enter() {
		SwiftAdaptor.getInstace().goForward(containerViewId, homeFragment, SuperClass.Imagine);
	}
}
