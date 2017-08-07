package eagle.test.screensaver.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import eagle.test.screensaver.R;
import eagle.test.screensaver.util.SharedPreUtil;
import eagle.test.screensaver.util.Utils;

public class FragmentScreenSaver extends Fragment implements View.OnClickListener{

    private static final String TAG = "FragmentScreenSaver";
    public static final String SCREEN_SAVER_TIME_SPF_KEY = "screenSaverTime";
    private static final String SPF_FIRST_START_KEY = "firstStatus";

    private View mFragmentScreenSaver;
    private RelativeLayout mScreenSaverRY;
    private Switch mSwitchScreenSaver;
    private LinearLayout mChildSaverLY;
    private SeekBar mSeekBarTime;
    private TextView mTvTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentScreenSaver = inflater.inflate(R.layout.fragment_screen_saver, container, false);

        initView();
        initActionView();
        updateSeekProgress();

        return mFragmentScreenSaver;
    }

    private void initView() {
        mScreenSaverRY = (RelativeLayout) mFragmentScreenSaver.findViewById(R.id.screen_saver_switch_ly);
        mSwitchScreenSaver = (Switch) mFragmentScreenSaver.findViewById(R.id.screen_saver_switch);
        mChildSaverLY = (LinearLayout) mFragmentScreenSaver.findViewById(R.id.screen_saver_child_ly);
        mSeekBarTime = (SeekBar) mFragmentScreenSaver.findViewById(R.id.set_screen_saver_time);
        mTvTime = (TextView) mFragmentScreenSaver.findViewById(R.id.screen_saver_start_time);

        mScreenSaverRY.setOnClickListener(this);


    }

    private void initActionView() {

        mSwitchScreenSaver.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveSwitchScreenStatus(isChecked);

                if (isChecked) {
                    openScreenSaver();
                    mChildSaverLY.setVisibility(View.VISIBLE);
                } else {
                    closeScreenSaver();
                    mChildSaverLY.setVisibility(View.GONE);
                }
            }
        });

        mSeekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String msg = progress + " " + getActivity().getResources().getString(R.string.show_screen_saver_time);
                mTvTime.setText(msg);

                saveScreenSaverTime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Switch默认关闭状态,屏保相应的界面隐藏,SeekBar默认进度为0
        Utils.logd(TAG, "init...getPreStatus(getActivity()): " + getPreStatus());
        if (getPreStatus()) {
            mSwitchScreenSaver.setChecked(true);
        } else {
            mSwitchScreenSaver.setChecked(false);
        }
    }

    private void updateSeekProgress() {
        int screenSaverTime = getScreenSaverTime();

        mSeekBarTime.setProgress(screenSaverTime);
    }

    private int getScreenSaverTime() {
        SharedPreferences Spf = getActivity().getSharedPreferences(SharedPreUtil.SETTING_SHAREPRE, Activity.MODE_PRIVATE);
        Utils.logd(TAG, "getScreenSaverTime: " + Spf.getInt(SCREEN_SAVER_TIME_SPF_KEY, 0));

        return Spf.getInt(SCREEN_SAVER_TIME_SPF_KEY, 0);
    }

    private void saveScreenSaverTime(int time) {
        SharedPreferences Spf = getActivity().getSharedPreferences(SharedPreUtil.SETTING_SHAREPRE, Activity.MODE_PRIVATE);
        SharedPreferences.Editor statusEditor = Spf.edit();

        statusEditor.putInt(SCREEN_SAVER_TIME_SPF_KEY, time);
        statusEditor.apply();
    }

    private void saveSwitchScreenStatus(boolean isFirst) {
        SharedPreferences Spf = getActivity().getSharedPreferences(SharedPreUtil.SETTING_SHAREPRE, Activity.MODE_PRIVATE);
        SharedPreferences.Editor statusEditor = Spf.edit();

        statusEditor.putBoolean(SPF_FIRST_START_KEY, isFirst);
        statusEditor.apply();
    }

    private boolean getPreStatus() {
        SharedPreferences Spf = getActivity().getSharedPreferences(SharedPreUtil.SETTING_SHAREPRE, Activity.MODE_PRIVATE);

        return Spf.getBoolean(SPF_FIRST_START_KEY, true);
    }

    private void closeScreenSaver() {
    }

    private void openScreenSaver() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.screen_saver_switch_ly:
                mSwitchScreenSaver.toggle();
                break;

            default:
                break;
        }
    }
}
