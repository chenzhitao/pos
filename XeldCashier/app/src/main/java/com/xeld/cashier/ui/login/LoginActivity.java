package com.xeld.cashier.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.xeld.cashier.BuildConfig;
import com.xeld.cashier.bean.ScanCodePayBean;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.elvishew.xlog.XLog;
import com.pgyer.pgyersdk.PgyerSDKManager;
import com.xeld.cashier.BaseAct;
import com.xeld.cashier.R;
import com.xeld.cashier.bean.LoginBean;
import com.xeld.cashier.bean.LoginInfoBean;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.mvp.http.URL;
import com.xeld.cashier.utils.CommonViewUtils;
import com.xeld.cashier.utils.JsonParseUtils;
import com.xeld.cashier.utils.PermissionUtil;
import com.xeld.cashier.utils.PreferencesUtil;
import com.xeld.cashier.utils.update.UploadAppProxy;
import com.xw.repo.XEditText;
import com.zhouyou.http.exception.ApiException;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import com.xeld.cashier.mvp.callback.OnRequestSuccess;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseAct {
    public static boolean isFirst = true;

    @BindView(R.id.login_btn)
    Button login_btn;
    @BindView(R.id.account_et)
    XEditText account_et;
    @BindView(R.id.input_pwd)
    XEditText input_pwd;

    //自动登录
    @BindView(R.id.remember_pwd)
    AppCompatCheckBox remember_pwd;

    //自动登录
    @BindView(R.id.auto_ogon)
    AppCompatCheckBox auto_ogon;


    boolean rememberPsw, autoLogin;//默认记住密码自动登录
    private Runnable runnable;


    public static String PHONE_KEY = "phone";
    public static String ACCOUNT_STR = "pwd";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBar();

        setContentView(R.layout.activity_xeld_login_layout);
        ButterKnife.bind(this);//一定要在layout初始化之后再绑定。
        mContext = this;
        initPermission();

        CommonViewUtils.getInputEnterResult(this, account_et);
        CommonViewUtils.getInputEnterResult(this, input_pwd);
        //记住密码
        remember_pwd.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            PreferencesUtil.setBoolean(mContext, Constant.REMEMBER_PSW_KEY, isChecked);
        });

        //是否自动登录
        auto_ogon.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            PreferencesUtil.setBoolean(mContext, Constant.AUTO_LOGIN, isChecked);
        });

        if (PreferencesUtil.getBoolean(mContext, Constant.REMEMBER_PSW_KEY, false)) {
//            PreferencesUtil.save(mContext, PHONE_KEY, "jiangziweidao");
//            PreferencesUtil.save(mContext, ACCOUNT_STR, "jzwd123456");
            account_et.setText(PreferencesUtil.getString(mContext, PHONE_KEY));
            input_pwd.setText(PreferencesUtil.getString(mContext, ACCOUNT_STR));
        }

        if (PreferencesUtil.getBoolean(mContext, Constant.AUTO_LOGIN, false)) {

            account_et.setText(PreferencesUtil.getString(mContext, PHONE_KEY));
            input_pwd.setText(PreferencesUtil.getString(mContext, ACCOUNT_STR));
//            postLogin();
        }

//        PreferencesUtil.save(mContext, PHONE_KEY, "admin");
//        PreferencesUtil.save(mContext, ACCOUNT_STR, "xeld2020");
//        PreferencesUtil.save(mContext, PHONE_KEY, "jiangziweidao");
//        PreferencesUtil.save(mContext, ACCOUNT_STR, "jzwd123456");
        PreferencesUtil.save(mContext, PHONE_KEY, "xeld");
        PreferencesUtil.save(mContext, ACCOUNT_STR, "123456");
        account_et.setText(PreferencesUtil.getString(mContext, PHONE_KEY));
        input_pwd.setText(PreferencesUtil.getString(mContext, ACCOUNT_STR));

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postLogin();

            }
        });

