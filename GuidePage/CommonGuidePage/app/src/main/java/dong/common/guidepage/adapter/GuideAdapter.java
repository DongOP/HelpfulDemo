package dong.common.guidepage.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Dong on 2017/11/16 0016.
 */

public class GuideAdapter extends PagerAdapter {

    // ViewPager的数据
    private List<ImageView> mImageViewList;

    public GuideAdapter(List<ImageView> list) {
        this.mImageViewList = list;
    }

    @Override
    public int getCount() {
        return mImageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = mImageViewList.get(position);
        // 1. 向ViewPager中添加一个view对象
        container.addView(iv);
        // 2. 返回当前添加的view对象
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
