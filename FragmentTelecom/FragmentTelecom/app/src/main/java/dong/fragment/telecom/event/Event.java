package dong.fragment.telecom.event;

/**
 * Created by Dong on 2017/11/15 0015.
 * <p>
 * EventBus的 event
 */

public class Event {

    public static class MessageEvent {
        private String msg;

        public MessageEvent(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "MessageEvent{" +
                    "msg='" + msg + '\'' +
                    '}';
        }
    }
}
