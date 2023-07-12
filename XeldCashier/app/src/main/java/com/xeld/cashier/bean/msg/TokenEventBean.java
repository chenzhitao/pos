package com.xeld.cashier.bean.msg;

import java.io.Serializable;

public class TokenEventBean {
    /**
     * eventType : 2
     * result : expired
     * token : 08ea842e-247f-4ea9-bb39-ca960a9a5743
     */

    private int eventType;
    private String result;
    private String token;

//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    private String token;


    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
