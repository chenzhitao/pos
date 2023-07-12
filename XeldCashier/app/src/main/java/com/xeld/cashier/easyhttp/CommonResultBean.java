package com.xeld.cashier.easyhttp;

import java.io.Serializable;

/**
 * Created by 曹荣冠
 * on 2020/5/27.
 */
public class CommonResultBean implements Serializable {
    /**
     * private int resultCode;
     * private String resultMsg;
     * private int total;
     */
    private int total;
    /**
     * 返回code 0表示成功
     */
    private int resultCode;
    /**
     * 后台提示信息
     */
    private String resultMsg = "";

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

}