//        getVersion();
//        checkVersion();
    }

    /**
     * 商家登录返回token
     */
    private void postLogin() {
        if (account_et == null) return;
        String accountStr = account_et.getText().toString().trim();
        String pwdStr = input_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(accountStr)) {
            showToast("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(pwdStr)) {
            showToast("请输入密码");
            return;
        }
        saveSp(accountStr, pwdStr);//所有编辑选项内容会在登录提交之时保持
        try {
            JSONObject json = new JSONObject();
            json.put("principal", accountStr);
            json.put("credentials", pwdStr);
            json.put("loginType", "1");
            json.put("cid", PreferencesUtil.getString(this, Constant.SP_CID));//个推的cid
            showLoading();
            easyPostWithOutToken(json, URL.DEBUG_URL + URL.LOGIN, LoginInfoBean.class, loginInfoBean -> {
                Constant.ACCOUNT_STR = accountStr;
                onResult(loginInfoBean);
            });
        } catch (Exception e) {
//            setLog("easyPostLogin", e.getMessage());
            XLog.e(e);
        }
    }

    private void saveSp(String phone, String psw) {
        PreferencesUtil.save(mContext, PHONE_KEY, phone);
        PreferencesUtil.save(mContext, ACCOUNT_STR, psw);
//        saveSp(Constant.REMEMBER_PSW_KEY, rememberPsw);
//        saveSp(Constant.AUTO_LOGIN, autoLogin);
    }

    private void onResult(LoginInfoBean loginInfoBean) {
        showLoading(false);
        if (loginInfoBean != null) {
            String accessToken = loginInfoBean.getAccess_token();
            XLog.e("accessToken = " + accessToken);
            String shopId = loginInfoBean.getShopId();
            String userId = loginInfoBean.getUserId();
            if (!TextUtils.isEmpty(accessToken)) {
                //保持数据到文件里面
                String appToken = "bearer" + accessToken;
                PreferencesUtil.save(mContext, Constant.SP_TOKEN, appToken);
                PreferencesUtil.save(mContext, Constant.SP_SHOPID, shopId);
                PreferencesUtil.save(mContext, Constant.SP_USERID, userId);


                String stoken = PreferencesUtil.getString(mContext, Constant.SP_TOKEN);
                XLog.e("stoken = " + stoken);
//
//                if (!"".equals(stoken)) {
//                    XLog.d("sp  token=   " + accessToken);
//                    //保持数据到文件里面
//                }

                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(intent);
                finish();//关闭当前活动

            } else {
                showToast("token为空");
            }
        } else {
            showToast("数据解析失败");
        }
    }


    private void initPermission() {

        if (!PermissionUtil.isHasPermission(mContext, PermissionUtil.perList)) {
            PermissionUtil.requestPermission(this, PermissionUtil.perList, 1003);
        } else {
            checkVersion();
        }

//        checkVersion();
    }

    private void initSp() {
        rememberPsw = PreferencesUtil.getBoolean(mContext, Constant.REMEMBER_PSW_KEY, false);   //是否记住账号密码
        autoLogin = PreferencesUtil.getBoolean(mContext, Constant.AUTO_LOGIN, false);   //是否自动登录
        remember_pwd.setChecked(rememberPsw);
        auto_ogon.setChecked(autoLogin);
        setPsw(rememberPsw); //记住密码和自动登录都先填入密码
    }

    private void setPsw(boolean rememberPsw) {
        if (rememberPsw) {
            String phone = PreferencesUtil.getString(mContext, Constant.PHONE_KEY);
            String psw = PreferencesUtil.getString(mContext, Constant.PSW);
            setText(input_pwd, psw);
            setText(account_et, phone);
        } else {
            saveSp("", "");
        }
    }


    private void checkVersion() {
//        if (!App.debug) {
//            if (isFirst)
//                UploadAppProxy.getVersion(this, true, result -> {
//                    if (autoLogin && rememberPsw) {    //报错继续使用之前版本，只要弹出更新框，则必须更新。
//                        // postLogin();
//                        //自动登录逻辑处理
//                        autoLogon();
//                    }
//                    isFirst = false;
//                });
//        }
        UploadAppProxy.getVersion(this, true, result -> {
            if (autoLogin && rememberPsw) {    //报错继续使用之前版本，只要弹出更新框，则必须更新。
//                 postLogin();
//                自动登录逻辑处理
//                autoLogon();

                XLog.e("call back login");
            }
            isFirst = false;
        });
    }

    private void autoLogon() {
        if (Constant.isLogOut) return;
        String phone = PreferencesUtil.getString(mContext, Constant.PHONE_KEY);
        String psw = PreferencesUtil.getString(mContext, Constant.PSW);
        //自动登录
        if (autoLogin && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(psw)) {
            runnable = () -> {
//                postLogin();
            };
            account_et.postDelayed(runnable, 600);
        }
    }
