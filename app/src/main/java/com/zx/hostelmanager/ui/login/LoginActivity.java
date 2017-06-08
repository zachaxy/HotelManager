package com.zx.hostelmanager.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zx.hostelmanager.R;
import com.zx.hostelmanager.ui.home.HomeActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private EditText mName;
    private EditText mPwd;
    private ImageView mShowPwd;
    private Button mLogin;

    private LoginPresenter mPresenter;

    private boolean pwdVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPresenter = new LoginPresenter(this);
        initView();
    }

    private void initView() {
        mName = (EditText) findViewById(R.id.et_login_name);
        mPwd = (EditText) findViewById(R.id.et_login_pwd);
        mShowPwd = (ImageView) findViewById(R.id.img_login_show_pwd);
        mLogin = (Button) findViewById(R.id.btn_login);

        //查看密码功能;
        mShowPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pwdVisible) { //当前可见,在点一下就是不可见
                    mShowPwd.setImageResource(R.drawable.hide_pwd);
                    mPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mPwd.setSelection(mPwd.getText().length());
                    pwdVisible = false;
                } else {//当前不可见,再点一下就是可见
                    mShowPwd.setImageResource(R.drawable.show_pwd);
                    mPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pwdVisible = true;
                }
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString();
                String pwd = mPwd.getText().toString();
                mPresenter.login(name, pwd);
            }
        });
    }

    @Override
    public void loginFailed(String reason) {
        Toast.makeText(this, reason, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    public void invalidateInput() {
        Toast.makeText(this, "用户名或密码不能为空,请重新输入", Toast.LENGTH_LONG).show();
    }
}
