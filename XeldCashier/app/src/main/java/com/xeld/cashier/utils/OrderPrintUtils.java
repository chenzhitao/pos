package com.xeld.cashier.utils;

import android.content.Context;
import android.util.Log;

import com.xeld.cashier.bean.MenusBean;
import com.xeld.cashier.bean.ShopOrderDetailBean;
import com.xeld.cashier.bean.ShopOrderDetailBean.DataBean;
import com.xeld.cashier.bean.ShopOrderDetailBean.DataBean.RecordsBean;
import com.xeld.cashier.bean.ShopOrderDetailBean.DataBean.RecordsBean.OrderItemsBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.xeld.cashier.R;

import static com.xeld.cashier.utils.CommonViewUtils.showToast;


public class OrderPrintUtils {
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private Context mBact;

    public OrderPrintUtils(Context context) {
        this.mBact = context;
    }

    public String formatGoods(RecordsBean relust) {
        if (relust == null) {
            return "";
        }
        if (relust.getOrderItems() == null || relust.getOrderItems().size() == 0) {
            showToast("没有订单数据 ");
            return "";
        }

        String goods_data = "";
        List<MenusBean> menus = new ArrayList<>();
        List<OrderItemsBean> orderItemsList = relust.getOrderItems();

        int size = orderItemsList.size();
        double totalPrice = 0.0;
        for (int i = 0; i < size; i++) {
            OrderItemsBean orderItemsBean = orderItemsList.get(i);
            MenusBean bean = new MenusBean();
            bean.setId("" + size + 1);
            bean.setName(orderItemsBean.getProdName());
            bean.setMoney(orderItemsBean.getPrice() + "");
            bean.setNet(orderItemsBean.getProdCount());
            bean.setType(0);
            menus.add(bean);
//            totalPrice += BigDecimalUtils.mul(orderItemsBean.getPrice(), orderItemsBean.getProdCount(), 1);
        }
        String price = decimalFormat.format(relust.getActualTotal());//从订单中获取 商品的价格

        return buildMenuJson(menus, price);
    }


    String goods_data = "";

    private String buildMenuJson(List<MenusBean> menus, String price) {

        try {
            JSONObject data = new JSONObject();
            data.put("title", "Sunmi " + ResourcesUtils.getString(mBact, R.string.menus_title));
            JSONObject head = new JSONObject();
            head.put("param1", ResourcesUtils.getString(mBact, R.string.menus_number));
            head.put("param2", ResourcesUtils.getString(mBact, R.string.menus_goods_name));
            head.put("param3", ResourcesUtils.getString(mBact, R.string.menus_unit_price));
            data.put("head", head);
            data.put("flag", "true");
            JSONArray list = new JSONArray();
            for (int i = 0; i < menus.size(); i++) {
                JSONObject listItem = new JSONObject();
                listItem.put("param1", "" + (i + 1));
                listItem.put("param2", menus.get(i).getName());
                listItem.put("param3", menus.get(i).getMoney());
                listItem.put("type", menus.get(i).getType());
                listItem.put("code", menus.get(i).getCode());
                listItem.put("net", menus.get(i).getNet());
                list.put(listItem);
            }
            data.put("list", list);
            JSONArray KVPList = new JSONArray();
            JSONObject KVPListOne = new JSONObject();
            KVPListOne.put("name", ResourcesUtils.getString(mBact, R.string.shop_car_total) + " ");
            KVPListOne.put("value", price);
            JSONObject KVPListTwo = new JSONObject();
            KVPListTwo.put("name", ResourcesUtils.getString(mBact, R.string.shop_car_offer) + " ");
            KVPListTwo.put("value", "0.00");
            JSONObject KVPListThree = new JSONObject();
            KVPListThree.put("name", ResourcesUtils.getString(mBact, R.string.shop_car_number) + " ");
            KVPListThree.put("value", "" + menus.size());
            JSONObject KVPListFour = new JSONObject();
            KVPListFour.put("name", ResourcesUtils.getString(mBact, R.string.shop_car_receivable) + " ");
            KVPListFour.put("value", price);
            KVPList.put(0, KVPListOne);
            KVPList.put(1, KVPListTwo);
            KVPList.put(2, KVPListThree);
            KVPList.put(3, KVPListFour);
            data.put("KVPList", KVPList);
            Log.d("HHHH", "onClick: ---------->" + data.toString());
            goods_data = data.toString();
//            Log.d(TAG, "buildMenuJson: ------->" + (videoMenuDisplay != null));
            // 购物车有东西

        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods_data;
    }

    private String autoalAmountPrice(MenusBean menusBean) {
        String strAmount = "";
        try {
            double price = Double.parseDouble(menusBean.getMoney());
            double amountPrice = BigDecimalUtils.mul(price, menusBean.getNet(), 2);
            strAmount = amountPrice + "";

        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }
        return strAmount;
    }
}
