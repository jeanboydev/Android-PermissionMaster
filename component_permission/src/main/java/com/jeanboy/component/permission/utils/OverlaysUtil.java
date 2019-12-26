package com.jeanboy.component.permission.utils;

import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author caojianbo
 * @since 2019/12/26 15:53
 */
public class OverlaysUtil {

    /**
     * 是否有悬浮窗权限
     *
     * @param context
     * @return
     */
    public static boolean isCanDraw(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // 6.0 以上
            return Settings.canDrawOverlays(context);
        } else { // 6.0 以下
            try {
                Class cls = Class.forName("android.content.Context");
                Field declaredField = cls.getDeclaredField("APP_OPS_SERVICE");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(cls);
                if (!(obj instanceof String)) {
                    return false;
                }
                String str2 = (String) obj;
                obj = cls.getMethod("getSystemService", String.class).invoke(context, str2);
                cls = Class.forName("android.app.AppOpsManager");
                Field declaredField2 = cls.getDeclaredField("MODE_ALLOWED");
                declaredField2.setAccessible(true);
                Method checkOp = cls.getMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class);
                int result = (Integer) checkOp.invoke(obj, 24, Binder.getCallingUid(),
                        context.getPackageName());
                return result == declaredField2.getInt(cls);
            } catch (Exception e) {
                return false;
            }
        }
    }


    /**
     * 获取带 type 的 LayoutParams
     *
     * @return
     */
    public static WindowManager.LayoutParams getLayoutParamsWithType() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 8.0 以上
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        return params;
    }
}
