package dong.create.barcode.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

import dong.create.barcode.R;
import dong.create.barcode.util.ZxingUtil;

public class BarCodeActivity extends FragmentActivity {

    private ImageView mDisplayBarCode;
    private TextView mTextBarCode;

    private static final String DEFAULT_CODE_INFO = "123456_default_info01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bar_code);

        initDialogView();
        displayBarCode();
    }

    private void initDialogView() {
        mDisplayBarCode = (ImageView) findViewById(R.id.display_bar_code);
        mTextBarCode = (TextView) findViewById(R.id.content_bar_code);
    }

    private void displayBarCode() {
        try {
            mTextBarCode.setText(DEFAULT_CODE_INFO);
            mDisplayBarCode.setImageBitmap(ZxingUtil.CreateBarCode(DEFAULT_CODE_INFO));
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            BarCodeActivity.this.finish();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            BarCodeActivity.this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onStop() {
        super.onStop();

        finish();
    }

}
