package com.jeanboy.component.permission.lifecycle;

import android.content.Context;
import android.os.Bundle;

/**
 * @author caojianbo
 * @since 2019/12/3 15:50
 */
public interface LifeCycleListener {

    void onAttach(Context context);

    void onCreate(Context context);

    void onStart(Context context);

    void onResume(Context context);

    void onPause(Context context);

    void onStop(Context context);

    void onDestroy(Context context);

    void onDetach(Context context);

    void onRequestPermissionsResult(Context context, int requestCode, String[] permissions,
                                    int[] grantResults);
}
