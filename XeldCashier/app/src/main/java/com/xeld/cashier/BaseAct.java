package com.xeld.cashier;

import android.content.Context;
import android.os.Build;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.elvishew.xlog.XLog;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.xeld.cashier.base.fragment.BaseEventBean;
import com.xeld.cashier.mvp.callback.OnRequestSuccess;
import com.xeld.cashier.utils.CommonUtils;
import com.xeld.cashier.utils.CommonViewUtils;
import com.xeld.cashier.utils.JsonParseUtils;
import com.xeld.cashier.utils.PostUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;


public class BaseAct extends AppCompatActivity {
    protected Context mContext;
    public String TAG;
    private BasePopupView popupView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //全屏
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mContext = this;
        TAG = getClass().getSimpleName();

//        initTint();

    }

    public void setLog(String text) {
        setLog(TAG, text);
    }

    public void setLog(String key, String text) {
        CommonViewUtils.setLog(key, text);
    }


    public void setText(TextView textView, String str) {
        CommonViewUtils.setText(textView, str);
    }


    protected void showToast(String msg) {
        CommonUtils.showToast(mContext, msg);
    }

    /**
     * 如果不需要token 则调用次方法
     */
    public <T> void easyPostWithOutToken(JSONObject requestParams, String url, Class<T> cls, OnRequestSuccess<T> listener) {
        JsonParseUtils.easyPost(this, requestParams, url, cls, false, listener);
    }

    /**
     * 默认需要token
     */
    public <T> void easyPost(JSONObject requestParams, String url, Class<T> cls, OnRequestSuccess<T> listener) {
        JsonParseUtils.easyPost(this, requestParams, url, cls, true, listener);
    }

    /**
     * 默认需要token
     */
    public <T> void easyGet(JSONObject requestParams, String url, Class<T> cls, OnRequestSuccess<T> listener) {
        JsonParseUtils.easyGet(this, requestParams, url, cls, true, listener);
    }

    /**
     * 如果不需要token 则调用次方法
     */
    public <T> void easyGetWithOutToken(JSONObject requestParams, String url, Class<T> cls, OnRequestSuccess<T> listener) {
        JsonParseUtils.easyGet(this, requestParams, url, cls, false, listener);
    }

    public void showProgressDialog(boolean cancelable, String msg) {
        dismissProgressDialog();
        popupView = new XPopup.Builder(this)
                .asLoading(TextUtils.isEmpty(msg) ? "加载中" : msg)
                .show();
    }

    public void dismissProgressDialog() {
        if (popupView != null) {
            popupView.dismiss();
        }
        popupView = null;
    }

    public void showLoading() {
        showLoading(true);
    }


    public void showLoading(boolean show) {
        PostUtils.getInstance().showLoading(mContext, show);
    }


    public Drawable getDrawableBase(int id) {
        return ContextCompat.getDrawable(mContext, id);
    }

    /**
     * @param title 标题
     *              注意用这个方法，必须要needShowTitle 为true
     */
    public void setCommonTitle(String title) {
//        setText(tvBaseTitle, title);
    }

    public void postEvent(BaseEventBean event) {
        EventBus.getDefault().post(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void jumpToAct(Class<?> cls) {
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

            long secondTime = System.currentTimeMillis();

            if (secondTime - firstTime > 2000) {
                showToast("再按一次退出程序");
                firstTime = secondTime;
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 初始化沉浸式
     */
    private void initTint() {
        XLog.d("initTint");
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}
