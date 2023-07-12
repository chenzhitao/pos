package com.xeld.cashier.utils.update;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.utils.CommonUtils;
import com.xeld.cashier.SplashActivity;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by caorongguan on 2019/1/14.
 * 自动更新
 */

public class UpDateUtils {
    public static String TAG = "UpDateUtils";
    public static String downloadUpdateApkFilePath;
    public static final String type = DownloadService.type;

    public static void install(Context mContext, String path) {
        downloadUpdateApkFilePath = path;
        if (TextUtils.isEmpty(downloadUpdateApkFilePath)) return;
        Log.i(TAG, "开始执行安装: " + downloadUpdateApkFilePath);
        File apkFile = new File(downloadUpdateApkFilePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.w(TAG, "版本大于 N ，开始使用 fileProvider 进行安装");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, Constant.fileProvider, apkFile);
            intent.setDataAndType(contentUri, DownloadService.type);
        } else {
            Log.w(TAG, "正常进行安装");
            intent.setDataAndType(Uri.fromFile(apkFile), DownloadService.type);
        }
        mContext.startActivity(intent);
        //兼容8.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean hasInstallPermission = mContext.getPackageManager().canRequestPackageInstalls();
            if (!hasInstallPermission) {
                CommonUtils.showToast(mContext, "请配置应用内安装权限");
                startInstallPermissionSettingActivity(mContext);
                return;
            }
        }
    }

    /**
     *   * 跳转到设置-允许安装未知来源-页面
     * <p>
     *   
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void startInstallPermissionSettingActivity(Context mContext) {
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    /**
     * 提示安装
     * <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
     *
     * @param context 上下文
     * @param apkPath apk下载完成在手机中的路径
     */
    public static void installNormal(Context context, String apkPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            File file = new File(apkPath);
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            //参数1:上下文, 参数2:Provider主机地址 和配置文件中保持一致,参数3:共享的文件
            Uri apkUri = FileProvider.getUriForFile(context, Constant.fileProvider, file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, type);
        } else {
            intent.setDataAndType(Uri.fromFile(new File(apkPath)),
                    type);
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        context.startActivity(intent);
        startApp(context);
    }

    /**
     * 安装后自启动apk
     *
     * @param context
     */
    private static void startApp(Context context) {
        execRootShellCmd("am start -S  " + context.getPackageName() + "/"
                + SplashActivity.class.getCanonicalName() + " \n");
    }

    /**
     * 执行shell命令
     *
     * @param cmds
     * @return
     */
    private static boolean execRootShellCmd(String... cmds) {
        if (cmds == null || cmds.length == 0) {
            return false;
        }
        DataOutputStream dos = null;
        InputStream dis = null;
        Process p = null;
        try {
            // 经过Root处理的android系统即有su命令
            p = Runtime.getRuntime().exec("su");
            dos = new DataOutputStream(p.getOutputStream());

            for (int i = 0; i < cmds.length; i++) {
                dos.writeBytes(cmds[i] + " \n");
            }
            dos.writeBytes("exit \n");

            int code = p.waitFor();

            return code == 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                if (p != null) {
                    p.destroy();
                    p = null;
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return false;
    }

}