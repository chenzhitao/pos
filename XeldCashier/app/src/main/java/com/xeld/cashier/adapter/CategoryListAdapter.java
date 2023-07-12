package com.xeld.cashier.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xeld.cashier.R;
import com.xeld.cashier.bean.CategoryBean;

import java.util.List;

public class CategoryListAdapter extends BaseAdapter {

    private Context mContext;
    private List<CategoryBean.DataBean> list;
    private int mFlag;
    private int selectPosition = -1;


    public CategoryListAdapter(Context context, List<CategoryBean.DataBean> lvBeans, int flag) {
        this.mContext = context;
        this.list = lvBeans;
        this.mFlag = flag;
    }

    public CategoryListAdapter(List<CategoryBean.DataBean> var1) {
        this.list = var1;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHold hold = null;
//        if (null == convertView) {
//            hold = new ViewHold();
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.cata_title_list_layout, null);
//            hold.txCataName = (TextView) convertView.findViewById(R.id.tv_cata_name);
//            hold.txCataName.setText(list.get(position).getCategoryName());
//
//        } else {
//            hold = (ViewHold) convertView.getTag();
//        }
        /**
         * 分类列表需要一次性把所有的分类都创建了，不需要复用，不然会有重复的数据
         */
        ViewHold hold = new ViewHold();
        convertView = LayoutInflater.from(mContext).inflate(R.layout.cata_title_list_layout, null);
        hold.txCataName = (TextView) convertView.findViewById(R.id.tv_cata_name);
        hold.txCataName.setText(list.get(position).getCategoryName());

        return convertView;
    }

    public class ViewHold {
        private TextView txCataName;

    }


}
