package com.zx.hostelmanager.ui.login;

/**
 * Created by zhangxin on 2017/4/21 0021.
 * <p>
 * Description :
 */

public class LoginContract {
    interface View {
        void loginFailed(String reason);

        void loginSuccess();

        void invalidateInput();
    }

    interface Presenter {
        void login(String name, String pwd);
    }
}
