package com.xeld.cashier.mvp.Interceptor;



import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 曹荣冠
 * on 2020/06/01.
 */
public class BaseUrlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        // 获取request
        Request request = chain.request();
        // 从request中获取原有的HttpUrl实例oldHttpUrl
        HttpUrl oldHttpUrl = request.url();
        // 获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        // 匹配获得新的BaseUrl
        HttpUrl newBaseUrl = null;
        newBaseUrl = oldHttpUrl;
        // 重建新的HttpUrl，修改需要修改的url部分
        HttpUrl newFullUrl = oldHttpUrl
                .newBuilder()
                // 更换网络协议
                .scheme(newBaseUrl.scheme())
                // 更换主机名
                .host(newBaseUrl.host())
                // 更换端口
                .port(newBaseUrl.port())
                .build();
        // 然后返回一个response至此结束修改
        return chain.proceed(builder.url(newFullUrl).build());
    }
}
