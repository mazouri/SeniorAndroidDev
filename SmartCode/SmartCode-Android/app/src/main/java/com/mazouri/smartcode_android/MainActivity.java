package com.mazouri.smartcode_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.mazouri.smartcode_android.activity.BasicActivity;
import com.mazouri.smartcode_android.activity.LockTaskActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.go_basic)
    Button mGoBasic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.go_basic)
    void goBasic() {
        startActivity(new Intent(this, BasicActivity.class));
    }

    @OnClick(R.id.go_locktask)
    void goLockTask() {
        startActivity(new Intent(this, LockTaskActivity.class));
    }
}
