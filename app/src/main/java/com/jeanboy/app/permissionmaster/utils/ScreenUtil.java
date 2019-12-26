package com.jeanboy.app.permissionmaster.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author caojianbo
 * @since 2019/12/26 16:07
 */
public class ScreenUtil {

    /**
     * 获取屏幕像素
     *
     * @param context
     * @return widthPixels, heightPixels
     */
    public static int[] getPixels(Context context) {
        WindowManager windowManager =
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager == null) return new int[]{0, 0};
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return new int[]{metrics.widthPixels, metrics.heightPixels};
    }
}
