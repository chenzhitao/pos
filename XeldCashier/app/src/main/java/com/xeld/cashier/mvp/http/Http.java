package com.xeld.cashier.mvp.http;




import com.xeld.cashier.App;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class Http {
    private static OkHttpClient client;
    private static Map<String, Object> serviceMap = new HashMap<>();
    private static String BaseUrl;

    public static <T> T getApiService(Class<T> service) {
        if (client==null){
            App.configOkHttp();
        }
        Object object = serviceMap.get(service.getCanonicalName());
        if (object == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            object = retrofit.create(service);
            serviceMap.put(service.getCanonicalName(), object);
        } else {
            object = serviceMap.get(service.getCanonicalName());
        }
        return (T) object;
    }

    public static void initClient(OkHttpClient client1, String baseUrl) {
        client = client1;
        BaseUrl = baseUrl;
    }
}
