package com.jeanboy.component.permission.core;

/**
 * @author caojianbo
 * @since 2019/12/3 15:02
 */
public interface PermissionCallback {

    /**
     * 已授权
     */
    void onGranted();

    /**
     * 权限被拒绝
     *
     * @param isNeverAsk 已选择不再询问
     */
    void onDenied(boolean isNeverAsk);
}
