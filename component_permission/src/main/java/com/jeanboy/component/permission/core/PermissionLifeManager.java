package com.jeanboy.component.permission.core;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.jeanboy.component.permission.constants.Code;
import com.jeanboy.component.permission.constants.Tag;
import com.jeanboy.component.permission.lifecycle.LifeCycleManager;


/**
 * @author caojianbo
 * @since 2019/12/3 15:58
 */
public class PermissionLifeManager extends LifeCycleManager {

    private String[] permissions;
    private PermissionCallback permissionCallback;
    private SettingsCallback settingsCallback;
    private int settingsOpenedCount = 0;

    private static volatile PermissionLifeManager instance;

    private PermissionLifeManager() {
    }

    public static PermissionLifeManager getInstance() {
        if (instance == null) {
            synchronized (PermissionLifeManager.class) {
                if (instance == null) {
                    instance = new PermissionLifeManager();
                }
            }
        }
        return instance;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public PermissionCallback getPermissionCallback() {
        return permissionCallback;
    }

    public SettingsCallback getSettingsCallback() {
        return settingsCallback;
    }

    public void request(Context context, String[] permissions, PermissionCallback callback) {
        this.permissions = permissions;
        this.permissionCallback = callback;
        settingsOpenedCount = 0;
        bind(context);
    }

    public void request(Context context, SettingsCallback settingsCallback,
                        PermissionCallback permissionCallback) {
        this.settingsCallback = settingsCallback;
        this.permissionCallback = permissionCallback;
        settingsOpenedCount = 0;
        bind(context);
    }

    private void request(FragmentActivity activity, String[] permissions,
                         int requestCode) {
        Fragment lifeFragment = activity.getSupportFragmentManager()
                .findFragmentByTag(getTag());
        if (lifeFragment != null) {
            lifeFragment.requestPermissions(permissions, requestCode);
        }
    }

    @Override
    protected String getTag() {
        return Tag.PERMISSION;
    }

    @Override
    public void onStart(Context context) {
        super.onStart(context);
        if (permissions != null) {
            request((FragmentActivity) context, permissions, Code.REQUEST);
            permissions = null;
        }

        if (settingsCallback != null) {
            if (settingsOpenedCount > 0) {
                if (settingsCallback.isGranted()) {
                    if (permissionCallback != null) {
                        permissionCallback.onGranted();
                    }
                } else {
                    if (permissionCallback != null) {
                        permissionCallback.onDenied(false);
                    }
                }
            } else {
                settingsOpenedCount++;
                settingsCallback.onAction();
            }
        }
    }

    @Override
    public void onDestroy(Context context) {
        super.onDestroy(context);
        settingsOpenedCount = 0;
    }

    @Override
    public void onRequestPermissionsResult(Context context, int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (Code.REQUEST != requestCode) return;
        if (grantResults.length == 0) {
            if (permissionCallback != null) {
                permissionCallback.onDenied(false);
            }
            return;
        }

        // TODO: 2019/12/26 目前只处理一个权限申请的情况
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (permissionCallback != null) {
                permissionCallback.onGranted();
            }
            return;
        }

        if (context instanceof Activity && permissions.length > 0) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    permissions[0])) {
                // 用户点击了不再询问
                if (permissionCallback != null) {
                    permissionCallback.onDenied(true);
                }
                return;
            }
        }

        if (permissionCallback != null) {
            permissionCallback.onDenied(false);
        }
    }
}
