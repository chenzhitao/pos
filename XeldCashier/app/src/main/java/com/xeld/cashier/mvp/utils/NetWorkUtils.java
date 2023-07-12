package com.xeld.cashier.mvp.utils;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.xeld.cashier.utils.CommonUtils;
import com.xeld.cashier.utils.CommonViewUtils;
import com.xeld.cashier.App;

/**
 * Created by 曹荣冠
 * on 2020/6/1.
 */
public class NetWorkUtils {

    /**
     * 检查互联网地址是否可以访问
     *
     * @param address  要检查的域名或IP地址
     * @param callback 检查结果回调（是否可以ping通地址）{@see java.lang.Comparable<T>}
     */
    public static void isNetWorkAvailable(final String address, final Comparable<Boolean> callback) {
        final String ip = subPort(address);//检测端口号是否填写也在里面处理
        if (TextUtils.isEmpty(ip)) {
            return;
        }
        final Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (callback != null) {
                    callback.compareTo(msg.arg1 == 0);
                }
            }

        };
        new Thread(() -> {
            Runtime runtime = Runtime.getRuntime();
            Message msg = new Message();
            try {
                Process pingProcess = runtime.exec("/system/bin/ping -c 1 " + ip);
                InputStreamReader isr = new InputStreamReader(pingProcess.getInputStream());
                BufferedReader buf = new BufferedReader(isr);
                if (buf.readLine() == null) {
                    msg.arg1 = -1;
                } else {
                    msg.arg1 = 0;
                }
                buf.close();
                isr.close();
            } catch (Exception e) {
                msg.arg1 = -1;
                e.printStackTrace();
            } finally {
                runtime.gc();
                handler.sendMessage(msg);
            }
        }).start();
    }

    /**
     * 检测ip是否可以用，需要先去掉端口号
     */
    public static String subPort(String ip) {
        ip = CommonUtils.ifStringEmpty(ip);
        String maoStr = ":";
        if (ip.contains(maoStr)) { //去掉端口号
            ip = ip.substring(0, ip.lastIndexOf(maoStr));  //截取后面的字符串
        } else {
            showToast("请输入端口号");
            return "";
        }
        CommonViewUtils.setLog("subPort", ip);
        return ip;
    }


    private static void showToast(String text) {
        CommonUtils.showToast(App.getContext(), text);
    }

}