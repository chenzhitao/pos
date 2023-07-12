package com.xeld.cashier.bean;

import com.xeld.cashier.easyhttp.CommonResultBean;

import java.io.Serializable;

public class AddProductBean extends CommonResultBean {


    /**
     * data : {"prodId":1460,"shopId":116,"prodName":"小二啷当面包","prodNamePy":"XE啷DMB","oriPrice":null,"price":55,"brief":null,"pic":null,"imgs":null,"status":1,"categoryId":293,"soldNum":null,"totalStocks":1000,"deliveryMode":null,"deliveryTemplateId":null,"createTime":"2021-03-05 11:25:00","updateTime":"2021-03-05 11:25:00","content":null,"putawayTime":"2021-03-05 11:25:00","version":null,"isSpread":null,"isSignboard":null,"skuList":null,"categoryList":null,"shopName":null,"tagList":null,"activityPrice":null,"activityId":null,"activityTimes":null,"activityTimesFlag":0,"activityOrderTimes":0,"activityOrderFlag":0,"isOnlySelfmention":null,"isHotProd":0,"isGroupProd":null,"saleType":2,"barCode":"318155367245","prodType":null,"count":0,"phoneNumber":null,"unit":"盒","vipDiscount":0,"vipPrice":null}
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
         * prodId : 1460
         * shopId : 116
         * prodName : 小二啷当面包
         * prodNamePy : XE啷DMB
         * oriPrice : null
         * price : 55.0
         * brief : null
         * pic : null
         * imgs : null
         * status : 1
         * categoryId : 293
         * soldNum : null
         * totalStocks : 1000
         * deliveryMode : null
         * deliveryTemplateId : null
         * createTime : 2021-03-05 11:25:00
         * updateTime : 2021-03-05 11:25:00
         * content : null
         * putawayTime : 2021-03-05 11:25:00
         * version : null
         * isSpread : null
         * isSignboard : null
         * skuList : null
         * categoryList : null
         * shopName : null
         * tagList : null
         * activityPrice : null
         * activityId : null
         * activityTimes : null
         * activityTimesFlag : 0
         * activityOrderTimes : 0
         * activityOrderFlag : 0
         * isOnlySelfmention : null
         * isHotProd : 0
         * isGroupProd : null
         * saleType : 2
         * barCode : 318155367245
         * prodType : null
         * count : 0
         * phoneNumber : null
         * unit : 盒
         * vipDiscount : 0
         * vipPrice : null
         */

        private int prodId;
        private int shopId;
        private String prodName;
        private String prodNamePy;
        private Object oriPrice;
        private double price;
        private Object brief;
        private Object pic;
        private Object imgs;
        private int status;
        private int categoryId;
        private Object soldNum;
        private int totalStocks;
        private Object deliveryMode;
        private Object deliveryTemplateId;
        private String createTime;
        private String updateTime;
        private Object content;
        private String putawayTime;
        private Object version;
        private Object isSpread;
        private Object isSignboard;
        private Object skuList;
        private Object categoryList;
        private Object shopName;
        private Object tagList;
        private Object activityPrice;
        private Object activityId;
        private Object activityTimes;
        private int activityTimesFlag;
        private int activityOrderTimes;
        private int activityOrderFlag;
        private Object isOnlySelfmention;
        private int isHotProd;
        private Object isGroupProd;
        private int saleType;
        private String barCode;
        private Object prodType;
        private int count;
        private Object phoneNumber;
        private String unit;
        private int vipDiscount;
        private Object vipPrice;

        public int getProdId() {
            return prodId;
        }

