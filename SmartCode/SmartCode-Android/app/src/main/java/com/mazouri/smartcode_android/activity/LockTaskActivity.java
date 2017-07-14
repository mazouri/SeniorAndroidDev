package com.mazouri.smartcode_android.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mazouri.smartcode_android.R;

import butterknife.OnClick;

/**
 * 屏幕固定功能（screen pinning）
 */
public class LockTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_task);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.lock_task)
    void lockTask() {
        startLockTask();
        Toast.makeText(this, "开启固定屏幕", Toast.LENGTH_SHORT).show();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.unlock_task)
    void unLockTask() {
        stopLockTask();
        Toast.makeText(this, "取消固定屏幕", Toast.LENGTH_SHORT).show();
    }
}
