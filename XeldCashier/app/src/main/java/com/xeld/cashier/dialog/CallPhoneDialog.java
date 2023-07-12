package com.xeld.cashier.dialog;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xeld.cashier.R;
import com.xeld.cashier.utils.CommonUtils;
import com.xeld.cashier.utils.CommonViewUtils;

import butterknife.BindView;

/**
 * Created by 曹荣冠
 * on 2020/7/22.
 */
public class CallPhoneDialog extends BaseDialog {
    @BindView(R.id.desc_tv)
    TextView descTv;
    @BindView(R.id.cancel_tv)
    TextView cancelTv;
    @BindView(R.id.sure_tv)
    TextView sureTv;
    @BindView(R.id.two_btn_ll)
    LinearLayout twoBtnLl;
    private String phone;

    public CallPhoneDialog(Activity context, String phone) {
        super(context);
        this.phone = phone;
        showDialog();
    }

    @Override
    protected void initView() {
        init();
    }

    private void init() {
        CommonViewUtils.setText(descTv, phone);
        CommonViewUtils.setOnClick(sureTv, view -> {
            CommonUtils.callPhone1(mContext, phone);
            dismiss();
        });
        CommonViewUtils.setOnClick(cancelTv, view -> {
            dismiss();
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.dialog_common_hint;
    }
}

