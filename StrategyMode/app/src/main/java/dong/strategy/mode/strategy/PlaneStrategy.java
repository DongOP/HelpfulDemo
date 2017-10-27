package dong.strategy.mode.strategy;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by Dong on 2017/10/27 0027.
 */

public class PlaneStrategy implements Strategy {

    private TextView mTv;

    public PlaneStrategy(TextView mTv) {
        this.mTv = mTv;
    }

    @Override
    public void travelMode() {
        Log.e("Strategy", "出行方式为-->Plane");
        mTv.setText("PlaneStrategy 出行方式为-->Plane");
    }

}
