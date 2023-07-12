package com.xeld.cashier.bean;

import com.google.gson.annotations.SerializedName;
import com.xeld.cashier.easyhttp.CommonResultBean;

import java.io.Serializable;
import java.util.List;

public class ListMemberInfoBean extends CommonResultBean {


    /**
     * data : [{"userId":"70086cb7a8504f6b854b4ade9f9088a3","nickName":"丁华剑","realName":null,"userMail":null,"loginPassword":null,"payPassword":null,"userMobile":"18611128418","modifyTime":"2020-08-14 14:42:23","userRegtime":"2020-08-14 14:42:23","userRegip":null,"userLasttime":null,"userLastip":null,"userMemo":null,"sex":"M","birthDate":null,"pic":"https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epGdmn7NtvEQgsmUUKasjTibggW5j5c0KvI7Aj8UVffNWU8uYYsRbYhHzPoNpEFlDonDmN1QrsicJgQ/132","status":1,"score":null,"yqm":"hhn74j","puserid":"7adc44a9ac18440497a00b1b8e42f2d9","account":0,"extensionWorker":"0","vipLevel":1,"userAccount":306.1,"code":null,"oldAccount":2032.3}]
     * resultMsg : null
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * userId : 70086cb7a8504f6b854b4ade9f9088a3
         * nickName : 丁华剑
         * realName : null
         * userMail : null
         * loginPassword : null
         * payPassword : null
         * userMobile : 18611128418
         * modifyTime : 2020-08-14 14:42:23
         * userRegtime : 2020-08-14 14:42:23
         * userRegip : null
         * userLasttime : null
         * userLastip : null
         * userMemo : null
         * sex : M
         * birthDate : null
         * pic : https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epGdmn7NtvEQgsmUUKasjTibggW5j5c0KvI7Aj8UVffNWU8uYYsRbYhHzPoNpEFlDonDmN1QrsicJgQ/132
         * status : 1
         * score : null
         * yqm : hhn74j
         * puserid : 7adc44a9ac18440497a00b1b8e42f2d9
         * account : 0.0
         * extensionWorker : 0
         * vipLevel : 1
         * userAccount : 306.1
         * code : null
         * oldAccount : 2032.3
         */

        private String userId;
        private String nickName;
        private Object realName;
        private Object userMail;
        private Object loginPassword;
        private Object payPassword;
        private String userMobile;
        private String modifyTime;
        private String userRegtime;
        private Object userRegip;
        private Object userLasttime;
        private Object userLastip;
        private Object userMemo;
        private String sex;
        private Object birthDate;
        private String pic;
        private int status;
        private Object score;
        private String yqm;
        private String puserid;
        private double account;
        private String extensionWorker;
        private int vipLevel;
        private double userAccount;
        private Object code;
        private double oldAccount;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public Object getRealName() {
            return realName;
        }

        public void setRealName(Object realName) {
            this.realName = realName;
        }

        public Object getUserMail() {
            return userMail;
        }

        public void setUserMail(Object userMail) {
            this.userMail = userMail;
        }

        public Object getLoginPassword() {
            return loginPassword;
        }

        public void setLoginPassword(Object loginPassword) {
            this.loginPassword = loginPassword;
        }

        public Object getPayPassword() {
            return payPassword;
        }

        public void setPayPassword(Object payPassword) {
            this.payPassword = payPassword;
        }

        public String getUserMobile() {
            return userMobile;
        }

        public void setUserMobile(String userMobile) {
            this.userMobile = userMobile;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getUserRegtime() {
            return userRegtime;
        }

        public void setUserRegtime(String userRegtime) {
            this.userRegtime = userRegtime;
        }

        public Object getUserRegip() {
            return userRegip;
        }

        public void setUserRegip(Object userRegip) {
            this.userRegip = userRegip;
        }

        public Object getUserLasttime() {
            return userLasttime;
        }

        public void setUserLasttime(Object userLasttime) {
            this.userLasttime = userLasttime;
        }

        public Object getUserLastip() {
            return userLastip;
        }

        public void setUserLastip(Object userLastip) {
            this.userLastip = userLastip;
        }

        public Object getUserMemo() {
            return userMemo;
        }

        public void setUserMemo(Object userMemo) {
            this.userMemo = userMemo;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Object getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Object birthDate) {
            this.birthDate = birthDate;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getScore() {
            return score;
        }

        public void setScore(Object score) {
            this.score = score;
        }

        public String getYqm() {
            return yqm;
        }

        public void setYqm(String yqm) {
            this.yqm = yqm;
        }

        public String getPuserid() {
            return puserid;
        }

        public void setPuserid(String puserid) {
            this.puserid = puserid;
        }

        public double getAccount() {
            return account;
        }

        public void setAccount(double account) {
            this.account = account;
        }

        public String getExtensionWorker() {
            return extensionWorker;
        }

        public void setExtensionWorker(String extensionWorker) {
            this.extensionWorker = extensionWorker;
        }

        public int getVipLevel() {
            return vipLevel;
        }

        public void setVipLevel(int vipLevel) {
            this.vipLevel = vipLevel;
        }

        public double getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(double userAccount) {
            this.userAccount = userAccount;
        }

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public double getOldAccount() {
            return oldAccount;
        }

        public void setOldAccount(double oldAccount) {
            this.oldAccount = oldAccount;
        }
    }
}
