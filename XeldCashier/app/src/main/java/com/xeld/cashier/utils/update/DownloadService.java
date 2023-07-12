package com.xeld.cashier.utils.update;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.util.SparseArray;

import java.io.File;

/**
 * Created by 曹荣冠
 * on 2020/5/17.
 */
public class DownloadService extends Service {
    private DownloadManager mDownloadManager = null;
    private BroadcastReceiver mDownloadReceiver = null;
    public static final String type = "application/vnd.android.package-archive";

    @SuppressLint("WrongConstant")
    @Override
    public void onCreate() {
        super.onCreate();
        mDownloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        mDownloadArray = new SparseArray<File>();
        mDownloadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (mDownloadArray.get((int) downloadId) != null)// 这个东西是我们下载的
                {
                    Intent installIntent = new Intent(Intent.ACTION_VIEW);
                    installIntent.setDataAndType(
                            Uri.fromFile(mDownloadArray.get((int) downloadId)),
                            type);
                    installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(installIntent);
                }
            }
        };
        registerReceiver(mDownloadReceiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mDownloadReceiver);
    }


    public IBinder onBind(Intent intent) {

        return null;

    }

    private SparseArray<File> mDownloadArray;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            String url = intent.getStringExtra("url");
            String contentName = intent.getStringExtra("contentName");
            startDownLoad(url, contentName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void startDownLoad(String url, String contentName) {
        File file = getExternalFilesDir("megviicloud_apk");
        String fileName = file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".apk";
        Log.w("ceshi", "fileName====" + fileName);
        if (fileName == null)
            return;
        Uri downloadUri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        // request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle(contentName);
        File tmpFile = new File(fileName);
        request.setDestinationUri(Uri.fromFile(tmpFile));
        long id = mDownloadManager.enqueue(request);
        mDownloadArray.put((int) id, tmpFile);
    }

}