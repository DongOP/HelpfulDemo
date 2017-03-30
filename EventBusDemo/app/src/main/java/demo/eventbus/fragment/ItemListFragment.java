package demo.eventbus.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import de.greenrobot.event.EventBus;
import demo.eventbus.Model.Event.ItemListEvent;
import demo.eventbus.Model.Item;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class ItemListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 开启线程加载列表
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                    // 发布事件，在后台线程发的时间
                    EventBus.getDefault().post(new ItemListEvent(Item.ITEMS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void onEventMainThread(ItemListEvent event) {
        setListAdapter(new ArrayAdapter<Item>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                event.getItems())
        );
        Log.e("dong", "ItemListFragment...调用了..onEventMainThread: ");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        EventBus.getDefault().post(getListView().getItemAtPosition(position));
    }
}
