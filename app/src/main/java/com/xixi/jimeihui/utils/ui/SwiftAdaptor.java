package com.xixi.jimeihui.utils.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.xixi.jimeihui.definition.SuperClass;
import java.util.LinkedList;

public final class SwiftAdaptor {
    private FragmentManager fragmentManager;
    private LinkedList<PagerInfo> histories;
    class PagerInfo {
        private int containerViewId;
        private Fragment fragment;
        private SuperClass curClass;
        private  PagerInfo(int containerViewId, Fragment fragment, SuperClass curClass) {
            this.containerViewId = containerViewId;
            this.fragment = fragment;
            this.curClass = curClass;
        }
    }
    public void intialize(FragmentManager ft, int containerViewId, Fragment fragment, SuperClass curClass) {
        fragmentManager = ft;
        histories.add(new PagerInfo(containerViewId, fragment, curClass));
    }
    private SwiftAdaptor() {
        histories = new LinkedList<PagerInfo>();
    }
    private static SwiftAdaptor instance = new SwiftAdaptor();
    public static SwiftAdaptor getInstace() {
        return instance;
    }
    public void goForward(int containerViewId, Fragment fragment, SuperClass curClass) {
        PagerInfo cur = histories.getLast();
        fragmentManager.beginTransaction().replace(cur.containerViewId, fragment).commit();
        histories.add(new PagerInfo(containerViewId, fragment, curClass));
    }
    public void goBack() {
        if (histories.size() <= 1) {
            return;
        }
        PagerInfo cur = histories.removeLast();
        PagerInfo newest = histories.getLast();
        fragmentManager.beginTransaction().replace(cur.containerViewId, newest.fragment).commit();
    }
}
