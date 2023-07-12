package com.xeld.cashier.ui.login;

import android.content.ComponentName;
import android.content.Context;
import android.view.WindowManager;
import android.os.Build;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.elvishew.xlog.XLog;
import com.sunmi.extprinterservice.ExtPrinterService;
import com.sunmi.peripheral.printer.InnerPrinterCallback;
import com.sunmi.peripheral.printer.InnerPrinterException;
import com.sunmi.peripheral.printer.InnerPrinterManager;
import com.sunmi.peripheral.printer.SunmiPrinterService;
import com.xeld.cashier.BaseAct;
import com.xeld.cashier.R;
import com.xeld.cashier.base.fragment.BaseEventBean;
import com.xeld.cashier.bean.HomeMemberInfoBean;
import com.xeld.cashier.bean.ShopInfobean;
import com.xeld.cashier.bean.ShopOrderDetailBean;
import com.xeld.cashier.bean.RecordListBean;
import com.xeld.cashier.bean.UserInfoBean;
import com.xeld.cashier.bean.RechargeBean;
import com.xeld.cashier.bean.msg.TokenEventBean;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.easyhttp.CommonResultBean;
import com.xeld.cashier.fragment.HomeAddMemberFragment;
import com.xeld.cashier.fragment.HomeCartFragment;
import com.xeld.cashier.fragment.HomeMemberFragment;
import com.xeld.cashier.fragment.HomePayFragment;
import com.xeld.cashier.fragment.HomeProductFragment;
import com.xeld.cashier.fragment.MemberRechargeFragment;
import com.xeld.cashier.fragment.TempOrderFragment;
import com.xeld.cashier.presenter.KPrinterPresenter;
import com.xeld.cashier.presenter.PrinterPresenter;
import com.xeld.cashier.utils.CommonViewUtils;
import com.xeld.cashier.mvp.http.URL;
import com.xeld.cashier.utils.JsonParseUtils;
import com.xeld.cashier.utils.PreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import woyou.aidlservice.jiuiv5.ICallback;
import woyou.aidlservice.jiuiv5.IWoyouService;

public class TestActivity extends BaseAct implements ICallback {
    //会员
    public HomeMemberFragment memberFragment;
    //增加会员
    public HomeAddMemberFragment addMemberFragment;
    //支付
    public HomePayFragment homePayFragment;
    //购物车
    public HomeCartFragment homeCartFragment;
    //商品列表
    public HomeProductFragment homeProductFragment;
    //会员充值
    public MemberRechargeFragment rechargeFragment;
    //挂单
    private TempOrderFragment tempOrderFragment;

    //销售单
    @BindView(R.id.layout_pay_fragment)
    LinearLayout layout_pay_fragment;
    //选择会员
    @BindView(R.id.layout_member_fragment)
    LinearLayout layout_member_fragment;
    //增加会员
    @BindView(R.id.layout_add_member_fragment)
    LinearLayout layout_add_member_fragment;
    //产品列表
    @BindView(R.id.layout_product_fragment)
    LinearLayout layout_product_fragment;
    //添加产品
    @BindView(R.id.layout_add_product)
    LinearLayout layout_add_product;
    //交接班
    @BindView(R.id.layout_shift_job)
    LinearLayout layout_shift_job;
    //销售单
    @BindView(R.id.layout_title_order)
    LinearLayout layout_title_order;
    //新增会员
    @BindView(R.id.layout_main_add_member)
    LinearLayout layout_main_add_member;
    //选择会员
    @BindView(R.id.layout_main_member)
    LinearLayout layout_main_member;
    //打卡钱箱
    @BindView(R.id.layout_open_drawer)
    LinearLayout layout_open_drawer;
    //logo布局
    @BindView(R.id.ly_logo_info)
    LinearLayout layoutLogoInfo;
    //会员充值布局
    @BindView(R.id.layout_recharge_fragment)
    LinearLayout layout_recharge_fragment;
    //挂单功能
    @BindView(R.id.layout_temp_order_fragment)
    LinearLayout layout_temp_order_fragment;

