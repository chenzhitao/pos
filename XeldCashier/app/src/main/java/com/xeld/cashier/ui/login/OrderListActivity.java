package com.xeld.cashier.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.elvishew.xlog.XLog;
import com.sunmi.peripheral.printer.InnerPrinterCallback;
import com.sunmi.peripheral.printer.InnerPrinterException;
import com.sunmi.peripheral.printer.InnerPrinterManager;
import com.sunmi.peripheral.printer.SunmiPrinterService;
import com.xeld.cashier.utils.JsonParseUtils;
import com.xeld.cashier.BaseAct;
import com.xeld.cashier.R;
import com.xeld.cashier.base.fragment.BaseEventBean;
import com.xeld.cashier.fragment.OrderListFragment;
import com.xeld.cashier.bean.ShopOrderDetailBean.DataBean.RecordsBean;
import com.xeld.cashier.bean.ShopOrderDetailBean;
import com.xeld.cashier.bean.HomeMemberInfoBean;
import com.xeld.cashier.presenter.PrinterPresenter;
import com.xeld.cashier.utils.CommonViewUtils;
import com.xeld.cashier.utils.PreferencesUtil;
import com.xeld.cashier.constant.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListActivity extends BaseAct {

    private OrderListFragment orderListFragment;
    public static PrinterPresenter printerPresenter;
    private SunmiPrinterService woyouService = null;//商米标准打印 打印服务

    @BindView(R.id.ly_logo_info)
    LinearLayout layoutLogoInfo;

    @BindView(R.id.tv_shop_name)
    TextView tv_shop_name;
    @BindView(R.id.tv_cashier_name)
    TextView tv_cashier_name;

    private InnerPrinterCallback innerPrinterCallback = new InnerPrinterCallback() {
        @Override
        protected void onConnected(SunmiPrinterService service) {
            woyouService = service;
            printerPresenter = new PrinterPresenter(OrderListActivity.this, woyouService);

        }

        @Override
        protected void onDisconnected() {
            woyouService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        mContext = this;
        ButterKnife.bind(this);//一定要在layout初始化之后再绑定。
        EventBus.getDefault().register(this);//一定要在layout初始化之后再绑定。

        orderListFragment = new OrderListFragment();
        replaceFragment(orderListFragment);

        CommonViewUtils.setOnClick(layoutLogoInfo, view -> {
            Intent intent = new Intent(getApplicationContext(), TestActivity.class);
            startActivity(intent);
        });
        connectPrintService();
        tv_shop_name.setText(PreferencesUtil.getString(mContext, Constant.SP_SHOPONAME));
        tv_cashier_name.setText(PreferencesUtil.getString(mContext, Constant.SP_SHOPOWNER));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (woyouService != null) {
            try {
                InnerPrinterManager.getInstance().unBindService(this,
                        innerPrinterCallback);
            } catch (InnerPrinterException e) {
                e.printStackTrace();
            }
        }
        printerPresenter = null;
        //当Activity退出以后，重置点击事件的itemID
        PreferencesUtil.setInt(this, Constant.SP_LISTITEM_ID, -1);
    }

    //连接打印服务
    private void connectPrintService() {

        try {
            InnerPrinterManager.getInstance().bindService(this,
                    innerPrinterCallback);
        } catch (InnerPrinterException e) {
            e.printStackTrace();
        }
    }


    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();   // 开启一个事务
        transaction.replace(R.id.layout_order_list_fragment, fragment);
        transaction.commit();
    }

    public void paySuccessToPrinter(String goods_data, int payMode, ShopOrderDetailBean.DataBean.RecordsBean orderBean, HomeMemberInfoBean.DataBean memberInfoBean) {
        XLog.d(goods_data);
        printerPresenter.setRelustOrder(orderBean);
        printerPresenter.setMemberInfoBean(memberInfoBean);
        printerPresenter.print(goods_data, payMode);
    }


    /**
     * 处理扫码支付硬件信息
     * 扫码枪扫码指令-通过广播接受者，传递在此，在此次做分发处理
     *
     * @param eventBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(BaseEventBean eventBean) {

//        if (eventBean.getType() == BaseEventBean.GETUI_PUSH_MSG) {
//            String msg = (String) eventBean.getValue();
//            if (msg.contains("token")) {
//                JsonParseUtils.parseTokenBean(mContext,msg);
//            }
//        }
    }

    private void gotoLoginAct() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

        finish();//关闭当前活动
    }
}