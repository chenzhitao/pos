package com.xeld.cashier.bean;

import com.google.gson.annotations.SerializedName;
import com.xeld.cashier.easyhttp.CommonResultBean;

import java.io.Serializable;

public class ShopInfobean extends CommonResultBean {


    /**
     * data : {"shopId":1,"shopName":"小二啷当","userId":null,"shopType":null,"intro":"致力为社会提供健康、美味、营养的精品美食！","shopNotice":null,"shopIndustry":null,"shopOwner":"周月","mobile":"18721961968","tel":"","shopLat":"32.126229","shopLng":"112.241647","shopAddress":"佳海工业园金煌食品A43-1","province":"湖北省","city":"襄阳市","area":"高新区","pcaCode":"44/4401/440113","shopLogo":"2018/08/78a6a63cf02d4965912bc5047f49afa0.jpg","shopPhotos":"2018/08/c7a50f443a85462d8129d83cf0f7eb91.jpg,2018/08/79791fc749444ef1ab4d2ca56fe9363f.jpg","openTime":"07:00:00-23:59:00","shopStatus":1,"transportType":null,"fixedFreight":null,"fullFreeShipping":null,"createTime":"2018-08-30 11:10:05","updateTime":null,"isDistribution":1,"state":0,"account":15171.3,"distance":null,"agentId":null,"bussinessLicense":"2020/09/26af071c5ddf4f1d97cf1c18d3fffc2e.jpg","idCard":"2020/08/58095366e20542b88590910176ff19a7.png,2020/08/cd5e5a2f182c4424910100d106185c39.png","rebateMoney":2140.5,"hygienicLicense":"2020/09/2bbdccfd651a472292a4996d8fb9bf60.jpg","foodBusinessLicense":null,"cardOwner":"周月","cardNumber":"6217001180001604985","openBank":"中国建设银行","startPrice":0,"deliveryPrice":0,"meituanDistributionFlag":0,"mainStore":0,"loginName":null,"shopTypeId":null,"cashMoney":11849.18}
     * resultMsg : null
     */

    private DataBean data;

    @SerializedName("resultMsg")
    private Object resultMsgX;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * shopId : 1
         * shopName : 小二啷当
         * userId : null
         * shopType : null
         * intro : 致力为社会提供健康、美味、营养的精品美食！
         * shopNotice : null
         * shopIndustry : null
         * shopOwner : 周月
         * mobile : 18721961968
         * tel :
         * shopLat : 32.126229
         * shopLng : 112.241647
         * shopAddress : 佳海工业园金煌食品A43-1
         * province : 湖北省
         * city : 襄阳市
         * area : 高新区
         * pcaCode : 44/4401/440113
         * shopLogo : 2018/08/78a6a63cf02d4965912bc5047f49afa0.jpg
         * shopPhotos : 2018/08/c7a50f443a85462d8129d83cf0f7eb91.jpg,2018/08/79791fc749444ef1ab4d2ca56fe9363f.jpg
         * openTime : 07:00:00-23:59:00
         * shopStatus : 1
         * transportType : null
         * fixedFreight : null
         * fullFreeShipping : null
         * createTime : 2018-08-30 11:10:05
         * updateTime : null
         * isDistribution : 1
         * state : 0
         * account : 15171.3
         * distance : null
         * agentId : null
         * bussinessLicense : 2020/09/26af071c5ddf4f1d97cf1c18d3fffc2e.jpg
         * idCard : 2020/08/58095366e20542b88590910176ff19a7.png,2020/08/cd5e5a2f182c4424910100d106185c39.png
         * rebateMoney : 2140.5
         * hygienicLicense : 2020/09/2bbdccfd651a472292a4996d8fb9bf60.jpg
         * foodBusinessLicense : null
         * cardOwner : 周月
         * cardNumber : 6217001180001604985
         * openBank : 中国建设银行
         * startPrice : 0.0
         * deliveryPrice : 0.0
         * meituanDistributionFlag : 0
         * mainStore : 0
         * loginName : null
         * shopTypeId : null
         * cashMoney : 11849.18
         */

        private int shopId;
        private String shopName;
        private Object userId;
        private Object shopType;
        private String intro;
        private Object shopNotice;
        private Object shopIndustry;
        private String shopOwner;
        private String mobile;
        private String tel;
        private String shopLat;
        private String shopLng;
        private String shopAddress;
        private String province;
        private String city;
        private String area;
        private String pcaCode;
        private String shopLogo;
        private String shopPhotos;
        private String openTime;
        private int shopStatus;
        private Object transportType;
        private Object fixedFreight;
        private Object fullFreeShipping;
        private String createTime;
        private Object updateTime;
        private int isDistribution;
        private int state;
        private double account;
        private Object distance;
        private Object agentId;
        private String bussinessLicense;
        private String idCard;
        private double rebateMoney;
        private String hygienicLicense;
        private Object foodBusinessLicense;
        private String cardOwner;
        private String cardNumber;
        private String openBank;
        private double startPrice;
        private double deliveryPrice;
        private int meituanDistributionFlag;
        private int mainStore;
        private Object loginName;
        private Object shopTypeId;
        private double cashMoney;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public Object getShopType() {
            return shopType;
        }

