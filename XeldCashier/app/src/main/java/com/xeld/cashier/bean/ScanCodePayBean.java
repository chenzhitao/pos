package com.xeld.cashier.bean;

import java.io.Serializable;

public class ScanCodePayBean implements Serializable {


    /**
     * errCode : 00
     * errInfo : 00000成功响应码
     * transactionTime : 160214
     * transactionDate : 0224
     * settlementDate : 1224
     * transactionDateWithYear : 20210224
     * settlementDateWithYear : 20211224
     * retrievalRefNum : 40007399483W
     * transactionAmount : 1590
     * actualTransactionAmount : 1490
     * amount : 1490
     * orderId : 20210224160214440007399483
     * marketingAllianceDiscountInstruction : 原价:15.90元,优惠:1.00元,应付:14.90元
     * thirdPartyDiscountInstrution : 微信钱包支付14.9元,商户优惠1.0元
     * thirdPartyDiscountInstruction : 微信钱包支付14.9元,商户优惠1.0元
     * thirdPartyName : 微信钱包
     * userId : otdJ_uD-whMKtPVxlf6B1Kzs-XXc
     * thirdPartyBuyerId : otdJ_uD-whMKtPVxlf6B1Kzs-XXc
     * thirdPartyOrderId : 4200000935202102241814547021
     * thirdPartyPayInformation : 现金:1490
     * discountStatus : 1
     * cardAttr : 03
     * merchantAllowance : 100
     */

    private String errCode;
    private String errInfo;
    private String transactionTime;
    private String transactionDate;
    private String settlementDate;
    private String transactionDateWithYear;
    private String settlementDateWithYear;
    private String retrievalRefNum;
    private int transactionAmount;
    private int actualTransactionAmount;
    private int amount;
    private String orderId;
    private String marketingAllianceDiscountInstruction;
    private String thirdPartyDiscountInstrution;
    private String thirdPartyDiscountInstruction;
    private String thirdPartyName;
    private String userId;
    private String thirdPartyBuyerId;
    private String thirdPartyOrderId;
    private String thirdPartyPayInformation;
    private int discountStatus;
    private String cardAttr;
    private int merchantAllowance;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getTransactionDateWithYear() {
        return transactionDateWithYear;
    }

    public void setTransactionDateWithYear(String transactionDateWithYear) {
        this.transactionDateWithYear = transactionDateWithYear;
    }

    public String getSettlementDateWithYear() {
        return settlementDateWithYear;
    }

    public void setSettlementDateWithYear(String settlementDateWithYear) {
        this.settlementDateWithYear = settlementDateWithYear;
    }

    public String getRetrievalRefNum() {
        return retrievalRefNum;
    }

    public void setRetrievalRefNum(String retrievalRefNum) {
        this.retrievalRefNum = retrievalRefNum;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getActualTransactionAmount() {
        return actualTransactionAmount;
    }

    public void setActualTransactionAmount(int actualTransactionAmount) {
        this.actualTransactionAmount = actualTransactionAmount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMarketingAllianceDiscountInstruction() {
        return marketingAllianceDiscountInstruction;
    }

    public void setMarketingAllianceDiscountInstruction(String marketingAllianceDiscountInstruction) {
        this.marketingAllianceDiscountInstruction = marketingAllianceDiscountInstruction;
    }

    public String getThirdPartyDiscountInstrution() {
        return thirdPartyDiscountInstrution;
    }

    public void setThirdPartyDiscountInstrution(String thirdPartyDiscountInstrution) {
        this.thirdPartyDiscountInstrution = thirdPartyDiscountInstrution;
    }

    public String getThirdPartyDiscountInstruction() {
        return thirdPartyDiscountInstruction;
    }

    public void setThirdPartyDiscountInstruction(String thirdPartyDiscountInstruction) {
        this.thirdPartyDiscountInstruction = thirdPartyDiscountInstruction;
    }

    public String getThirdPartyName() {
        return thirdPartyName;
    }

    public void setThirdPartyName(String thirdPartyName) {
        this.thirdPartyName = thirdPartyName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getThirdPartyBuyerId() {
        return thirdPartyBuyerId;
    }

    public void setThirdPartyBuyerId(String thirdPartyBuyerId) {
        this.thirdPartyBuyerId = thirdPartyBuyerId;
    }

    public String getThirdPartyOrderId() {
        return thirdPartyOrderId;
    }

    public void setThirdPartyOrderId(String thirdPartyOrderId) {
        this.thirdPartyOrderId = thirdPartyOrderId;
    }

    public String getThirdPartyPayInformation() {
        return thirdPartyPayInformation;
    }

    public void setThirdPartyPayInformation(String thirdPartyPayInformation) {
        this.thirdPartyPayInformation = thirdPartyPayInformation;
    }

    public int getDiscountStatus() {
        return discountStatus;
    }

    public void setDiscountStatus(int discountStatus) {
        this.discountStatus = discountStatus;
    }

    public String getCardAttr() {
        return cardAttr;
    }

    public void setCardAttr(String cardAttr) {
        this.cardAttr = cardAttr;
    }

    public int getMerchantAllowance() {
        return merchantAllowance;
    }

    public void setMerchantAllowance(int merchantAllowance) {
        this.merchantAllowance = merchantAllowance;
    }
}