//    private void getVersion() {
//        try {
//            JSONObject json = new JSONObject();
////            json.put("principal", accountStr);
////            json.put("credentials", pwdStr);
////            json.put("loginType", "1");
//            showLoading();
//            easyPostWithOutToken(json, URL.DEBUG_URL + URL.CHECK_VERSION, UpdateBean.class, result -> {
//                onResult(result);
//            });
//        } catch (Exception e) {
////            setLog("easyPostLogin", e.getMessage());
//            XLog.e(e);
//        }
//    }
//
//    private void onResult(UpdateBean result) {
//        showLoading(false);
//        XLog.d("latestCode = " + result.getData().getLatestCode());
//    }

    private void initBar() {

        int flag = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        getWindow().getDecorView().setSystemUiVisibility(flag);
        getWindow().setStatusBarColor(Color.parseColor("#66000000"));
        getWindow().setNavigationBarColor(Color.parseColor("#66000000"));


    }

//    private String detail = "";
//    public static final int SHOW_DATA = 1;
//
//    private Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            if (msg.what == SHOW_DATA) {
//                XLog.d(detail);
//                parseLoginData();
//            }
//        }
//
//        ;
//    };
//
//
//    String accountStr;
//    String pwdStr;
//
//    private void getToken() {
//        accountStr = account_et.getText().toString().trim();
//        pwdStr = input_pwd.getText().toString().trim();
//        if (TextUtils.isEmpty(accountStr)) {
//            showToast("请输入账号");
//            return;
//        }
//        if (TextUtils.isEmpty(pwdStr)) {
//            showToast("请输入密码");
//            return;
//        }
//
//
//        StringBuffer buffer = new StringBuffer(URL.DEBUG_URL + URL.LOGIN);
//        buffer.append("?principal=" + accountStr);
//
//        showLoading();
//
//        new Thread() {
//
//            @Override
//            public void run() {
//
//                try {
//
//                    HttpClient httpClient = new DefaultHttpClient();
//                    HttpPost httpPost = new HttpPost(URL.DEBUG_URL + URL.LOGIN);
//                    httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
//
//                    JSONObject json = new JSONObject();
//                    json.put("principal", accountStr);
//                    json.put("credentials", pwdStr);
//                    json.put("loginType", "1");
//                    json.put("cid", PreferencesUtil.getString(getApplicationContext(), Constant.SP_CID));//个推的cid
//
//                    StringEntity postingString = new StringEntity(json.toString(),
//                            "utf-8");
//
//
//                    httpPost.setEntity(postingString);
//                    HttpResponse httpResponse = httpClient.execute(httpPost);
//
//                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                        HttpEntity entity2 = httpResponse.getEntity();
//                        detail = EntityUtils.toString(entity2, "utf-8");
//                        XLog.d("detail" + detail);
//                        handler.sendEmptyMessage(SHOW_DATA);
//                    }
//
//                } catch (Exception e) {
//
//                    PgyerSDKManager.reportException(e);
//                }
//            }
//
//        }.start();
//
//    }
//
//
//    private void parseLoginData() {
//        LoginInfoBean baseBean = com.xeld.cashier.utils.JsonParseUtils.parse(detail, LoginInfoBean.class);
//        onResult(baseBean);
//    }

}