package com.xeld.cashier.utils;


/**
 * Created by caorongguan on 2019/5/6.
 */
public class ApiException extends Exception {
    private int code;
    private String msg;

    public ApiException(String msg, int code) {
        super(msg);
        this.code = code;
        this.msg = msg;
        CommonUtils.setLog("ApiException", "code:" + code + "  msg:" + msg);
        //  CommonViewUtils.showToast(msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
