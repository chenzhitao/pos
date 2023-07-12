package com.xeld.cashier.ui.login;

import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.graphics.Bitmap;
import android.widget.TextView;

import android.text.InputFilter;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.xeld.cashier.utils.JsonParseUtils;
import com.elvishew.xlog.XLog;
import com.xeld.cashier.BaseAct;
import com.xeld.cashier.R;
import com.xeld.cashier.base.fragment.BaseEventBean;
import com.xeld.cashier.bean.CategoryBean;
import com.xeld.cashier.bean.AddProductBean;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.utils.PreferencesUtil;
import com.xeld.cashier.mvp.http.URL;
import com.xeld.cashier.utils.CommonViewUtils;
import com.xw.repo.XEditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import com.bumptech.glide.Glide;

import com.king.zxing.util.CodeUtils;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.widget.ArrayAdapter;

public class AddProductActivity extends BaseAct {
    //商品名
    @BindView(R.id.et_product_name)
    XEditText et_product_name;
    //条码
    @BindView(R.id.et_product_bar_code)
    XEditText et_product_bar_code;
    //分类
    @BindView(R.id.et_product_bar_cata)
    XEditText et_product_bar_cata;
    //单位sku
    @BindView(R.id.et_product_bar_unit)
    XEditText et_product_bar_unit;
    //零售价
    @BindView(R.id.et_product_price)
    XEditText et_product_price;
    //库存
    @BindView(R.id.et_product_num)
    XEditText et_product_num;
    //VIP价格
    @BindView(R.id.et_product_vip_price)
    XEditText et_product_vip_price;
    //保质期
    @BindView(R.id.et_product_bzq)
    XEditText et_product_bzq;
    //品牌选择 小二啷当
    @BindView(R.id.ck_product_xeld)
    AppCompatCheckBox ck_product_xeld;
    //品牌选择 自营
    @BindView(R.id.ck_product_zy)
    AppCompatCheckBox ck_product_zy;
    //商品价格 vip
    @BindView(R.id.ck_product_vip_yes)
    AppCompatCheckBox ck_product_vip_yes;
    //商品价格 非vip
    @BindView(R.id.ck_product_vip_no)
    AppCompatCheckBox ck_product_vip_no;
    //保质期 按天
    @BindView(R.id.ck_product_bzq_day)
    AppCompatCheckBox ck_product_bzq_day;
    //保质期 按月
    @BindView(R.id.ck_product_bzq_yue)
    AppCompatCheckBox ck_product_bzq_yue;
    //确定
    @BindView(R.id.tx_add_product_ok)
    TextView tx_add_product_ok;
    @BindView(R.id.tv_get_bar_code)
    TextView tv_get_bar_code;
    //取消
    @BindView(R.id.tx_add_product_no)
    TextView tx_add_product_no;

    @BindView(R.id.tv_act_back)
    TextView tv_act_back;
    //VIP会员价格
    @BindView(R.id.layout_product_member_price)
    LinearLayout layout_product_member_price;

    //选择-商品分类
    @BindView(R.id.spinner1)
    Spinner spinner1;
    //选择sku
    @BindView(R.id.spinner_unit)
    Spinner spinner_unit;

    @BindView(R.id.iv_product_pic_code)
    ImageView iv_product_pic_code;

    @BindView(R.id.iv_add_product_pic)
    ImageView iv_add_product_pic;

    //获取的 商品分类列表
    private List<CategoryBean.DataBean> categoryList;
    private List<String> stringList;
    private List<String> unitList;
    private int categoryId;
    private String cateID;

    private String unitName;
    private int barCode = 0;

    private String logo;
    String product_bzq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        mContext = this;
        ButterKnife.bind(this);//一定要在layout初始化之后再绑定。
        EventBus.getDefault().register(this);

        //生成二维码
//        String data = "https://images.51xeld.com/2020/09/59062f7d4e4748ff851bc7f8415db274.jpg";
//        String data = "https://app.xiaoerlangdang.com?cid=7f82ac174bbfdb4d9c4e0c967ac55b1c";
        String data = produceQRCode();
        XLog.e(data);
        Bitmap qrCode = CodeUtils.createQRCode(data, 160, null);
        iv_product_pic_code.setImageBitmap(qrCode);


