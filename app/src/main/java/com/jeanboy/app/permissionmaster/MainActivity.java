package com.jeanboy.app.permissionmaster;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanboy.app.permissionmaster.views.FloatView;
import com.jeanboy.component.permission.PermissionMaster;
import com.jeanboy.component.permission.constants.Type;
import com.jeanboy.component.permission.core.Watcher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toRequestLocationPermission(View view) {
        PermissionMaster.request(this, Manifest.permission.ACCESS_FINE_LOCATION,
                new Watcher() {
                    @Override
                    public void onGranted() {
                        Log.e(MainActivity.class.getSimpleName(), "======onGranted======");
                    }

                    @Override
                    public void onDenied(boolean isNeverAsk) {
                        Log.e(MainActivity.class.getSimpleName(),
                                "======onDenied=====isNeverAsk=" + isNeverAsk);
                    }
                });
    }

    public void toRequestOverlaysPermission(View view) {
        PermissionMaster.request(this, Type.OVERLAYS, new Watcher() {
            @Override
            public void onGranted() {
                Log.e(MainActivity.class.getSimpleName(), "======onGranted======");
                FloatView floatView = new FloatView(getApplicationContext());
                floatView.show();
            }

            @Override
            public void onDenied(boolean isNeverAsk) {
                Log.e(MainActivity.class.getSimpleName(),
                        "======onDenied=====isNeverAsk=" + isNeverAsk);
            }
        });
    }
}
