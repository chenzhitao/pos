package com.unionpay.facepay;

interface IFacePayListener {
    void onDetectResult(boolean result, String msg);
    void onPayResult(boolean result, String msg);
}
