package com.xeld.cashier.utils;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;


/**
 * Created by caorongguan on 2019/4/30.
 */

public class PostUtils {
    private static com.xeld.cashier.utils.PostUtils postUtils;
    private BasePopupView popupView;

    public static com.xeld.cashier.utils.PostUtils getInstance() {
        if (postUtils == null) {
            postUtils = new com.xeld.cashier.utils.PostUtils();
        }
        return postUtils;
    }

    public void showLoading(Context mContext, boolean needShow) {
        showLoading(mContext, needShow, "");
    }

    public void showLoading(Context mContext, boolean needShow, String text) {
        if (TextUtils.isEmpty(text)) {
            text = "加载中";
        }
        if (needShow) {
            if (mContext == null) return;
            if (!isDialogShow()) {
                popupView = new XPopup.Builder(mContext)
                        .asLoading(text)
                        .show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isDialogShow()) {
                            popupView.dismiss();
                            popupView = null;
                        }
                    }
                }, 2000);
            }
        } else {
            if (isDialogShow()) {
                popupView.dismiss();
                popupView = null;
            }
        }
    }

//    public void showPayLoading(Context mContext, boolean needShow, String text) {
//        if (TextUtils.isEmpty(text)) {
//            text = "支付中";
//        }
//        if (needShow) {
//            if (mContext == null) return;
//            if (!isDialogShow()) {
//                popupView = new XPopup.Builder(mContext)
//                        .asLoading(text)
//                        .show();
//            }
//        }
//    }
//
//    public void hidePayLoading(Context mContext, boolean needShow) {
//        if (mContext == null) return;
//        popupView.dismiss();
//        popupView = null;
//    }


    private boolean isDialogShow() {
        return popupView != null && popupView.isShow();
    }

}
