package com.jeanboy.component.permission.core;

import android.content.Context;

/**
 * @author caojianbo
 * @since 2019/12/26 15:03
 */
public interface Ranger {

    boolean isGranted(Context context);

    void onAction(Context context);
}
