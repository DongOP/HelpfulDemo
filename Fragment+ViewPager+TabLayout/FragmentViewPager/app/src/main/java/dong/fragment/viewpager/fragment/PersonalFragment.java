package dong.fragment.viewpager.fragment;

import android.widget.TextView;

import dong.fragment.viewpager.R;

/**
 * Created by Dong on 2017/11/16 0016.
 */

public class PersonalFragment extends BaseLazyFragment {

    TextView mContentTv;

    public PersonalFragment () {
    }

    @Override
    protected void initLazyLoadData() {
        mContentTv.setText("第三个Fragment. 懒加载数据");
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initPrepareView() {
        mContentTv = (TextView) mRootView.findViewById(R.id.txt_content);
    }

    @Override
    protected void onInvisible() {

    }
}
