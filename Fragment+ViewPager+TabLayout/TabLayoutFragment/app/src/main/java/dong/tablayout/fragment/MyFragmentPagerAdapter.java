package dong.tablayout.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Dong on 2017/11/17 0017.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<String> mList;

    public MyFragmentPagerAdapter(FragmentManager fm, List<String> mList) {
        super(fm);
        this.mList = mList;
    }

    @Override
    public Fragment getItem(int position) {
        return BlankFragment.newInstance(mList.get(position));
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    /**
     * 当 TabLayout与 ViewPager绑定的时候能够绑定Tab标签的标题
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);
    }
}
