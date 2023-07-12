package com.xeld.cashier.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.trecyclerview.multitype.MultiTypeAdapter;
import com.xeld.cashier.adapter.holder.HealthResultsHolder;
import com.xeld.cashier.adapter.holder.AdapterPool;
import com.xeld.cashier.base.fragment.BaseListFragment;
import com.xeld.cashier.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class HealthResultsFragment extends BaseListFragment {
    private HealthResultsHolder holder;

    @Override
    protected MultiTypeAdapter createAdapter() {
        holder = new HealthResultsHolder(mAct);
        return AdapterPool.newInstance().getHealthResultsAdapter(mAct, holder);
    }

    public LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    public int getLayout() {
        return super.getLayout();
    }

    @Override
    public void initView() {
        super.initView();


        post();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        post();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        post();
    }

    public void post() {
        int count = CommonUtils.getRandom(5) + 2;
        List<String> list = new ArrayList<>();
        for (int a = 0; a < count; a++) {
            list.add("");
        }
        setHasNextPage(true);
        setData(list);
    }

}
