package com.xeld.cashier.base.fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xeld.cashier.R;
import com.xeld.cashier.base.fragment.BaseFragment;
import com.xeld.cashier.utils.CommonViewUtils;
import com.xeld.cashier.view.refresh.RecyclerViewNoBugGridLayoutManager;
import com.xeld.cashier.view.refresh.RecyclerViewNoBugLinearLayoutManager;
import com.xeld.cashier.view.refresh.SwipeRecyclerView;
import com.xeld.cashier.view.refresh.SwipeRefreshLayout;
import com.trecyclerview.listener.OnLoadMoreListener;
import com.trecyclerview.multitype.Items;
import com.trecyclerview.multitype.MultiTypeAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public abstract class BaseListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {
    //    protected TRecyclerView mRecyclerView;
    public SwipeRecyclerView mRecyclerView;
    public SwipeRefreshLayout mSwipeRefreshLayout;

    protected RelativeLayout mTitleBar;

    protected TextView mTitle;

    public MultiTypeAdapter adapter;




    protected boolean isLoadMore = false;

    protected boolean isLoading = true;

    protected boolean isRefresh = false, hasNextPage = true, hasResult = false;

    public Items oldItems;

    public Items newItems;
    public int page = 1;
    public int PAGE_SIZE = 10;
    public LinearLayout rootLl;
    private Handler handler;
    public LinearLayout emptyLl;
    TextView emptyTv;
    public RelativeLayout titleLl;

    @Override
    public int getLayout() {
        return R.layout.fragment_list;
    }

    @Override
    public void initView() {
        rootLl = getViewById(R.id.root_ll);
        mRecyclerView = getViewById(R.id.recycler_view);
        mTitleBar = getViewById(R.id.rl_title_bar);
        mTitle = getViewById(R.id.tv_title);
        mSwipeRefreshLayout = getViewById(R.id.swipe_refresh_layout);
        emptyLl = getViewById(R.id.empty_ll);
        emptyTv = getViewById(R.id.empty_tv);
        titleLl = getViewById(R.id.rl_title_bar);

        oldItems = new Items();
        newItems = new Items();
        adapter = createAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(createLayoutManager());
        mRecyclerView.addOnLoadMoreListener(this);
        //设置刷新时动画的颜色，可以设置4个
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setProgressViewOffset(false, 0, 60);
            mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,
                    android.R.color.holo_blue_light, android.R.color.holo_orange_light,
                    android.R.color.holo_green_light);
        }
        mSwipeRefreshLayout.setOnRefreshListener(this);
        hasResult = false;

    }

    public void setBg(int color) {
        try {
            rootLl.setBackgroundColor(color);
        } catch (Exception e) {
            rootLl.setBackground(CommonViewUtils.getDrawableBase(mAct, color));
        }

    }

    public void setRefreshAble(boolean canRefresh) {
        if (mRecyclerView != null)
            mRecyclerView.setLoadingMoreEnabled(canRefresh);
    }

    protected void lazyLoad() {
        isLoadMore = false;
    }

    public void setData(List<?> collection) {
        //  hasNextPage();
      //  clearDelayAnim();
        if (isLoadMore) {
            onLoadMoreSuccess(collection);
        } else {
            onRefreshSuccess(collection);
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        isRefresh = true;
        isLoadMore = false;
        hasResult = false;
       // delayCloseAnim();
    }

    @Override
    public void onLoadMore() {
        isLoadMore = true;
        hasResult = false;
      /*  if (mRecyclerView.isGrid)
            mRecyclerView.onLoadingMore();*/
        page++;
       // delayCloseAnim();
    }

    private Runnable refreshRunnale = () -> {
        setRefreshState(false);
    };
    private Runnable loadMoreRunnale = () -> {
        mRecyclerView.closeLoading();
    };
    int delayTime = 300;

    private void delayCloseAnim() {
        if (handler == null) {
            handler = new Handler();
        }
        if (isRefresh) {
            handler.postDelayed(refreshRunnale, delayTime);
        }
        if (isLoadMore) {
            handler.postDelayed(loadMoreRunnale, delayTime);
        }
    }

    private void clearDelayAnim() {
        try {
            if (handler != null) {
                if (isRefresh) {
                    handler.removeCallbacks(refreshRunnale);
                }
                if (isLoadMore) {
                    handler.removeCallbacks(loadMoreRunnale);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void onRefreshSuccess(Collection<?> collection) {
        if (newItems.size() > 0) {
            newItems.clear();
        }
        newItems.addAll(collection);
        oldItems.clear();
        oldItems.addAll(newItems);
        mRecyclerView.refreshComplete(oldItems, !hasNextPage);
        isRefresh = false;
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public MultiTypeAdapter getAdapter() {
        return adapter;
    }

    public void notifyDataSet() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    protected void onLoadMoreSuccess(List<?> collection) {
        isLoading = true;
        isLoadMore = false;
        oldItems.addAll(collection);
        mRecyclerView.loadMoreComplete(collection, !hasNextPage);
    }

    public boolean hasNextPage() {
        if (itemCount <= 0) {
            hasNextPage = true;  //默认当条目数小于等于0的时候，可以加载更多。
        } else {
            boolean hasMore = (itemCount % PAGE_SIZE) > 0;
            int maxSize = (itemCount / PAGE_SIZE) + (hasMore ? 1 : 0);
            hasNextPage = page < maxSize;
        }
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public void refreshList() {
        try {
            mRecyclerView.notifyItemRangeChanged(0, oldItems.size());
        } catch (Exception e) {
            CommonViewUtils.setLog("refreshList", e.getMessage());
        }
    }

    /**
     * adapter
     *
     * @return MultiTypeAdapter
     */
    protected abstract MultiTypeAdapter createAdapter();

    /**
     * LayoutManager
     * 如果需要切换方向，重写这个方法就行了。
     *
     * @return LayoutManager
     */
    public LinearLayoutManager createLayoutManager() {
        return new RecyclerViewNoBugLinearLayoutManager(activity);
    }

    /**
     * 设置网格布局
     */
    public GridLayoutManager setGridLayoutManager(int count) {
        mRecyclerView.isGrid = true;
        return new RecyclerViewNoBugGridLayoutManager(getAct(), count);
    }

    public void setRefreshState(boolean isRefresh) {
        if (mSwipeRefreshLayout != null)
            mSwipeRefreshLayout.setRefreshing(isRefresh);
    }

    @SuppressLint("WrongConstant")
    protected void setTitle(String titleName) {
        mTitleBar.setVisibility(View.VISIBLE);
        mTitle.setText(titleName);
    }

    /*
     * 显示空布局
     * */
    protected void showEmptyView(String emptyText) {
        mSwipeRefreshLayout.setVisibility(View.GONE);
        emptyLl.setVisibility(View.VISIBLE);
        emptyTv.setText(emptyText);
    }

    /*
     * 隐藏空布局
     * */
    protected void hideEmptyView() {
        emptyLl.setVisibility(View.GONE);
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
    }


    public int getListSize() {
        return adapter.getItemCount();
    }

    public void resetEmptyView(String text) {
        emptyLl.postDelayed(() -> {
            if (getListSize() == 0) {
                showEmptyView(text);
            } else {
                hideEmptyView();
            }
        }, 60);
    }

    public HashMap<String, Object> setPageParams(HashMap<String, Object> map) {
        map.put("pageSize", PAGE_SIZE);
        map.put("pageIndex", page);
        return map;
    }

    int itemCount;

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
        hasNextPage();
    }

    public void setArgumentsType(String key, int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(key, type);
        setArguments(bundle);
    }

    public <T> List<T> getItemData(Class<T> cls) {
        List<T> resultList = new ArrayList<>();
        for (int a = 0; a < oldItems.size(); a++) {
            T bean = (T) oldItems.get(a);
            resultList.add(bean);
        }
        return resultList;
    }

    public void setDefaultBg(){
        Drawable drawable = getAct().getDrawableBase(R.color.default_bg_gray);
        rootLl.setBackground(drawable);
        emptyLl.setBackground(drawable);
    }


}
