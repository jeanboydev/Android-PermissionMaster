package com.jeanboy.component.permission.ui;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanboy.component.permission.core.PermissionCallback;
import com.jeanboy.component.permission.core.PermissionLifeManager;

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] permissions = PermissionLifeManager.getInstance().getPermissions();
        final PermissionCallback permissionCallback =
                PermissionLifeManager.getInstance().getPermissionCallback();
        if (permissions == null) {
            if (permissionCallback != null) {
                permissionCallback.onDenied(false);
            }
            RequestActivity.this.finish();
        }

        PermissionLifeManager.getInstance().request(this, permissions, new PermissionCallback() {
            @Override
            public void onGranted() {
                if (permissionCallback != null) {
                    permissionCallback.onGranted();
                }
                RequestActivity.this.finish();
            }

            @Override
            public void onDenied(boolean isNeverAsk) {
                if (permissionCallback != null) {
                    permissionCallback.onDenied(isNeverAsk);
                }
                RequestActivity.this.finish();
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
