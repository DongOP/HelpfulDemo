package dong.strategy.mode.strategy;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by Dong on 2017/10/27 0027.
 */

public class TrainStrategy implements Strategy {

    private TextView mTv;

    public TrainStrategy(TextView mTv) {
        this.mTv = mTv;
    }

    @Override
    public void travelMode() {
        Log.e("Strategy", "出行方式为-->Train");
        mTv.setText("TrainStrategy 出行方式为-->Train");
    }

}
