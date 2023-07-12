package com.xeld.cashier.utils;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpUtils {
    public static final String  BASE_URL="http://www.xiaoerlangdang.com/apis/login?grant_type=admin";

    public static final String  BASE_URL_LOGIN="login?grant_type=admin";


    public static String getXeldUrl(String url){

        return BASE_URL+url;
    }
    public final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    public static OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("xeld","OkHttp====Message:"+message);
            }
        });


        loggingInterceptor.setLevel(level);

        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }

}