        public void setProdId(int prodId) {
            this.prodId = prodId;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getProdName() {
            return prodName;
        }

        public void setProdName(String prodName) {
            this.prodName = prodName;
        }

        public String getProdNamePy() {
            return prodNamePy;
        }

        public void setProdNamePy(String prodNamePy) {
            this.prodNamePy = prodNamePy;
        }

        public Object getOriPrice() {
            return oriPrice;
        }

        public void setOriPrice(Object oriPrice) {
            this.oriPrice = oriPrice;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Object getBrief() {
            return brief;
        }

        public void setBrief(Object brief) {
            this.brief = brief;
        }

        public Object getPic() {
            return pic;
        }

        public void setPic(Object pic) {
            this.pic = pic;
        }

        public Object getImgs() {
            return imgs;
        }

        public void setImgs(Object imgs) {
            this.imgs = imgs;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public Object getSoldNum() {
            return soldNum;
        }

        public void setSoldNum(Object soldNum) {
            this.soldNum = soldNum;
        }

        public int getTotalStocks() {
            return totalStocks;
        }

        public void setTotalStocks(int totalStocks) {
            this.totalStocks = totalStocks;
        }

        public Object getDeliveryMode() {
            return deliveryMode;
        }

        public void setDeliveryMode(Object deliveryMode) {
            this.deliveryMode = deliveryMode;
        }

        public Object getDeliveryTemplateId() {
            return deliveryTemplateId;
        }

        public void setDeliveryTemplateId(Object deliveryTemplateId) {
            this.deliveryTemplateId = deliveryTemplateId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public String getPutawayTime() {
            return putawayTime;
        }

        public void setPutawayTime(String putawayTime) {
            this.putawayTime = putawayTime;
        }

        public Object getVersion() {
            return version;
        }

        public void setVersion(Object version) {
            this.version = version;
        }

        public Object getIsSpread() {
            return isSpread;
        }

        public void setIsSpread(Object isSpread) {
            this.isSpread = isSpread;
        }

        public Object getIsSignboard() {
            return isSignboard;
        }

        public void setIsSignboard(Object isSignboard) {
            this.isSignboard = isSignboard;
        }

        public Object getSkuList() {
            return skuList;
        }

        public void setSkuList(Object skuList) {
            this.skuList = skuList;
        }

        public Object getCategoryList() {
            return categoryList;
        }

        public void setCategoryList(Object categoryList) {
            this.categoryList = categoryList;
        }

        public Object getShopName() {
            return shopName;
        }

        public void setShopName(Object shopName) {
            this.shopName = shopName;
        }

        public Object getTagList() {
            return tagList;
        }

        public void setTagList(Object tagList) {
            this.tagList = tagList;
        }

        public Object getActivityPrice() {
            return activityPrice;
        }

        public void setActivityPrice(Object activityPrice) {
            this.activityPrice = activityPrice;
        }

        public Object getActivityId() {
            return activityId;
        }

        public void setActivityId(Object activityId) {
            this.activityId = activityId;
        }

        public Object getActivityTimes() {
            return activityTimes;
        }

        public void setActivityTimes(Object activityTimes) {
            this.activityTimes = activityTimes;
        }

        public int getActivityTimesFlag() {
            return activityTimesFlag;
        }

        public void setActivityTimesFlag(int activityTimesFlag) {
            this.activityTimesFlag = activityTimesFlag;
        }

        public int getActivityOrderTimes() {
            return activityOrderTimes;
        }

        public void setActivityOrderTimes(int activityOrderTimes) {
            this.activityOrderTimes = activityOrderTimes;
        }

        public int getActivityOrderFlag() {
            return activityOrderFlag;
        }

        public void setActivityOrderFlag(int activityOrderFlag) {
            this.activityOrderFlag = activityOrderFlag;
        }

        public Object getIsOnlySelfmention() {
            return isOnlySelfmention;
        }

        public void setIsOnlySelfmention(Object isOnlySelfmention) {
            this.isOnlySelfmention = isOnlySelfmention;
        }

        public int getIsHotProd() {
            return isHotProd;
        }

        public void setIsHotProd(int isHotProd) {
            this.isHotProd = isHotProd;
        }

        public Object getIsGroupProd() {
            return isGroupProd;
        }

        public void setIsGroupProd(Object isGroupProd) {
            this.isGroupProd = isGroupProd;
        }

        public int getSaleType() {
            return saleType;
        }

        public void setSaleType(int saleType) {
            this.saleType = saleType;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public Object getProdType() {
            return prodType;
        }

        public void setProdType(Object prodType) {
            this.prodType = prodType;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public Object getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(Object phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getVipDiscount() {
            return vipDiscount;
        }

        public void setVipDiscount(int vipDiscount) {
            this.vipDiscount = vipDiscount;
        }

        public Object getVipPrice() {
            return vipPrice;
        }

        public void setVipPrice(Object vipPrice) {
            this.vipPrice = vipPrice;
        }
    }
}
