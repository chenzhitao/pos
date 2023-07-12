package com.xeld.cashier.utils.update;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.ui.login.LoginActivity;
import com.xeld.cashier.utils.CommonUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by caorongguan on 2019/1/14.
 */

public class ApkInstallReceiver extends BroadcastReceiver {
    public String downloadPath;
    public static final String ACTION_START = "ACTION_START";
    public static final String ACTION_UPDATE = "ACTION_UPDATE";
    public static final String ACTION_FINISHED = "ACTION_FINISHED";
    public static final String ACTION_CANCEL = "ACTION_CANCEL";
    public static final String ACTION_ERROR = "ACTION_ERROR";
    public static final String ACTION_REDIRECT_ERROR = "ACTION_REDIRECT_ERROR";
    public static final String HIDE_DIALOG = "HIDE_DIALOG";
    public static final String type = DownloadService.type;
    public static boolean isDownLoadApk = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = CommonUtils.ifStringEmpty(intent.getAction());
        switch (action) {
            case ACTION_START:
                isDownLoadApk = true;//单例下载，防止多任务进行
                // 下载开始的时候启动通知栏
                // mNotificationUtil.showNotification(intent.getIntExtra("id", 0));
                break;
            case ACTION_UPDATE:
                // 更新进度条
                //  mNotificationUtil.updateNotification(intent.getIntExtra("id", 0), intent.getIntExtra("finished", 0));
                Log.e("update_progress", "");
                break;
            case ACTION_FINISHED:
                isDownLoadApk = false;//变更未任务未下载
                // mNotificationUtil.cancelNotification(intent.getIntExtra("id", 0));// 下载结束后取消通知
                //  UpdateManger.installApk(context, new File(downloadPath));
                // installNormal(context, downloadPath);
                break;
            case ACTION_CANCEL:
                isDownLoadApk = false;//变更未任务未下载
                //  mNotificationUtil.cancelNotification(intent.getIntExtra("id", 0));// 下载结束后取消通知
                break;
            case ACTION_ERROR:
                showToast(context, "读取文件失败，请前往官方网站扫码下载最新版本！");
                break;
            case ACTION_REDIRECT_ERROR:
                showToast(context, "下载地址重定向出现错误，请稍后再试！");
                break;
            case DownloadManager.ACTION_NOTIFICATION_CLICKED:
                //处理 如果还未完成下载，用户点击Notification ，跳转到下载中心
               /* Intent viewDownloadIntent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
                viewDownloadIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  //安装完成重启
                context.startActivity(viewDownloadIntent);*/
                // openAssignFolder(context, downloadPath);
                Log.e("test_install", action);
                //    UpDateUtils.install(context);
                //blog.csdn.net/qq_29428215/article/details/80570034
                break;
            case DownloadManager.ACTION_DOWNLOAD_COMPLETE://下载完成
                //blog.csdn.net/fzkf9225/article/details/80969439
                if (!TextUtils.isEmpty(downloadPath)) {
                    Log.e("test_installNormal", action);
                    //  installNormal(context, downloadPath);
                    // UpDateUtils.install(context);
                }
                break;
            default:
                break;
        }
    }

    private void openAssignFolder(Context context, String path) {
        File file = new File(path);
        if (null == file || !file.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //安装完成重启
        // intent.setDataAndType(Uri.fromFile(file), "file/*.apk");
        intent.setDataAndType(Uri.fromFile(file), type);
        try {
            context.startActivity(intent);
//            startActivity(Intent.createChooser(intent,"选择浏览工具"));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void showToast(Context context, String str) {
        CommonUtils.showToast(context, str);
    }

    public void setDownloadPath(String downloadUpdateApkFilePath) {
        downloadPath = downloadUpdateApkFilePath;
    }

    /**
     * 提示安装
     * <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
     *
     * @param context 上下文
     * @param apkPath apk下载完成在手机中的路径
     */
    private static void installNormal(Context context, String apkPath) {
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
                + LoginActivity.class.getCanonicalName() + " \n");
    }
    //blog.csdn.net/zxc514257857/article/details/77485561

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
