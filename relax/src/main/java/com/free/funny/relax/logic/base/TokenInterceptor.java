package com.free.funny.relax.logic.base;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhujunlin on 2017/7/12.
 * Token拦截器
 */

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
//        if (Your.sToken == null || alreadyHasAuthorizationHeader(originalRequest)) {
//            return chain.proceed(originalRequest);
//        }
        Request authorised = originalRequest.newBuilder()
                .header("Authorization", "")
                .build();
        return chain.proceed(authorised);
    }
}
