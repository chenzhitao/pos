package com.xeld.cashier.bean.msg;

import com.xeld.cashier.base.fragment.BaseEventBean;

public class ProductClickEventBean extends BaseEventBean {

    public ProductClickEventBean(int type, int position) {
        super(type);
        this.position = position;
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
