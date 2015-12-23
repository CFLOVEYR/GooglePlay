package com.beyondself.jalen.googleplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    private static BaseActivity mForegroundActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        initView();
    }

    protected abstract void initView();

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        this.mForegroundActivity = this;
    }


    public static BaseActivity getForegroundActivity() {
        return mForegroundActivity;
    }
}
