package com.xeld.cashier.adapter.holder;

import android.content.Context;

import com.trecyclerview.multitype.MultiTypeAdapter;
import com.trecyclerview.pojo.FootVo;
import com.trecyclerview.pojo.HeaderVo;
import com.trecyclerview.progressindicator.ProgressStyle;
import com.trecyclerview.view.FootViewHolder;
import com.trecyclerview.view.HeaderViewHolder;
import com.xeld.cashier.bean.ShopOrderDetailBean;

public class AdapterPool {
    private volatile static AdapterPool adapterPool;

    public static AdapterPool newInstance() {
        if (adapterPool == null) {
            synchronized (AdapterPool.class) {
                if (adapterPool == null) {
                    adapterPool = new AdapterPool();
                }
            }
        }
        return adapterPool;
    }

    public MultiTypeAdapter getAdapter(MultiTypeAdapter.Builder builder, Context context) {
        return builder.bind(HeaderVo.class, new HeaderViewHolder(context, ProgressStyle.SysProgress))
                .bind(FootVo.class, new FootViewHolder(context, ProgressStyle.SysProgress)).build();
    }



    public MultiTypeAdapter getHealthResultsAdapter(Context context, HealthResultsHolder holder) {
        return getAdapter(new MultiTypeAdapter.Builder<>().bind(String.class, holder), context);
    }

//    public MultiTypeAdapter getOrderListAdapter(Context context, OrderListHolder holder) {
//        return getAdapter(new MultiTypeAdapter.Builder<>().bind(ShopOrderDetailBean.DataBean.RecordsBean.class, holder), context);
//    }




}
