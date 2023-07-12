package com.xeld.cashier.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xeld.cashier.R;
import com.xeld.cashier.bean.HomeMemberInfoBean;
import com.xeld.cashier.bean.ShopOrderDetailBean.DataBean.RecordsBean;
import com.xeld.cashier.bean.ShopOrderDetailBean.DataBean.RecordsBean.OrderItemsBean;
import com.xeld.cashier.callback.ModifyCountInterface;
import com.xeld.cashier.utils.BigDecimalUtils;

import java.util.List;

public class TempOrderListdetailAdapter extends BaseAdapter {

    private Context mContext;
    private List<OrderItemsBean> list;//子订单的list 数量
    private RecordsBean recordsBean; //订单对象
    private int mFlag;
    private ModifyCountInterface modifyCountInterface;

    private boolean isMember = false;
    private HomeMemberInfoBean.DataBean memberInfoBean;

    private int productNum = 0;
    private int preProductNum = 0;

    public TempOrderListdetailAdapter(Context context, RecordsBean orderlist, int flag) {
        this.mContext = context;
        this.list = orderlist.getOrderItems();
        this.mFlag = flag;
    }


    public void setMember(HomeMemberInfoBean.DataBean memberInfoBean) {
        this.memberInfoBean = memberInfoBean;
        notifyDataSetChanged();
    }


    public ModifyCountInterface getModifyCountInterface() {
        return modifyCountInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();

    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static ViewHold hold = null;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (null == convertView) {

            hold = new ViewHold();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.temp_order_detail_list_adapter_layout, null);

            hold.ivProdectIcon = (ImageView) convertView.findViewById(R.id.iv_order_detaol_list_icon);

            hold.tvProductName = (TextView) convertView.findViewById(R.id.tv_order_detaol_list_product_name);

            hold.tvProdectPrice = (TextView) convertView.findViewById(R.id.tv_order_detail_price);

            hold.tvProdectNum = (TextView) convertView.findViewById(R.id.tv_order_detail_num);

            hold.tvProdectTotal = (TextView) convertView.findViewById(R.id.tv_order_detaol_list_product_price);


//            hold.tvProdectNum = (TextView) convertView.findViewById(R.id.tv_cart_list_prodect_num);

//            hold.tvProdectTotal = (TextView) convertView.findViewById(R.id.tv_cart_list_item_total);

//            hold.layout_member_item_price = (LinearLayout) convertView.findViewById(R.id.layout_member_item_price);

//            hold.tv_list_item_vip_price = (TextView) convertView.findViewById(R.id.tv_list_item_vip_price);

            convertView.setTag(hold);
        } else {
            hold = (ViewHold) convertView.getTag();
        }

        //商品名称
        hold.tvProductName.setText(list.get(position).getProdName());
        //商品价格
        hold.tvProdectPrice.setText("¥ " + list.get(position).getPrice());

        hold.tvProdectNum.setText("X " + list.get(position).getProdCount());

        hold.tvProdectTotal.setText("¥ " + BigDecimalUtils.mul(list.get(position).getPrice(), list.get(position).getProdCount(), 2));

        //商品图片
        Glide
                .with(mContext)
                .load("https://images.51xeld.com/" + list.get(position).getPic())
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(hold.ivProdectIcon);

        //商品数量
//        hold.tvProdectNum.setText(list.get(position).getOrderNum() + "");
        //计算商品的价格
//        double itemTotalPrice = 0.0;
//
//        if (memberInfoBean != null) {
//
//            //购物车中 VIP的价格
//            if (memberInfoBean.getOldAccount() > 0) {
//
//                hold.layout_member_item_price.setVisibility(View.VISIBLE);
//                hold.tv_list_item_vip_price.setText(list.get(position).getVipPrice() + "");
//                itemTotalPrice = BigDecimalUtils.mul(list.get(position).getVipPrice(), list.get(position).getProdCount(), 2);
//                XLog.e("vip =" + list.get(position).getVipPrice());
//
//            } else {
//                itemTotalPrice = BigDecimalUtils.mul(list.get(position).getPrice(), list.get(position).getProdCount(), 2);
//                hold.layout_member_item_price.setVisibility(View.GONE);
//
//                XLog.e("vip2 =" + list.get(position).getVipPrice());
//            }
//        } else {
//            itemTotalPrice = BigDecimalUtils.mul(list.get(position).getPrice(), list.get(position).getPrice(), 2);
//            hold.layout_member_item_price.setVisibility(View.GONE);
//            XLog.e("vip3 =" + list.get(position).getVipPrice());
//        }
//        hold.tvProdectTotal.setText(itemTotalPrice + "");


        return convertView;
    }


    public class ViewHold {
        //商品主图图片
        private ImageView ivProdectIcon;
        //商品名称
        private TextView tvProductName;
        //商品价格
        private TextView tvProdectPrice;
        //商品数量
        private TextView tvProdectNum;
        //商品总计价格
        private TextView tvProdectTotal;
        //会员价格
//        private LinearLayout layout_member_item_price;
//        private TextView tv_list_item_vip_price;

    }

}
