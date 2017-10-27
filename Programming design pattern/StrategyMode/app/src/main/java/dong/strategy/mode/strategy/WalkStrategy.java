package dong.strategy.mode.strategy;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by Dong on 2017/10/27 0027.
 */

public class WalkStrategy implements Strategy {

    private TextView mTv;

    public WalkStrategy(TextView mTv) {
        this.mTv = mTv;
    }

    @Override
    public void travelMode() {
        Log.e("Strategy", "出行方式为-->Walk");
        mTv.setText("WalkStrategy 出行方式为-->Walk");
    }

}
