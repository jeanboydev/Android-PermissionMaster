package com.jeanboy.component.permission;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.jeanboy.component.permission.core.Watcher;
import com.jeanboy.component.permission.core.WatcherManager;
import com.jeanboy.component.permission.core.Ranger;
import com.jeanboy.component.permission.core.RangerFactory;

/**
 * @author caojianbo
 * @since 2019/12/24 16:17
 */
public class PermissionMaster {

    /**
     * 权限是否已经授权
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean isGranted(@NonNull Context context, @NonNull String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求权限
     *
     * @param context
     * @param permission
     * @param watcher
     */
    public static void request(@NonNull Context context, @NonNull String permission,
                               Watcher watcher) {
        if (isGranted(context, permission)) {
            if (watcher != null) {
                watcher.onGranted();
            }
            return;
        }
        WatcherManager.getInstance().request(context, new String[]{permission}, watcher);
    }


    /**
     * 请求需要打开系统设置界面的权限
     *
     * @param context
     * @param type
     * @param watcher
     */
    public static void request(@NonNull Context context, int type, Watcher watcher) {
        Ranger ranger = RangerFactory.build(type);
        request(context, ranger, watcher);
    }


    /**
     * 请求需要打开系统设置界面的权限，自定义操作
     *
     * @param context
     * @param ranger
     * @param watcher
     */
    public static void request(@NonNull Context context, @NonNull Ranger ranger, Watcher watcher) {
        if (ranger.isGranted(context)) {
            if (watcher != null) {
                watcher.onGranted();
            }
            return;
        }
        WatcherManager.getInstance().request(context, ranger, watcher);
    }
}
