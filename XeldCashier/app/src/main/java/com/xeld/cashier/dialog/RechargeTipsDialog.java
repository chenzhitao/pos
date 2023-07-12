package com.xeld.cashier.dialog;

import android.app.Activity;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import com.xeld.cashier.R;

import butterknife.BindView;

/**
 * Created by 曹荣冠
 * on 2020/7/22.
 */
public class RechargeTipsDialog extends BaseDialog {

    @BindView(R.id.iv_order_pay_state)
    ImageView iv_order_pay_state;

    @BindView(R.id.tv_order_pay_tips)
    TextView tv_order_pay_tips;

    public RechargeTipsDialog(Activity context) {
        super(context);
        showDialog(730, 560);
    }

    @Override
    protected void initView() {
//        init();
//        paySussesHandle();
    }

    private void init() {

//        if (payState == 1) {
//            tv_order_pay_tips.setText("支付成功");
//            iv_order_pay_state.setBackgroundResource(R.mipmap.order_pay_sussess);
//        } else {
//            tv_order_pay_tips.setText("支付失败");
//            iv_order_pay_state.setBackgroundResource(R.mipmap.order_pay_fail);
//        }

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
        return R.layout.dialog_recharge_tips;
    }


    public void dismiss() {
        dismiss();   //自动消失
    }
}