    //店铺信息-收银员名称
    @BindView(R.id.tv_cashier_name)
    TextView tv_cashier_name;
    //店铺信息-店铺名称
    @BindView(R.id.tv_shop_name)
    TextView tv_shop_name;


    //商米硬件相关的aidl服务
    public static boolean isK1 = false;
    public static boolean isVertical = false;
    private IWoyouService iWoyouService;
    private ServiceConnection sc;
    private SunmiPrinterService woyouService = null;//商米标准打印 打印服务
    private ExtPrinterService extPrinterService = null;//k1 打印服务
    public static PrinterPresenter printerPresenter;
    public static KPrinterPresenter kPrinterPresenter;

    private InnerPrinterCallback innerPrinterCallback = new InnerPrinterCallback() {
        @Override
        protected void onConnected(SunmiPrinterService service) {
            woyouService = service;
            printerPresenter = new PrinterPresenter(TestActivity.this, woyouService);
        }

        @Override
        protected void onDisconnected() {
            woyouService = null;

        }
    };

    private ServiceConnection connService = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            extPrinterService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            extPrinterService = ExtPrinterService.Stub.asInterface(service);
            kPrinterPresenter = new KPrinterPresenter(TestActivity.this, extPrinterService);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xeld_home);
        ButterKnife.bind(this);//一定要在layout初始化之后再绑定。
        EventBus.getDefault().register(this);
        mContext = this;
        memberFragment = new HomeMemberFragment();
        addMemberFragment = new HomeAddMemberFragment();
        homePayFragment = new HomePayFragment();
        homeCartFragment = new HomeCartFragment();
        homeProductFragment = new HomeProductFragment();
        rechargeFragment = new MemberRechargeFragment();
        tempOrderFragment = new TempOrderFragment();

        initTint();

        CommonViewUtils.setOnClick(layoutLogoInfo, view -> {
//            BaseEventBean eventBean = new BaseEventBean(BaseEventBean.TYPE_GOTO_HOME_FRAGMENT);
//            EventBus.getDefault().post(eventBean);
//            replaceFragment(homeFragment);
            selectDepositMoneyRecord();
        });

        CommonViewUtils.setOnClick(layout_title_order, view -> {
            Intent intent = new Intent(getApplicationContext(), OrderListActivity.class);
            startActivity(intent);
        });

        CommonViewUtils.setOnClick(layout_main_add_member, view -> {
            //添加会员的fragment
            showAddMemberFragment();
        });

        CommonViewUtils.setOnClick(layout_main_member, view -> {
            //打开选择会员fragment
            showMemberFragment();
        });

        CommonViewUtils.setOnClick(layout_open_drawer, view -> {
            openDrawer();//打开钱箱
        });
        CommonViewUtils.setOnClick(layout_add_product, view -> {
            //添加产品
            Intent intent = new Intent(getApplicationContext(), AddProductActivity.class);
            startActivity(intent);
        });


        CommonViewUtils.setOnClick(layout_shift_job, view -> {
            //交接班
            Intent intent = new Intent(getApplicationContext(), ShiftJobActivity.class);
            startActivity(intent);
        });

        initHomeFragment();

        connectPrintService();
        connectKPrintService();

        initData();
    }

    private void initData() {
        getShopInfo();
        getUserByUserId();//获取收银员相关的数据
    }

    //首页 fragment
    public void initHomeFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();   // 开启一个事务

        transaction.add(R.id.layout_home_left, homeCartFragment);
        transaction.add(R.id.layout_product_fragment, homeProductFragment);

        transaction.add(R.id.layout_pay_fragment, homePayFragment);
        transaction.add(R.id.layout_member_fragment, memberFragment);
        transaction.add(R.id.layout_add_member_fragment, addMemberFragment);
        transaction.add(R.id.layout_recharge_fragment, rechargeFragment);

        transaction.add(R.id.layout_temp_order_fragment, tempOrderFragment);//挂单页面

        transaction.commit();

    }

    //显示支付页面的View
    public void showPayFragment() {
        layout_product_fragment.setVisibility(View.GONE);
        layout_member_fragment.setVisibility(View.GONE);
        layout_add_member_fragment.setVisibility(View.GONE);
        layout_recharge_fragment.setVisibility(View.GONE);
        layout_temp_order_fragment.setVisibility(View.GONE);

        layout_pay_fragment.setVisibility(View.VISIBLE);
    }

    //显示会员查询页面的View
    public void showMemberFragment() {
        layout_product_fragment.setVisibility(View.GONE);
        layout_pay_fragment.setVisibility(View.GONE);
        layout_add_member_fragment.setVisibility(View.GONE);
        layout_recharge_fragment.setVisibility(View.GONE);
        layout_temp_order_fragment.setVisibility(View.GONE);

        layout_member_fragment.setVisibility(View.VISIBLE);
    }

    //增加会员的页面View
    public void showAddMemberFragment() {
        layout_product_fragment.setVisibility(View.GONE);
        layout_member_fragment.setVisibility(View.GONE);
        layout_pay_fragment.setVisibility(View.GONE);
        layout_recharge_fragment.setVisibility(View.GONE);
        layout_temp_order_fragment.setVisibility(View.GONE);

        layout_add_member_fragment.setVisibility(View.VISIBLE);
    }

    //产品相关的VIew
    public void showProductFragment() {
        layout_member_fragment.setVisibility(View.GONE);
        layout_pay_fragment.setVisibility(View.GONE);
        layout_add_member_fragment.setVisibility(View.GONE);
        layout_recharge_fragment.setVisibility(View.GONE);
        layout_temp_order_fragment.setVisibility(View.GONE);

        layout_product_fragment.setVisibility(View.VISIBLE);
    }

    //会员支付相关的View
    public void showRechargeFragment() {
        layout_member_fragment.setVisibility(View.GONE);
        layout_pay_fragment.setVisibility(View.GONE);
        layout_add_member_fragment.setVisibility(View.GONE);
        layout_product_fragment.setVisibility(View.GONE);
        layout_temp_order_fragment.setVisibility(View.GONE);

        layout_recharge_fragment.setVisibility(View.VISIBLE);
    }

    public void showTempFragment() {

        layout_member_fragment.setVisibility(View.GONE);
        layout_pay_fragment.setVisibility(View.GONE);
        layout_add_member_fragment.setVisibility(View.GONE);
        layout_product_fragment.setVisibility(View.GONE);
        layout_recharge_fragment.setVisibility(View.GONE);

        layout_temp_order_fragment.setVisibility(View.VISIBLE);
        tempOrderFragment.updateTempOrderData();//更新挂单数据
    }

    /**
     * 切换Fragment
     *
     * @param fragment
     */
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();   // 开启一个事务
        transaction.replace(R.id.fragment_home, fragment);
        transaction.commit();
    }

    /**
     * 处理扫码支付硬件信息
     * 扫码枪扫码指令-通过广播接受者，传递在此，在此次做分发处理
     *
     * @param eventBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(BaseEventBean eventBean) {
        XLog.d("handleEvent  =  " + eventBean.getType());
        if (eventBean.getType() == BaseEventBean.SCAN_CODE_MSG) {
            String code = (String) eventBean.getValue();
            showToast(code);

            if (layout_pay_fragment.getVisibility() == View.VISIBLE) {
//                showToast("payFragment");
                homePayFragment.orderUnionPay(code);//扫码支付订单
            } else if (layout_product_fragment.getVisibility() == View.VISIBLE) {
                homeProductFragment.setProductData(code);//扫码识别产品
                //showToast("homeProductFragment");

            } else if (layout_recharge_fragment.getVisibility() == View.VISIBLE) {
                rechargeFragment.depositMoneyByUms(code);//扫码充值
//                showToast("rechargeFragment");
            }
        } else if (eventBean.getType() == BaseEventBean.GETUI_PUSH_MSG) {

            if (layout_pay_fragment.getVisibility() == View.VISIBLE) {
                homePayFragment.printOrderInfo();
            } else if (layout_recharge_fragment.getVisibility() == View.VISIBLE) {
                rechargeFragment.rechargeSussess();
            }

            String msg = (String) eventBean.getValue();
            if (msg.contains("token")) {
//                parseTokenBean(msg);
                JsonParseUtils.parseTokenBean(mContext,msg);
            }
        }
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


    //连接K1打印服务
    private void connectKPrintService() {
        Intent intent = new Intent();
        intent.setPackage("com.sunmi.extprinterservice");
        intent.setAction("com.sunmi.extprinterservice.PrinterService");
        bindService(intent, connService, Context.BIND_AUTO_CREATE);
    }


    /**
     * 打印方法
     *
     * @param goods_data 订单数据
     */
    public void paySuccessToPrinter(String goods_data, ShopOrderDetailBean.DataBean.RecordsBean orderBean) {
        XLog.d(goods_data);
        if (orderBean != null)
            printerPresenter.setRelustOrder(orderBean);

        printerPresenter.print(goods_data, 1);
//        kPrinterPresenter.print(goods_data,1);
    }

    /**
     * 打印方法
     *
     * @param goods_data 订单数据
     */
    public void paySuccessToPrinter(String goods_data, int payMode, ShopOrderDetailBean.DataBean.RecordsBean orderBean, HomeMemberInfoBean.DataBean memberInfoBean) {
        XLog.d(goods_data);

        printerPresenter.setRelustOrder(orderBean);
        printerPresenter.setMemberInfoBean(memberInfoBean);
        printerPresenter.print(goods_data, payMode);
    }

    public void printMemberRechargeInfo(HomeMemberInfoBean.DataBean memberInfoBean, RechargeBean rechargeBean, int payMode) {
        printerPresenter.printMemberInfo(memberInfoBean, rechargeBean, payMode);
    }

    /**
     * 获取商米提供的硬件、打印等相关的服务
     */
    public void bindService() {

        sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("usb", "onServiceConnected: ");
                iWoyouService = IWoyouService.Stub.asInterface(service);
                if (iWoyouService == null) {
//                Toast.makeText(MainActivity.this,"绑定失败",Toast.LENGTH_LONG).show();
                    Log.i("usb", "onServiceConnected 绑定失败 : ");
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i("usb", "onServiceDisconnected: ");
                iWoyouService = null;
            }
        };

        Intent intent = new Intent();
        intent.setPackage("woyou.aidlservice.jiuiv5");
        intent.setAction("woyou.aidlservice.jiuiv5.IWoyouService");

        bindService(intent, sc, Context.BIND_AUTO_CREATE);
    }

    /**
     * 打开钱箱
     */
    public void openDrawer() {
//        if (iWoyouService != null) {
//            try {
//                iWoyouService.openDrawer(TestActivity.this);
////                showToast("打卡钱箱成功");
//
//
//            } catch (android.os.RemoteException e) {
//                e.printStackTrace();
//                showToast("打卡钱箱失败");
//            }
//
//        } else {
//            showToast("iWoyouService null");
//        }

        if (woyouService != null) {

            try {
                woyouService.openDrawer(new com.sunmi.peripheral.printer.ICallback() {

                    @Override
                    public void onRunResult(boolean isSuccess) throws RemoteException {

                    }

                    @Override
                    public void onReturnString(String result) throws RemoteException {

                    }

                    @Override
                    public void onRaiseException(int code, String msg) throws RemoteException {

                    }

                    @Override
                    public void onPrintResult(int code, String msg) throws RemoteException {

                    }

                    @Override
                    public IBinder asBinder() {
                        return null;
                    }
                });

            } catch (android.os.RemoteException e) {
                e.printStackTrace();
                showToast("打卡钱箱失败");
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (sc != null) {
            unbindService(sc);
        }
        if (extPrinterService != null) {
            unbindService(connService);
        }
        if (woyouService != null) {
            try {
                InnerPrinterManager.getInstance().unBindService(this,
                        innerPrinterCallback);
            } catch (InnerPrinterException e) {
                e.printStackTrace();
            }
        }
        printerPresenter = null;
        kPrinterPresenter = null;
//        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    public IBinder asBinder() {
        return null;
    }

    @Override
    public void onRunResult(boolean isSuccess, int code, String msg) throws RemoteException {

    }

    /**
     * 首次进入收银主页，获取门店信息
     */
    private void getShopInfo() {
        try {
            JSONObject json = new JSONObject();
            json.put("shopId", Constant.getShopId());
            json.put("userId", Constant.getUserId());
//            showLoading(true);
            easyPost(json, URL.DEBUG_URL + URL.APPSHOPDETAIL, ShopInfobean.class, resultBean -> {
                onShopInfoResult(resultBean);
            });
        } catch (Exception e) {
            XLog.e(e);
        }
    }

    private void onShopInfoResult(ShopInfobean resultBean) {
//        showLoading(false);
        if (resultBean == null) {
            return;
        }

        if (resultBean.getData() != null) {

            tv_shop_name.setText(resultBean.getData().getShopName());

            PreferencesUtil.save(mContext, Constant.SP_SHOPONAME, resultBean.getData().getShopName());

            //拼接 省市区字符串
            StringBuffer buffer = new StringBuffer();
            buffer.append(resultBean.getData().getProvince());
            buffer.append(resultBean.getData().getCity());
            buffer.append(resultBean.getData().getArea());
            buffer.append(resultBean.getData().getShopAddress());
            String address = buffer.toString();

            PreferencesUtil.save(mContext, Constant.SP_SHOPOADDRESS, address);

            PreferencesUtil.save(mContext, Constant.SP_SHOPOMOBILE, resultBean.getData().getMobile());
        }
    }

    /**
     * 交接班，获取收银员信息
     */
    public void getUserByUserId() {
        try {
            JSONObject json = new JSONObject();
            json.put("userId", Constant.getUserId());
//            showLoading(true);

            easyPost(json, URL.DEBUG_URL + URL.GETUSERBYUSERID, UserInfoBean.class, resultBean -> {
                onUserResult(resultBean);
            });
        } catch (Exception e) {

            XLog.e(e);
        }
    }

    private void onUserResult(UserInfoBean result) {
//        showLoading(false);
        if (result != null) {
            if (result.getData() != null) {

                String name = result.getData().getName();
                tv_cashier_name.setText(name);
                PreferencesUtil.save(mContext, Constant.SP_SHOPOWNER, name);
            }
        }
    }

    /**
     * 初始化沉浸式
     */
    private void initTint() {
        XLog.d("initTint");
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏

        }
    }

    /**
     * 会员的充值记录
     */
    public void selectDepositMoneyRecord() {
        try {
            JSONObject json = new JSONObject();
//            json.put("userId", Constant.getUserId());
            json.put("mobile", "");
            json.put("startTime", "");
            json.put("current", 1);

            easyPost(json, URL.DEBUG_URL + URL.SELECTDEPOSITMONEYRECORD, RecordListBean.class, resultBean -> {
                onRecordResult(resultBean);
            });
        } catch (Exception e) {

            XLog.e(e);
        }
    }

    private void onRecordResult(RecordListBean relult) {
        XLog.d(relult);
        if (relult.getData() == null) {
            showToast("没有数据哟");
            return;
        }

        for (int i = 0; i < relult.getData().getRecords().size(); i++) {

            XLog.d("shop = " + relult.getData().getRecords().get(i).getShopId() + " amount = "
                    + relult.getData().getRecords().get(i).getAmount());
        }
    }

    /**
     * 从个推的消息中，获取字符串，解析成对象
     * 从对象中回去token
     *
     * @param resultBean
     */
    private void parseTokenBean(String resultBean) {
        TokenEventBean tokenEventBean = com.xeld.cashier.utils.JsonParseUtils.parse(resultBean, TokenEventBean.class);
        if (tokenEventBean != null) {
            String accessToken = tokenEventBean.getToken();
            String appToken = "bearer" + accessToken;
            PreferencesUtil.save(this, Constant.SP_TOKEN, appToken);
//            XLog.d("token 重新获取了");
//            showToast("重新获取了");
        }

//        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//        startActivity(intent);

//        finish();//关闭当前活动
    }

}