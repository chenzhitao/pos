package com.xeld.cashier.adapter.holder;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xeld.cashier.utils.PreferencesUtil;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseViewHolder;
import com.elvishew.xlog.Logger;
import com.trecyclerview.holder.BaseHolder;
import com.xeld.cashier.R;
import com.xeld.cashier.bean.ShopOrderDetailBean;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.widget.Toast;

import com.elvishew.xlog.XLog;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.fragment.OrderListFragment;

public class OrderListHolder extends BaseListVH<ShopOrderDetailBean.DataBean.RecordsBean> {


    private ListViewItemClickInterface itemClickInterface;
    private Context context;
    private int position = 0;
    private int clickPosition = -1;
    private OrderListFragment orderListFragment;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setItemClickInterface(ListViewItemClickInterface itemClickInterface) {
        this.itemClickInterface = itemClickInterface;
    }

    public void setOrderListFragment(OrderListFragment orderListFragment) {
        this.orderListFragment = orderListFragment;
    }

    @Override
    public int getAdapterLayoutId() {
        //销售单列表的item
        return R.layout.order_list_item_layout;
    }


    @Override
    public void convert(@NonNull BaseViewHolder helper, ShopOrderDetailBean.DataBean.RecordsBean item) {
        if (item == null) {
            return;
        }
        helper.setText(R.id.tv_shop_order_orderNumber, item.getOrderNumber());
        helper.setText(R.id.tv_shop_order_total, item.getTotal() + "");
        helper.setText(R.id.tv_shop_order_createTime, item.getCreateTime());

        int status = item.getStatus();
        switch (status) {
            case OrderListFragment.STATE_ORDER_UNPAID:
                helper.setText(R.id.tv_shop_order_state, OrderListFragment.STATE_ORDER_UNPAID_STR);
                break;
            case OrderListFragment.STATE_ORDER_UNDELIVERED:
                helper.setText(R.id.tv_shop_order_state, OrderListFragment.STATE_ORDER_UNDELIVERED_STR);
                break;
            case OrderListFragment.STATE_ORDER_NOT_RECEIVED:
                helper.setText(R.id.tv_shop_order_state, OrderListFragment.STATE_ORDER_NOT_RECEIVED_STR);
                break;
            case OrderListFragment.STATE_ORDER_CONFIRM_RECEIPT:
                helper.setText(R.id.tv_shop_order_state, OrderListFragment.STATE_ORDER_CONFIRM_RECEIPT_STR);
                break;
            case OrderListFragment.STATE_ORDER_CONFIRM_RECEIPT_THREEDAY:
                helper.setText(R.id.tv_shop_order_state, OrderListFragment.STATE_ORDER_CONFIRM_RECEIPT_STR);
                break;
            case OrderListFragment.STATE_ORDER_CLOSED:
                helper.setText(R.id.tv_shop_order_state, OrderListFragment.STATE_ORDER_CLOSED_STR);
                break;
        }

        int size = item.getOrderItems().size();
        LinearLayout linearLayout = helper.getView(R.id.layout_order_list_product);
        linearLayout.removeAllViews();
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(90, 90));  //设置图片宽高
            //imageView.setImageResource(R.mipmap.ic_launcher); //图片资源
            linearLayout.addView(imageView); //动态添加图片

            //加载图片的内容
            Glide
                    .with(context)
                    .load(item.getOrderItems().get(i).getPic())
                    .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageView);

        }
        //商品数量
        helper.setText(R.id.tv_shop_order_num, size + "");
        helper.setText(R.id.tv_order_user_name, item.getNikeName());

        helper.getView(R.id.layout_order_list_item).setTag("" + position);


//        if (helper.getView(R.id.layout_order_list_item).getTag().equals(helper.getAdapterPosition() + "")) {
//
//            helper.getView(R.id.layout_order_list_item).setBackgroundColor(R.color.list_item_bg_color);
//        } else {
//            helper.getView(R.id.layout_order_list_item).setBackgroundColor(R.color.white);
//        }

        helper.getView(R.id.layout_order_list_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemClickInterface.onHandleItemClick(item);
                clickPosition = helper.getLayoutPosition();

                PreferencesUtil.setInt(context, Constant.SP_LISTITEM_ID, clickPosition);
            }
        });
        clickPosition = PreferencesUtil.getInt(context, Constant.SP_LISTITEM_ID, -1);

        boolean isCurrentPosition = helper.getAdapterPosition() == clickPosition;

        //用这个背景方法，才能修改背景颜色
        helper.getView(R.id.layout_order_list_item).setBackgroundResource(isCurrentPosition ? R.color.list_item_bg_color : R.color.white);

        position++;
    }

    public class ViewHolder extends BaseHolder {
        //item的父布局，用于接收点击事件
        @BindView(R.id.layout_order_list_item)
        LinearLayout layout_order_list_item;
        //订单编号
        @BindView(R.id.tv_shop_order_orderNumber)
        TextView tvShopOrderNumber;
        //订单总金额
        @BindView(R.id.tv_shop_order_total)
        TextView tvShopOrderTotal;
        //订单状态
        @BindView(R.id.tv_shop_order_state)
        TextView tvShopOrderState;
        //订单创建时间
        @BindView(R.id.tv_shop_order_createTime)
        TextView tvShopOrderCreateTime;
        //动态加载用户购买的商品数量
        @BindView(R.id.layout_order_list_product)
        LinearLayout layout_order_list_product;
        //商品的数量
        @BindView(R.id.tv_shop_order_num)
        TextView tv_shop_order_num;
        //订单用户
        @BindView(R.id.tv_order_user_name)
        TextView tv_order_user_name;

//        @BindView(R.id.iv_order_list_product_img)
//        ImageView iv_order_list_product_img;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ListViewItemClickInterface {
        void onHandleItemClick(ShopOrderDetailBean.DataBean.RecordsBean item);
    }
}
