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
public class WatcherManager extends LifeCycleManager {

    private String[] permissions;
    private Watcher watcher;
    private Ranger ranger;
    private int actionCount = 0;

    private static volatile WatcherManager instance;

    private WatcherManager() {
    }

    public static WatcherManager getInstance() {
        if (instance == null) {
            synchronized (WatcherManager.class) {
                if (instance == null) {
                    instance = new WatcherManager();
                }
            }
        }
        return instance;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public Watcher getWatcher() {
        return watcher;
    }

    public Ranger getRanger() {
        return ranger;
    }

    public void request(Context context, String[] permissions, Watcher watcher) {
        this.permissions = permissions;
        this.watcher = watcher;
        actionCount = 0;
        bind(context);
    }

    public void request(Context context, Ranger ranger,
                        Watcher permissionCallback) {
        this.ranger = ranger;
        this.watcher = permissionCallback;
        actionCount = 0;
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

        if (ranger != null) {
            if (actionCount > 0) {
                if (ranger.isGranted(context)) {
                    if (watcher != null) {
                        watcher.onGranted();
                    }
                } else {
                    if (watcher != null) {
                        watcher.onDenied(false);
                    }
                }
            } else {
                actionCount++;
                ranger.onAction(context);
            }
        }
    }

    @Override
    public void onDestroy(Context context) {
        super.onDestroy(context);
        actionCount = 0;
    }

    @Override
    public void onRequestPermissionsResult(Context context, int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (Code.REQUEST != requestCode) return;
        if (grantResults.length == 0) {
            if (watcher != null) {
                watcher.onDenied(false);
            }
            return;
        }

        // TODO: 2019/12/26 目前只处理一个权限申请的情况
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (watcher != null) {
                watcher.onGranted();
            }
            return;
        }

        if (context instanceof Activity && permissions.length > 0) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    permissions[0])) {
                // 用户点击了不再询问
                if (watcher != null) {
                    watcher.onDenied(true);
                }
                return;
            }
        }

        if (watcher != null) {
            watcher.onDenied(false);
        }
    }
}
