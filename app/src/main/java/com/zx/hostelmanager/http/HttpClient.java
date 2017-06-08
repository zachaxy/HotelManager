package com.zx.hostelmanager.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangxin on 2017/4/21 0021.
 * <p>
 * Description :
 */

public class HttpClient {
    private static final String BASE_SERVER_API = "http://www.xxx/";

   /* private HttpClient() {
    }*/

    public static Api getClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(Api.class);
    }
}
