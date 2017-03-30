package demo.eventbus.Model;

import java.util.List;

/**
 * 列表加载事件
 * <p>
 * Created by Administrator on 2017/3/30 0030.
 */

public class Event {

    public static class ItemListEvent {

        private List<Item> items;

        public ItemListEvent(List<Item> items) {
            this.items = items;
        }

        public List<Item> getItems() {
            return items;
        }
    }
}
