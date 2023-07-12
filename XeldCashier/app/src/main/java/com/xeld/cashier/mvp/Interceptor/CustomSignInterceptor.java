package com.xeld.cashier.mvp.Interceptor;


import android.text.TextUtils;

import com.xeld.cashier.constant.Constant;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 曹荣冠
 * on 2020/5/27.
 * 添加token 拦截器，默认所有接口都添加token
 * EasyHttp的拦截器
 */
public class CustomSignInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        // 获取request
        Request request = chain.request();
        // 从request中获取原有的HttpUrl实例oldHttpUrl
        HttpUrl oldHttpUrl = request.url();
        // 获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        List<String> headerValues = request.headers(Constant.TOKEN_KEY);//获取token
        if (TextUtils.equals(request.method(), "GET")) {
            builder.addHeader(Constant.TOKEN_KEY, Constant.getToken());
        } else {
            if (headerValues == null || headerValues.size() == 0) { //当请求没加token，则自动加上。
//                builder.addHeader(Constant.TOKEN_KEY, Constant.getToken());
            } else {
                String token = headerValues.get(0);
                if (TextUtils.isEmpty(token)) {//如果token没值则再次添加一下。
                   // builder.removeHeader(Constant.TOKEN_KEY);
//                    builder.addHeader(Constant.TOKEN_KEY, Constant.getToken());
                }
            }
        }
        return chain.proceed(builder.url(oldHttpUrl).build());
    }


}
