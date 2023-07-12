package com.unionpay.facepay;

import com.unionpay.facepay.IFacePayListener;

interface IFacePayInterface {
    boolean facePay(String payInfo, IFacePayListener listener);
}
