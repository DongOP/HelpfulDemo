package dong.tablayout.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTab;
    private ViewPager mViewPager;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mTab = (TabLayout) findViewById(R.id.tab);
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(String.format(Locale.CHINA, "第 %d 页", (i + 1)));
        }

        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), mList));
        // TabLayout 和 ViewPager 绑定
        mTab.setupWithViewPager(mViewPager);
    }

}