        public void setShopType(Object shopType) {
            this.shopType = shopType;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public Object getShopNotice() {
            return shopNotice;
        }

        public void setShopNotice(Object shopNotice) {
            this.shopNotice = shopNotice;
        }

        public Object getShopIndustry() {
            return shopIndustry;
        }

        public void setShopIndustry(Object shopIndustry) {
            this.shopIndustry = shopIndustry;
        }

        public String getShopOwner() {
            return shopOwner;
        }

        public void setShopOwner(String shopOwner) {
            this.shopOwner = shopOwner;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getShopLat() {
            return shopLat;
        }

        public void setShopLat(String shopLat) {
            this.shopLat = shopLat;
        }

        public String getShopLng() {
            return shopLng;
        }

        public void setShopLng(String shopLng) {
            this.shopLng = shopLng;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getPcaCode() {
            return pcaCode;
        }

        public void setPcaCode(String pcaCode) {
            this.pcaCode = pcaCode;
        }

        public String getShopLogo() {
            return shopLogo;
        }

        public void setShopLogo(String shopLogo) {
            this.shopLogo = shopLogo;
        }

        public String getShopPhotos() {
            return shopPhotos;
        }

        public void setShopPhotos(String shopPhotos) {
            this.shopPhotos = shopPhotos;
        }

        public String getOpenTime() {
            return openTime;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public int getShopStatus() {
            return shopStatus;
        }

        public void setShopStatus(int shopStatus) {
            this.shopStatus = shopStatus;
        }

        public Object getTransportType() {
            return transportType;
        }

        public void setTransportType(Object transportType) {
            this.transportType = transportType;
        }

        public Object getFixedFreight() {
            return fixedFreight;
        }

        public void setFixedFreight(Object fixedFreight) {
            this.fixedFreight = fixedFreight;
        }

        public Object getFullFreeShipping() {
            return fullFreeShipping;
        }

        public void setFullFreeShipping(Object fullFreeShipping) {
            this.fullFreeShipping = fullFreeShipping;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public int getIsDistribution() {
            return isDistribution;
        }

        public void setIsDistribution(int isDistribution) {
            this.isDistribution = isDistribution;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public double getAccount() {
            return account;
        }

        public void setAccount(double account) {
            this.account = account;
        }

        public Object getDistance() {
            return distance;
        }

        public void setDistance(Object distance) {
            this.distance = distance;
        }

        public Object getAgentId() {
            return agentId;
        }

        public void setAgentId(Object agentId) {
            this.agentId = agentId;
        }

        public String getBussinessLicense() {
            return bussinessLicense;
        }

        public void setBussinessLicense(String bussinessLicense) {
            this.bussinessLicense = bussinessLicense;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public double getRebateMoney() {
            return rebateMoney;
        }

        public void setRebateMoney(double rebateMoney) {
            this.rebateMoney = rebateMoney;
        }

        public String getHygienicLicense() {
            return hygienicLicense;
        }

        public void setHygienicLicense(String hygienicLicense) {
            this.hygienicLicense = hygienicLicense;
        }

        public Object getFoodBusinessLicense() {
            return foodBusinessLicense;
        }

        public void setFoodBusinessLicense(Object foodBusinessLicense) {
            this.foodBusinessLicense = foodBusinessLicense;
        }

        public String getCardOwner() {
            return cardOwner;
        }

        public void setCardOwner(String cardOwner) {
            this.cardOwner = cardOwner;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getOpenBank() {
            return openBank;
        }

        public void setOpenBank(String openBank) {
            this.openBank = openBank;
        }

        public double getStartPrice() {
            return startPrice;
        }

        public void setStartPrice(double startPrice) {
            this.startPrice = startPrice;
        }

        public double getDeliveryPrice() {
            return deliveryPrice;
        }

        public void setDeliveryPrice(double deliveryPrice) {
            this.deliveryPrice = deliveryPrice;
        }

        public int getMeituanDistributionFlag() {
            return meituanDistributionFlag;
        }

        public void setMeituanDistributionFlag(int meituanDistributionFlag) {
            this.meituanDistributionFlag = meituanDistributionFlag;
        }

        public int getMainStore() {
            return mainStore;
        }

        public void setMainStore(int mainStore) {
            this.mainStore = mainStore;
        }

        public Object getLoginName() {
            return loginName;
        }

        public void setLoginName(Object loginName) {
            this.loginName = loginName;
        }

        public Object getShopTypeId() {
            return shopTypeId;
        }

        public void setShopTypeId(Object shopTypeId) {
            this.shopTypeId = shopTypeId;
        }

        public double getCashMoney() {
            return cashMoney;
        }

        public void setCashMoney(double cashMoney) {
            this.cashMoney = cashMoney;
        }
    }
}
