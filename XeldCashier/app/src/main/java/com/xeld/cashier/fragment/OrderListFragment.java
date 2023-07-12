package com.xeld.cashier.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.elvishew.xlog.XLog;
import com.xeld.cashier.BaseAct;
import com.xeld.cashier.R;
import com.xeld.cashier.adapter.CardListAdapter;
import com.xeld.cashier.adapter.OrderListdetailAdapter;
import com.xeld.cashier.adapter.holder.BaseListVH;
import com.xeld.cashier.adapter.holder.OrderListHolder;
import com.xeld.cashier.base.IBaseFragmentList;
import com.xeld.cashier.bean.HomeMemberInfoBean;
import com.xeld.cashier.bean.ShopOrderDetailBean;
import com.xeld.cashier.bean.ShopOrderDetailBean.DataBean.RecordsBean;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.easyhttp.CommonResultBean;
import com.xeld.cashier.mvp.http.URL;
import com.xeld.cashier.ui.login.OrderListActivity;
import com.xeld.cashier.utils.CommonUtils;
import com.xeld.cashier.utils.CommonViewUtils;
import com.xeld.cashier.utils.OrderPrintUtils;
import com.xeld.cashier.utils.PreferencesUtil;
import com.xw.repo.XEditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderListFragment extends IBaseFragmentList implements OrderListHolder.ListViewItemClickInterface {

    public final static int STATE_ORDER_UNPAID = 1;//代付款
    public final static int STATE_ORDER_UNDELIVERED = 2;//待发货
    public final static int STATE_ORDER_NOT_RECEIVED = 3;//待收货
    public final static int STATE_ORDER_CONFIRM_RECEIPT = 5;//确认收货
    public final static int STATE_ORDER_CONFIRM_RECEIPT_THREEDAY = 6;//确认收货3天后订单完成
    public final static int STATE_ORDER_CLOSED = 7;//订单关闭

    public final static String STATE_ORDER_UNPAID_STR = "待付款";
    public final static String STATE_ORDER_UNDELIVERED_STR = "待发货";
    public final static String STATE_ORDER_NOT_RECEIVED_STR = "待收货";
    public final static String STATE_ORDER_CONFIRM_RECEIPT_STR = "确认收货";
    public final static String STATE_ORDER_CLOSED_STR = "订单关闭";

    private OrderListHolder holder;

    private TextView tvOrderStateAll;
    private TextView tvOrderStateFs;
    private TextView tvOrderStateZiti;
    private TextView tvOrderStateExpress;
    private TextView tvOrderStateCashier;

    @BindView(R.id.tv_order_list_state_right)
    TextView tv_order_list_state_right;

    @BindView(R.id.tv_order_list_number)
    TextView tv_order_list_number;
    @BindView(R.id.tv_order_list_pay_type)
    TextView tv_order_list_pay_type;
    @BindView(R.id.tv_order_list_cashier)
    TextView tv_order_list_cashier;
    @BindView(R.id.tv_order_list_time)
    TextView tv_order_list_time;

    @BindView(R.id.tv_order_list_member_nick)
    TextView tv_order_list_member_nick;

    @BindView(R.id.tv_order_list_phone)
    TextView tv_order_list_phone;

    @BindView(R.id.tv_order_list_product_total)
    TextView tv_order_list_product_total;
    @BindView(R.id.tv_order_list_actual_payment)
    TextView tv_order_list_actual_payment;
    @BindView(R.id.tv_order_list_discount)
    TextView tv_order_list_discount;
    //订单打印
    @BindView(R.id.tv_order_list_print)
    TextView tv_order_list_print;
    //退款
    @BindView(R.id.tv_order_pay_exit)
    TextView tv_order_pay_exit;
    //订单搜索
    @BindView(R.id.ev_order_search)
    XEditText orderSearchView;

    //商品详情的listView
    @BindView(R.id.order_list_product)
    ListView order_list_product;
    //订单详情中 展示 购买商品的具体数量和单价
    private OrderListdetailAdapter orderListdetailAdapter;


    private BaseAct mBact;
    private OrderListActivity orderListActivity;
    private int current = 1;

    private RecordsBean relustOrder;  //listView的点击以后的订单对象

    private OrderPrintUtils printUtils;

    //存储会员信息的对象
    private HomeMemberInfoBean.DataBean memberInfoBean;

    @Override
    public int getLayout() {
        return R.layout.main_order_shop_layout;
    }

    @Override
    public void initView() {
        super.initView();
        printUtils = new OrderPrintUtils(mBact);


        tvOrderStateAll = getViewById(R.id.tv_order_state_all);
        CommonViewUtils.setOnClick(tvOrderStateAll, view -> {
            getAllOrderByshopId(1, 1);
        });

        tvOrderStateFs = getViewById(R.id.tv_order_state_fs);
        CommonViewUtils.setOnClick(tvOrderStateFs, view -> {
            getAllOrderByshopId(2, 1);
        });

        tvOrderStateZiti = getViewById(R.id.tv_order_state_ziti);
        CommonViewUtils.setOnClick(tvOrderStateZiti, view -> {
            getAllOrderByshopId(3, 1);

        });

        // 根据订单状态，查询订单信息  待快递
        tvOrderStateExpress = getViewById(R.id.tv_order_state_express);
        CommonViewUtils.setOnClick(tvOrderStateExpress, view -> {
            getAllOrderByshopId(4, 1);

        });

        // 根据订单状态，查询订单信息  门店收银
        tvOrderStateCashier = getViewById(R.id.tv_order_state_cashier);
        CommonViewUtils.setOnClick(tvOrderStateCashier, view -> {
            getAllOrderByshopId(5, 1);

        });

        //打印小票
        CommonViewUtils.setOnClick(tv_order_list_print, view -> {

            //散客订单，不需要打印会员信息
            if ("".equals(relustOrder.getUserPhone()) || TextUtils.isEmpty(relustOrder.getUserPhone())) {
                orderListActivity.paySuccessToPrinter(printUtils.formatGoods(relustOrder), relustOrder.getPayType(), relustOrder, null);
            } else {
                //根据订单中的手机号码，查询会员信息
                getMemberData(relustOrder.getUserPhone());
            }
        });


        mBact = getAct();
        orderListActivity = (OrderListActivity) getAct();
//        post();

        orderSearchView.setOnXTextChangeListener(new XEditText.OnXTextChangeListener() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String phone = s.toString();
                getOrderByinput(phone);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void Refresh() {
//        post();
        getAllOrderByshopId(0, 1);
    }


    @Override
    public void onLoadMore() {
//        post();
        if (current > 1)
            current++;

        getAllOrderByshopId(0, current);
    }

    /**
     * 重写父类的方法
     *
     * @return
     */
    @Override
    protected BaseListVH getAdapterConvertVH() {
        holder = new OrderListHolder();
        holder.setContext(getContext());
        holder.setItemClickInterface(this);
        holder.setOrderListFragment(this);
        return holder;
    }

    public void post() {
        int count = CommonUtils.getRandom(5) + 2;
        List<String> list = new ArrayList<>();
        for (int a = 0; a < count; a++) {
            list.add("");
        }
//        setHasNextPage(true);
//        setData(list);
    }

    private void getAllOrderByshopId(int orderStatus, int current) {
        try {
            JSONObject json = new JSONObject();
            json.put("shopId", Constant.getShopId());
            json.put("size", 10);
            json.put("current", current);
            if (orderStatus == 0) {
                json.put("status", "");
            } else {
                json.put("status", orderStatus);
            }

            mBact.showLoading();
            easyPost(json, URL.DEBUG_URL + URL.GETALLORDERBYSHOPID, ShopOrderDetailBean.class, stringResult -> {
                onOrderResult(stringResult);
            });
        } catch (Exception e) {
            XLog.e(e);
        }
    }

    /**
     * 搜索订单
     *
     * @param phone
     */
    private void getOrderByinput(String phone) {
        try {
            JSONObject json = new JSONObject();
            json.put("shopId", Constant.getShopId());
            json.put("size", 10);
            json.put("current", 1);
            json.put("status", "");
            json.put("phone", phone);

            mBact.showLoading();
            easyPost(json, URL.DEBUG_URL + URL.GETALLORDERBYSHOPID, ShopOrderDetailBean.class, stringResult -> {
                onOrderSearchResult(stringResult);
            });
        } catch (Exception e) {
            XLog.e(e);
        }
    }

    private List<RecordsBean> mdataList = new ArrayList<>();

    private void onOrderSearchResult(ShopOrderDetailBean relust) {
        mBact.showLoading(false);

        if (relust.getData().getRecords() == null || relust.getData().getRecords().size() == 0) {
            showToast("没有订单数据 ");
            return;
        }
        mdataList.addAll(relust.getData().getRecords());
        setSearchData(relust.getData().getRecords());
        initItemView(relust.getData().getRecords().get(0));
    }

    private void onOrderResult(ShopOrderDetailBean relust) {
        mBact.showLoading(false);


        if (relust == null || relust.getData().getRecords() == null || relust.getData().getRecords().size() == 0) {
            showToast("没有订单数据 ");
            return;
        }
        mdataList.addAll(relust.getData().getRecords());

        this.relustOrder = relust.getData().getRecords().get(0);

        current = relust.getData().getCurrent();

        setData(relust.getData().getRecords());

        initItemView(relust.getData().getRecords().get(0));
    }

    private void initItemView(RecordsBean bean) {
        if (bean == null) {
            return;
        }
        tv_order_list_number.setText(bean.getOrderNumber());
        tv_order_list_cashier.setText(bean.getCashierName());
        tv_order_list_time.setText(bean.getCreateTime());
        tv_order_list_phone.setText(bean.getUserPhone() + "");
        tv_order_list_actual_payment.setText(bean.getActualTotal() + "");
        tv_order_list_product_total.setText(bean.getActualTotal() + "");

        if ("".equals(bean.getNikeName())) {
            tv_order_list_member_nick.setText("散客");
        } else {
            tv_order_list_member_nick.setText(bean.getNikeName());
        }

        int status = bean.getStatus();
        switch (status) {
            case OrderListFragment.STATE_ORDER_UNPAID:
                tv_order_list_state_right.setText(OrderListFragment.STATE_ORDER_UNPAID_STR);
                break;
            case OrderListFragment.STATE_ORDER_UNDELIVERED:
                tv_order_list_state_right.setText(OrderListFragment.STATE_ORDER_UNDELIVERED_STR);
                break;
            case OrderListFragment.STATE_ORDER_NOT_RECEIVED:
                tv_order_list_state_right.setText(OrderListFragment.STATE_ORDER_NOT_RECEIVED_STR);
                break;
            case OrderListFragment.STATE_ORDER_CONFIRM_RECEIPT:
                tv_order_list_state_right.setText(OrderListFragment.STATE_ORDER_CONFIRM_RECEIPT_STR);
                break;
            case OrderListFragment.STATE_ORDER_CONFIRM_RECEIPT_THREEDAY:
                tv_order_list_state_right.setText(OrderListFragment.STATE_ORDER_CONFIRM_RECEIPT_STR);
                break;
            case OrderListFragment.STATE_ORDER_CLOSED:
                tv_order_list_state_right.setText(OrderListFragment.STATE_ORDER_CLOSED_STR);
                break;
        }

        int type = bean.getPayType();
        switch (type) {
            case HomePayFragment.PAY_TYPE_CARH:
                tv_order_list_pay_type.setText("现金支付");
                break;
            case HomePayFragment.PAY_TYPE_CODE:
                tv_order_list_pay_type.setText("扫码支付");
                break;
            case HomePayFragment.PAY_TYPE_MEMBER:
                tv_order_list_pay_type.setText("会员支付");
            case HomePayFragment.PAY_TYPE_MEMBER_OLD:
                tv_order_list_pay_type.setText("会员支付");
            case HomePayFragment.PAY_TYPE_MEMBER_HHZF:
                tv_order_list_pay_type.setText("混合支付");
                break;
        }

        if (bean.getStatus() == STATE_ORDER_CONFIRM_RECEIPT) {
            tv_order_pay_exit.setVisibility(View.VISIBLE);
        } else {
            tv_order_pay_exit.setVisibility(View.INVISIBLE);
        }

        orderListdetailAdapter = new OrderListdetailAdapter(mBact, relustOrder, 1);
        order_list_product.setAdapter(orderListdetailAdapter);

        CommonViewUtils.setOnClick(tv_order_pay_exit, view -> {


            AlertDialog alert = new AlertDialog.Builder(mBact).create();
            alert.setTitle("操作提示");
            StringBuffer buffer = new StringBuffer();
            String tips = "您确定要将此订单退款吗？\n";
            buffer.append(tips);
            buffer.append("订单编号：" + relustOrder.getOrderNumber() + "\n");
            buffer.append("订单金额：" + relustOrder.getActualTotal());

            alert.setMessage(buffer.toString());
            alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            refundCarhMoney();
                        }
                    });
            alert.show();

        });

    }


    @Override
    public void onHandleItemClick(RecordsBean item) {
        if (item == null) {
            return;
        }
        this.relustOrder = item;
        initItemView(item);


//        commonAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//
//                showToast(position + "");
//            }
//        });

        commonAdapter.notifyDataSetChanged();
    }

    /**
     * 退款
     * 现金退款，直接退款
     * 会员退款，返回会员账户
     * 扫码退款，直接原路返回
     */
    private void refundCarhMoney() {

        try {
            JSONObject json = new JSONObject();
//            if (memberInfoBean != null) {
//                json.put("phoneNumber", memberInfoBean.getData().getUserMobile());
//            } else {
//                json.put("phoneNumber", -1);
//            }
            json.put("orderNumber", relustOrder.getOrderNumber());//订单编号

            //退款的产品
            List<ShopOrderDetailBean.DataBean.RecordsBean.OrderItemsBean> orderItems = relustOrder.getOrderItems();
            JSONArray jsonArray = new JSONArray();
            if (orderItems != null) {

                for (ShopOrderDetailBean.DataBean.RecordsBean.OrderItemsBean bean : orderItems) {
//                    ConfirmOrderBean.OrderEntity orderEntity = comfirmOrderList.get(i);

                    JSONObject orderBean = new JSONObject();
                    orderBean.put("count", bean.getProdCount());//产品数量
                    orderBean.put("prodId", bean.getProdId());//产品ID
                    jsonArray.put(orderBean);
                }
            }

            json.put("productList", jsonArray);
            json.put("payType", relustOrder.getPayType());
            json.put("cid", PreferencesUtil.getString(mBact, Constant.SP_CID));
            mBact.showLoading();

            easyPost(json, URL.DEBUG_URL + URL.BACKOUT, CommonResultBean.class, result -> {
                onRefundResult(result);
            });
        } catch (Exception e) {
            mBact.setLog("easyPostLogin", e.getMessage());
            XLog.e(e);
        }
    }

    /**
     * 现金退款的回调处理函数
     *
     * @param result
     */
    private void onRefundResult(CommonResultBean result) {
        if (result.getResultCode() == 200) {
//            layout_pay_susses.setVisibility(View.GONE);
//            layout_pay_state.setVisibility(View.VISIBLE);
            showToast("退款成功");
//            tv_susses_pay_refund.setEnabled(false);
//            tv_pay_state.setText("退款成功");
            getAllOrderByshopId(0, 1);//重新更新列表

            //散客订单，不需要打印会员信息
            if ("".equals(relustOrder.getUserPhone()) || TextUtils.isEmpty(relustOrder.getUserPhone())) {
                orderListActivity.paySuccessToPrinter(printUtils.formatGoods(relustOrder), relustOrder.getPayType(), relustOrder, null);
            } else {
                //根据订单中的手机号码，查询会员信息
                getMemberData(relustOrder.getUserPhone());
            }


        } else {
            showToast("退款失败");
//            tv_pay_state.setText("退款失败");
        }
    }


    /**
     * 会员充值成功以后，更新会员的余额信息
     */
    private void getMemberData(String mobile) {
        try {
            JSONObject json = new JSONObject();

            String url = URL.DEBUG_URL + URL.GETMEMBER;
            StringBuffer buffer = new StringBuffer(url);
            buffer.append("?tel=" + mobile);

            String stringUrl = buffer.toString();

            easyPost(json, stringUrl, HomeMemberInfoBean.class, result -> {
                onMemberResult(result);
            });
        } catch (Exception e) {
            mBact.setLog("easyPostLogin", e.getMessage());
            XLog.e(e);
        }

    }

    private void onMemberResult(HomeMemberInfoBean relust) {
        if (relust.getData() == null) {
            showToast("会员信息为空");
            return;
        } else {
//            BaseEventBean eventBean = new BaseEventBean(BaseEventBean.TYPE_GOTO_MEMBER_FRAGMENT);
//            eventBean.setValue(relust.getData().get(0));
//            EventBus.getDefault().post(eventBean);
            memberInfoBean = relust.getData().get(0);
            if (memberInfoBean != null) {
                orderListActivity.paySuccessToPrinter(printUtils.formatGoods(relustOrder), relustOrder.getPayType(), relustOrder, memberInfoBean);
            }
        }

    }
}
