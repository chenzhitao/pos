package com.xeld.cashier.bean;

import com.google.gson.annotations.SerializedName;
import com.xeld.cashier.easyhttp.CommonResultBean;

import java.io.Serializable;
import java.util.List;


public class ProductListBean extends CommonResultBean {

    public ProductPage data;

    public ProductPage getData() {
        return data;
    }

    public void setData(ProductPage data) {
        this.data = data;
    }

    public static class ProductPage {

        private int total;
        private int size;
        private int current;
        private boolean searchCount;
        private int pages;
        private List<ProductListBean.DataBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<DataBean> getRecords() {
            return records;
        }

        public void setRecords(List<DataBean> records) {
            this.records = records;
        }
    }

    public static class DataBean implements Serializable {
        /**
         * prodId : 864
         * shopId : 1
         * prodName : 花生酥原味160g袋装
         * oriPrice : 19.9
         * price : 15.9
         * brief : 零添加 更营养
         * pic : https://images.51xeld.com/2020/09/59062f7d4e4748ff851bc7f8415db274.jpg
         * imgs : 2020/09/59062f7d4e4748ff851bc7f8415db274.jpg,2020/09/17eab5c778df41e896d5390c0e4c6a42.jpg,2020/09/2d0f204fb00342b5b496bab50754e75d.jpg,2020/09/c9fe1263e21b4c68ba4d321071b2db89.jpg
         * status : 1
         * categoryId : 106
         * soldNum : 10
         * totalStocks : 99989
         * deliveryMode : null
         * deliveryTemplateId : null
         * createTime : 2020-07-23 16:30:15
         * updateTime : 2020-11-02 13:35:36
         * content : <p><img src="https://images.51xeld.com/2020/07/1859c6ac0a0c4e3dbc8614cb604152d0.jpg" alt="" width="750" height="963" /><img src="https://images.51xeld.com/2020/07/16ccd7f334ed4c0aa885812c4edbe9a4.jpg" alt="" width="750" height="1038" /><img src="https://images.51xeld.com/2020/07/6e472b274eb04cdbbf978491635fe68d.jpg" alt="" /><img src="https://images.51xeld.com/2020/07/4d99dcacacdb400dbc3fbeeea7c9217c.jpg" alt="" width="750" height="1002" /><img src="https://images.51xeld.com/2020/07/42807b5fd3f540ccaaca37268f356fb5.jpg" alt="" /><img src="https://images.51xeld.com/2020/07/43cb22901e534699b9c65762f9fd54ce.jpg" alt="" width="750" height="844" /></p>
         * putawayTime : 2020-11-02 13:35:36
         * version : null
         * isSpread : 0
         * isSignboard : 0
         * skuList : null
         * categoryList : null
         * shopName : null
         * tagList : null
         * activityPrice : 0.0
         * activityId : 0
         * activityTimes : 0
         * activityTimesFlag : 0
         * activityOrderTimes : 0
         * activityOrderFlag : 0
         * isOnlySelfmention : 1
         * isHotProd : 0
         * isGroupProd : null
         * saleType : 2
         * barCode : null
         */

        private int prodId;
        private int shopId;
        private String prodName;
        private double oriPrice;
        private double price;
        private String brief;
        private String pic;
        private String imgs;
        private int status;
        private int categoryId;
        private int soldNum;
        private int totalStocks;
        private String deliveryMode;
        private Object deliveryTemplateId;
        private String createTime;
        private String updateTime;
        private String content;
        private String putawayTime;
        private Object version;
        private int isSpread;
        private int isSignboard;
        private Object skuList;
        private Object categoryList;
        private Object shopName;
        private Object tagList;
        private double activityPrice;
        private int activityId;
        private int activityTimes;
        private int activityTimesFlag;
        private int activityOrderTimes;
        private int activityOrderFlag;
        private String isOnlySelfmention;
        private int isHotProd;
        private Object isGroupProd;
        private int saleType;
        private Object barCode;

        private double vipPrice;
        private int vipDiscount;

        public int getVipDiscount() {
            return vipDiscount;
        }

        public void setVipDiscount(int vipDiscount) {
            this.vipDiscount = vipDiscount;
        }



        public double getVipPrice() {
            return vipPrice;
        }

        public void setVipPrice(double vipPrice) {
            this.vipPrice = vipPrice;
        }

        private int orderNum = 1;

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

        public double getOriPrice() {
            return oriPrice;
        }

        public void setOriPrice(double oriPrice) {
            this.oriPrice = oriPrice;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
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

        public int getSoldNum() {
            return soldNum;
        }

        public void setSoldNum(int soldNum) {
            this.soldNum = soldNum;
        }

        public int getTotalStocks() {
            return totalStocks;
        }

        public void setTotalStocks(int totalStocks) {
            this.totalStocks = totalStocks;
        }

        public String getDeliveryMode() {
            return deliveryMode;
        }

        public void setDeliveryMode(String deliveryMode) {
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
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

        public int getIsSpread() {
            return isSpread;
        }

        public void setIsSpread(int isSpread) {
            this.isSpread = isSpread;
        }

        public int getIsSignboard() {
            return isSignboard;
        }

        public void setIsSignboard(int isSignboard) {
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

        public double getActivityPrice() {
            return activityPrice;
        }

        public void setActivityPrice(double activityPrice) {
            this.activityPrice = activityPrice;
        }

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

        public int getActivityTimes() {
            return activityTimes;
        }

        public void setActivityTimes(int activityTimes) {
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

        public String getIsOnlySelfmention() {
            return isOnlySelfmention;
        }

        public void setIsOnlySelfmention(String isOnlySelfmention) {
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

        public Object getBarCode() {
            return barCode;
        }

        public void setBarCode(Object barCode) {
            this.barCode = barCode;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }
    }
}
