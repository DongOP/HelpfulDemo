package com.dong.recyclerandlistview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dong.recyclerandlistview.R;

import java.util.List;

/**
 * Created by Dong on 2017/11/23 0023.
 * <p>
 * 简单的 RecyclerView 适配器
 * <p>
 * 1、onBindViewHolder 方法中设置单击和长按事件
 */
public class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mDataList;
    private OnItemClickListener mOnItemClickLitener;

    public BaseRecyclerAdapter(Context mContext, List<String> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    public interface OnItemClickListener {
        // item 单击
        void onItemClick(View view, int position);

        // item 长按
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_base_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mDataList.get(position));

        if (null != mOnItemClickLitener) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    // item 的单击回调
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    // item 长按回调
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);

                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
}
