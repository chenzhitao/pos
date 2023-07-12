package com.xeld.cashier.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;

import com.xeld.cashier.constant.Constant;


/**
 * Created by caorongguan on 2019/5/6.
 */

public class UiUtils {
    /**
     * 直接跳转至位置信息设置界面
     */
    public static void openLocation(Context context) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }

    // 开始扫码
    public static void jumToScan(Activity act) {
        // 申请相机权限
        if (ActivityCompat.checkSelfPermission(act, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(act, new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
            return;
        }
        // 申请文件读写权限（部分朋友遇到相册选图需要读写权限的情况，这里一并写一下）
        if (ActivityCompat.checkSelfPermission(act, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(act, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.REQ_PERM_EXTERNAL_STORAGE);
            return;
        }
        // 二维码扫码
        //  Intent intent = new Intent(act, ScanActivity.class);
        //  act.startActivityForResult(intent, Constant.REQ_QR_CODE);
    }

    public static void jumpToAct(Context mContext, Class<?> cls) {
        if (mContext == null) return;
        if (cls == null) return;
        mContext.startActivity(new Intent(mContext, cls));
    }




}
