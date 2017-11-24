package com.dong.recyclerandlistview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dong.recyclerandlistview.R;
import com.dong.recyclerandlistview.adapter.BaseRecyclerAdapter;
import com.dong.recyclerandlistview.itemDecoration.BaseRecyclerDecoration;
import com.dong.recyclerandlistview.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dong on 2017/11/23 0023.
 */

public class FragmentRecyclerBase extends Fragment {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private BaseRecyclerAdapter mAdapter;
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_recycler_base, container, false);
        initView();
        return mRootView;
    }

    private void initView() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }

        mAdapter = new BaseRecyclerAdapter(getActivity(), mDatas);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.base_recyclerview);
        // 线性管理器，支持横向、纵向
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4)); // 网格布局
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)); // 爆瀑流式布局
        mRecyclerView.setAdapter(mAdapter);
        // 添加线性分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        // 添加自定义的分割线
        mRecyclerView.addItemDecoration(new BaseRecyclerDecoration(getActivity()));
        mAdapter.setOnItemClickLitener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showToast(getActivity(), "Click 单击 pos=" + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ToastUtils.showToast(getActivity(), "LongClick 长按 pos=" + position);
            }
        });
    }


}
