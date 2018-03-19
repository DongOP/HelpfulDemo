package too.young.toosimple.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;

import too.young.toosimple.util.Util;

public class ScheduleSleepReceiver extends BroadcastReceiver {
    private static final String TAG = ScheduleSleepReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
//        pm.goToSleep(SystemClock.uptimeMillis());

        // 重复执行
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Alarm mSleepAlarm = Alarms.getAlarm(context.getContentResolver(), Util.SLEEP);
            Alarms.setNextAlertSleep(context, mSleepAlarm);
            Util.loge("dong", "...............SleepReceiver :" + mSleepAlarm);
        }
    }
}
