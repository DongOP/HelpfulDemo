package dong.fragment.viewpager;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import dong.fragment.viewpager.adapter.MainFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    // ViewPager滑动完毕
    public static final int PAGE_SCROLL_COMPLETE = 2;

    private RadioGroup rg_tab_bar;
    private RadioButton rb_one;
    private RadioButton rb_two;
    private RadioButton rb_three;
    private RadioButton rb_four;
    private ViewPager mViewPager;

    private MainFragmentPagerAdapter mMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除标题
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mMainAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());

        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_one = (RadioButton) findViewById(R.id.rb_1);
        rb_two = (RadioButton) findViewById(R.id.rb_2);
        rb_three = (RadioButton) findViewById(R.id.rb_3);
        rb_four = (RadioButton) findViewById(R.id.rb_4);

        // TabBar（RadioGroup）整体设置CheckedChange监听事件
        rg_tab_bar.setOnCheckedChangeListener(this);
        // ViewPager初始化并添加PageChange监听事件，默认选中第一个 index = 0
        mViewPager = (ViewPager) findViewById(R.id.main_viewPager);
        mViewPager.setAdapter(mMainAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(this);

        // 初始选中第一个
        rb_one.setChecked(true);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_1:
                mViewPager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_2:
                mViewPager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_3:
                mViewPager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.rb_4:
                mViewPager.setCurrentItem(PAGE_FOUR);
                break;
        }
    }

    // ViewPager.OnPageChangeListener start
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (PAGE_SCROLL_COMPLETE == state) {
            switch (mViewPager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_one.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_two.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_three.setChecked(true);
                    break;
                case PAGE_FOUR:
                    rb_four.setChecked(true);
                    break;
                default:
                    break;
            }
        }
    }
    // ViewPager.OnPageChangeListener end

}
