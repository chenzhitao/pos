package com.xeld.cashier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.elvishew.xlog.XLog;
import com.xeld.cashier.adapter.CardListAdapter;
import com.xeld.cashier.adapter.CategoryListAdapter;
import com.xeld.cashier.adapter.GoodsAdapter;
import com.xeld.cashier.bean.CategoryBean;
import com.xeld.cashier.bean.OrderSussesBean;
import com.xeld.cashier.bean.ProductListBean;
import com.xeld.cashier.callback.ModifyCountInterface;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.fragment.OrderListFragment;
import com.xeld.cashier.mvp.http.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAct  {
    //监听 商米收银设备 扫码枪的广播
    private static final String ACTION_DATA_CODE_RECEIVED =
            "com.sunmi.scanner.ACTION_DATA_CODE_RECEIVED";
    //监听 商米收银设备 扫码枪的广播 key
    private static final String DATA = "data";
    //监听 商米收银设备 扫码枪的广播 key
    private static final String SOURCE = "source_byte";
    //首次获取商品列表的GridView的适配器
    private GoodsAdapter goodsAdapter;
    //商品分类的listView适配器
    private CategoryListAdapter cataListAdapter;
    //购物车列表里面的listView适配器
    private CardListAdapter cardListAdapter;
    //首次加载获取的商品列表
    private List<ProductListBean.DataBean> cardList;
    //首次加载获取的商品分类
    private List<CategoryBean.DataBean> categoryList;


    //首次获取商品列表的GridView
    @BindView(R.id.gv_shop_list)
    GridView goodGridView;
    //购物车列表
    @BindView(R.id.lv_cart_list)
    ListView cartListView;
    //商品类目列表
    @BindView(R.id.lv_product_cata_title)
    ListView cataListView;
    //商品搜索的输入框
    @BindView(R.id.input_shop_name)
    EditText evTextProdectName;
    //结算 按钮的父布局
    @BindView(R.id.layout_order_confrim)
    LinearLayout layoutConfirm;
    @BindView(R.id.layout_main_member)
    LinearLayout layoutMember;
    @BindView(R.id.layout_main_add_member)
    LinearLayout layoutTitleAddMember;
    @BindView(R.id.ly_main_right_parent)
    LinearLayout layoutMainRightParent;
    @BindView(R.id.ly_main_right_product)
    LinearLayout layoutMainRightProduct;
    LinearLayout layoutMemberSelect;
    LinearLayout layoutParentAddMember;

    @BindView(R.id.layout_title_order)
    LinearLayout layoutTitleOrder;
    @BindView(R.id.layout_main_top_paient)
    RelativeLayout layoutMainTopPaient;

    private OrderListFragment orderListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        ButterKnife.bind(this);//一定要在layout初始化之后再绑定。

        cardList = new ArrayList<>();
        categoryList = new ArrayList<>();
        getProducts();
        initCataData();
//        getProductByBarCode("400");
        layoutConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                confirmOrder();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //注册
//        registerReceiver(receiver, filter());
    }

    @Override
    protected void onPause() {
        super.onPause();
        //取消
//        unregisterReceiver(receiver);

    }

    /**
     * 首次进入收银主页，获取推荐商品列表
     */
    private void getProducts() {
        try {
            JSONObject json = new JSONObject();
            json.put("shopId", Constant.getShopId());
            showLoading();
            easyPost(json, URL.DEBUG_URL + URL.GETPRODUCTS, ProductListBean.class, stringResult -> {
                onProductsResult(stringResult);
            });
        } catch (Exception e) {
            setLog("easyPostLogin", e.getMessage());
            XLog.e(e);
        }
    }

    /**
     * 获取商品列表的接口回调处理方法
     *
     * @param result
     */
    private void onProductsResult(ProductListBean result) {
        XLog.d("onProductsResult    = ");
        showLoading(false);
        if (result != null) {
            List<ProductListBean.DataBean> data = result.getData().getRecords();
            if (data != null) {
                ProductListBean.DataBean bean = data.get(0);
                String shopName = bean.getProdName();
                XLog.d("shopName   = " + shopName);

                goodsAdapter = new GoodsAdapter(getApplicationContext(), data, 1);
                goodGridView.setAdapter(goodsAdapter);
//                cataListView.setAdapter(goodsAdapter);

                goodGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        ProductListBean.DataBean bean1 = data.get(position);
                        cardList.add(bean1);
                        cardListAdapter = new CardListAdapter(getApplicationContext(), cardList, 1);
                        cartListView.setAdapter(cardListAdapter);

                    }
                });

            }
        }

    }

    static String getProductByShopIdUrl = "http://192.168.10.193:8088/app/homeData/getProductByShopId?categoryId=106";

    /**
     * 首次进入收银主页，获取推荐商品列表
     */
    private void getProductByShopId(int categoryId) {
        try {
            JSONObject json = new JSONObject();
            json.put("shopId", Constant.getShopId());
            json.put("categoryId", categoryId);
            showLoading();
            easyPost(json, getProductByShopIdUrl, ProductListBean.class, stringResult -> {
                onProductsResult(stringResult);
            });
        } catch (Exception e) {
            setLog("easyPostLogin", e.getMessage());
            XLog.e(e);
        }
    }

    /**
     * 获取商品类目列表
     */
    private void initCataData() {
        try {
            JSONObject json = new JSONObject();
            json.put("shopId", "1");
//            showLoading();
            easyPost(json, URL.DEBUG_URL + URL.GETCATEGORY, CategoryBean.class, result -> {
                onCateGoryResult(result);
            });
        } catch (Exception e) {
            setLog("easyPostLogin", e.getMessage());
            XLog.e(e);
        }

    }

    static String getProductByBarCode = "http://192.168.10.193:8088/app/homeData/getProductByBarCode";
    static String getProductByBarCodeUrl = "http://192.168.10.193:8088/app/homeData/getProductByBarCode?barCode=400";

    private void getProductByBarCode(String barCode) {
        try {
            showLoading(false);
            XLog.e("barCode =  " + barCode);
            JSONObject json = new JSONObject();
            json.put("barCode", "400");
            showLoading();
            easyPost(json, getProductByBarCodeUrl, ProductListBean.class, result -> {
                onProductResultBybarCode(result);
            });
        } catch (Exception e) {
            setLog("easyPostLogin", e.getMessage());
            XLog.e(e);
        }
    }

    private void onProductResultBybarCode(ProductListBean result) {
        showLoading(false);
        XLog.d("onProductsResult    = ");
        if (result != null) {
            List<ProductListBean.DataBean> data = result.getData().getRecords();
            if (data != null) {
                ProductListBean.DataBean bean = data.get(0);
                String shopName = bean.getProdName();
                XLog.d("shopName   = " + shopName);
            }
        }
    }

    /**
     * 根据店铺ID 获取 商品分类列表 的回调处方法
     * * @param result
     */
    private void onCateGoryResult(CategoryBean result) {
//        showLoading(false);
        if (result != null) {
            categoryList = result.getData();
            cataListAdapter = new CategoryListAdapter(getApplicationContext(), categoryList, 1);
            cataListView.setAdapter(cataListAdapter);
            cataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //获取用户点击item对应的category ID；
                    getProductByShopId(categoryList.get(position).getCategoryId());
                }
            });
        }
    }

    private void confirmOrder() {
        try {

            JSONObject json = new JSONObject();
            json.put("phoneNumber", "18611128418");
            JSONArray jsonArray = new JSONArray();
            JSONObject orderBean = new JSONObject();
            orderBean.put("count", "2");
            orderBean.put("prodId", "1456");
            jsonArray.put(orderBean);
            json.put("productList", jsonArray);
            showLoading();
            easyPost(json, URL.DEBUG_URL + URL.CONFIRMORDERURL, OrderSussesBean.class, orderResult -> {
                onconfirmOrderResult(orderResult);
            });
        } catch (Exception e) {
            setLog("easyPostLogin", e.getMessage());
            XLog.e(e);
        }

    }

    /**
     * 订单提交到服务器，生成订单号
     *
     * @param result
     */
    private void onconfirmOrderResult(OrderSussesBean result) {
        showLoading(false);
        if (result.getData() != null) {
            String orderNum = result.getData().getOrderNumber();
            orderSussesBean = result;
            double actualTotal = result.getData().getActualTotal();
            XLog.d("orderNum " + orderNum);
            XLog.d("actualTotal " + actualTotal);
            showToast("解析成功，可以支付");
        }
    }

    private OrderSussesBean orderSussesBean;
    static String unionPayUrl = "http://192.168.10.193:8088/app/pay/payment?";

    private void orderUnionPay(String payCode) {
        try {

            StringBuilder builder = new StringBuilder(unionPayUrl);
            builder.append("orderNumber=" + orderSussesBean.getData().getOrderNumber());
            builder.append("&payMoney=" + 1.11);
            builder.append("&payCode=" + payCode);

            String unionPayUrl = builder.toString();
            XLog.d("unionPayUrl = " + unionPayUrl);

            JSONObject json = new JSONObject();
//            json.put("orderNumber", "1354250603961913344");
//            json.put("orderNumber", orderSussesBean.getData().getOrderNumber());
//            json.put("payMoney", 1.11);
//            json.put("payCode", payCode);

            showLoading();
            easyPost(json, unionPayUrl, String.class, stringResult -> {
                onUnionPayResult(stringResult);
            });
        } catch (Exception e) {
            setLog("easyPostLogin", e.getMessage());
            XLog.e(e);
        }
    }

    private void onUnionPayResult(String string) {
        showLoading(false);
    }


//    private static IntentFilter filter() {
//        final IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(ACTION_DATA_CODE_RECEIVED);
//        return intentFilter;
//    }

//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(getApplicationContext(), "接收广播成功", Toast.LENGTH_SHORT).show();
//            String code = intent.getStringExtra(DATA);
//            byte[] arr = intent.getByteArrayExtra(SOURCE);
//            if (code != null && !code.isEmpty()) {
//
//                evTextProdectName.setText(code);
////                orderUnionPay(code);
//                showToast(code);
//            }
//        }
//    };
}