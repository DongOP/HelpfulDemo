package com.dong.recyclerandlistview.fragment;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.dong.recyclerandlistview.R;
import com.dong.recyclerandlistview.adapter.ListViewBaseAdapter;
import com.dong.recyclerandlistview.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dong on 2017/11/23 0023.
 */

public class FragmentListView extends Fragment implements View.OnClickListener {

    private View mRootView;
    private Button mBtnArrayAdapter, mBtnSimpleAdapter, mBtnSimpleCursorAdapter, mBtnCustomAdapter;
    private ListView mListView;
    private ArrayAdapter mArrayAdapter;
    // listView 的item数据
    private int[] mIconDefault = {
            R.mipmap.set_list_binding_normal, R.mipmap.set_date_time_normal,
            R.mipmap.set_list_wifi_normal, R.mipmap.set_memory_normal,
            R.mipmap.set_list_set_normal, R.mipmap.set_list_info_normal };
    private String[] mTextArray;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_list, container, false);

        initView();
        return mRootView;
    }

    private void initView() {
        mBtnArrayAdapter = (Button) mRootView.findViewById(R.id.list_ArrayAdapter);
        mBtnSimpleAdapter = (Button) mRootView.findViewById(R.id.list_SimpleAdapter);
        mBtnSimpleCursorAdapter = (Button) mRootView.findViewById(R.id.list_SimpleCursorAdapter);
        mBtnCustomAdapter = (Button) mRootView.findViewById(R.id.list_custom_Adapter);
        mListView = (ListView) mRootView.findViewById(R.id.id_listView);

        mBtnArrayAdapter.setOnClickListener(this);
        mBtnSimpleAdapter.setOnClickListener(this);
        mBtnSimpleCursorAdapter.setOnClickListener(this);
        mBtnCustomAdapter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_ArrayAdapter:
                updateArrayAdapter();
                break;
            case R.id.list_SimpleAdapter:
                updateSimpleAdapter();
                break;
            case R.id.list_SimpleCursorAdapter:
                updateSimpleCursorAdapter();
                break;
            case R.id.list_custom_Adapter:
                updateBaseAdapter();
                break;
            default:
                break;
        }
    }

    private void updateBaseAdapter() {
        if (null == getActivity()) {
            return;
        }
        mTextArray = getActivity().getResources().getStringArray(R.array.settings_menu_titles);

        ListViewBaseAdapter baseAdapter = new ListViewBaseAdapter(getActivity(), mIconDefault, mTextArray);
        mListView.setAdapter(baseAdapter);
        mListView.setOnItemClickListener(mOnItemClickLis);
        baseAdapter.notifyDataSetChanged();
    }

    private AdapterView.OnItemClickListener mOnItemClickLis = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ToastUtils.showToast(getActivity(), "ListView OnItemClickListener 单击position=" + position);
        }
    };

    private void updateSimpleCursorAdapter() {
        if (null == getActivity()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ToastUtils.showToast(getActivity(), "未兼容6.0以上机器，权限获取问题");
            return;
        }
        // 读取联系人
        Cursor cursor = getActivity().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getActivity(),
                android.R.layout.simple_expandable_list_item_2,
                cursor,
                new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                new int[]{android.R.id.text1}
        );
        mListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void updateSimpleAdapter() {
        if (null == getActivity()) {
            return;
        }
        //分别定义通讯录中的用户名、电话、地区等信息
        String[] info_Names = {"史珍香", "赖月京", "秦寿生", "刘产", "扬伟", "范剑"};
        String[] info_Phones = {"13844445144", "13844444444", "13444445144", "13544445144", "13644445144", "13744445144"};
        String[] info_Regions = {"火星", "水星", "木星", "月球", "美国", "未知地区"};
        // 每一条数据对应通讯录中的一个联系人信息
        ArrayList<Map<String, Object>> mInfos = new ArrayList<Map<String, Object>>();
        //添加联系人信息
        for (int i = 0; i < info_Names.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("img", R.mipmap.ic_launcher_round);
            item.put("name", info_Names[i]);
            item.put("phone", info_Phones[i]);
            item.put("region", info_Regions[i]);
            mInfos.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(),
                mInfos,
                R.layout.item_simpler_listview_adapter,
                new String[]{"img", "name", "phone", "region"},
                new int[]{R.id.info_img, R.id.info_name, R.id.info_phone, R.id.info_region}
        );
        mListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void updateArrayAdapter() {
        if (null == getActivity()) {
            return;
        }
        // item显示的内容
        String[] items = {
                "1、ArrayAdapter数据 1",
                "2、ArrayAdapter数据 2",
                "3、ArrayAdapter数据 3",
                "4、ArrayAdapter数据 4",
                "5、ArrayAdapter数据 5"
        };
        mArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        mListView.setAdapter(mArrayAdapter);
        mArrayAdapter.notifyDataSetChanged();
    }

}
