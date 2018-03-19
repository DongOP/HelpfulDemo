package too.young.toosimple;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import too.young.too.simple.R;
import too.young.toosimple.fragment.MyListFragment;
import too.young.toosimple.fragment.TimePickerFragment;

public class ToolBarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private NavigationView mNagigationView;
    private Toolbar mToolbar;
    private Button mBtn;
    private static Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        initTranslucentStatus();
    }

    private void initTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                //将侧边栏顶部延伸至status bar
                mDrawerLayout.setFitsSystemWindows(true);
                //将主页面顶部延伸至status bar
                mDrawerLayout.setClipToPadding(false);
            }
        }
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerlayout);
        mNagigationView = (NavigationView) findViewById(R.id.id_navigationview);
//        mNagigationView.inflateHeaderView(R.layout.header_nav);
//        mNagigationView.inflateMenu(R.menu.menu_nav);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mBtn = (Button) findViewById(R.id.goToActionBar);

        // 设置导航菜单宽度为屏幕一半
        ViewGroup.LayoutParams params = mNagigationView.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels * 3 / 5;
        mNagigationView.setLayoutParams(params);
        // 添加item点击事件
        mNagigationView.setNavigationItemSelectedListener(this);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        // 设置Toggle、addDrawerListener
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
//        mBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ToolBarActivity.this, ActionBarActivity.class));
//            }
//        });
        // 添加fragment，将ViewPager与TabLayout关联
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_menu_categories:
                showToast(this, "钦点");
                // 退出 NavigationView
//                mDrawerLayout.closeDrawers();
                break;
//            case R.id.nav_menu_setting:
//                showToast(this, "闷声发大财");
//                break;
//            case R.id.nav_menu_feedback:
//                showToast(this, "苟生苟死苟残喘");
//                break;
//            case R.id.nav_menu_loginOut:
//                showToast(this, "亦祸亦福亦趋之");
//                break;
//            case R.id.action_qq:
//                showToast(this, "利家利国利天下");
//                break;
//            case R.id.action_weixin:
//                showToast(this, "泽民泽世泽众生");
//                break;
        }
        item.setChecked(true);

        return true;
    }

    public static void showToast(Context context, String resid) {
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(resid);
        mToast.show();
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new TimePickerFragment(), "TimePicker");
        adapter.addFragment(new MyListFragment(), "我一秒");
        adapter.addFragment(new MyListFragment(), "万里长城永不倒");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
