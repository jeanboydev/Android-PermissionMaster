package com.jeanboy.component.permission;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.jeanboy.component.permission.core.PermissionCallback;
import com.jeanboy.component.permission.core.PermissionLifeManager;
import com.jeanboy.component.permission.core.SettingsCallback;
import com.jeanboy.component.permission.utils.OverlaysUtil;
import com.jeanboy.component.permission.utils.SettingsUtil;

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
    public static boolean isGranted(@NonNull Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 请求权限
     *
     * @param context
     * @param permission
     * @param callback
     */
    public static void request(Context context, String permission,
                               PermissionCallback callback) {
        if (isGranted(context, permission)) {
            if (callback != null) {
                callback.onGranted();
            }
            return;
        }
        PermissionLifeManager.getInstance().request(context, new String[]{permission}, callback);
    }

    /**
     * 请求悬浮窗权限
     *
     * @param context
     * @param callback
     */
    public static void requestOverlay(final Context context, PermissionCallback callback) {
        if (OverlaysUtil.isCanDraw(context)) {
            if (callback != null) {
                callback.onGranted();
            }
            return;
        }
        PermissionLifeManager.getInstance().request(context, new SettingsCallback() {
            @Override
            public boolean isGranted() {
                return OverlaysUtil.isCanDraw(context);
            }

            @Override
            public void onAction() {
                SettingsUtil.toOpenOverlaySettings(context);
            }
        }, callback);
    }
}
