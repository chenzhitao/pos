package com.xeld.cashier.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConfirmOrderBean implements Serializable {

    public String phoneNumber;
    public List<OrderEntity> productList;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<OrderEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderEntity> productList) {
        this.productList = productList;
    }

    public static class OrderEntity implements Serializable {

        public int count;
        public int prodId;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getProdId() {
            return prodId;
        }

        public void setProdId(int prodId) {
            this.prodId = prodId;
        }
    }


}
