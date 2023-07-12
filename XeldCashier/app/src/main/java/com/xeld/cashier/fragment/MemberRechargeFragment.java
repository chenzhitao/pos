package com.xeld.cashier.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.elvishew.xlog.XLog;
import com.xeld.cashier.BaseAct;
import com.xeld.cashier.R;
import com.xeld.cashier.base.fragment.BaseEventBean;
import com.xeld.cashier.base.fragment.HomeBaseFragment;
import com.xeld.cashier.bean.HomeMemberInfoBean;
import com.xeld.cashier.bean.RechargeBean;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.dialog.PaySussessDialog;
import com.xeld.cashier.dialog.RechargeTipsDialog;
import com.xeld.cashier.mvp.callback.OnRequestSuccess;
import com.xeld.cashier.mvp.http.URL;
import com.xeld.cashier.ui.login.TestActivity;
import com.xeld.cashier.utils.CommonViewUtils;
import com.xeld.cashier.utils.PreferencesUtil;
import com.xeld.cashier.utils.TTSUtils;
import com.xw.repo.XEditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import butterknife.BindView;

public class MemberRechargeFragment extends HomeBaseFragment implements OnRequestSuccess<Integer> {

    //会员充值关闭
    @BindView(R.id.tv_recgarge_member_fragment)
    TextView tv_recgarge_member_fragment;
    //充值订单号显示
    @BindView(R.id.tv_code_recgarge_order_num)
    TextView tv_code_recgarge_order_num;
    //扫码充值提示
    @BindView(R.id.tv_code_recgarge_order_tips)
    TextView tv_code_recgarge_order_tips;
    //充值确定
    @BindView(R.id.recharge_order_ok)
    TextView recharge_order_ok;
    //充值取消
    @BindView(R.id.recharge_order_no)
    TextView recharge_order_no;
    //现金充值
    @BindView(R.id.recharge_type_cash)
    AppCompatCheckBox recharge_type_cash;
    //扫码充值
    @BindView(R.id.recharge_type_code)
    AppCompatCheckBox recharge_type_code;
    //充值100
    @BindView(R.id.tv_cash_one_hundred)
    TextView tv_cash_one_hundred;
    //充值200
    @BindView(R.id.tv_cash_two_hundred)
    TextView tv_cash_two_hundred;
    ////充值300
    @BindView(R.id.tv_cash_three_hundred)
    TextView tv_cash_three_hundred;
    //充值500
    @BindView(R.id.tv_cash_five_hundred)
    TextView tv_cash_five_hundred;
    //充值800
    @BindView(R.id.tv_cash_eight_hundred)
    TextView tv_cash_eight_hundred;
    //充值 自定义，弹出软键盘，自由输入
    @BindView(R.id.tv_cash_zdy_hundred)
    TextView tv_cash_zdy_hundred;
    //充值金额 显示
    @BindView(R.id.et_cash_pay_recharge_total)
    XEditText et_cash_pay_recharge_total;

    //用来记录 用户的充值金额
    StringBuffer chshBuffer;

    private TestActivity testActivity;
    private BaseAct mBact;
    //存储会员对象
    private HomeMemberInfoBean.DataBean memberInfoBean;
    //记录 生产的充值订单对象
    private RechargeBean rechargeBean;

    private RechargeTipsDialog dialog;

    @Override
    public int getLayout() {
        return R.layout.fragment_member_cash_recharge_layout;
    }

