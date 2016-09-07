package dong.keyboardtest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * 检测键盘 弹出与隐藏
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private Context mContext;
    private EditText editTextKB;
    private Button buttonKB;

    private LinearLayout layoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        initView();
    }

    private void initView() {

        editTextKB = (EditText) findViewById(R.id.et_kb);
        buttonKB = (Button) findViewById(R.id.btn_kb);

        buttonKB.setOnClickListener(this);

        layoutMain = (LinearLayout) findViewById(R.id.layout_main);
        layoutMain.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
    }

    @Override
    public void onClick(View view) {

        editTextKB.requestFocus();
        closeSoftKeyBoard(editTextKB, mContext);
    }

    //点击关闭，反之则弹出输入框
    private void closeSoftKeyBoard(EditText mEditText, Context context) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    // 软键盘的显示状态
    private boolean ShowKeyboard;
    private ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {

        @Override
        public void onGlobalLayout() {
            // 应用可以显示的区域。此处包括应用占用的区域，包括标题栏不包括状态栏
            Rect r = new Rect();
            layoutMain.getWindowVisibleDisplayFrame(r);
            // 键盘最小高度
            int minKeyboardHeight = 150;
            // 获取状态栏高度
            int statusBarHeight = getStatusBarHeight(mContext);
            // 屏幕高度,不含虚拟按键的高度
            int screenHeight = layoutMain.getRootView().getHeight();
            // 在不显示软键盘时，height等于状态栏的高度
            int height = screenHeight - (r.bottom - r.top);


            if (ShowKeyboard) {
                // 如果软键盘是弹出的状态，并且height小于等于状态栏高度，
                // 说明这时软键盘已经收起
                if (height - statusBarHeight < minKeyboardHeight) {
                    ShowKeyboard = false;
                    Toast.makeText(mContext,"键盘隐藏了",Toast.LENGTH_SHORT).show();
                }
            } else {
                // 如果软键盘是收起的状态，并且height大于状态栏高度，
                // 说明这时软键盘已经弹出
                if (height - statusBarHeight > minKeyboardHeight) {
                    ShowKeyboard = true;
                    Toast.makeText(mContext,"键盘显示了",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
