package com.jeanboy.component.permission.core;

/**
 * @author caojianbo
 * @since 2019/12/26 15:03
 */
public interface SettingsCallback {

    boolean isGranted();

    void onAction();
}
