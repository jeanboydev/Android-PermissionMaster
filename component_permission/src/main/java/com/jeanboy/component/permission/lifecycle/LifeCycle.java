package com.jeanboy.component.permission.lifecycle;

import android.content.Context;
import android.os.Bundle;

/**
 * @author caojianbo
 * @since 2019/12/3 15:52
 */
public class LifeCycle implements LifeCycleListener {

    private LifeCycleListener lifeCycleListener;

    public void addListener(LifeCycleListener listener) {
        this.lifeCycleListener = listener;
    }

    @Override
    public void onAttach(Context context) {
        if (lifeCycleListener != null) {
            lifeCycleListener.onAttach(context);
        }
    }

    @Override
    public void onCreate(Context context) {
        if (lifeCycleListener != null) {
            lifeCycleListener.onCreate(context);
        }
    }

    @Override
    public void onStart(Context context) {
        if (lifeCycleListener != null) {
            lifeCycleListener.onStart(context);
        }
    }

    @Override
    public void onResume(Context context) {
        if (lifeCycleListener != null) {
            lifeCycleListener.onResume(context);
        }
    }

    @Override
    public void onPause(Context context) {
        if (lifeCycleListener != null) {
            lifeCycleListener.onPause(context);
        }
    }

    @Override
    public void onStop(Context context) {
        if (lifeCycleListener != null) {
            lifeCycleListener.onStop(context);
        }
    }

    @Override
    public void onDestroy(Context context) {
        if (lifeCycleListener != null) {
            lifeCycleListener.onDestroy(context);
        }
    }

    @Override
    public void onDetach(Context context) {
        if (lifeCycleListener != null) {
            lifeCycleListener.onDetach(context);
        }
    }

    @Override
    public void onRequestPermissionsResult(Context context, int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (lifeCycleListener != null) {
            lifeCycleListener.onRequestPermissionsResult(context, requestCode, permissions,
                    grantResults);
        }
    }
}
