package com.xeld.cashier.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {



    public CommonAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public CommonAdapter(@Nullable List<T> data) {
        super(data);
    }

//
//    public List<T> getData(Object data, boolean page) {
//
//        if (data == null) return new ArrayList<>();
//        BaseListRs base = (BaseListRs) data;
//        if (base.getData() == null) {
//            base.setData(new ArrayList());
//        }
//        if (page) {
//            mData.clear();
//            mData.addAll(base.getData());
//            setEnableLoadMore(true);
//
//        } else {
//            mData.addAll(base.getData());
//        }
//
//        if (base.getData() == null || base.getData().size() == 0) {
//            setEnableLoadMore(false);
//        } else {
//            loadMoreComplete();
//        }
//        notifyDataSetChanged();
//        return mData;
//    }


    public void setAdapterEmptyView(View emptyView) {
        if (mData != null && mData.size() > 0) return;

        mData.clear();
        notifyDataSetChanged();
        setEmptyView(emptyView);
    }

    public void setAdapterEmptyView(View emptyView, boolean page) {
        if (emptyView == null) return;

        //如果是上拉就不处理显示空页面
        if (!page)
            return;

        mData.clear();
        notifyDataSetChanged();
        setEmptyView(emptyView);
    }

}