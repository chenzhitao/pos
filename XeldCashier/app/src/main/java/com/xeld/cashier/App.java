package com.xeld.cashier;

import android.app.Application;
import android.content.Context;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.ConsolePrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.backup.NeverBackupStrategy;
import com.elvishew.xlog.printer.file.clean.FileLastModifiedCleanStrategy;
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator;
import com.iflytek.cloud.SpeechUtility;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.mvp.Interceptor.BaseUrlInterceptor;
import com.xeld.cashier.mvp.http.Http;
import com.xeld.cashier.mvp.http.URL;
import com.xeld.cashier.utils.NetworkUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.SerializableDiskConverter;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.model.HttpHeaders;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SpeechConstant;

import com.pgyer.pgyersdk.PgyerSDKManager;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class App extends Application {
    public static boolean debug = false;
    public static Context context;
    public static Application application;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        application = this;
        initLog();
        initNetRequest();
        com.igexin.sdk.PushManager.getInstance().initialize(application);
        //讯飞语音合成sdk
        SpeechUtility speechUtility = SpeechUtility.createUtility(application, SpeechConstant.APPID + "=8185faf0");
        //蒲公英日志上传
        initPgyerSDK(application);

    }

    public static Context getContext() {
        return context;
    }


    /**
     * 初始化蒲公英SDK
     *
     * @param application
     */
    private static void initPgyerSDK(Context application) {
        new PgyerSDKManager.Init()
                .setContext(application) //设置上下问对象
                .start();
    }


    private void initLog() {
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(LogLevel.ALL             // 指定日志级别，低于该级别的日志将不会被打印，默认为 LogLevel.ALL
                )
                .tag("ww")                                         // 指定 TAG，默认为 "X-LOG"
                .t()                                                   // 允许打印线程信息，默认禁止
                .st(2)                                                 // 允许打印深度为2的调用栈信息，默认禁止
                .b()                                                   // 允许打印日志边框，默认禁止
                .build();

        Printer androidPrinter = new AndroidPrinter();             // 通过 android.util.Log 打印日志的打印器
        Printer consolePrinter = new ConsolePrinter();             // 通过 System.out 打印日志到控制台的打印器
        Printer filePrinter = new FilePrinter                      // 打印日志到文件的打印器
                .Builder("/sdcard/xlog/")                              // 指定保存日志文件的路径
                .fileNameGenerator(new DateFileNameGenerator())        // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")
                .backupStrategy(new NeverBackupStrategy())             // 指定日志文件备份策略，默认为 FileSizeBackupStrategy(1024 * 1024)
                .cleanStrategy(new FileLastModifiedCleanStrategy(1000 * 60 * 60 * 24))     // 指定日志文件清除策略，默认为 NeverCleanStrategy()
//                        .flattener(new MyFlattener()                          // 指定日志平铺器，默认为 DefaultFlattener

                .build();


        XLog.init(                                                 // Initialize XLog
                config                                                // Specify the log configuration, if not specified, will use new LogConfiguration.Builder().build()
                , androidPrinter                                        // Specify printers, if no printer is specified, AndroidPrinter(for Android)/ConsolePrinter(for java) will be used.
                , consolePrinter
//                ,filePrinter
        );
    }

    /**
     * 网络请求框架
     */
    private void initNetRequest() {
        EasyHttp.init(this);//默认初始化,必须调用

        //全局设置请求头
        HttpHeaders headers = new HttpHeaders();
//        headers.put(Constant.TOKEN_KEY, Constant.getToken());
        //全局设置请求参数
//        HttpParams params = new HttpParams();
//        params.put("appId", AppConstant.APPID);

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        EasyHttp.getInstance()
                //可以全局统一设置全局URL
//                .setBaseUrl(HttpConstant.HOST)//设置全局URL  url只能是域名 或者域名+端口号
                // 打开该调试开关并设置TAG,不需要就不要加入该行
                // 最后的true表示是否打印内部异常，一般打开方便调试错误
                .debug("EasyHttp", true)
                .addCommonHeaders(headers)
                //如果使用默认的60秒,以下三行也不需要设置
                .setReadTimeOut(20 * 1000)
                .setWriteTimeOut(20 * 100)
                .setConnectTimeout(20 * 100)
                //  .setBaseUrl(URL.BASE_URL)
                //可以全局统一设置超时重连次数,默认为3次,那么最差的情况会请求4次(一次原始请求,三次重连请求),
                //不需要可以设置为0
                .setRetryCount(1)//网络不好自动重试3次
                //可以全局统一设置超时重试间隔时间,默认为500ms,不需要可以设置为0
                .setRetryDelay(500)//每次延时500ms重试
                //可以全局统一设置超时重试间隔叠加时间,默认为0ms不叠加
                .setRetryIncreaseDelay(500)//每次延时叠加500ms

                //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体请看CacheMode
                .setCacheMode(CacheMode.NO_CACHE)
                //可以全局统一设置缓存时间,默认永不过期
                .setCacheTime(-1)//-1表示永久缓存,单位:秒 ，Okhttp和自定义RxCache缓存都起作用
                //全局设置自定义缓存保存转换器，主要针对自定义RxCache缓存
                .setCacheDiskConverter(new SerializableDiskConverter())//默认缓存使用序列化转化
                //全局设置自定义缓存大小，默认50M
                .setCacheMaxSize(200 * 1024 * 1024)//设置缓存大小为100M
                //设置缓存版本，如果缓存有变化，修改版本后，缓存就不会被加载。特别是用于版本重大升级时缓存不能使用的情况
                .setCacheVersion(1)//缓存版本为1
                //.setHttpCache(new Cache())//设置Okhttp缓存，在缓存模式为DEFAULT才起作用
                //可以设置https的证书,以下几种方案根据需要自己设置
                .setCertificates()                                  //方法一：信任所有证书,不安全有风险
        //.setCertificates(new SafeTrustManager())            //方法二：自定义信任规则，校验服务端证书
        //配置https的域名匹配规则，不需要就不要加入，使用不当会导致https握手失败
        //.setHostnameVerifier(new SafeHostnameVerifier())
        //.addConverterFactory(GsonConverterFactory.create(gson))//本框架没有采用Retrofit的Gson转化，所以不用配置
//                .addCommonHeaders(headers)//设置全局公共头
//                .addCommonParams(params)//设置全局公共参数
        //.addNetworkInterceptor(new NoCacheInterceptor())//设置网络拦截器
        //.setCallFactory()//局设置Retrofit对象Factory
        //.setCookieStore()//设置cookie
        //.setOkproxy()//设置全局代理
        //.setOkconnectionPool()//设置请求连接池
        //.setCallbackExecutor()//全局设置Retrofit callbackExecutor
        //可以添加全局拦截器，不需要就不要加入，错误写法直接导致任何回调不执行
        //.addInterceptor(new GzipRequestInterceptor())//开启post数据进行gzip后发送给服务器
//                .addInterceptor(new CustomSignInterceptor())//添加参数签名拦截器

        ;
    }

    public static void configOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new BaseUrlInterceptor());
        setLoggingInterceptor(builder);
        setCacheDirectory(builder);
        setCacheInterceptor(builder);
        setTimeout(builder);
        Http.initClient(builder.build(), URL.BASE_URL);
    }

    /**
     * 日志拦截器
     *
     * @param builder
     */
    private static void setLoggingInterceptor(OkHttpClient.Builder builder) {
        if (builder != null && BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
    }

    /**
     * 设置缓存路径
     *
     * @param builder
     */
    public static void setCacheDirectory(OkHttpClient.Builder builder) {
        //设置缓存路径
        Context context = getContext();
        if (context != null) {
            String path = context.getCacheDir().getPath();
            File httpCacheDirectory = new File(path, "responses");
            Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
            builder.cache(cache);
        }
    }

    /**
     * 缓存拦截器
     *
     * @param builder
     */
    private static void setCacheInterceptor(OkHttpClient.Builder builder) {
        if (builder != null) {
            Interceptor cacheInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Context context = getContext();
                    if (context != null) {
//                        if (!Network.isAvailable(context)) {
//                            request = request.newBuilder()
//                                    .cacheControl(CacheControl.FORCE_CACHE)
//                                    .build();
//                        }

                        if (!NetworkUtils.iConnected(context)) {
                            request = request.newBuilder()
                                    .cacheControl(CacheControl.FORCE_CACHE)
                                    .build();
                        }

                        Response response = chain.proceed(request);

//                        if (Network.isAvailable(context)) {
//                            int maxAge = 0;
//                            // 有网络时,设置缓存超时时间0个小时
//                            response.newBuilder()
//                                    .header("Cache-Control", "public, max-age=" + maxAge)
//                                    .removeHeader("Pragma")
//                                    .build();
//                        }

                        if (NetworkUtils.iConnected(context)) {
                            int maxAge = 0;
                            // 有网络时,设置缓存超时时间0个小时
                            response.newBuilder()
                                    .header("Cache-Control", "public, max-age=" + maxAge)
                                    .removeHeader("Pragma")
                                    .build();
                        } else {
                            // 无网络时,设置超时为4周
                            int maxStale = 60 * 60 * 24 * 3;
                            response.newBuilder()
                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                    .removeHeader("Pragma")
                                    .build();
                        }

                        return response;
                    } else {
                        //默认当作有网络处理
                        int maxAge = 0;
                        Response response = chain.proceed(request);
                        response.newBuilder()
                                .header("Cache-Control", "public, max-age=" + maxAge)
                                .removeHeader("Pragma")
                                .build();
                        return response;
                    }
                }
            };
            builder.addInterceptor(cacheInterceptor);
        }
    }

    /**
     * 设置超时和重试
     *
     * @param builder
     */
    private static void setTimeout(OkHttpClient.Builder builder) {
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.writeTimeout(15, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
    }

    public static Application getInstance() {
        return application;
    }

    public static boolean isHaveCamera() {
//        HashMap<String, UsbDevice> deviceHashMap = ((UsbManager) getSystemService(Activity.USB_SERVICE)).getDeviceList();
//        for (Map.Entry entry : deviceHashMap.entrySet()) {
//            UsbDevice usbDevice = (UsbDevice) entry.getValue();
//            if (!TextUtils.isEmpty(usbDevice.getInterface(0).getName()) && usbDevice.getInterface(0).getName().contains("Orb")) {
//                return true;
//            }
//            if (!TextUtils.isEmpty(usbDevice.getInterface(0).getName()) && usbDevice.getInterface(0).getName().contains("Astra")) {
//                return true;
//            }
//        }
        return false;
    }

}
