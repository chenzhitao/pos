package com.xeld.cashier.utils.update;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.xeld.cashier.BuildConfig;
import com.xeld.cashier.BaseAct;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.mvp.http.URL;
import com.xeld.cashier.mvp.callback.OnRequestSuccess;
import com.elvishew.xlog.XLog;
import com.xeld.cashier.R;
import com.google.gson.Gson;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.utils.HttpLog;
import com.xeld.cashier.utils.update.UpdateBean;

public class UploadAppProxy {

    /**
     * 检测APP版本  自动更新暂时不切换地址
     */
    public static void getVersion(BaseAct act, boolean isAuto, OnRequestSuccess<String> listener) {
        EasyHttp.get(URL.DEBUG_URL + URL.CHECK_VERSION)
                .cacheTime(300)//缓存300s 单位s
                .cacheMode(CacheMode.NO_CACHE)//设置请求缓存模式
                .execute(
                        new SimpleCallBack<String>() {
                            @Override
                            public void onError(ApiException e) {
                                //请求错误
                                Constant.isFirst = false;
                                XLog.d(e.getDisplayMessage() + "    " + e.getCode());
                                listener.onResult("");//当报错，继续使用之前版本。
                            }

                            @Override
                            public void onSuccess(String s) {
                                Constant.isFirst = false;
                                UpdateBean updateBean = new Gson().fromJson(s, UpdateBean.class);
                                if (updateBean.getData() == null) {
                                    listener.onResult("");
                                    return;
                                }
                                if (updateBean.getCode() == 0) {
                                    //temp
//                                    new VersionUpdateDialog(act, "https://images.51xeld.com/app-debug.apk", listener);

                                    if (updateBean.getData().getLatestCode() > BuildConfig.VERSION_CODE) {
                                        String url = updateBean.getData().getAddress();
                                        if (isAuto) {
                                            new VersionUpdateDialog(act, url, listener);
                                        } else {
                                            listener.onResult(url);
                                        }
                                    } else {
                                        listener.onResult("");
//                                        Toast.makeText(UpdateAppService.this, "已是最新版本", Toast.LENGTH_SHORT).show();
//                                        ToastUtil.showSuccessView("已是最新版本");
                                    }
                                }
                                XLog.d("  升级   s   = " + s);
                            }
                        }
                );
    }

    /**
     * 下载
     */
    public static void downloadApp(Activity act, String url, OnRequestSuccess<Integer> listener) {
        XLog.d("downloadApp      " + url);
        String apkPath = getDiskCachePath(act);
        if (TextUtils.isEmpty(apkPath)) {
            apkPath = Environment.getExternalStorageDirectory().getPath();
        }
        // String name = System.currentTimeMillis() + "collection.apk";
        String name = act.getResources().getString(R.string.app_name) + ".apk";
        EasyHttp.downLoad(url)
                .savePath(apkPath)
                .saveName(name)//不设置默认名字是时间戳生成的
                .execute(new DownloadProgressCallBack<String>() {
                    @Override
                    public void update(long bytesRead, long contentLength, boolean done) {
                        int progress = (int) (bytesRead * 100 / contentLength);
                        listener.onResult(progress);
                        Log.i("downloadAppprogress", progress + "================");
                        if (progress == 99)
                            HttpLog.e(progress + "% ");
                        XLog.d(progress + "% ");
                    }

                    @Override
                    public void onStart() {
                        //开始下载
                    }

                    @Override
                    public void onComplete(String path) {
                        //下载完成，path：下载文件保存的完整路径
                        XLog.d("path=" + path);
                        UpDateUtils.install(act, path);
//                         UpDateUtils.openAPKFile(act, path);
//                        UpDateUtils.installNormal(act, path);
                    }

                    @Override
                    public void onError(ApiException e) {
                        //下载失败
                        XLog.d(e.getDisplayMessage() + "   " + e.getCode());
                    }
                });
    }

    /**
     * 获取cache路径
     *
     * @param context
     * @return
     */
    public static String getDiskCachePath(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            return context.getExternalCacheDir().getPath();
        } else {
            return context.getCacheDir().getPath();
        }
    }


}
