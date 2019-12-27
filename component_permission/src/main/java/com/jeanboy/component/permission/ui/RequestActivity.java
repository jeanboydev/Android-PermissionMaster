package com.jeanboy.component.permission.ui;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.jeanboy.component.permission.core.Watcher;
import com.jeanboy.component.permission.core.WatcherManager;

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] permissions = WatcherManager.getInstance().getPermissions();
        final Watcher watcher =
                WatcherManager.getInstance().getWatcher();
        if (permissions == null) {
            if (watcher != null) {
                watcher.onDenied(false);
            }
            RequestActivity.this.finish();
        }

        WatcherManager.getInstance().request(this, permissions, new Watcher() {
            @Override
            public void onGranted() {
                if (watcher != null) {
                    watcher.onGranted();
                }
                RequestActivity.this.finish();
            }

            @Override
            public void onDenied(boolean isNeverAsk) {
                if (watcher != null) {
                    watcher.onDenied(isNeverAsk);
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