    @Override
    public void initView() {
        mBact = getAct();
        testActivity = (TestActivity) getAct();

        tv_code_recgarge_order_num.setVisibility(View.GONE);
        tv_code_recgarge_order_tips.setVisibility(View.GONE);

        CommonViewUtils.setOnClick(recharge_order_no, view -> {
            testActivity.showProductFragment();
            et_cash_pay_recharge_total.setText("请选择充值金额");

            if (tv_code_recgarge_order_num.getVisibility() == View.VISIBLE)
                tv_code_recgarge_order_num.setVisibility(View.GONE);

            if (tv_code_recgarge_order_tips.getVisibility() == View.VISIBLE)
                tv_code_recgarge_order_tips.setVisibility(View.GONE);
        });

        recharge_type_code.setChecked(true);

        CommonViewUtils.setOnClick(recharge_order_ok, view -> {
//
//            if (recharge_type_cash.isChecked()) {
//                depositMoney();//现金
//                return;
//            }
//            if (recharge_type_code.isChecked()) {
//                depositMoneyByCode();//扫码
//                return;
//            }
//
//            showToast("请选择充值方式");

            depositMoneyByCode();//扫码

        });

        recharge_type_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recharge_type_code.isChecked()) {
                    recharge_type_code.setChecked(false);
                }
            }
        });

        recharge_type_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (recharge_type_cash.isChecked()) {
                    recharge_type_cash.setChecked(false);
                }

            }
        });


        CommonViewUtils.setOnClick(tv_recgarge_member_fragment, view -> {
            testActivity.showProductFragment();
            et_cash_pay_recharge_total.setText("请选择充值金额");
        });

        CommonViewUtils.setOnClick(tv_cash_one_hundred, view -> {
            setEtPhone("100");
        });
        CommonViewUtils.setOnClick(tv_cash_two_hundred, view -> {
            setEtPhone("200");
        });
        CommonViewUtils.setOnClick(tv_cash_three_hundred, view -> {
            setEtPhone("300");
        });

        CommonViewUtils.setOnClick(tv_cash_five_hundred, view -> {
            setEtPhone("500");
        });
        CommonViewUtils.setOnClick(tv_cash_eight_hundred, view -> {
            setEtPhone("800");
        });
        CommonViewUtils.setOnClick(tv_cash_zdy_hundred, view -> {
            setEtPhone("300");
        });

    }

    private void setEtPhone(String chart) {
//        XLog.d(chart);
//        if (chshBuffer == null)
//            chshBuffer = new StringBuffer();
//
//        chshBuffer.append(chart);
//        String numberStr = chshBuffer.toString();
//        etInputCash.setText(chart);
        et_cash_pay_recharge_total.setText(chart);
    }

    /**
     * 获取 充值金额
     * 如果转换失败，那么用户输入的可能不是数字，提示用户输入
     *
     * @return
     */
    private double getRechargeMoney() {
        double moneyDouble = 0.0;
        try {
            String moneyStr = et_cash_pay_recharge_total.getText().toString();
            moneyDouble = Double.parseDouble(moneyStr);
            return moneyDouble;

        } catch (Exception e) {
            e.printStackTrace();
            showToast("请输入正确的金额");
        }

        return moneyDouble;
    }


    /**
     * 现金支付 生成充值订单号
     */
    private void depositMoney() {
        String moneyStr = et_cash_pay_recharge_total.getText().toString().trim();
        if (TextUtils.isEmpty(moneyStr)) {
            showToast("请输入充值金额");
            return;
        }

        try {
            JSONObject json = new JSONObject();
            json.put("depositMoney", getRechargeMoney());
            json.put("mobile", memberInfoBean.getUserMobile());
            json.put("cid", PreferencesUtil.getString(mBact, Constant.SP_CID));
            mBact.showLoading(true);
            easyPost(json, URL.DEBUG_URL + URL.DEPOSITMONEY, RechargeBean.class, result -> {
                onDepositMoney(result);
            });
        } catch (Exception e) {
            mBact.setLog("easyPostLogin", e.getMessage());
            XLog.e(e);
        }
    }

    /**
     * 生成订单号以后
     * 调用充值接口
     *
     * @param result
     */
    private void onDepositMoney(RechargeBean result) {
        mBact.showLoading(false);
        if (result == null) {
            showToast("订单生成失败");
            return;
        }
        this.rechargeBean = result;

        if (result.getResultCode() == 200) {
            depositMoneyByCash(result);
        }
    }

    /**
     * 现金充值
     * 充值接口
     */
    private void depositMoneyByCash(RechargeBean databean) {
        try {
            JSONObject json = new JSONObject();
            json.put("orderNumber", databean.getData());
            mBact.showLoading(true);
            easyPost(json, URL.DEBUG_URL + URL.DEPOSITMONEYBYCASH, HomeMemberInfoBean.class, result -> {
                onDepositMoneyByCash(result);
            });
        } catch (Exception e) {
            mBact.setLog("easyPostLogin", e.getMessage());
            XLog.e(e);
        }
    }

    /**
     * 扫码充值成功以后，不发消息给显示购物车fragment
     *
     * @param result
     */
    private void onDepositMoneyByCode(HomeMemberInfoBean result) {
        mBact.showLoading(false);

        if (result == null) {
            return;
        }
        if (result.getData() == null) {
            return;
        }
        if (result.getData().get(0) == null) {
            return;
        }

        if (result != null) {
            if (result.getData().get(0) != null) {

                //收到支付成功的消息以后，在发消息
//                showToast("解析成功");
//                BaseEventBean eventBean = new BaseEventBean(BaseEventBean.TYPE_GOTO_MEMBER_FRAGMENT);
//                eventBean.setValue(result.getData().get(0));
//                EventBus.getDefault().post(eventBean);
                //重置View
                tv_code_recgarge_order_tips.setVisibility(View.GONE);
                tv_code_recgarge_order_num.setVisibility(View.GONE);
                tv_code_recgarge_order_num.setText("");
                et_cash_pay_recharge_total.setText("");

//                testActivity.showProductFragment();
                testActivity.printMemberRechargeInfo(result.getData().get(0), rechargeBean, 1);


                PaySussessDialog dialog = new PaySussessDialog(getAct(), 1);
                dialog.setListener(this::onResult);

            } else {
                showToast("无会员数据");
                PaySussessDialog dialog = new PaySussessDialog(getAct(), 0);
                dialog.setListener(this::onResult);
            }

        }
    }

    /**
     * 充值成功以后，发消息给显示购物车fragment
     *
     * @param result
     */
    private void onDepositMoneyByCash(HomeMemberInfoBean result) {
        mBact.showLoading(false);

        if (result != null) {
            if (result.getData().get(0) != null) {

                //收到现金支付成功的消息以后，在发消息
                showToast("解析成功");
                BaseEventBean eventBean = new BaseEventBean(BaseEventBean.TYPE_GOTO_MEMBER_FRAGMENT);
                eventBean.setValue(result.getData().get(0));
                EventBus.getDefault().post(eventBean);
                //重置View
                tv_code_recgarge_order_tips.setVisibility(View.GONE);
                tv_code_recgarge_order_num.setVisibility(View.GONE);
                tv_code_recgarge_order_num.setText("");
                et_cash_pay_recharge_total.setText("");

//                testActivity.showProductFragment();
                testActivity.printMemberRechargeInfo(result.getData().get(0), rechargeBean, 1);


                PaySussessDialog dialog = new PaySussessDialog(getAct(), 1);
                dialog.setListener(this::onResult);

            } else {
                showToast("无会员数据");
                PaySussessDialog dialog = new PaySussessDialog(getAct(), 0);
                dialog.setListener(this::onResult);
            }

        }
    }

    /**
     * 扫码 充值成功以后，发消息给购物车，重新请求 会员信息
     */
    public void rechargeSussess() {

        BaseEventBean eventBean = new BaseEventBean(BaseEventBean.UPDATE_MEMBER_FRAGMENT);
        EventBus.getDefault().post(eventBean);

        //重置View
//        tv_code_recgarge_order_tips.setVisibility(View.GONE);
//        tv_code_recgarge_order_num.setVisibility(View.GONE);
//        tv_code_recgarge_order_num.setText("");

        startSpeaking();

        if (dialog != null) {
            dialog.dismiss();
        }

        testActivity.showProductFragment();
    }


    /**
     * 收款成功以后 语音播报
     */
    private void startSpeaking() {

        StringBuffer buffer = new StringBuffer();

        double price = getRechargeMoney();

        buffer.append("扫码充值" + price + "元");

        TTSUtils.getInstance(testActivity).startSpeaking(buffer.toString());

    }

    /**
     * 扫码支付 生成充值订单号
     */
    private void depositMoneyByCode() {
        String moneyStr = et_cash_pay_recharge_total.getText().toString().trim();
        if (TextUtils.isEmpty(moneyStr)) {
            showToast("请输入充值金额");
            return;
        }

        try {
            JSONObject json = new JSONObject();
            json.put("depositMoney", getRechargeMoney());
            json.put("mobile", memberInfoBean.getUserMobile());
            json.put("cid", PreferencesUtil.getString(mBact, Constant.SP_CID));
            mBact.showLoading(true);
            easyPost(json, URL.DEBUG_URL + URL.DEPOSITMONEY, RechargeBean.class, result -> {
                onDepositMoneyByCode(result);
            });
        } catch (Exception e) {
            mBact.setLog("easyPostLogin", e.getMessage());
            XLog.e(e);
        }
    }


    /**
     * 扫码支付
     * 发起支付请求
     */
    public void depositMoneyByUms(String code) {
        try {
            JSONObject json = new JSONObject();
            json.put("orderNumber", rechargeBean.getData());
            json.put("payCode", code);
            mBact.showLoading(true);
            easyPost(json, URL.DEBUG_URL + URL.DEPOSITMONEYBYUMS, HomeMemberInfoBean.class, result -> {
                onDepositMoneyByCode(result);
                //扫码 充值以后，收到服务端请求以后，在更新会员行
            });
        } catch (Exception e) {
            mBact.setLog("easyPostLogin", e.getMessage());
            XLog.e(e);
        }
    }


    /**
     * 扫码充值-生成订单号
     *
     * @param result
     */
    private void onDepositMoneyByCode(RechargeBean result) {
        if (result.getResultCode() == 200) {
            this.rechargeBean = result;
            tv_code_recgarge_order_tips.setVisibility(View.VISIBLE);
            tv_code_recgarge_order_num.setVisibility(View.VISIBLE);
            tv_code_recgarge_order_num.setText(result.getData());

            dialog = new RechargeTipsDialog(testActivity);
        }
    }


    /**
     * 在购物车页面，点击充值，进入充值页面
     * 并且把会员信息对象，传递过来
     *
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(BaseEventBean messageEvent) {
        if (messageEvent.getType() == BaseEventBean.RECHARGE_MEMBER_FRAGMENT) {
            this.memberInfoBean = (HomeMemberInfoBean.DataBean) messageEvent.getValue();
        }
    }

    @Override
    public void onResult(Integer result) {

        if (result == 1) {
            handleOrderSussess();
        }
    }

    private void handleOrderSussess() {

    }
}
