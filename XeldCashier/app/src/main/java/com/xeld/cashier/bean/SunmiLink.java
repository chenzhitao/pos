package com.xeld.cashier.bean;

/**
 * Copyright (C), 2018-2019, 商米科技有限公司
 * FileName: SunmiLink
 *
 * @author: liuzhicheng
 * Date: 19-4-25
 * Description: ${DESCRIPTION}
 * History:
 */
public class SunmiLink {

    /**
     * type : password
     * data : {"ssid":"无线牛逼","password":"12345678","qrcode":"","expires_in":0}
     */

    private String type;
    private DataBean data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ssid : 无线牛逼
         * password : 12345678
         * qrcode :
         * expires_in : 0
         */

        private String ssid;
        private String password;
        private String qrcode;
        private int expires_in;

        public String getSsid() {
            return ssid;
        }

        public void setSsid(String ssid) {
            this.ssid = ssid;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }
    }
}
