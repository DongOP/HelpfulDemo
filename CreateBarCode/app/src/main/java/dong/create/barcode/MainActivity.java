package dong.create.barcode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import dong.create.barcode.view.BarCodeActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BarCode";
    private Button mShowBarCode; // show_bar_code
    private Context mContext;
    // 点击次数
    private int mClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mContext = MainActivity.this;
        mShowBarCode = (Button) findViewById(R.id.btn_bar_code);

        mShowBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBarCodeDialog();
                // 点击6次出现条形码dialog
//                clickBtn();
            }
        });
    }

    private void clickBtn() {
        mClickTime++;

        // 6次一个循环
        if (6 == mClickTime) {
            showBarCodeDialog();
            mClickTime = 0;
        }

        Log.d(TAG, "点击按钮的次数=" + mClickTime);
    }

    private void showBarCodeDialog() {
        Intent intent = new Intent(mContext, BarCodeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mClickTime = 0;
    }
}
