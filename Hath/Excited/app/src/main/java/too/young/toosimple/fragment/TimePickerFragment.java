package too.young.toosimple.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import java.lang.reflect.Field;

import too.young.too.simple.R;
import too.young.toosimple.alarm.Alarm;
import too.young.toosimple.util.Util;
import too.young.toosimple.view.CustomTimePickerDialog;
import too.young.toosimple.view.DialogCreatable;

/**
 * Created by Dong on 2018/3/5 0005.
 */

public class TimePickerFragment extends Fragment implements CustomTimePickerDialog.OnTimeSetListener, View.OnClickListener
        , DialogCreatable{
    private View mRootView;
    private Button mAutoSleepDialog;
    private Button mAutoWakeDialog;
    private int mCurrSetIndex = -1;
    private Alarm mWakeupAlarm = null;
    private Alarm mSleepAlarm = null;
    private SettingsDialogFragment mDialogFragment;
    private int mDaysOfWeekWake;
    private int mDaysOfWeekSleep;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_timepicker, container, false);
        initView();
        getSchedulePowerInfo();
        return mRootView;
    }

    private void initView() {
        mAutoSleepDialog = (Button) mRootView.findViewById(R.id.auto_sleep_dialog);
        mAutoWakeDialog = (Button) mRootView.findViewById(R.id.auto_wake_dialog);

        mAutoSleepDialog.setOnClickListener(this);
        mAutoWakeDialog.setOnClickListener(this);

        preferences  = getActivity().getSharedPreferences(Util.PREF_NAME , Context.MODE_PRIVATE);
        editor  =  preferences.edit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.auto_sleep_dialog:
                mCurrSetIndex = Util.SLEEP;
                showDialog(Util.SLEEP);
                break;
            case R.id.auto_wake_dialog:
                mCurrSetIndex = Util.WAKEUP;
                showDialog(Util.WAKEUP);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (Util.WAKEUP == mCurrSetIndex) {
            mWakeupAlarm.hour = hourOfDay;
            mWakeupAlarm.minutes = minute;
        } else if (Util.SLEEP == mCurrSetIndex) {
            mSleepAlarm.hour = hourOfDay;
            mSleepAlarm.minutes = minute;
        }
//        updateSummary();
        saveLatestAlarm();
    }

    protected void showDialog(int dialogId) {
        mDialogFragment = new SettingsDialogFragment();
        mDialogFragment.setArguments(this, dialogId);
        mDialogFragment.show(getActivity().getSupportFragmentManager(), Integer.toString(dialogId));
    }

    public void onDialogShowing() {
    }

    @Override
    public void onDetach() {
        super.onDetach();

        try {
            if (isRemoving()) {
                if (mDialogFragment != null) {
                    mDialogFragment.dismiss();
                    mDialogFragment = null;
                }
            }
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            if(childFragmentManager!=null){
                childFragmentManager.setAccessible(true);
                childFragmentManager.set(this, null);
            }

        } catch (NoSuchFieldException e) {
            //throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            //throw new RuntimeException(e);
        }
    }

    @Override
    public Dialog onCreateDialog(int dialogId) {
        Dialog d;
        switch (dialogId) {
            case Util.WAKEUP: {
                d = new CustomTimePickerDialog(getActivity(), this,
                        mWakeupAlarm.hour,
                        mWakeupAlarm.minutes,
                        DateFormat.is24HourFormat(getActivity()));
                break;
            }
            case Util.SLEEP: {
                Util.loge("dong", "getActivity()=" + getActivity() + ", this=" + getActivity() + ", mSleepAlarm=" + mSleepAlarm);
                d = new CustomTimePickerDialog(getActivity(), this,
                        mSleepAlarm.hour,
                        mSleepAlarm.minutes,
                        DateFormat.is24HourFormat(getActivity()));
                break;
            }
            default:
                d = null;
                break;
        }
        return d;
    }

    public static class SettingsDialogFragment extends DialogFragment {
        private static final String KEY_DIALOG_ID = "key_dialog_id";
        private static final String KEY_PARENT_FRAGMENT_ID = "key_parent_fragment_id";
        private int mDialogId;
        private Fragment mParentFragment;

        private DialogInterface.OnCancelListener mOnCancelListener;
        private DialogInterface.OnDismissListener mOnDismissListener;

        public SettingsDialogFragment() {
            /* do nothing */
        }

        public void setArguments(DialogCreatable fragment, int dialogId){
            mDialogId = dialogId;
            if (!(fragment instanceof Fragment)) {
                throw new IllegalArgumentException("fragment argument must be an instance of "
                        + Fragment.class.getName());
            }
            mParentFragment = (Fragment) fragment;
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            if (mParentFragment != null) {
                outState.putInt(KEY_DIALOG_ID, mDialogId);
                outState.putInt(KEY_PARENT_FRAGMENT_ID, mParentFragment.getId());
            }
        }

        @Override
        public void onStart() {
            super.onStart();

            if (mParentFragment != null && mParentFragment instanceof TimePickerFragment) {
                ((TimePickerFragment) mParentFragment).onDialogShowing();
            }
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            if (savedInstanceState != null) {
                mDialogId = savedInstanceState.getInt(KEY_DIALOG_ID, 0);
                int mParentFragmentId = savedInstanceState.getInt(KEY_PARENT_FRAGMENT_ID, -1);
                if (mParentFragmentId > -1) {
                    mParentFragment = getActivity().getSupportFragmentManager().findFragmentById(mParentFragmentId);
                    if (!(mParentFragment instanceof DialogCreatable)) {
                        throw new IllegalArgumentException(
                                KEY_PARENT_FRAGMENT_ID + " must implement "
                                        + DialogCreatable.class.getName());
                    }
                }
                // This dialog fragment could be created from non-SettingsPreferenceFragment
                if (mParentFragment instanceof TimePickerFragment) {
                    // restore mDialogFragment in mParentFragment
                    ((TimePickerFragment) mParentFragment).mDialogFragment = this;
                }
            }
            return ((DialogCreatable) mParentFragment).onCreateDialog(mDialogId);
        }

        @Override
        public void onCancel(DialogInterface dialog) {
            super.onCancel(dialog);
            if (mOnCancelListener != null) {
                mOnCancelListener.onCancel(dialog);
            }
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            super.onDismiss(dialog);
            if (mOnDismissListener != null) {
                mOnDismissListener.onDismiss(dialog);
            }
        }

        public int getDialogId() {
            return mDialogId;
        }

        @Override
        public void onDetach() {
            super.onDetach();
            // This dialog fragment could be created from non-SettingsPreferenceFragment
            if (mParentFragment instanceof TimePickerFragment) {
                // in case the dialog is not explicitly removed by removeDialog()
                if (((TimePickerFragment) mParentFragment).mDialogFragment == this) {
                    ((TimePickerFragment) mParentFragment).mDialogFragment = null;
                }
            }
        }
    }

    private void saveLatestAlarm() {
        // 求出daysOfWeek参数
        String daysOfWeekWakeTemp = String.valueOf(mWakeupAlarm.daysOfWeek);
        mDaysOfWeekWake = Integer.valueOf(daysOfWeekWakeTemp.split("=")[1]);
        // 求出daysOfWeek参数
        String daysOfWeekSleepTemp = String.valueOf(mSleepAlarm.daysOfWeek);
        mDaysOfWeekSleep = Integer.valueOf(daysOfWeekSleepTemp.split("=")[1]);

        // 保存mWakeupAlarm信息
        editor.putInt(Util.WAKEUP_ID, mWakeupAlarm.id);
        editor.putInt(Util.WAKEUP_HOUR, mWakeupAlarm.hour);
        editor.putInt(Util.WAKEUP_MINUTES, mWakeupAlarm.minutes);
        editor.putBoolean(Util.WAKEUP_ENABLED, mWakeupAlarm.enabled);
        editor.putInt(Util.WAKEUP_DAYSOFWEEK, mDaysOfWeekWake);
        // 保存mSleepAlarm信息
        editor.putInt(Util.SLEEP_ID, mSleepAlarm.id);
        editor.putInt(Util.SLEEP_HOUR, mSleepAlarm.hour);
        editor.putInt(Util.SLEEP_MINUTES, mSleepAlarm.minutes);
        editor.putBoolean(Util.SLEEP_ENABLED, mSleepAlarm.enabled);
        editor.putInt(Util.SLEEP_DAYSOFWEEK, mDaysOfWeekSleep);

        editor.commit();
    }

    private void getSchedulePowerInfo() {
        // 当保存有闹钟数据时，重启会使用已保存的数据
        if (preferences.getInt(Util.WAKEUP_ID, -1) > 0) {
            mWakeupAlarm = new Alarm(
                    preferences.getInt(Util.WAKEUP_ID, -1),
                    preferences.getInt(Util.WAKEUP_HOUR, -1),
                    preferences.getInt(Util.WAKEUP_MINUTES, -1),
                    preferences.getBoolean(Util.WAKEUP_ENABLED, false),
                    preferences.getInt(Util.WAKEUP_DAYSOFWEEK, -1)
            );
            mSleepAlarm = new Alarm(
                    preferences.getInt(Util.SLEEP_ID, -1),
                    preferences.getInt(Util.SLEEP_HOUR, -1),
                    preferences.getInt(Util.SLEEP_MINUTES, -1),
                    preferences.getBoolean(Util.SLEEP_ENABLED, false),
                    preferences.getInt(Util.SLEEP_DAYSOFWEEK, -1)
            );
        } else {
            mWakeupAlarm = new Alarm(Util.WAKEUP,
                    0,
                    0,
                    false,
                    0
            );
            mSleepAlarm = new Alarm(Util.SLEEP,
                    0,
                    0,
                    false,
                    0
            );
        }
        Util.loge("dong", "mWakeupAlarm=" + mWakeupAlarm + ", mSleepAlarm=" + mSleepAlarm);
    }
}
