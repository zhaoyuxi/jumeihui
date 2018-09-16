package com.xixi.jimeihui.utils.permission;

import java.util.List;

/**
 * 已授权、未授权的接口回调
 */
public interface PermissionListener {

    void onGranted();//已授权

    void onDenied(List<String> deniedPermission);//未授权

}