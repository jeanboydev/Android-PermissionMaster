package com.jeanboy.component.permission.core.module;

import android.content.Context;

import com.jeanboy.component.permission.core.Ranger;
import com.jeanboy.component.permission.utils.OverlaysUtil;
import com.jeanboy.component.permission.utils.SettingsUtil;

/**
 * @author caojianbo
 * @since 2019/12/27 14:20
 */
public class OverlaysModule implements Ranger {

    @Override
    public boolean isGranted(Context context) {
        return OverlaysUtil.isCanDraw(context);
    }

    @Override
    public void onAction(Context context) {
        SettingsUtil.toOpenOverlaySettings(context);
    }
}
