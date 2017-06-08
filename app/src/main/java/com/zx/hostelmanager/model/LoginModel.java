package com.zx.hostelmanager.model;

import com.zx.hostelmanager.http.HttpClient;
import com.zx.hostelmanager.http.RequestImpl;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhangxin on 2017/4/21 0021.
 * <p>
 * Description :
 */

public class LoginModel {


    public void login(String name, String pwd, final RequestImpl<Integer> request) {
        Subscription subscription =
                HttpClient.getClient()
                        .login(name, pwd)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                request.loadComplete();
                            }

                            @Override
                            public void onError(Throwable e) {
                                request.loadFailed();
                            }

                            @Override
                            public void onNext(Integer integer) {
                                request.loadSuccess(integer);
                            }
                        });
    }
}
