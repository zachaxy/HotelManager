package com.zx.hostelmanager.http;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zhangxin on 2017/4/21 0021.
 * <p>
 * Description :
 */

public interface Api {
    @FormUrlEncoded
    @POST("user/login")
    Observable<Integer> login(@Field("name") String first, @Field("password") String pwd);
}
