package com.xeld.cashier.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import com.elvishew.xlog.XLog;
import com.xeld.cashier.App;
import com.xeld.cashier.R;

public class PreferencesUtil {
    private static String APP_SHARED_PREFERENCES_NAME = App.getContext().getResources().getString(R.string.app_name);
    private static final String token = "token";
    private static SharedPreferences m_appSharedPreferences = null;

    public static void save(Context context, String key, String value) {
        if (context == null) return;
        if (key == null) return;
        m_appSharedPreferences = context.getSharedPreferences(APP_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        Editor editor = m_appSharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
        XLog.d(" sp  " + key + " = " + value);
    }

    public static String getString(Context context, String key) {
        if (context == null) return "";
        m_appSharedPreferences = context.getSharedPreferences(APP_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String value = m_appSharedPreferences.getString(key, "");
        return value;
    }

    public static String getToken(Context context) {
        return getString(context, token);
    }

    public static void saveToken(Context context, String tokenStr) {
        save(context, token, tokenStr);
    }

    public static void setBoolean(Context context, String key, boolean value) {
        m_appSharedPreferences = context.getSharedPreferences(APP_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        Editor editor = m_appSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void setInt(Context context, String key, int value) {
        m_appSharedPreferences = context.getSharedPreferences(APP_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        Editor editor = m_appSharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
        XLog.d(" sp  " + key + " = " + value);
    }

    public static int getInt(Context context, String key, int defaultInt) {
        m_appSharedPreferences = context.getSharedPreferences(APP_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        int value = m_appSharedPreferences.getInt(key, defaultInt);
        return value;
    }

    public static boolean getBoolean(Context context, String key, boolean defaultBoolean) {
        m_appSharedPreferences = context.getSharedPreferences(APP_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        boolean value = m_appSharedPreferences.getBoolean(key, defaultBoolean);
        return value;
    }
}
