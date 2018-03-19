package too.young.toosimple.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;

import too.young.toosimple.util.Util;

public class ScheduleWakeupReceiver extends BroadcastReceiver {
    private static final String TAG = ScheduleWakeupReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
    	PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
//        pm.wakeUp(SystemClock.uptimeMillis());

        // 重复执行
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Alarm mWakeUpAlarm = Alarms.getAlarm(context.getContentResolver(), Util.WAKEUP);
            Alarms.setNextAlertWakeup(context, mWakeUpAlarm);
            Util.loge("dong", ".........WakeupReceiver....... :" + mWakeUpAlarm);
        }
    }
}