        CommonViewUtils.getInputEnterResult(this, et_product_name);
        CommonViewUtils.getInputEnterResult(this, et_product_bar_code);
        CommonViewUtils.getInputEnterResult(this, et_product_price);
        CommonViewUtils.getInputEnterResult(this, et_product_num);
        CommonViewUtils.getInputEnterResult(this, et_product_vip_price);
        CommonViewUtils.getInputEnterResult(this, et_product_bzq);


//        et_product_name.setText("小二啷当面包");
//        et_product_price.setText(55 + "");
//        et_product_vip_price.setText(88 + "");
//        et_product_bzq.setText(120 + "");
//        et_product_num.setText(1000 + "");

        CommonViewUtils.setOnClick(tx_add_product_ok, view -> {
            checkInput();
        });

        CommonViewUtils.setOnClick(tv_get_bar_code, view -> {

            et_product_bar_code.setText(testRandom3());
        });

        CommonViewUtils.setOnClick(tx_add_product_no, view -> {
            finish();
        });
        CommonViewUtils.setOnClick(tv_act_back, view -> {
            finish();
        });

        initData();

        initCataData();

//        produceQRCode();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stringList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                cateID = stringList.get(position);

                for (int i = 0; i < categoryList.size(); i++) {

                    if (cateID.equals(categoryList.get(i).getCategoryName())) {
                        categoryId = categoryList.get(i).getCategoryId();
                        XLog.e(categoryId);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapterUnit = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unitList);
        adapterUnit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_unit.setAdapter(adapterUnit);

        spinner_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unitName = unitList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        ck_product_vip_yes.setChecked(true);
        ck_product_xeld.setChecked(true);
        ck_product_bzq_day.setChecked(true);

        ck_product_vip_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ck_product_vip_no.setChecked(false);
                layout_product_member_price.setVisibility(View.VISIBLE);
            }
        });


        ck_product_vip_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ck_product_vip_yes.setChecked(false);
                layout_product_member_price.setVisibility(View.GONE);
            }
        });


        ck_product_xeld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ck_product_zy.setChecked(false);
            }
        });

        ck_product_zy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ck_product_xeld.setChecked(false);
            }
        });

        ck_product_bzq_yue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ck_product_bzq_day.setChecked(false);
            }
        });

        ck_product_bzq_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ck_product_bzq_yue.setChecked(false);
            }
        });

    }

    private void checkInput() {
        String product_name = et_product_name.getText().toString().trim();//商品名称
        String product_bar_code = et_product_bar_code.getText().toString().trim();//条形码

        String product_price = et_product_price.getText().toString().trim();//价格

        String product_num = et_product_num.getText().toString().trim();//数量
        String product_vip_price = et_product_vip_price.getText().toString().trim();//VIP价格
        product_bzq = et_product_bzq.getText().toString().trim();//保质期

        if (TextUtils.isEmpty(product_name)) {
            showToast("请输入商品名称");
            XLog.d("product_name");
            return;
        }
        if (TextUtils.isEmpty(product_bar_code)) {
            showToast("请输入商品编码");
            XLog.d("product_bar_code");
            return;
        }

        if (cateID.equals("请选择商品分类")) {
            showToast("请输入商品类别");
            XLog.d("cateID");
            return;
        }
        if (unitName.equals("请选择计量单位")) {
            showToast("请输入sku单位");
            XLog.d("unitName");
            return;
        }

        if (TextUtils.isEmpty(product_num)) {
            showToast("请输入商品库存");
            XLog.d("product_num");
            return;
        }

        if (TextUtils.isEmpty(product_bzq)) {
            showToast("请输入商品保质期");
            XLog.d("product_bzq");
            return;
        }


        if (ck_product_vip_yes.isChecked()) {

            if (TextUtils.isEmpty(product_price)) {
                showToast("请输入商品零售价格");
                XLog.d("product_price");
                return;
            }

            if (TextUtils.isEmpty(product_vip_price)) {
                showToast("请输入会员价格");
                XLog.d("product_vip_price");
                return;
            }

        }

        if (ck_product_vip_no.isChecked()) {
            if (TextUtils.isEmpty(product_price)) {
                showToast("请输入商品零售价格");
                XLog.d("ck_product_vip_no product_vip_price");
                return;
            }
        }

        if (ck_product_bzq_yue.isChecked()) {
            int intproduct_bzq = Integer.parseInt(product_bzq);
            int bzq = intproduct_bzq * 30;
            product_bzq = bzq + "";
        }

        if (ck_product_xeld.isChecked()) {
            logo = "xeld";
        }
        if (ck_product_zy.isChecked()) {
            logo = "zy";
        }


        try {
            JSONObject json = new JSONObject();
            json.put("prodName", product_name);
            json.put("price", product_price);
            json.put("totalStocks", product_num);
            json.put("unit", unitName);
            json.put("barCode", product_bar_code);
            json.put("categoryId", categoryId);
            json.put("et_product_bzq", product_bzq);
            json.put("logo", logo);

            if (ck_product_vip_yes.isChecked()) {
                json.put("vipPrice", product_vip_price); //如果是会员，传入会员的价格

            }
            showLoading(true);
            easyPost(json, URL.DEBUG_URL + URL.ADDPRODUCT, AddProductBean.class, resultBean -> {
                onProductResult(resultBean);
            });
        } catch (Exception e) {
            XLog.e(e);
        }


    }

    //在一定范围内生成不重复的随机数
    //在testRandom2中生成的随机数可能会重复.
    //在此处避免该问题
    private String testRandom3() {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            int randomInt = random.nextInt(9);
            buffer.append(randomInt);
        }
        return buffer.toString();
    }


    /**
     * 首次进入收银主页，获取门店信息
     */
    private void addProduct() {
        try {
            JSONObject json = new JSONObject();
            json.put("shopId", Constant.getShopId());
            json.put("userId", Constant.getUserId());
            showLoading(true);
            easyPost(json, URL.DEBUG_URL + URL.ADDPRODUCT, AddProductBean.class, resultBean -> {
                onProductResult(resultBean);
            });
        } catch (Exception e) {
            XLog.e(e);
        }
    }

    private void onProductResult(AddProductBean resultBean) {
        showLoading(false);
        if (resultBean == null) {
            return;
        } else {
            if (resultBean.getData() != null) {

                showToast("商品添加成功");

                et_product_name.setText("");
                et_product_price.setText("");
                et_product_vip_price.setText("");
                et_product_bzq.setText("");
                et_product_num.setText("");
                et_product_bar_code.setText("");
            }
        }

    }

    /**
     * 处理扫码支付硬件信息
     * 扫码枪扫码指令-通过广播接受者，传递在此，在此次做分发处理
     *
     * @param eventBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(BaseEventBean eventBean) {

        if (eventBean.getType() == BaseEventBean.GETUI_PUSH_MSG) {
            XLog.d("handleEvent  =  " + eventBean.getType());
            String picAddress = (String) eventBean.getValue();
            iv_add_product_pic.setVisibility(View.VISIBLE);

            //加载图片的内容
            Glide
                    .with(this)
                    .load(picAddress)
                    .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(iv_add_product_pic);


//            if (picAddress.contains("token")) {
//                JsonParseUtils.parseTokenBean(mContext, picAddress);
//            }

        }

    }

    private void gotoLoginAct() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

        finish();//关闭当前活动
    }

    private void initData() {

        categoryList = new ArrayList<>();

        stringList = new ArrayList<>();
        stringList.add("请选择商品分类");

        unitList = new ArrayList<>();
        unitList.add("请选择计量单位");
        unitList.add("袋");
        unitList.add("份");
        unitList.add("瓶");
        unitList.add("包");
        unitList.add("盒");
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
            XLog.e(e);
        }

    }


    private String produceQRCode() {
//        try {
//            JSONObject json = new JSONObject();
//            json.put("cid", Constant.getCid());
////            showLoading();
//            easyPost(json, URL.DEBUG_URL + URL.PRODUCEQRCODE, String.class, result -> {
//                onProduceQRCodeResult(result);
//            });
//        } catch (Exception e) {
//            XLog.e(e);
//        }

        String url = "https://upload.51xeld.com?cid=";
        StringBuffer buffer = new StringBuffer(url);
        buffer.append(PreferencesUtil.getString(this, Constant.SP_CID));
//        buffer.append(Constant.getCid());

        return buffer.toString();

    }

    private void onProduceQRCodeResult(String result) {

        XLog.d(result);
    }

    /**
     * 根据店铺ID 获取 商品分类列表 的回调处方法
     * * @param result
     */
    private void onCateGoryResult(CategoryBean result) {
        categoryList = result.getData();
//        showLoading(false);
        if (categoryList != null) {
            for (int i = 0; i < categoryList.size(); i++) {
                stringList.add(categoryList.get(i).getCategoryName());
            }
        }
    }


}