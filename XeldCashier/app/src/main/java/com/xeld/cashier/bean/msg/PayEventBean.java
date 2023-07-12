package com.xeld.cashier.bean.msg;

import com.xeld.cashier.base.fragment.BaseEventBean;

public class PayEventBean extends BaseEventBean {

    private Object memberInfo;

    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(Object memberInfo) {
        this.memberInfo = memberInfo;
    }

    public PayEventBean(int type) {
        super(type);
    }
}
