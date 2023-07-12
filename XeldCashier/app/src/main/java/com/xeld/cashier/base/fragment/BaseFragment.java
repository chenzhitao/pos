package com.xeld.cashier.base.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.xeld.cashier.BaseAct;
import com.xeld.cashier.base.fragment.BaseEventBean;
import com.xeld.cashier.mvp.callback.OnRequestSuccess;
import com.xeld.cashier.utils.CommonUtils;
import com.xeld.cashier.utils.JsonParseUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by caorongguan on 2019/5/6.
 */

public abstract class BaseFragment extends Fragment {
    public View rootView;
    protected FragmentActivity activity;
    Unbinder unbinder;
    protected boolean mIsFirstVisible = true;
    public Activity mAct;
    private Context mContext;
    public int mPage = 1;
    protected boolean isPullAndPush = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        rootView = inflater.inflate(getLayout(), null, false);
        mAct = getActivity();
        mContext = this.getContext();
        unbinder = ButterKnife.bind(this, rootView);
        Bundle bundle = getArguments();
        if (bundle != null) {
            initIntentExtras(bundle);
        }
        initView();
        initEvents();
        EventBus.getDefault().register(this);


        return rootView;
    }

    protected void initIntentExtras(Bundle bundle) {

    }

    ;

    /**
     * 如果不需要token 则调用次方法
     */
    public <T> void easyPostWithOutToken(JSONObject requestParams, String url, Class<T> cls, OnRequestSuccess<T> listener) {
        JsonParseUtils.easyPost(getAct(), requestParams, url, cls, false, listener);
    }

    /**
     * 默认需要token
     */
    public <T> void easyPost(JSONObject requestParams, String url, Class<T> cls, OnRequestSuccess<T> listener) {
        JsonParseUtils.easyPost(getAct(), requestParams, url, cls, true, listener);
    }

    /**
     * 下拉
     */
    protected void setPullAction() {
        mPage = 1;
        isPullAndPush = true;
    }

    /**
     * 上啦
     */
    protected void setPushAction() {
        mPage++;
        isPullAndPush = false;
    }


    protected void initEvents() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean isVis = isHidden() || getUserVisibleHint();
        if (isVis && mIsFirstVisible) {
            mIsFirstVisible = false;
        }
    }


    public void showLoadingDialog() {
        showLoadDialog(true);
    }

    public void showLoadDialog(boolean show) {
        getAct().showLoading(show);
    }

    public int getColorBase(int id) {
        return ContextCompat.getColor(activity, id);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(BaseEventBean messageEvent) {

    }

    /**
     * @return
     */
    public abstract int getLayout();

    /**
     * 初始化views
     */
    public abstract void initView();

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    /**
     * 当界面可见时的操作
     */
    protected void onVisible() {
        if (mIsFirstVisible && isResumed()) {
            lazyLoad();
            mIsFirstVisible = false;
        }
    }

    /**
     * 数据懒加载
     */
    protected void lazyLoad() {

    }

    /**
     * 当界面不可见时的操作
     */
    protected void onInVisible() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        EventBus.getDefault().unregister(this);
        this.activity = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (FragmentActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T getViewById(int id) {
        return (T) rootView.findViewById(id);
    }

    public void showToast(String msg) {
        CommonUtils.showToast(mContext, msg);
    }

    public void setText(TextView textView, String text) {
        CommonUtils.setText(textView, text);
    }

    public void setText(TextView textView, int text) {
        setText(textView, text + "");
    }

    public void setText(TextView textView, double text) {
        setText(textView, text + "");
    }

    public void jumpToFragment(Fragment fragment, int id) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public void jumpToChildFragment(Fragment fragment, int id) {
        getChildFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

    public void jumpToAct(Class activity) {
        Intent intent = new Intent(mAct, activity);
        mAct.startActivity(intent);
    }


    public void jumpToAct(Class activity, Bundle data) {
        Intent intent = new Intent(mAct, activity);
        intent.putExtra("data", data);
        mAct.startActivity(intent);
    }


    public <T> void addParams(HashMap<String, Object> map, String key, T value) {
        if (value != null && !TextUtils.isEmpty(value.toString())) {
            map.put(key, value);
        }
    }

    public void setStateHeight(View view) {
        view.setPadding(0, getStatusHeight(activity), 0, 0);
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public Bundle getBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return bundle;
        }
        return new Bundle();
    }

    public BaseAct getAct() {
        return (BaseAct) getActivity();
    }


}
