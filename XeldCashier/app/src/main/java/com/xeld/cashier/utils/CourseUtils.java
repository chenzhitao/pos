package com.xeld.cashier.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import com.xeld.cashier.App;

import java.util.List;

public class CourseUtils {

    public static boolean resultProcess(String pid) {
        String processName = getProcessName(App.getContext(), android.os.Process.myPid());
        return (!TextUtils.isEmpty(processName) && pid.equals(processName));

    }

    private static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }
}
