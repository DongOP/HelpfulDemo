package com.dong.recyclerandlistview.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Dong on 2017/11/24 0024.
 */

public abstract class OnEndRecyclerScrollListener extends RecyclerView.OnScrollListener {

    // 标记是否正在向上滑动
    private boolean isSlidingUpward = false;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        // 不滑动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            int lastItemPos = manager.findLastCompletelyVisibleItemPosition();
            int itemCount = manager.getItemCount();
            // 判读是否滑到了最后一个item，并且是向上滑动
            if (lastItemPos == (itemCount - 1) && isSlidingUpward) {
                // 加载更多
                onLoadMore();
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        // 大于0表示正在向上滑动，小于0表示停止或者想下滑动
        isSlidingUpward = dy > 0;
    }

    public abstract void onLoadMore();
}
