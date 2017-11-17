package dong.common.guidepage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import dong.common.guidepage.util.CommonUtils;
import dong.common.guidepage.util.SharePFUtils;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private Button mClearSP;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mClearSP = (Button) findViewById(R.id.clear_sharep);

        mClearSP.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.clear_sharep) {
            SharePFUtils.setIsFirstLaunch(MainActivity.this);
            CommonUtils.showToast(MainActivity.this, "已初始化为第一次启动当前应用!");
        }
    }
}
