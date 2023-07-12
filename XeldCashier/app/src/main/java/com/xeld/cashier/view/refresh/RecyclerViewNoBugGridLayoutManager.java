package com.xeld.cashier.view.refresh;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by 曹荣冠
 * on 2020/1/20.
 */
public class RecyclerViewNoBugGridLayoutManager extends GridLayoutManager {
    public RecyclerViewNoBugGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public RecyclerViewNoBugGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public RecyclerViewNoBugGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            //try catch一下
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
