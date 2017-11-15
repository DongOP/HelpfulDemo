package demo.fragment.telecom.fragment;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import demo.fragment.telecom.Item;

/**
 * Created by Dong on 2017/4/1 0001.
 */

public class TitleFragment extends ListFragment {

    private ArrayAdapter<Item> itemAdapter;
    private Handler mHandler = new Handler();

    OnTitleSelectedListener mTitleSeletedListener;

    public interface OnTitleSelectedListener {
        public void onTitleSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mTitleSeletedListener = (OnTitleSelectedListener) context;
        } catch (Exception e) {
//            e.printStackTrace();
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //开启线程加装列表
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                    itemAdapter = new ArrayAdapter<Item>(
                            getActivity(),
                            android.R.layout.simple_list_item_activated_1,
                            android.R.id.text1
                    );
                    itemAdapter.add(new Item("1", "Item 1"));
                    itemAdapter.add(new Item("2", "Item 2"));
                    itemAdapter.add(new Item("3", "Item 3"));
                    itemAdapter.add(new Item("4", "Item 4"));
                    itemAdapter.add(new Item("5", "Item 5"));
                    itemAdapter.add(new Item("6", "Item 6"));

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            setListAdapter(itemAdapter);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        mTitleSeletedListener.onTitleSelected(position + 1);
    }
}
