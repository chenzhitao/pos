package com.xeld.cashier.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elvishew.xlog.XLog;
import com.xeld.cashier.R;
import com.xeld.cashier.bean.HomeMemberInfoBean;
import com.xeld.cashier.bean.ProductListBean;
import com.xeld.cashier.callback.ModifyCountInterface;
import com.xeld.cashier.utils.BigDecimalUtils;

import java.util.List;

public class CardListAdapter extends BaseAdapter {

    private Context mContext;
    private List<ProductListBean.DataBean> list;
    private int mFlag;
    private ModifyCountInterface modifyCountInterface;

    private boolean isMember = false;
    private HomeMemberInfoBean.DataBean memberInfoBean;

    private int productNum = 0;
    private int preProductNum = 0;

    public CardListAdapter(Context context, List<ProductListBean.DataBean> lvBeans, int flag) {
        this.mContext = context;
        this.list = lvBeans;
        this.mFlag = flag;
    }


    public void setMember(HomeMemberInfoBean.DataBean memberInfoBean) {
        this.memberInfoBean = memberInfoBean;
        notifyDataSetChanged();
    }

    public CardListAdapter(List<ProductListBean.DataBean> var1) {
        this.list = var1;
    }

    public void setNewData(List<ProductListBean.DataBean> list) {
        this.list = list;
    }

    public void setDataBean(ProductListBean.DataBean dataBean) {
        if (list != null) {
            list.add(dataBean);
            notifyDataSetChanged();
        }
    }

    public ModifyCountInterface getModifyCountInterface() {
        return modifyCountInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    public List<ProductListBean.DataBean> getList() {
        return list;
    }

    public void setList(List<ProductListBean.DataBean> list) {
        this.list = list;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.shop_cart_list_layout, null);

            hold.tvCardId = (TextView) convertView.findViewById(R.id.tv_cart_list_id);
            hold.ivProdectIcon = (ImageView) convertView.findViewById(R.id.iv_cart_list_icon);
            hold.ivCardDel = (ImageView) convertView.findViewById(R.id.iv_cart_list_del);
            hold.ivCardAdd = (ImageView) convertView.findViewById(R.id.iv_cart_list_add);

            hold.tvProductName = (TextView) convertView.findViewById(R.id.tv_cart_list_product_name);
            hold.tvProdectPrice = (TextView) convertView.findViewById(R.id.tv_cart_list_product_price);
            hold.tvProdectNum = (TextView) convertView.findViewById(R.id.tv_cart_list_prodect_num);
            hold.tvProdectTotal = (TextView) convertView.findViewById(R.id.tv_cart_list_item_total);
            hold.tvCardOperation = (TextView) convertView.findViewById(R.id.tv_cart_list_prodect_operation);
            hold.layout_member_item_price = (LinearLayout) convertView.findViewById(R.id.layout_member_item_price);
            hold.tv_list_item_vip_price = (TextView) convertView.findViewById(R.id.tv_list_item_vip_price);
            convertView.setTag(hold);
        } else {
            hold = (ViewHold) convertView.getTag();
        }

        //商品编号
        hold.tvCardId.setText(position + 1 + "");
        //商品名称
        hold.tvProductName.setText(list.get(position).getProdName());
        //商品价格
        hold.tvProdectPrice.setText(list.get(position).getPrice() + "");
        //商品图片
        Glide
                .with(mContext)
                .load(list.get(position).getPic())
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(hold.ivProdectIcon);

        //商品数量
        hold.tvProdectNum.setText(list.get(position).getOrderNum() + "");
        //计算商品的价格
        double itemTotalPrice = 0.0;

        if (memberInfoBean != null) {

            //购物车中 VIP的价格
            if (memberInfoBean.getOldAccount() > 0) {

                hold.layout_member_item_price.setVisibility(View.VISIBLE);
                hold.tv_list_item_vip_price.setText(list.get(position).getVipPrice() + "");
                itemTotalPrice = BigDecimalUtils.mul(list.get(position).getVipPrice(), list.get(position).getOrderNum(), 2);
                XLog.e("vip ="+ list.get(position).getVipPrice());

            } else {
                itemTotalPrice = BigDecimalUtils.mul(list.get(position).getPrice(), list.get(position).getOrderNum(), 2);
                hold.layout_member_item_price.setVisibility(View.GONE);

                XLog.e("vip2 ="+ list.get(position).getVipPrice());
            }
        } else {
            itemTotalPrice = BigDecimalUtils.mul(list.get(position).getPrice(), list.get(position).getOrderNum(), 2);
            hold.layout_member_item_price.setVisibility(View.GONE);
            XLog.e("vip3 ="+ list.get(position).getVipPrice());
        }
        hold.tvProdectTotal.setText(itemTotalPrice + "");

        modifyCountInterface.calcProductPrice();


        hold.ivCardDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doDecrease(position, hold.tvProdectNum, false);//暴露增加接口
            }
        });

        hold.ivCardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doIncrease(position, hold.tvProdectNum, false);//暴露增加接口
            }
        });

        hold.tvCardOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog alert = new AlertDialog.Builder(mContext).create();
//                alert.setTitle("操作提示");
//                alert.setMessage("您确定要将这些商品从购物车中移除吗？");
//                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                return;
//                            }
//                        });
//                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        });
//                alert.show();

                modifyCountInterface.childDelete(position);//删除 目前只是从item中移除

            }
        });
        return convertView;
    }


    public class ViewHold {
        private TextView tvCardId;
        //商品主图图片
        private ImageView ivProdectIcon;
        //数量 减一
        private ImageView ivCardDel;
        //数量 加一
        private ImageView ivCardAdd;
        //商品名称
        private TextView tvProductName;
        //商品价格
        private TextView tvProdectPrice;
        //商品数量
        private TextView tvProdectNum;
        //商品总计价格
        private TextView tvProdectTotal;
        //删除操作
        private TextView tvCardOperation;
        //会员价格
        private LinearLayout layout_member_item_price;
        private TextView tv_list_item_vip_price;

    }

}
