package com.xeld.cashier.base.fragment;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xeld.cashier.R;
import com.xeld.cashier.base.fragment.BaseFragment;
import com.xeld.cashier.view.refresh.RecyclerViewNoBugGridLayoutManager;
import com.xeld.cashier.view.refresh.RecyclerViewNoBugLinearLayoutManager;
import com.xeld.cashier.view.refresh.SwipeRecyclerView;
import com.trecyclerview.listener.OnLoadMoreListener;
import com.trecyclerview.multitype.Items;
import com.trecyclerview.multitype.MultiTypeAdapter;

import java.util.Collection;
import java.util.List;

/**
 * Created by 曹荣冠 on 2019/8/26.
 * 不带刷新的公共列表封装。兼容scrollView。
 */

public abstract class BaseOpenListFragment extends BaseFragment implements OnLoadMoreListener {
    public SwipeRecyclerView mRecyclerView;
    public MultiTypeAdapter adapter;
    public Items newItems = new Items();
    public Items oldItems = new Items();
    protected boolean isLoadMore = false, isLoading = true, hasNextPage = true;
    public int page = 1;
    public int PAGE_SIZE = 10;

    @Override
    public int getLayout() {
        return R.layout.item_base_open_recycleview;
    }

    public void initView() {
        mRecyclerView = getViewById(R.id.recycler_view);
        adapter = createAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(createLayoutManager());
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.setFocusable(false);
        mRecyclerView.setNestedScrollingEnabled(true);
        mRecyclerView.addOnLoadMoreListener(this);
        if (newItems.size() > 0) {
            mRecyclerView.refreshComplete(newItems, false);
        }
    }

    @Override
    public void onLoadMore() {
        isLoadMore = true;
        if (mRecyclerView.isGrid)
            mRecyclerView.onLoadingMore();
        page++;
    }

    public void onRefresh() {
        page = 1;
        isLoadMore = false;
    }

    public RecyclerView.LayoutManager createLayoutManager() {
        return new RecyclerViewNoBugLinearLayoutManager(activity);
    }

    public void setData(List<?> collection) {
        if (isLoadMore){
            onLoadMoreSuccess(collection);
        }else {
            onRefreshSuccess(collection);
        }
    }

    public void onRefreshSuccess(Collection<?> collection) {
        if (newItems.size() > 0) {
            newItems.clear();
        }
        if (collection == null) {
            return;
        }
        newItems.addAll(collection);
        if (mRecyclerView != null) {
            mRecyclerView.refreshComplete(newItems, !hasNextPage);
        }
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    protected void onLoadMoreSuccess(List<?> collection) {
        isLoading = true;
        isLoadMore = false;
        oldItems.addAll(collection);
        mRecyclerView.loadMoreComplete(collection, !hasNextPage);
    }

    /**
     * adapter
     *
     * @return MultiTypeAdapter
     */
    protected abstract MultiTypeAdapter createAdapter();

    public void setGridColumn(int size) {
        RecyclerViewNoBugGridLayoutManager mGManager = new RecyclerViewNoBugGridLayoutManager(getActivity(), size);
        mRecyclerView.setLayoutManager(mGManager);
    }

    /**
     * 设置网格布局
     */
    public GridLayoutManager setGridLayoutManager(int count) {
        mRecyclerView.isGrid = true;
        return new RecyclerViewNoBugGridLayoutManager(getAct(), count);
    }

}
