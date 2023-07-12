package com.xeld.cashier.webView;

import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.ui.login.LoginActivity;
import com.xeld.cashier.ui.login.ShiftJobActivity;
import com.xeld.cashier.utils.PreferencesUtil;

public class WebAppInterface {

    ShiftJobActivity mContext;
    WebView mWebView;

    /**
     * Instantiate the interface and set the context
     */
    public WebAppInterface(ShiftJobActivity c, WebView webView) {
        this.mContext = c;
        this.mWebView = webView;
    }

    /**
     * Show a toast from the web page
     */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    /**
     * js 调用 退出当前Activity
     */
    @JavascriptInterface
    public void goBack() {
        mContext.backAct();
    }

    /**
     * js 调用 获取 收银员的姓名
     */
    @JavascriptInterface
    public void getShopOwner(String jsName) {
        String shopOwner = PreferencesUtil.getString(mContext, Constant.SP_SHOPOWNER);
        loadJSWithParam(jsName, shopOwner);
    }


    /**
     * js 调用 退出当前Activity
     */
    @JavascriptInterface
    public void loginOut() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
        mContext.finish();
    }

    /**
     * js 调用android 方法
     *
     * @param JsModthName
     */
    @JavascriptInterface
    public void getData(String jsModthName) {
        mContext.userShiftStatistics(jsModthName);
    }


    /**
     * 加载带参数的JS函数
     *
     * @param JsName JS函数名
     * @param params 不定参数
     */
    public void loadJSWithParam(String JsName, String... params) {
        String TotalParam = "";
        for (int i = 0; i < params.length; i++) {
            if (i == params.length - 1) {
                //最后一个
                TotalParam += (params[i]);
            } else {
                TotalParam += (params[i] + "','");
            }
        }
        mWebView.loadUrl("javascript:" + JsName + "('" + TotalParam + "')");
    }

    /**
     * 加载不带参数的JS函数
     *
     * @param JsName JS函数名
     */
    public void loadJS(String JsName) {
        mWebView.loadUrl("javascript:" + JsName + "()");
    }

    /**
     * 为了方便获取String 类型的字符串
     *
     * @param s 加‘’号的参数
     * @return 加了‘’号的参数
     */
    private static String getJsStringParam(String s) {
        return "'" + s + "'";
    }


    public static String makeSentence(String world1, String world2) {
        return "javascript:makeSentence(" + getJsStringParam(world1) + "," + getJsStringParam(world2) + ")";   // 这里要注意的是，若是传递的参数是字符串，那么在拼接调用的url的时候需要对参数加上‘’号。
    }


    public interface JsDataCallback {


        void loadJSWithParam(String JsName, String... params);
    }

}
