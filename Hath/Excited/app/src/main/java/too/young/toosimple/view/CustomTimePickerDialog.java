package too.young.toosimple.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import too.young.too.simple.R;


public class CustomTimePickerDialog extends Dialog implements
        OnClickListener, OnTimeChangedListener {

	public interface OnTimeSetListener {
		void onTimeSet(TimePicker view, int hourOfDay, int minute);
	}

	private static final String HOUR = "hour";
	private static final String MINUTE = "minute";
	private static final String IS_24_HOUR = "is24hour";

	private final TimePicker mTimePicker;
	private final Button mCancel;
	private final Button mDone;
	private final OnTimeSetListener mCallback;

	int mInitialHourOfDay;
	int mInitialMinute;
	boolean mIs24HourView;

	public CustomTimePickerDialog(Context context, OnTimeSetListener callBack,
                                  int hourOfDay, int minute, boolean is24HourView) {
		this(context, 0, callBack, hourOfDay, minute, is24HourView);
	}

	public CustomTimePickerDialog(Context context, int theme,
                                  OnTimeSetListener callBack, int hourOfDay, int minute,
                                  boolean is24HourView) {
		super(context, R.style.CustomDateTimePickerDialogTheme);

		setContentView(R.layout.custom_time_picker_dialog);
		mCallback = callBack;
		mInitialHourOfDay = hourOfDay;
		mInitialMinute = minute;
		mIs24HourView = is24HourView;
		
		mCancel = (Button) findViewById(R.id.cancel);
		mDone = (Button) findViewById(R.id.done);
		mTimePicker = (TimePicker) findViewById(R.id.timePicker);
		mCancel.setOnClickListener(this);
		mDone.setOnClickListener(this);

		// initialize state
		mTimePicker.setIs24HourView(mIs24HourView);
		mTimePicker.setCurrentHour(mInitialHourOfDay);
		mTimePicker.setCurrentMinute(mInitialMinute);
		mTimePicker.setOnTimeChangedListener(this);
		// 点击dialog空白处，dialog消失
		this.setCanceledOnTouchOutside(true);
	}

	public void updateTime(int hourOfDay, int minutOfHour) {
		mTimePicker.setCurrentHour(hourOfDay);
		mTimePicker.setCurrentMinute(minutOfHour);
	}

	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		/* do nothing */
	}

	private void tryNotifyTimeSet() {
		if (mCallback != null) {
			mTimePicker.clearFocus();
			mCallback.onTimeSet(mTimePicker, mTimePicker.getCurrentHour(),
					mTimePicker.getCurrentMinute());
		}
	}

	@Override
	public void onClick(View view) {
		dismiss();
		if(view.getId() == R.id.done){
			tryNotifyTimeSet();
		}
	}

	@Override
	public Bundle onSaveInstanceState() {
		Bundle state = super.onSaveInstanceState();
		state.putInt(HOUR, mTimePicker.getCurrentHour());
		state.putInt(MINUTE, mTimePicker.getCurrentMinute());
		state.putBoolean(IS_24_HOUR, mTimePicker.is24HourView());
		return state;
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		int hour = savedInstanceState.getInt(HOUR);
		int minute = savedInstanceState.getInt(MINUTE);
		mTimePicker.setIs24HourView(savedInstanceState.getBoolean(IS_24_HOUR));
		mTimePicker.setCurrentHour(hour);
		mTimePicker.setCurrentMinute(minute);
	}
	
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER
				|| event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
				&& !(mCancel.isFocused()||mDone.isFocused())
				) { 
				mDone.requestFocus();
				return true;	
			} 
		return super.dispatchKeyEvent(event);
	}
	
}
