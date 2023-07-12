package com.xeld.cashier.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by 曹荣冠 on 2017/10/28.
 */

public class PermissionUtil {
    // 拍照，图库，相册  权限请求码与 相机请求码一致。
    public static final int[] requestCodeList = {1001, 1002, 1003};
    public static int denied = PackageManager.PERMISSION_DENIED;
    public static String cameraPermission = Manifest.permission.CAMERA;
    public static String storePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static String storeReadPermission = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static String installPermission = Manifest.permission.REQUEST_INSTALL_PACKAGES;
    public static String installPermission1 = Manifest.permission.INSTALL_PACKAGES;
    public static String location = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static String callPhone = Manifest.permission.CALL_PHONE;
    public static String[] perList = new String[]{storePermission, storeReadPermission, cameraPermission, installPermission, installPermission1, location,callPhone};
    public static String[] perList1 = new String[]{storePermission, storeReadPermission, cameraPermission, location,callPhone};
    public static String[] cameraPermissionList = new String[]{storePermission, storeReadPermission, cameraPermission};

    /**
     * 是否有读权限
     *
     * @param context
     * @return
     */
    public static boolean isHasReadExternal_storage(Context context) {
        int permissionCheck = ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        return permissionCheck != PackageManager.PERMISSION_DENIED;
    }

    /**
     * 是否有相机和存储权限
     */
    public static boolean isHasCameraAndStoragePermission(Context context) {
        return ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(Activity context, String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(context, permissions, requestCode);
        }
    }

    public static boolean isHasPermission(Context context, String[] perList) {
        boolean ifInclude = true;
        int permissionCheck;
        for (int i = 0; i < perList.length; i++) {
            try {
                permissionCheck = ContextCompat.checkSelfPermission(context, perList[i]);
                if (permissionCheck == denied) {
                    ifInclude = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ifInclude;
    }


}
