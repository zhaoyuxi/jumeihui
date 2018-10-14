package com.xixi.jimeihui.image;

import android.support.v4.app.Fragment;
import com.xixi.jimeihui.definition.SuperClass;
import com.xixi.jimeihui.utils.ui.SwiftAdaptor;
import java.util.ArrayList;

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
