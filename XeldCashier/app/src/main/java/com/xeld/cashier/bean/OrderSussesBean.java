package com.xeld.cashier.bean;

import com.xeld.cashier.easyhttp.CommonResultBean;

public class OrderSussesBean extends CommonResultBean {

    private OrderInfo data;

    public OrderInfo getData() {
        return data;
    }

    public void setData(OrderInfo data) {
        this.data = data;
    }

    public static class OrderInfo{

        /**
         * actualTotal : 59.8
         * total : null
         * totalCount : 2
         * orderReduce : 0
         * userAddr : null
         * shopCartOrders : null
         * coupons : null
         * couponRecords : null
         * orderNumber : 1354240633233084416
         */

        private double actualTotal;
        private Object total;
        private int totalCount;
        private int orderReduce;
        private Object userAddr;
        private Object shopCartOrders;
        private Object coupons;
        private Object couponRecords;
        private String orderNumber;

        public double getActualTotal() {
            return actualTotal;
        }

        public void setActualTotal(double actualTotal) {
            this.actualTotal = actualTotal;
        }

        public Object getTotal() {
            return total;
        }

        public void setTotal(Object total) {
            this.total = total;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getOrderReduce() {
            return orderReduce;
        }

        public void setOrderReduce(int orderReduce) {
            this.orderReduce = orderReduce;
        }

        public Object getUserAddr() {
            return userAddr;
        }

        public void setUserAddr(Object userAddr) {
            this.userAddr = userAddr;
        }

        public Object getShopCartOrders() {
            return shopCartOrders;
        }

        public void setShopCartOrders(Object shopCartOrders) {
            this.shopCartOrders = shopCartOrders;
        }

        public Object getCoupons() {
            return coupons;
        }

        public void setCoupons(Object coupons) {
            this.coupons = coupons;
        }

        public Object getCouponRecords() {
            return couponRecords;
        }

        public void setCouponRecords(Object couponRecords) {
            this.couponRecords = couponRecords;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }
    }

}
