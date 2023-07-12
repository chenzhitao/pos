package com.xeld.cashier.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.elvishew.xlog.XLog;
import com.xeld.cashier.BaseAct;
import com.xeld.cashier.R;
import com.xeld.cashier.base.fragment.BaseEventBean;
import com.xeld.cashier.bean.UserInfoBean;
import com.xeld.cashier.bean.RecordListBean;
import com.xeld.cashier.mvp.http.URL;
import com.xeld.cashier.webView.WebAppInterface;
import com.xeld.cashier.constant.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShiftJobActivity extends BaseAct {

    @BindView(R.id.webview_shift_job)
    WebView mWebView;

//    @BindView(R.id.ly_logo_info)
//    LinearLayout layoutLogoInfo;

    WebAppInterface webAppInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_job);
        ButterKnife.bind(this);//一定要在layout初始化之后再绑定。
        EventBus.getDefault().register(this);


//        CommonViewUtils.setOnClick(layoutLogoInfo, view -> {
//            Intent intent = new Intent(getApplicationContext(), TestActivity.class);
//            startActivity(intent);
//            finish();
//        });

        WebSettings settings = mWebView.getSettings();
        //默认是false 设置true允许和js交互
        settings.setJavaScriptEnabled(true);
        //  WebSettings.LOAD_DEFAULT 如果本地缓存可用且没有过期则使用本地缓存，否加载网络数据 默认值
        //  WebSettings.LOAD_CACHE_ELSE_NETWORK 优先加载本地缓存数据，无论缓存是否过期
        //  WebSettings.LOAD_NO_CACHE  只加载网络数据，不加载本地缓存
        //  WebSettings.LOAD_CACHE_ONLY 只加载缓存数据，不加载网络数据
        //Tips:有网络可以使用LOAD_DEFAULT 没有网时用LOAD_CACHE_ELSE_NETWORK
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //开启 DOM storage API 功能 较大存储空间，使用简单
        settings.setDomStorageEnabled(true);
        //设置数据库缓存路径 存储管理复杂数据 方便对数据进行增加、删除、修改、查询 不推荐使用
        settings.setDatabaseEnabled(true);
        final String dbPath = getApplicationContext().getDir("db", Context.MODE_PRIVATE).getPath();
        settings.setDatabasePath(dbPath);
        //开启 Application Caches 功能 方便构建离线APP 不推荐使用
        settings.setAppCacheEnabled(true);
        final String cachePath = getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        settings.setAppCachePath(cachePath);
        settings.setAppCacheMaxSize(5 * 1024 * 1024);

        webAppInterface = new WebAppInterface(this, mWebView);
        mWebView.addJavascriptInterface(webAppInterface, "android");
        mWebView.loadUrl("file:///android_asset/index.html");

        initData();
    }

    private void initData() {
//        userShiftStatistics();
//        getUserByUserId();
    }

    public void backAct() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 处理扫码支付硬件信息
     * 扫码枪扫码指令-通过广播接受者，传递在此，在此次做分发处理
     *
     * @param eventBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(BaseEventBean eventBean) {
        XLog.d("handleEvent  =  " + eventBean.getType());
    }


    /**
     * 交接班，获取交接班信息
     */
    public void userShiftStatistics(String jsModthName) {
        try {
            JSONObject json = new JSONObject();
//            json.put("shopId", Constant.getShopId());
//            json.put("userId", Constant.getUserId());
            showLoading(true);

            easyPost(json, URL.DEBUG_URL + URL.USERSHIFTSTATISTICS, String.class, resultBean -> {
//                onUserShiftResult(resultBean);
                webAppInterface.loadJSWithParam(jsModthName, resultBean);
            });
        } catch (Exception e) {

            XLog.e(e);
        }
    }

    private void onUserShiftResult(String result) {
        showLoading(false);
        XLog.d(result);
    }

    /**
     * 会员的充值记录
     */
    public void selectDepositMoneyRecord() {
        try {
            JSONObject json = new JSONObject();
//            json.put("userId", Constant.getUserId());
            json.put("mobile", "");
            json.put("startTime", "");
            json.put("current", 1);

            easyPost(json, URL.DEBUG_URL + URL.SELECTDEPOSITMONEYRECORD, RecordListBean.class, resultBean -> {
                onRecordResult(resultBean);
            });
        } catch (Exception e) {

            XLog.e(e);
        }
    }

    private void onRecordResult(RecordListBean relult) {
        XLog.d(relult);
        if (relult.getData() == null) {
            showToast("没有数据哟");
            return;
        }

        for (int i = 0; i < relult.getData().getRecords().size(); i++) {

            XLog.d("shop = " + relult.getData().getRecords().get(i).getShopId() + " amount = "
                    + relult.getData().getRecords().get(i).getAmount());
        }
    }


}