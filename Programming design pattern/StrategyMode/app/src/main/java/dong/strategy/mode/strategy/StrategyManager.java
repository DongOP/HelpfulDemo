package dong.strategy.mode.strategy;

/**
 * Strategy管理类
 *
 * Created by Dong on 2017/10/27 0027.
 */

public class StrategyManager {

    private Strategy mStrategy;
    private static final StrategyManager mStrategyManager = new StrategyManager();

    public StrategyManager() {
    }

    public static StrategyManager getInstance() {
        return mStrategyManager;
    }

    public Strategy getStrategy() {
        return mStrategy;
    }

    public void setStrategy(Strategy mStrategy) {
        this.mStrategy = mStrategy;
    }

    public void travel() {
        if (mStrategy != null) {
            mStrategy.travelMode();
        }
    }
}
