//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xeld.cashier.utils;

import com.sunmi.devicemanager.cons.Cons;
import com.xeld.cashier.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Utils {

    public static String getPmOrAm() {
        long var0 = System.currentTimeMillis();
        Calendar var2 = Calendar.getInstance();
        var2.setTimeInMillis(var0);
        return var2.get(Calendar.AM_PM) == 0 ? ResourcesUtils.getString(R.string.user_hello_am) : ResourcesUtils.getString(R.string.user_hello_pm);
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString 原始字符串
     * @param length      指定长度
     * @return
     */
    public static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }


    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString 原始字符串
     * @param length      指定长度
     * @param size        指定列表大小
     * @return
     */
    public static List<String> getStrList(String inputString, int length,
                                          int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }


    /**
     * 分割字符串，如果开始位置大于字符串长度，返回空
     *
     * @param str 原始字符串
     * @param f   开始位置
     * @param t   结束位置
     * @return
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }


    /**
     * 获取打印机接口类型
     *
     * @param type
     * @return
     */
    public static String getPrinterType(int type) {
        switch (type) {
            case Cons
                    .ConT.REMOTE:
                return "远程";
            case Cons
                    .ConT.NET:
                return "WIFI";
            case Cons
                    .ConT.USB:
                return "USB";
            case Cons
                    .ConT.BLUETOOTH:
                return "BLUETOOTH";
            case Cons
                    .ConT.INNER:

                return "内置";
            case Cons
                    .ConT.SERIAL:

                return "串口";
            default:
                return "未知";
        }
    }

    public static String nullToBlank(Object obj) {
        return obj != null ? obj.toString() : "";
    }

}
