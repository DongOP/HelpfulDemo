package dong.factory.mode.cloth;

import android.util.Log;

/**
 * Created by Dong on 2017/10/27 0027.
 */

public class SevenPointShirt implements Cloth {

    @Override
    public void getStyle() {
        Log.e("dong", "Style=SevenPoint");
    }

    @Override
    public void getName() {
        Log.e("dong", "Name=Seven Point Shirt");
    }
}
