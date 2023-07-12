package com.xeld.cashier.bean;

import com.xeld.cashier.easyhttp.CommonResultBean;

import java.io.Serializable;

public class UserInfoBean extends CommonResultBean {


    /**
     * data : {"userId":null,"cashRegisterId":null,"name":"jiangziweidao","password":null,"shopId":116,"adminId":null,"isEnable":null,"isDelete":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"shopName":null}
     * resultMsg : null
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * userId : null
         * cashRegisterId : null
         * name : jiangziweidao
         * password : null
         * shopId : 116
         * adminId : null
         * isEnable : null
         * isDelete : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * shopName : null
         */

        private int userId;
        private String cashRegisterId;
        private String name;
        private String password;
        private int shopId;
        private String adminId;
        private String isEnable;
        private String isDelete;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String shopName;
        private String nikeName;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getCashRegisterId() {
            return cashRegisterId;
        }

        public void setCashRegisterId(String cashRegisterId) {
            this.cashRegisterId = cashRegisterId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }

        public String getIsEnable() {
            return isEnable;
        }

        public void setIsEnable(String isEnable) {
            this.isEnable = isEnable;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getNikeName() {
            return nikeName;
        }

        public void setNikeName(String nikeName) {
            this.nikeName = nikeName;
        }
    }
}
