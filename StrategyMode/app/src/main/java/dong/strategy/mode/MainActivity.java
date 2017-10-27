package dong.strategy.mode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import dong.strategy.mode.strategy.PlaneStrategy;
import dong.strategy.mode.strategy.StrategyManager;
import dong.strategy.mode.strategy.TrainStrategy;
import dong.strategy.mode.strategy.WalkStrategy;

/**
 * 策略模式：
 *
 * 定义了一系列的算法，并将每一个算法封装起来，而且使它们还可以相互替换。策略模式让算法独立于使用它的客户而独立变换
 */
public class MainActivity extends AppCompatActivity implements OnClickListener{

    private Button mBTNWalk;
    private Button mBTNTrain;
    private Button mBTNPlane;
    private TextView mTVTravelMode;
    private StrategyManager mStrategyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBTNWalk = (Button) findViewById(R.id.btn_walk);
        mBTNTrain = (Button) findViewById(R.id.btn_train);
        mBTNPlane = (Button) findViewById(R.id.btn_plane);

        mTVTravelMode = (TextView) findViewById(R.id.tv_travel);

        mStrategyManager = StrategyManager.getInstance();

        mBTNWalk.setOnClickListener(this);
        mBTNTrain.setOnClickListener(this);
        mBTNPlane.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_walk:
                mStrategyManager.setStrategy(new WalkStrategy(mTVTravelMode));
                mStrategyManager.travel();
                break;
            case R.id.btn_train:
                mStrategyManager.setStrategy(new TrainStrategy(mTVTravelMode));
                mStrategyManager.travel();
                break;
            case R.id.btn_plane:
                mStrategyManager.setStrategy(new PlaneStrategy(mTVTravelMode));
                mStrategyManager.travel();
                break;
            default:
                break;
        }

    }

}
