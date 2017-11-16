package dong.fragment.viewpager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Dong on 2017/11/16 0016.
 * <p>
 * 实现 懒加载的Fragment
 */

public abstract class BaseLazyFragment extends Fragment {

    protected View mRootView;
    protected Context mContext;
    // 当前Fragment是否可见
    protected boolean isVisible;
    // 父Activity已经创建完毕
    private boolean isPrepared;
    /**
     * 是否为第一次加载数据。
     *
     * true 第一次加载
     * false 不是第一次加载，此状态在来回切换Fragment时将不再加载数据
     *
     * 可用于下拉刷新时赋值true，这样即可触发刷新
     */
//    private boolean isFirstLoading = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        mRootView = inflater.inflate(setContentView(), container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isPrepared = true;
        initPrepareView();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getUserVisibleHint()) {
            setUserVisibleHint(true);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 懒加载
     */
    protected void lazyLoad(){
        if(!isPrepared || !isVisible/** || !isFirstLoading**/){
            return;
        }
        initLazyLoadData();
//        isFirstLoading = false;
    }

    /**
     * 开始懒加载
     *
     * 获取数据，刷新当前界面.
     */
    protected abstract void initLazyLoadData();

    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的 layoutId
     */
    protected abstract int setContentView();

    /**
     * 在onActivityCreated中调用的方法，可以用来进行初始化操作。
     */
    protected abstract void initPrepareView();

    /**
     * fragment 不可见时调用
     */
    protected abstract void onInvisible();
}
