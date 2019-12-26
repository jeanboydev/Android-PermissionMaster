package com.jeanboy.component.permission.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jeanboy.component.permission.ui.RequestActivity;

/**
 * @author caojianbo
 * @since 2019/12/4 16:52
 */
public abstract class LifeCycleManager implements LifeCycleListener {

    protected void bind(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        } else if (context instanceof Application) { // 悬浮窗请求权限
            Intent intent = new Intent(context, RequestActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(intent);
        } else {
            if (context instanceof FragmentActivity) {
                bind((FragmentActivity) context);
            } else if (context instanceof Activity) {
                bind((Activity) context);
            } else if (context instanceof ContextWrapper
                    && ((ContextWrapper) context).getBaseContext().getApplicationContext() != null) {
                bind(((ContextWrapper) context).getBaseContext());
            }
        }
    }

    private void bind(Activity activity) {
        android.app.FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentGet(fragmentManager);
    }

    private void bind(FragmentActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        supportFragmentGet(fragmentManager);
    }

    private void supportFragmentGet(FragmentManager fragmentManager) {
        LifeCycleSupportFragment current = new LifeCycleSupportFragment();
        Fragment ready = fragmentManager.findFragmentByTag(getTag());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (ready != null) {
            fragmentTransaction.remove(ready);
        }
        fragmentTransaction.add(current, getTag()).commitAllowingStateLoss();
        current.getLifeCycle().addListener(this);
    }

    private void fragmentGet(android.app.FragmentManager fragmentManager) {
        LifeCycleFragment current = new LifeCycleFragment();
        android.app.Fragment ready = fragmentManager.findFragmentByTag(getTag());
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (ready != null) {
            fragmentTransaction.remove(ready);
        }
        fragmentTransaction.add(current, getTag()).commitAllowingStateLoss();
        current.getLifeCycle().addListener(this);
    }

    protected abstract String getTag();

    @Override
    public void onAttach(Context context) {
    }

    @Override
    public void onCreate(Context context) {
    }

    @Override
    public void onStart(Context context) {
    }

    @Override
    public void onResume(Context context) {
    }

    @Override
    public void onPause(Context context) {
    }

    @Override
    public void onStop(Context context) {
    }

    @Override
    public void onDestroy(Context context) {
    }

    @Override
    public void onDetach(Context context) {
    }

    @Override
    public void onRequestPermissionsResult(Context context, int requestCode, String[] permissions,
                                           int[] grantResults) {
    }
}
