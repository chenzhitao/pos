package com.xeld.cashier.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.pgyer.pgyersdk.PgyerSDKManager;

import com.elvishew.xlog.XLog;
import com.xeld.cashier.BaseAct;
import com.xeld.cashier.R;
import com.xeld.cashier.adapter.CardListAdapter;
import com.xeld.cashier.adapter.CategoryListAdapter;
import com.xeld.cashier.adapter.holder.BaseListVH;
import com.xeld.cashier.adapter.holder.TempOrderListHolder;
import com.xeld.cashier.bean.ShopOrderDetailBean.DataBean.RecordsBean;
import com.xeld.cashier.adapter.GoodsAdapter;
import com.xeld.cashier.base.fragment.BaseEventBean;
import com.xeld.cashier.base.fragment.HomeBaseFragment;
import com.xeld.cashier.bean.ShopOrderDetailBean;
import com.xeld.cashier.bean.CategoryBean;
import com.xeld.cashier.bean.OrderSussesBean;
import com.xeld.cashier.bean.ProductListBean;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.mvp.http.URL;
import com.xeld.cashier.ui.login.TestActivity;
import com.xeld.cashier.utils.CommonViewUtils;

import com.xeld.cashier.bean.msg.PayEventBean;
import com.xeld.cashier.base.IBaseFragmentList;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TempOrderFragment extends IBaseFragmentList implements TempOrderListHolder.ListViewItemClickInterface {


    private BaseAct mBact;
    private TestActivity testActivity;
    //会员充值关闭
    @BindView(R.id.tv_close_temp_order_fragment)
    TextView tv_close_temp_order_fragment;


    private TempOrderListHolder holder;
    private int current = 1;

    private RecordsBean relustOrder;  //listView的点击以后的订单对象

    @Override
    public int getLayout() {
        return R.layout.act_home_temp_order_layout;
    }

    @Override
    public void initView() {
        super.initView();//初始化 下拉刷新的ListView

        mBact = getAct();
        testActivity = (TestActivity) getAct();

        CommonViewUtils.setOnClick(tv_close_temp_order_fragment, view -> {
            testActivity.showProductFragment();

        });

    }

    @Override
    public void Refresh() {
//        post();
//        getToPaidOrder(0, 1);
    }


    public void updateTempOrderData() {
        getToPaidOrder(0, 1);
    }

    @Override
    public void onLoadMore() {
//        post();
        if (current > 1)
            current++;
        getToPaidOrder(0, current);
    }

    /**
     * 重写父类的方法
     *
     * @return
     */
    @Override
    protected BaseListVH getAdapterConvertVH() {
        holder = new TempOrderListHolder();
        holder.setContext(getContext());
        holder.setItemClickInterface(this);
        return holder;
    }


    private void getToPaidOrder(int orderStatus, int current) {
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
            easyPost(json, URL.DEBUG_URL + URL.GETTOPAIDORDER, ShopOrderDetailBean.class, stringResult -> {
                onOrderResult(stringResult);
            });
        } catch (Exception e) {
            XLog.e(e);
        }
    }

    private void onOrderResult(ShopOrderDetailBean relust) {
        mBact.showLoading(false);

        try {
            if (relust == null) {
                setAdapterEmptyView();
                return;
            }
        } catch (Exception e) {
            PgyerSDKManager.reportException(e);
        }

        if (relust.getData() == null) {
            showToast("没有挂单数据 ");
            return;
        } else {

            if (relust.getData().getRecords() != null && relust.getData().getRecords().size() > 0) {

                if (relust.getData().getRecords().size() > 0) {

                    this.relustOrder = relust.getData().getRecords().get(0);

                    current = relust.getData().getCurrent();

                    setData(relust.getData().getRecords());

                }

            }
        }

    }

    @Override
    public void onHandleItemClick(RecordsBean item) {
        if (item == null) {
            return;
        }

        this.relustOrder = item;
        testActivity.showProductFragment();

        PayEventBean payEventBean = new PayEventBean(BaseEventBean.TEMP_ORDER_DATA);
        payEventBean.setValue(relustOrder);
        payEventBean.setMobile(item.getUserMobile());
        EventBus.getDefault().post(payEventBean);
    }
}
