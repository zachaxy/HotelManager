package com.zx.hostelmanager.ui.login;

import android.text.TextUtils;

import com.zx.hostelmanager.http.RequestImpl;
import com.zx.hostelmanager.model.LoginModel;

import rx.Subscription;

/**
 * Created by zhangxin on 2017/4/21 0021.
 * <p>
 * Description :
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;
    private LoginModel mModel = new LoginModel();

    private static final int LOGIN_OK = 0;
    private static final int LOGIN_NOK = 1;

    public LoginPresenter(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void login(String name, String pwd) {
        //合法性验证;目前仅仅判断是否为空;
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            mView.invalidateInput();
            return;
        }

        mModel.login(name, pwd, new RequestImpl<Integer>() {
            @Override
            public void loadSuccess(Integer result) {
                switch (result) {
                    case LOGIN_OK:
                        mView.loginSuccess();
                        break;
                    case LOGIN_NOK:
                        mView.loginFailed("密码错误");
                        break;
                }
            }

            @Override
            public void loadFailed() {
                mView.loginFailed("网络错误");
            }

            @Override
            public void loadComplete() {

            }

            @Override
            public void addSubscription(Subscription subscription) {

            }
        });
    }
}
