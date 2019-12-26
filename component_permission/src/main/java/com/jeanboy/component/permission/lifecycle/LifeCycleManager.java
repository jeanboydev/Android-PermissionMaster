package com.jeanboy.component.permission.lifecycle;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author caojianbo
 * @since 2019/12/4 16:52
 */
public abstract class LifeCycleManager implements LifeCycleListener {

    protected void bind(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        } else if (!(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                bind((FragmentActivity) context);
            } else if (context instanceof ContextWrapper) {
                bind(((ContextWrapper) context).getBaseContext());
            }
        }
    }

    private void bind(FragmentActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        supportFragmentGet(fragmentManager);
    }

    private void supportFragmentGet(FragmentManager fragmentManager) {
        LifeCycleFragment current = new LifeCycleFragment();
        Fragment ready = fragmentManager.findFragmentByTag(getTag());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (ready != null) {
            fragmentTransaction.remove(ready);
        }
        fragmentTransaction.add(current, getTag()).commitAllowingStateLoss();
        current.getLifeCycle().addListener(this);
    }

    protected abstract String getTag();

    @Override
    public void onAttach(Context context) {
        Log.e(LifeCycleManager.class.getSimpleName(),"=======onAttach======");
    }

    @Override
    public void onCreate(Context context) {
        Log.e(LifeCycleManager.class.getSimpleName(),"=======onCreate======");
    }

    @Override
    public void onStart(Context context) {
        Log.e(LifeCycleManager.class.getSimpleName(),"=======onStart======");
    }

    @Override
    public void onResume(Context context) {
        Log.e(LifeCycleManager.class.getSimpleName(),"=======onResume======");
    }

    @Override
    public void onPause(Context context) {
        Log.e(LifeCycleManager.class.getSimpleName(),"=======onPause======");
    }

    @Override
    public void onStop(Context context) {
        Log.e(LifeCycleManager.class.getSimpleName(),"=======onStop======");
    }

    @Override
    public void onDestroy(Context context) {
        Log.e(LifeCycleManager.class.getSimpleName(),"=======onDestroy======");
    }

    @Override
    public void onDetach(Context context) {
        Log.e(LifeCycleManager.class.getSimpleName(),"=======onDetach======");
    }

    @Override
    public void onRequestPermissionsResult(Context context, int requestCode, String[] permissions,
                                           int[] grantResults) {
        Log.e(LifeCycleManager.class.getSimpleName(),"=======onRequestPermissionsResult======");

    }
}
