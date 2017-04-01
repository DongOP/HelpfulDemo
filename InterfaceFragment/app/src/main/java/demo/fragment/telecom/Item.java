package demo.fragment.telecom;

/**
 * Created by Dong on 2017/4/1 0001.
 */

public class Item {

    public String id;
    public String content;

    public Item(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
