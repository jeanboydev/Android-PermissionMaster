package com.jeanboy.app.permissionmaster.views;

import android.Manifest;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.jeanboy.app.permissionmaster.R;
import com.jeanboy.app.permissionmaster.utils.ScreenUtil;
import com.jeanboy.component.permission.PermissionMaster;
import com.jeanboy.component.permission.core.PermissionCallback;
import com.jeanboy.component.permission.utils.OverlaysUtil;

/**
 * @author caojianbo
 * @since 2019/12/26 16:03
 */
public class FloatView extends View {

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private int screenWidth;
    private int screenHeight;

    public FloatView(Context context) {
        this(context, null);
    }

    public FloatView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    private WindowManager getWindowManager() {
        if (windowManager == null) {
            windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }

    private void setup() {
        setBackgroundColor(getResources().getColor(R.color.colorAccent));
        int[] pixels = ScreenUtil.getPixels(getContext());
        screenWidth = pixels[0];
        screenHeight = pixels[1];
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(FloatView.class.getSimpleName(), "=====onClick=====");
                PermissionMaster.request(getContext(), Manifest.permission.ACCESS_FINE_LOCATION,
                        new PermissionCallback() {
                            @Override
                            public void onGranted() {
                                Log.e(FloatView.class.getSimpleName(), "======onGranted======");
                            }

                            @Override
                            public void onDenied(boolean isNeverAsk) {
                                Log.e(FloatView.class.getSimpleName(), "======onDenied" +
                                        "===isNeverAsk===" + isNeverAsk);
                            }
                        });
            }
        });
    }

    private WindowManager.LayoutParams getParams() {
        if (layoutParams == null) {
            layoutParams = OverlaysUtil.getLayoutParamsWithType();
            layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            layoutParams.format = PixelFormat.TRANSLUCENT;
            layoutParams.gravity = Gravity.START | Gravity.TOP;
        }
        return layoutParams;
    }

    public void show() {
        if (this.getParent() != null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = getParams();
        layoutParams.width = 200;
        layoutParams.height = 200;
        layoutParams.x = 0;
        layoutParams.y = screenHeight / 4;
        getWindowManager().addView(this, layoutParams);
    }
}
