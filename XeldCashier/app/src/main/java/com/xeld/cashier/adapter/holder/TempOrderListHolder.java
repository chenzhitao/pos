package com.xeld.cashier.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseViewHolder;
import com.trecyclerview.holder.BaseHolder;
import com.xeld.cashier.R;
import com.xeld.cashier.adapter.TempOrderListdetailAdapter;
import com.xeld.cashier.bean.ShopOrderDetailBean;
import com.xeld.cashier.constant.Constant;
import com.xeld.cashier.utils.BigDecimalUtils;
import com.xeld.cashier.utils.PreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TempOrderListHolder extends BaseListVH<ShopOrderDetailBean.DataBean.RecordsBean> {


    private ListViewItemClickInterface itemClickInterface;
    private Context context;
    private int position = 0;
    private int clickPosition = -1;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setItemClickInterface(ListViewItemClickInterface itemClickInterface) {
        this.itemClickInterface = itemClickInterface;
    }


    @Override
    public int getAdapterLayoutId() {
        //销售单列表的item
        return R.layout.act_temp_order_adapter_layout;
    }


    @Override
    public void convert(@NonNull BaseViewHolder helper, ShopOrderDetailBean.DataBean.RecordsBean item) {
        if (item == null) {
            return;
        }

        helper.setText(R.id.tv_temp_order_time, item.getCreateTime());
        helper.setText(R.id.tv_temp_order_name, item.getUserMobile());


        int size = item.getOrderItems().size();
        double totalPrice = 0;
        int proCount = 0;

        for (int i = 0; i < size; i++) {
            totalPrice += BigDecimalUtils.mul(item.getOrderItems().get(i).getPrice(), item.getOrderItems().get(i).getProdCount(), 1);
            proCount += item.getOrderItems().get(i).getProdCount();
        }

        helper.setText(R.id.tv_temp_order_price, "订单金额 ¥  " + totalPrice);

        helper.setText(R.id.tv_temp_order_num, "     共  " + proCount + "件商品");

        TempOrderListdetailAdapter orderListdetailAdapter = new TempOrderListdetailAdapter(context, item, 1);

        helper.setAdapter(R.id.lv_temp_order_detail, orderListdetailAdapter);


        helper.getView(R.id.layout_temp_order_list_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemClickInterface.onHandleItemClick(item);
                clickPosition = helper.getLayoutPosition();

                PreferencesUtil.setInt(context, Constant.SP_LISTITEM_ID, clickPosition);
            }
        });

        clickPosition = PreferencesUtil.getInt(context, Constant.SP_LISTITEM_ID, -1);

        boolean isCurrentPosition = helper.getAdapterPosition() == clickPosition;

        //改变背景颜色
        helper.getView(R.id.layout_temp_order_list_item).setBackgroundResource(isCurrentPosition ? R.color.list_item_bg_color : R.color.white);

        position++;
    }

    public class ViewHolder extends BaseHolder {
        //item的父布局，用于接收点击事件
        @BindView(R.id.layout_temp_order_list_item)
        LinearLayout layout_temp_order_list_item;
        //订单总金额
        @BindView(R.id.tv_temp_order_price)
        TextView tv_temp_order_price;
        //订单创建时间
        @BindView(R.id.tv_temp_order_time)
        TextView tv_temp_order_time;
        //商品的数量
        @BindView(R.id.tv_temp_order_num)
        TextView tv_temp_order_num;

        @BindView(R.id.tv_temp_order_name)
        TextView tv_temp_order_name;

        //挂单详情页面 具体的商品数量
        @BindView(R.id.lv_temp_order_detail)
        ListView lv_temp_order_detail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ListViewItemClickInterface {
        void onHandleItemClick(ShopOrderDetailBean.DataBean.RecordsBean item);
    }
}
