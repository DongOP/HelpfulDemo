package dong.fragment.viewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import dong.fragment.viewpager.MainActivity;
import dong.fragment.viewpager.fragment.MessageFragment;
import dong.fragment.viewpager.fragment.PersonalFragment;
import dong.fragment.viewpager.fragment.RemindFragment;
import dong.fragment.viewpager.fragment.SettingFragment;
import dong.fragment.viewpager.util.CommonUtils;

/**
 * Created by Dong on 2017/11/16 0016.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4; // 全部菜单Tab数量
    private RemindFragment myFragment1 = null;
    private MessageFragment myFragment2 = null;
    private PersonalFragment myFragment3 = null;
    private SettingFragment myFragment4 = null;
    private static final String TAG = "Dong";

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new RemindFragment();
        myFragment2 = new MessageFragment();
        myFragment3 = new PersonalFragment();
        myFragment4 = new SettingFragment();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.PAGE_ONE:
                fragment = myFragment1;
                break;
            case MainActivity.PAGE_TWO:
                fragment = myFragment2;
                break;
            case MainActivity.PAGE_THREE:
                fragment = myFragment3;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = myFragment4;
                break;
            default:
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        CommonUtils.loge(TAG, "instantiateItem 初始化界面 page=" + (position + 1));
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        CommonUtils.loge(TAG, "destroyItem 销毁的界面 page=" + (position + 1));
        super.destroyItem(container, position, object);
    }
}
