package com.dong.recyclerandlistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.recyclerandlistview.R;

/**
 * Created by Dong on 2017/11/24 0024.
 */

public class ListViewBaseAdapter extends BaseAdapter {

    private int[] mIconDefault;
    private String[] mTextArray;
    private LayoutInflater mInflater;

    public ListViewBaseAdapter(Context context, int[] mIconDefault, String[] mTextArray) {
        this.mIconDefault = mIconDefault;
        this.mTextArray = mTextArray;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mIconDefault.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null != convertView && null != convertView.getTag()) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.item_listview_baseadapter, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        ImageView menuIcon = holder.getMenuIcon();
        TextView menuText = holder.getMenuText();
        menuIcon.setImageResource(mIconDefault[position]);
        menuText.setText(mTextArray[position]);

        return convertView;
    }

    public final class ViewHolder {
        public View baseView;
        public ImageView menuIcon;
        public TextView menuText;

        public ViewHolder(View view) {
            baseView = view;
        }

        public ImageView getMenuIcon() {
            if (menuIcon == null) {
                ImageView imageView = (ImageView) baseView.findViewById(R.id.list_icon);
                menuIcon = imageView;
            }
            return menuIcon;
        }

        public TextView getMenuText() {
            if (menuText == null) {
                TextView textView = (TextView) baseView.findViewById(R.id.list_text);
                menuText = textView;
            }
            return menuText;
        }
    }

}
