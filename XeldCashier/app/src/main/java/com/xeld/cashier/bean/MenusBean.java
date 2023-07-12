package com.xeld.cashier.bean;

/**
 * Created by highsixty on 2018/3/9.
 * mail  gaolulin@sunmi.com
 */

public class MenusBean {
    private String id;
    private String name;
    private String money;
    private int type;
    private String code;
    private int net;

    public int getNet() {
        return net;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
