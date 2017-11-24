package com.dong.recyclerandlistview.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dong.recyclerandlistview.R;
import com.dong.recyclerandlistview.adapter.LoadMoreAdapter;
import com.dong.recyclerandlistview.listener.OnEndRecyclerScrollListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Dong on 2017/11/24 0024.
 */

public class FragmentRecyclerRefresh extends Fragment {

    private View mRootView;
    private RecyclerView mRecyclerView;
    private LoadMoreAdapter mLoadMoreAdapter;
    private List<String> mDatalist;
    private Handler mUpdateHandler = new Handler();
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_recycler_refresh, container, false);

        initView();
        return mRootView;
    }

    private void initView() {
        mDatalist = new ArrayList<String>();
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_refresh_layout);
        // 模拟获取数据
        getData();
        mLoadMoreAdapter = new LoadMoreAdapter(mDatalist);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mLoadMoreAdapter);

        // 设置刷新控件颜色
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4DB6AC"));
        // 设置下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 刷新数据
                mDatalist.clear();
                getData();
                mLoadMoreAdapter.notifyDataSetChanged();

                // 延时1s关闭下拉刷新
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (null != mSwipeRefreshLayout && mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, 1000);
            }
        });

        // 设置加载更多监听
        mRecyclerView.addOnScrollListener(new OnEndRecyclerScrollListener() {
            @Override
            public void onLoadMore() {
                mLoadMoreAdapter.setLoadState(mLoadMoreAdapter.LOADING);

                if (mDatalist.size() < 52) {
                    // 模拟获取网络数据延时，延时1s
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            mUpdateHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    getData();
                                    mLoadMoreAdapter.setLoadState(mLoadMoreAdapter.LOADING_COMPLETE);
                                }
                            });
                        }
                    }, 1000);
                } else {
                    // 显示加载到底的提示
                    mLoadMoreAdapter.setLoadState(mLoadMoreAdapter.LOADING_END);
                }
            }
        });
    }

    private void getData() {
        char letter = 'A';
        for (int i = 0; i < 26; i++) {
            mDatalist.add(String.valueOf(letter));
            letter++;
        }
    }

}
