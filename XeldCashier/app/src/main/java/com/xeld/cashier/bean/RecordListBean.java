package com.xeld.cashier.bean;

import com.google.gson.annotations.SerializedName;
import com.xeld.cashier.easyhttp.CommonResultBean;

import java.io.Serializable;
import java.util.List;

public class RecordListBean extends CommonResultBean {


    /**
     * data : {"records":[{"depositId":100,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 17:04:51","payType":1,"status":1,"orderNumber":"1369212574926770176","updateTime":"2021-03-09 17:04:52","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":99,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 17:03:29","payType":2,"status":1,"orderNumber":"1369212231627182080","updateTime":"2021-03-09 17:03:38","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":98,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 16:46:00","payType":2,"status":1,"orderNumber":"1369207831147974656","updateTime":"2021-03-09 16:47:16","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":96,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 15:30:31","payType":1,"status":1,"orderNumber":"1369188834738311168","updateTime":"2021-03-09 15:30:32","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":95,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 15:22:08","payType":1,"status":1,"orderNumber":"1369186726005837824","updateTime":"2021-03-09 15:22:09","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":94,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 15:05:12","payType":1,"status":1,"orderNumber":"1369182464106434560","updateTime":"2021-03-09 15:05:13","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":93,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 15:01:56","payType":1,"status":1,"orderNumber":"1369181642631024640","updateTime":"2021-03-09 15:01:57","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":92,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 14:58:03","payType":1,"status":1,"orderNumber":"1369180663839854592","updateTime":"2021-03-09 14:58:03","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":91,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 14:40:00","payType":1,"status":1,"orderNumber":"1369176123207979008","updateTime":"2021-03-09 14:40:01","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":90,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 14:29:07","payType":1,"status":1,"orderNumber":"1369173384050642944","updateTime":"2021-03-09 14:29:08","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null}],"total":23,"size":10,"current":1,"searchCount":true,"pages":3}
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
         * records : [{"depositId":100,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 17:04:51","payType":1,"status":1,"orderNumber":"1369212574926770176","updateTime":"2021-03-09 17:04:52","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":99,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 17:03:29","payType":2,"status":1,"orderNumber":"1369212231627182080","updateTime":"2021-03-09 17:03:38","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":98,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 16:46:00","payType":2,"status":1,"orderNumber":"1369207831147974656","updateTime":"2021-03-09 16:47:16","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":96,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 15:30:31","payType":1,"status":1,"orderNumber":"1369188834738311168","updateTime":"2021-03-09 15:30:32","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":95,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 15:22:08","payType":1,"status":1,"orderNumber":"1369186726005837824","updateTime":"2021-03-09 15:22:09","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":94,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 15:05:12","payType":1,"status":1,"orderNumber":"1369182464106434560","updateTime":"2021-03-09 15:05:13","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":93,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 15:01:56","payType":1,"status":1,"orderNumber":"1369181642631024640","updateTime":"2021-03-09 15:01:57","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":92,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 14:58:03","payType":1,"status":1,"orderNumber":"1369180663839854592","updateTime":"2021-03-09 14:58:03","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":91,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 14:40:00","payType":1,"status":1,"orderNumber":"1369176123207979008","updateTime":"2021-03-09 14:40:01","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null},{"depositId":90,"userId":"70086cb7a8504f6b854b4ade9f9088a3","cashier":4,"shopId":116,"amount":100,"createTime":"2021-03-09 14:29:07","payType":1,"status":1,"orderNumber":"1369173384050642944","updateTime":"2021-03-09 14:29:08","cid":"5c0cb7fcc8f134357cde0aa3b66a880d","enterAccountTime":null,"enterAccountStatus":2,"presentMoney":null}]
         * total : 23
         * size : 10
         * current : 1
         * searchCount : true
         * pages : 3
         */

        private int total;
        private int size;
        private int current;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;

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

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean implements Serializable {
            /**
             * depositId : 100
             * userId : 70086cb7a8504f6b854b4ade9f9088a3
             * cashier : 4
             * shopId : 116
             * amount : 100.0
             * createTime : 2021-03-09 17:04:51
             * payType : 1
             * status : 1
             * orderNumber : 1369212574926770176
             * updateTime : 2021-03-09 17:04:52
             * cid : 5c0cb7fcc8f134357cde0aa3b66a880d
             * enterAccountTime : null
             * enterAccountStatus : 2
             * presentMoney : null
             */

            private int depositId;
            private String userId;
            private int cashier;
            private int shopId;
            private double amount;
            private String createTime;
            private int payType;
            private int status;
            private String orderNumber;
            private String updateTime;
            private String cid;
            private Object enterAccountTime;
            private int enterAccountStatus;
            private Object presentMoney;

            public int getDepositId() {
                return depositId;
            }

            public void setDepositId(int depositId) {
                this.depositId = depositId;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getCashier() {
                return cashier;
            }

            public void setCashier(int cashier) {
                this.cashier = cashier;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getOrderNumber() {
                return orderNumber;
            }

            public void setOrderNumber(String orderNumber) {
                this.orderNumber = orderNumber;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public Object getEnterAccountTime() {
                return enterAccountTime;
            }

            public void setEnterAccountTime(Object enterAccountTime) {
                this.enterAccountTime = enterAccountTime;
            }

            public int getEnterAccountStatus() {
                return enterAccountStatus;
            }

            public void setEnterAccountStatus(int enterAccountStatus) {
                this.enterAccountStatus = enterAccountStatus;
            }

            public Object getPresentMoney() {
                return presentMoney;
            }

            public void setPresentMoney(Object presentMoney) {
                this.presentMoney = presentMoney;
            }
        }
    }
}
