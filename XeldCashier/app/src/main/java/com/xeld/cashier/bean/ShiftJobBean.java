package com.xeld.cashier.bean;

import com.xeld.cashier.easyhttp.CommonResultBean;

import java.io.Serializable;
import java.util.List;

public class ShiftJobBean extends CommonResultBean {


    /**
     * data : {"loginTime":null,"totalSales":0,"cashSales":0,"umsPaySales":0,"appTransactionRefunds":[],"orders":[],"userDeposits":[],"userDepositAmount":0,"userDepositCount":0,"dealCount":null,"cashCount":null,"umsCount":null,"appTransactionRefundAmount":0,"appTransactionRefundCashAmount":0,"appTransactionRefundUmsAmount":0,"appTransactionRefundCount":0,"appTransactionRefundCashCount":0,"appTransactionRefundUmsCount":0,"accountSales":null}
     * resultMsg : null
     */

    private DataBean data;

    public static class DataBean implements Serializable {
        /**
         * loginTime : null
         * totalSales : 0.0
         * cashSales : 0.0
         * umsPaySales : 0.0
         * appTransactionRefunds : []
         * orders : []
         * userDeposits : []
         * userDepositAmount : 0.0
         * userDepositCount : 0
         * dealCount : null
         * cashCount : null
         * umsCount : null
         * appTransactionRefundAmount : 0.0
         * appTransactionRefundCashAmount : 0.0
         * appTransactionRefundUmsAmount : 0.0
         * appTransactionRefundCount : 0
         * appTransactionRefundCashCount : 0
         * appTransactionRefundUmsCount : 0
         * accountSales : null
         */

        private Object loginTime;
        private double totalSales;
        private double cashSales;
        private double umsPaySales;
        private double userDepositAmount;
        private int userDepositCount;
        private Object dealCount;
        private Object cashCount;
        private Object umsCount;
        private double appTransactionRefundAmount;
        private double appTransactionRefundCashAmount;
        private double appTransactionRefundUmsAmount;
        private int appTransactionRefundCount;
        private int appTransactionRefundCashCount;
        private int appTransactionRefundUmsCount;
        private Object accountSales;
        private List<?> appTransactionRefunds;
        private List<?> orders;
        private List<?> userDeposits;


        public Object getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(Object loginTime) {
            this.loginTime = loginTime;
        }

        public double getTotalSales() {
            return totalSales;
        }

        public void setTotalSales(double totalSales) {
            this.totalSales = totalSales;
        }

        public double getCashSales() {
            return cashSales;
        }

        public void setCashSales(double cashSales) {
            this.cashSales = cashSales;
        }

        public double getUmsPaySales() {
            return umsPaySales;
        }

        public void setUmsPaySales(double umsPaySales) {
            this.umsPaySales = umsPaySales;
        }

        public double getUserDepositAmount() {
            return userDepositAmount;
        }

        public void setUserDepositAmount(double userDepositAmount) {
            this.userDepositAmount = userDepositAmount;
        }

        public int getUserDepositCount() {
            return userDepositCount;
        }

        public void setUserDepositCount(int userDepositCount) {
            this.userDepositCount = userDepositCount;
        }

        public Object getDealCount() {
            return dealCount;
        }

        public void setDealCount(Object dealCount) {
            this.dealCount = dealCount;
        }

        public Object getCashCount() {
            return cashCount;
        }

        public void setCashCount(Object cashCount) {
            this.cashCount = cashCount;
        }

        public Object getUmsCount() {
            return umsCount;
        }

        public void setUmsCount(Object umsCount) {
            this.umsCount = umsCount;
        }

        public double getAppTransactionRefundAmount() {
            return appTransactionRefundAmount;
        }

        public void setAppTransactionRefundAmount(double appTransactionRefundAmount) {
            this.appTransactionRefundAmount = appTransactionRefundAmount;
        }

        public double getAppTransactionRefundCashAmount() {
            return appTransactionRefundCashAmount;
        }

        public void setAppTransactionRefundCashAmount(double appTransactionRefundCashAmount) {
            this.appTransactionRefundCashAmount = appTransactionRefundCashAmount;
        }

        public double getAppTransactionRefundUmsAmount() {
            return appTransactionRefundUmsAmount;
        }

        public void setAppTransactionRefundUmsAmount(double appTransactionRefundUmsAmount) {
            this.appTransactionRefundUmsAmount = appTransactionRefundUmsAmount;
        }

        public int getAppTransactionRefundCount() {
            return appTransactionRefundCount;
        }

        public void setAppTransactionRefundCount(int appTransactionRefundCount) {
            this.appTransactionRefundCount = appTransactionRefundCount;
        }

        public int getAppTransactionRefundCashCount() {
            return appTransactionRefundCashCount;
        }

        public void setAppTransactionRefundCashCount(int appTransactionRefundCashCount) {
            this.appTransactionRefundCashCount = appTransactionRefundCashCount;
        }

        public int getAppTransactionRefundUmsCount() {
            return appTransactionRefundUmsCount;
        }

        public void setAppTransactionRefundUmsCount(int appTransactionRefundUmsCount) {
            this.appTransactionRefundUmsCount = appTransactionRefundUmsCount;
        }

        public Object getAccountSales() {
            return accountSales;
        }

        public void setAccountSales(Object accountSales) {
            this.accountSales = accountSales;
        }

        public List<?> getAppTransactionRefunds() {
            return appTransactionRefunds;
        }

        public void setAppTransactionRefunds(List<?> appTransactionRefunds) {
            this.appTransactionRefunds = appTransactionRefunds;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public List<?> getUserDeposits() {
            return userDeposits;
        }

        public void setUserDeposits(List<?> userDeposits) {
            this.userDeposits = userDeposits;
        }
    }
}
