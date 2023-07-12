package com.xeld.cashier.utils;

import android.content.Context;
import android.text.TextUtils;


import com.elvishew.xlog.XLog;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.xeld.cashier.BaseAct;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.easyhttp.CommonResultBean;
import com.xeld.cashier.bean.msg.TokenEventBean;
import com.xeld.cashier.mvp.callback.OnRequestSuccess;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.request.PostRequest;
import com.pgyer.pgyersdk.PgyerSDKManager;

import org.json.JSONObject;

/**
 * Created by 曹荣冠
 * on 2020/1/10.
 */
public class JsonParseUtils {
    public static Gson gson = new Gson();


    public JsonParseUtils() {

    }

    private static JsonParseUtils singleton;

    public static JsonParseUtils getInstance() {
        if (singleton == null) {
            synchronized (JsonParseUtils.class) {
                if (singleton == null) {
                    singleton = new JsonParseUtils();
                }
            }
        }
        return singleton;
    }


    /**
     * 如果对方还是要求我们分部解析，则使用这个解析工具，data统一改为String,然后在返回值里面这样处理。
     */
    public static <T> T parse(String result, Class<T> cls) {
        try {
            T bean = new Gson().fromJson(result, cls);
            return bean;
        } catch (Exception e) {
            CommonUtils.setLog("JsonParseUtils", e.getMessage());
            return parseFastJson(result, cls);
        }
    }

    public static <T> T parseFastJson(String result, Class<T> cls) {
        try {
            return com.alibaba.fastjson.JSONObject.parseObject(result, cls);
        } catch (Exception e) {
            CommonUtils.setLog("JsonParseFastJson", e.getMessage());
            CommonViewUtils.showToast("解析错误");
            return null;
        }
    }


    /**
     * 如果对方还是要求我们分部解析，则使用这个解析工具，data统一改为String,然后在返回值里面这样处理。
     */
    public static <T> T parse(Context mContext, JsonElement result, Class<T> cls) {
        try {
            T bean = new Gson().fromJson(result, cls);
            return bean;
        } catch (Exception e) {
            CommonUtils.setLog("JsonParseUtils", e.getMessage());
            CommonUtils.showToast(mContext, "解析错误");
            return null;
        }
    }

    public static <T> void easyPost(BaseAct act, JSONObject requestParams, String url, Class<T> cls, boolean needToken, OnRequestSuccess<T> listener) {
        if (act == null) return;
//        PostRequest postRequest = EasyHttp.post(URL.BASE_URL + url);
        PostRequest postRequest = EasyHttp.post(url);

        if (needToken) {
            postRequest.headers(Constant.TOKEN_KEY, Constant.getToken());
        }
        postRequest.upJson(requestParams.toString())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        XLog.e("ApiException e   " + e.getDisplayMessage());
                        CommonUtils.showToast(act, "网络错误");
                        act.showLoading(false);
                        PgyerSDKManager.reportException(e);
                    }

                    @Override
                    public void onSuccess(String s) {
                        act.showLoading(false);
                        XLog.json(s);
                        commonResult(s, cls, listener);
                    }
                });


    }

    /**
     * get请求方法
     *
     * @param act
     * @param requestParams
     * @param url
     * @param cls
     * @param needToken
     * @param listener
     * @param <T>
     */
    public static <T> void easyGet(Context act, JSONObject requestParams, String url, Class<T> cls, boolean needToken, OnRequestSuccess<T> listener) {

    }

    public static <T> void commonResult(String s, Class<T> cls, OnRequestSuccess<T> listener) {
        if (TextUtils.isEmpty(s)) listener.onResult(null);  //当s为空，则回调空，因为图片不一定必须加载成功。
        CommonResultBean baseBean = com.xeld.cashier.utils.JsonParseUtils.parse(s, CommonResultBean.class);
        if (baseBean == null) return;
        if (baseBean.getResultCode() == 0) {
            T bean = com.xeld.cashier.utils.JsonParseUtils.parse(s, cls);
            if (listener != null) listener.onResult(bean);
        } else if (baseBean.getResultCode() == 200) {
            T bean = com.xeld.cashier.utils.JsonParseUtils.parse(s, cls);
            if (listener != null) listener.onResult(bean);
        } else {
            CommonViewUtils.showToast(baseBean.getResultMsg());
        }
    }


    public static void parseTokenBean(Context act, String resultBean) {
        TokenEventBean tokenEventBean = com.xeld.cashier.utils.JsonParseUtils.parse(resultBean, TokenEventBean.class);
        if (tokenEventBean != null) {
            String accessToken = tokenEventBean.getToken();
            String appToken = "bearer" + accessToken;
            PreferencesUtil.save(act, Constant.SP_TOKEN, appToken);
        }

    }

}
