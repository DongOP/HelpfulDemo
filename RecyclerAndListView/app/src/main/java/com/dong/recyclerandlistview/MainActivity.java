package com.dong.recyclerandlistview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dong.recyclerandlistview.adapter.MyFragmentPagerAdapter;
import com.dong.recyclerandlistview.fragment.FragmentListView;
import com.dong.recyclerandlistview.fragment.FragmentRecyclerBase;
import com.dong.recyclerandlistview.fragment.FragmentRecyclerRefresh;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除标题
//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);

        // 添加fragment，将ViewPager与TabLayout关联
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentRecyclerBase(), "简单的RecyclerView");
        adapter.addFragment(new FragmentListView(), "ListView");
        adapter.addFragment(new FragmentRecyclerRefresh(), "RecyclerView上拉刷新、下滑加载");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}
