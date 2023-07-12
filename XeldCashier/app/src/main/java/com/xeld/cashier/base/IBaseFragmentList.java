package com.xeld.cashier.base;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.elvishew.xlog.XLog;
import com.xeld.cashier.R;
import com.xeld.cashier.adapter.CommonAdapter;
import com.xeld.cashier.adapter.holder.BaseListVH;
import com.xeld.cashier.base.fragment.BaseFragment;
import com.trecyclerview.multitype.Items;

import java.util.ArrayList;
import java.util.List;

public abstract class IBaseFragmentList extends BaseFragment {

    SwipeRefreshLayout swipeRefreshLayout;
    public RecyclerView recycler_view;
    public LinearLayout empty_ll;
    public Items oldItems;
    public CommonAdapter commonAdapter;
    public BaseListVH convertVH;

    @Override
    public int getLayout() {
        return R.layout.fragment_ibase_list;
    }

    @Override
    public void initView() {
        recycler_view = getViewById(R.id.recycler_view);
        swipeRefreshLayout = getViewById(R.id.swipeRefreshLayout);
        empty_ll = getViewById(R.id.empty_ll);
        if (swipeRefreshLayout != null && recycler_view != null) {
            //设置刷新时动画的颜色，可以设置4个
            swipeRefreshLayout.setProgressViewOffset(false, 0, 60);
            swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,
                    android.R.color.holo_blue_light, android.R.color.holo_orange_light,
                    android.R.color.holo_green_light);
            convertVH = getAdapterConvertVH();
            oldItems = new Items();
            recycler_view.setLayoutManager(getLayoutManager());
            commonAdapter = new CommonAdapter(convertVH.getAdapterLayoutId(), oldItems) {
                @Override
                protected void convert(@NonNull BaseViewHolder helper, Object item) {
                    convertVH.convert(helper, item);
                }
            };


//        analysisAdapter.setOnItemClickViewListener(this);
            recycler_view.setAdapter(commonAdapter);

            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    setPullAction();
                    Refresh();
                }
            });

            commonAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    // onItemClick(position); //子类继承 子类处理
//                    showToast(position + "");
                    // XLog.d("position = " + position);
                }
            });

            commonAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    setPushAction();
                    onLoadMore();
                }
            });
        }

        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                Refresh();
            }
        }, 700);

    }


    public void setRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        setPullAction();
        Refresh();
    }

    public void clearAdapterData() {
        oldItems.clear();
        convertVH.setData(oldItems);
        commonAdapter.setEnableLoadMore(true);
        commonAdapter.notifyDataSetChanged();
    }


    public List<?> getData(List<?> collection, boolean page) {
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        empty_ll.setVisibility(View.GONE);

        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 200);

        if (collection == null) {
            oldItems.addAll(new ArrayList());
        }
        if (page) {
            oldItems.clear();
            oldItems.addAll(collection);
            commonAdapter.setEnableLoadMore(true);

        } else {
            oldItems.addAll(collection);
        }

        if (collection == null || collection.size() == 0) {
            commonAdapter.setEnableLoadMore(false);
        } else {
            commonAdapter.loadMoreComplete();
        }
        convertVH.setData(oldItems);
        commonAdapter.notifyDataSetChanged();
        return oldItems;
    }


    public void restState() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 200);

        commonAdapter.setEnableLoadMore(false);
    }

    public Items setData(List<?> collection) {
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        empty_ll.setVisibility(View.GONE);

        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 200);

        if (collection == null) {
            oldItems.addAll(new ArrayList<>());
        }
        if (isPullAndPush) {
            oldItems.clear();
            oldItems.addAll(collection);
            commonAdapter.setEnableLoadMore(true);
        } else {
            oldItems.addAll(collection);
        }


        if (oldItems == null || oldItems.size() == 0) {
            commonAdapter.setEnableLoadMore(false);
        } else {
            commonAdapter.loadMoreComplete();
        }

        convertVH.setData(oldItems);
        commonAdapter.notifyDataSetChanged();
        return oldItems;
    }

    public Items setSearchData(List<?> collection) {
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        empty_ll.setVisibility(View.GONE);

        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 200);

        if (collection == null) {
            oldItems.addAll(new ArrayList<>());
        }

        oldItems.clear();
        oldItems.addAll(collection);
        commonAdapter.setEnableLoadMore(true);


        if (oldItems == null || oldItems.size() == 0) {
            commonAdapter.setEnableLoadMore(false);
        } else {
            commonAdapter.loadMoreComplete();
        }

        convertVH.setData(oldItems);
        commonAdapter.notifyDataSetChanged();
        return oldItems;
    }

    public void setAdapterEmptyView() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 200);
        enableLoadMore();
        if (isPullAndPush) {
            swipeRefreshLayout.setVisibility(View.GONE);
            empty_ll.setVisibility(View.VISIBLE);
        }
    }

    public void enableLoadMore() {
        commonAdapter.setEnableLoadMore(false);
    }

    public abstract void Refresh();

    public abstract void onLoadMore();

//    public abstract void onItemClick(int position);

    public void addAdatapterHeader(View view) {
        commonAdapter.addHeaderView(view);
    }


    public CommonAdapter getCommonAdapter() {
        return commonAdapter;
    }


    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mAct);
    }

    protected RecyclerView.LayoutManager getGridLayoutManager(int count) {
        return new GridLayoutManager(mAct, count);
    }

    public Items getOldItems() {
        return oldItems;
    }


    protected abstract BaseListVH getAdapterConvertVH();

    public void setDefaultBg() {
        Drawable drawable = getAct().getDrawableBase(R.color.default_bg_gray);
        recycler_view.setBackground(drawable);
        empty_ll.setBackground(drawable);
        
    }

}
