package dong.fragment.viewpager.fragment;

import android.widget.TextView;

import dong.fragment.viewpager.R;

import static dong.fragment.viewpager.R.id.txt_content;

/**
 * Created by Dong on 2017/11/16 0016.
 */

public class RemindFragment extends BaseLazyFragment {

    TextView mContentTv;

    public RemindFragment() {
    }

    @Override
    protected void initLazyLoadData() {
        mContentTv.setText("第一个Fragment. 懒加载数据");
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initPrepareView() {
        mContentTv = (TextView) mRootView.findViewById(txt_content);
    }

    @Override
    protected void onInvisible() {

    }
}
