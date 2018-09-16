package com.xixi.jimeihui.video;


import android.support.v4.app.Fragment;

import com.xixi.jimeihui.definition.SuperClass;
import com.xixi.jimeihui.utils.ui.SwiftAdaptor;

import java.util.ArrayList;

public class VideoManager {
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private Fragment homeFragment;
	private int containerViewId;
	private static VideoManager instance = new VideoManager();
	private VideoManager() {
	}
	public void initialize(int containerViewId) {
		this.containerViewId = containerViewId;
		homeFragment = new VideoFragment();
		fragments.add(homeFragment);
	}
	public static VideoManager getInstace() {
		return instance;
	}
	public void enter() {
		SwiftAdaptor.getInstace().goForward(containerViewId, homeFragment, SuperClass.Video);
	}
}
