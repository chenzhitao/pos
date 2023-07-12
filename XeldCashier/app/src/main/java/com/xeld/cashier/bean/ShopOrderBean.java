package com.xeld.cashier.bean;

import com.xeld.cashier.easyhttp.CommonResultBean;

import java.util.Date;
import java.util.List;

public class ShopOrderBean extends CommonResultBean {

    private OrderData data;

    public OrderData getData() {
        return data;
    }

    public void setData(OrderData data) {
        this.data = data;
    }

    public static class OrderData {

        private int current;
        private int pages;

        private List<OrderDetailed> records;

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<OrderDetailed> getRecords() {
            return records;
        }

        public void setRecords(List<OrderDetailed> records) {
            this.records = records;
        }
    }


    public static class OrderDetailed {

        private int actualTotal;
        private int cashier;
        private String cashierName;
        private Date createTime;
        private int deleteStatus;
        private int deleteType;
        private double deliveryPrice;
        private int distributionFlag;
        private int distributionType;
        private String dvyFlowId;
        private double freightAmount;
        private int isPayed;
        private int isSelfRais;
        private int orderId;

        private List<OrderItem> orderItems;

        public int getActualTotal() {
            return actualTotal;
        }

        public void setActualTotal(int actualTotal) {
            this.actualTotal = actualTotal;
        }

        public int getCashier() {
            return cashier;
        }

        public void setCashier(int cashier) {
            this.cashier = cashier;
        }

        public String getCashierName() {
            return cashierName;
        }

        public void setCashierName(String cashierName) {
            this.cashierName = cashierName;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public int getDeleteStatus() {
            return deleteStatus;
        }

        public void setDeleteStatus(int deleteStatus) {
            this.deleteStatus = deleteStatus;
        }

        public int getDeleteType() {
            return deleteType;
        }

        public void setDeleteType(int deleteType) {
            this.deleteType = deleteType;
        }

        public double getDeliveryPrice() {
            return deliveryPrice;
        }

        public void setDeliveryPrice(double deliveryPrice) {
            this.deliveryPrice = deliveryPrice;
        }

        public int getDistributionFlag() {
            return distributionFlag;
        }

        public void setDistributionFlag(int distributionFlag) {
            this.distributionFlag = distributionFlag;
        }

        public int getDistributionType() {
            return distributionType;
        }

        public void setDistributionType(int distributionType) {
            this.distributionType = distributionType;
        }

        public String getDvyFlowId() {
            return dvyFlowId;
        }

        public void setDvyFlowId(String dvyFlowId) {
            this.dvyFlowId = dvyFlowId;
        }

        public double getFreightAmount() {
            return freightAmount;
        }

        public void setFreightAmount(double freightAmount) {
            this.freightAmount = freightAmount;
        }

        public int getIsPayed() {
            return isPayed;
        }

        public void setIsPayed(int isPayed) {
            this.isPayed = isPayed;
        }

        public int getIsSelfRais() {
            return isSelfRais;
        }

        public void setIsSelfRais(int isSelfRais) {
            this.isSelfRais = isSelfRais;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public List<OrderItem> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
        }
    }

    public static class OrderItem {
        /**
         * 订单项ID
         */
        private Long orderItemId;


        private Long shopId;

        /**
         * 订单sub_number
         */
        private String orderNumber;

        /**
         * 产品ID
         */
        private Long prodId;

        /**
         * 产品SkuID
         */
        private Long skuId;

        /**
         * 购物车产品个数
         */
        private Integer prodCount;

        /**
         * 产品名称
         */
        private String prodName;

        /**
         * sku名称
         */
        private String skuName;

        /**
         * 产品主图片路径
         */
        private String pic;

        /**
         * 产品价格
         */
        private Double price;

        /**
         * 用户Id
         */

        private String userId;

        /**
         * 商品总金额
         */
        private Double productTotalAmount;

        /**
         * 购物时间
         */

        private Date recTime;

        /**
         * 评论状态： 0 未评价  1 已评价
         */

        private Integer commSts;

        /**
         * 推广员使用的推销卡号
         */
        private String distributionCardNo;

        /**
         * 加入购物车的时间
         */
        private Date basketDate;


        public Long getOrderItemId() {
            return orderItemId;
        }

        public void setOrderItemId(Long orderItemId) {
            this.orderItemId = orderItemId;
        }

        public Long getShopId() {
            return shopId;
        }

        public void setShopId(Long shopId) {
            this.shopId = shopId;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public Long getProdId() {
            return prodId;
        }

        public void setProdId(Long prodId) {
            this.prodId = prodId;
        }

        public Long getSkuId() {
            return skuId;
        }

        public void setSkuId(Long skuId) {
            this.skuId = skuId;
        }

        public Integer getProdCount() {
            return prodCount;
        }

        public void setProdCount(Integer prodCount) {
            this.prodCount = prodCount;
        }

        public String getProdName() {
            return prodName;
        }

        public void setProdName(String prodName) {
            this.prodName = prodName;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Double getProductTotalAmount() {
            return productTotalAmount;
        }

        public void setProductTotalAmount(Double productTotalAmount) {
            this.productTotalAmount = productTotalAmount;
        }

        public Date getRecTime() {
            return recTime;
        }

        public void setRecTime(Date recTime) {
            this.recTime = recTime;
        }

        public Integer getCommSts() {
            return commSts;
        }

        public void setCommSts(Integer commSts) {
            this.commSts = commSts;
        }

        public String getDistributionCardNo() {
            return distributionCardNo;
        }

        public void setDistributionCardNo(String distributionCardNo) {
            this.distributionCardNo = distributionCardNo;
        }

        public Date getBasketDate() {
            return basketDate;
        }

        public void setBasketDate(Date basketDate) {
            this.basketDate = basketDate;
        }
    }
}
