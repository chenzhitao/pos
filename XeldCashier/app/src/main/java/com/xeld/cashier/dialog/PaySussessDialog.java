package com.xeld.cashier.dialog;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xeld.cashier.R;
import com.xeld.cashier.utils.CommonUtils;
import com.xeld.cashier.utils.CommonViewUtils;

import android.os.CountDownTimer;

import butterknife.BindView;

/**
 * Created by 曹荣冠
 * on 2020/7/22.
 */
public class PaySussessDialog extends BaseDialog {

    private int payState = 0;

    @BindView(R.id.iv_order_pay_state)
    ImageView iv_order_pay_state;

    @BindView(R.id.tv_order_pay_tips)
    TextView tv_order_pay_tips;

    public PaySussessDialog(Activity context, int payState) {
        super(context);
        this.payState = payState;
        showDialog(730, 560);
    }

    @Override
    protected void initView() {
        init();
        paySussesHandle();
    }

    private void init() {

        if (payState == 1) {
            tv_order_pay_tips.setText("支付成功");
            iv_order_pay_state.setBackgroundResource(R.mipmap.order_pay_sussess);
        } else {
            tv_order_pay_tips.setText("支付失败");
            iv_order_pay_state.setBackgroundResource(R.mipmap.order_pay_fail);
        }

//        CommonViewUtils.setOnClick(sureTv, view -> {
//            CommonUtils.callPhone1(mContext, phone);
//            dismiss();
//        });
//        CommonViewUtils.setOnClick(cancelTv, view -> {
//            dismiss();
//        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.dialog_pay_sussess;
    }


    private void paySussesHandle() {
        /**
         * CountDownTimer timer = new CountDownTimer(3000, 1000)中，
         * 第一个参数表示总时间，第二个参数表示间隔时间。
         * 意思就是每隔一秒会回调一次方法onTick，然后1秒之后会回调onFinish方法。
         */
        CountDownTimer timer = new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
//                tv_susses_pay_ok.setText("倒计时" + millisUntilFinished / 1000 + "秒");
            }

            public void onFinish() {

                dismiss();   //自动消失
                listener.onResult(payState);
            }
        };
        //调用 CountDownTimer 对象的 start() 方法开始倒计时，也不涉及到线程处理
        timer.start();
    }
}

