package immersive.transparent.statusbar;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

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
        mBtn = (Button) findViewById(R.id.goToActionBar);

        // 设置导航菜单宽度为屏幕一半
        ViewGroup.LayoutParams params = mNagigationView.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels / 2;
        mNagigationView.setLayoutParams(params);
//        mBtn.setText("");
        mNagigationView.setNavigationItemSelectedListener(this);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ToolBarActivity.this, ActionBarActivity.class));
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_menu_categories:
                showToast(this, "首页信息");
                // 退出 NavigationView
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_menu_setting:
                showToast(this, "设置");
                break;
            case R.id.nav_menu_feedback:
                showToast(this, "关于");
                break;
            case R.id.nav_menu_loginOut:
                showToast(this, "退出");
                break;
            case R.id.action_qq:
                showToast(this, "QQ...");
                break;
            case R.id.action_weixin:
                showToast(this, "WeChat...");
                break;
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
}
