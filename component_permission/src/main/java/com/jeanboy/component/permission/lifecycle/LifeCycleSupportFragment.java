package com.jeanboy.component.permission.lifecycle;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @author caojianbo
 * @since 2019/12/3 15:54
 */
public class LifeCycleSupportFragment extends Fragment {

    private LifeCycle lifeCycle;

    public LifeCycleSupportFragment() {
        this(new LifeCycle());
    }

    public LifeCycleSupportFragment(LifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        lifeCycle.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifeCycle.onCreate(getContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        lifeCycle.onStart(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        lifeCycle.onResume(getContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        lifeCycle.onPause(getContext());
    }

    @Override
    public void onStop() {
        super.onStop();
        lifeCycle.onStop(getContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifeCycle.onDestroy(getContext());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        lifeCycle.onDetach(getContext());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        lifeCycle.onRequestPermissionsResult(getContext(), requestCode, permissions, grantResults);
    }
}
