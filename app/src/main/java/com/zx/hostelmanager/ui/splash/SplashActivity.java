package com.zx.hostelmanager.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zx.hostelmanager.R;
import com.zx.hostelmanager.ui.home.HomeActivity;
import com.zx.hostelmanager.ui.login.LoginActivity;
import com.zx.hostelmanager.utils.Constant;

public class SplashActivity extends AppCompatActivity {

    private static final int jump2login = 1;
    private static final int jump2home = 2;

    SharedPreferences sp;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case jump2login:
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                    break;
                case jump2home:
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sp = getSharedPreferences(Constant.LOGIN_SP, Context.MODE_PRIVATE);
        if (!sp.contains("name")) { //不包含,跳转到登陆页面
//            mHandler.sendEmptyMessageDelayed(jump2login, 3000);
            mHandler.sendEmptyMessageDelayed(jump2home, 3000);
        } else { //包含,跳转到主页面
            mHandler.sendEmptyMessageDelayed(jump2home, 3000);
        }

    }


/*    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }*/
}
