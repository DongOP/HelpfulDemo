package dong.common.guidepage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import dong.common.guidepage.adapter.GuideAdapter;
import dong.common.guidepage.util.CommonUtils;
import dong.common.guidepage.util.SharePFUtils;

public class GuideActivity extends Activity implements View.OnClickListener, ViewPager.OnPageChangeListener{

    private static final String TAG = "Dong";
    // ViewPager的数据
    private List<ImageView> mImageViewList;
    private ViewPager mViewPager;
    private Button mStartExperience;
    private Context mContext;
    // 向导点的组布局
    private LinearLayout llPointGroup;
    // 选中的点view对象
    private View mSelectPointView;
    // 点之间的宽度
    private int pWidth;
    // 向导点的宽高，需和 R.id.select_point 的宽高一致
    private static final int POINT_PX_WIDTH_HEIGHT = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去标题, 需要在setContentView方法之前调用
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        checkIsFirstLaunch();
    }

    private void checkIsFirstLaunch() {
        if (SharePFUtils.getIsFirstLaunch(GuideActivity.this)) {
            setContentView(R.layout.activity_guide);
            initView();
        } else {
            openActivity(MainActivity.class);
        }
    }

    private void initView() {
        mContext = GuideActivity.this;
        mImageViewList = new ArrayList<ImageView>();
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        mStartExperience = (Button) findViewById(R.id.btn_guide_start_experience);
        llPointGroup = (LinearLayout) findViewById(R.id.ll_guide_point_group);
        mSelectPointView = findViewById(R.id.select_point);

        initPic();

        GuideAdapter adapter = new GuideAdapter(mImageViewList);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
        mStartExperience.setOnClickListener(this);
    }

    // 初始化向导图片
    private void initPic() {
        int[] imageResIds = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
        // 图片
        ImageView iv = null;
        // 向导点
        View view;
        LayoutParams params;
        for (int i = 0; i < imageResIds.length; i++) {
            iv = new ImageView(this);
            iv.setBackgroundResource(imageResIds[i]);
            mImageViewList.add(iv);
            // 根据图片的个数, 每循环一次向LinearLayout中添加一个点
            view = new View(this);
            view.setBackgroundResource(R.drawable.point_normal);
            // 设置参数 单位px
            params = new LayoutParams(POINT_PX_WIDTH_HEIGHT, POINT_PX_WIDTH_HEIGHT);
            if (i != 0) {
                params.leftMargin = POINT_PX_WIDTH_HEIGHT;
            }

            view.setLayoutParams(params);// 添加参数
            llPointGroup.addView(view);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_guide_start_experience:
                openActivity(MainActivity.class);
                CommonUtils.showToast(mContext, "开始体验APP");
                break;
            default:
                break;
        }
    }

    // ViewPager.OnPageChangeListener start
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {// 当页面正在滚动时被回调
        // positionOffsetPixels 偏移像素
        //获取两个点间的距离,获取一次即可
        if(pWidth==0) {
            pWidth = llPointGroup.getChildAt(1).getLeft()
                    - llPointGroup.getChildAt(0).getLeft();
        }

        // 获取点要移动的距离
        int leftMargin = (int) (pWidth * (position + positionOffset));
        // 给红点设置参数
        RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) mSelectPointView
                .getLayoutParams();
        params.leftMargin = leftMargin;
        mSelectPointView.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {
        // 当选中最后一张向导图片时,显示按钮
        if (position == (mImageViewList.size() -1)) {
            mStartExperience.setVisibility(View.VISIBLE);
        } else {
            mStartExperience.setVisibility(View.GONE);
        }

        CommonUtils.logd(TAG, "onPageSelected position=" + position + ", mImageViewList.size() -1=" + (mImageViewList.size() -1));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // 当页面滚动状态改变时被回调
    }
    // ViewPager.OnPageChangeListener end

    public void openActivity(Class s){
        Intent intent = new Intent(this,s);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// Intent.FLAG_ACTIVITY_NO_ANIMATION |

        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharePFUtils.setNotFirstLaunch(GuideActivity.this);
    }
}
