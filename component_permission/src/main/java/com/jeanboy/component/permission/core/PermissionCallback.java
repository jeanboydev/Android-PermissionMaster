package com.jeanboy.component.permission.core;

/**
 * @author caojianbo
 * @since 2019/12/3 15:02
 */
public interface PermissionCallback {

    void onGranted();

    void onDenied(boolean isNeverAsk);
}
