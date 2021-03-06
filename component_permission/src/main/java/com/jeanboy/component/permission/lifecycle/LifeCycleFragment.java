package com.jeanboy.component.permission.lifecycle;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

/**
 * @author caojianbo
 * @since 2019/12/3 15:54
 */
public class LifeCycleFragment extends Fragment {

    private LifeCycle lifeCycle;

    public LifeCycleFragment() {
        this(new LifeCycle());
    }

    @SuppressLint("ValidFragment")
    public LifeCycleFragment(LifeCycle lifeCycle) {
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
        lifeCycle.onCreate(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        lifeCycle.onStart(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        lifeCycle.onResume(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        lifeCycle.onPause(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        lifeCycle.onStop(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifeCycle.onDestroy(getActivity());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        lifeCycle.onDetach(getActivity());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        lifeCycle.onRequestPermissionsResult(getActivity(), requestCode, permissions, grantResults);
    }
}
