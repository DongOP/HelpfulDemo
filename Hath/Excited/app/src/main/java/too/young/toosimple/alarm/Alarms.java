package too.young.toosimple.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Parcel;
import android.text.format.DateFormat;

import java.util.Calendar;

import too.young.toosimple.util.Util;

import static android.os.Build.VERSION.SDK_INT;

public class Alarms {
	private final static String TAG = "Alarms";

    private final static String M12 = "h:mm aa";
    private final static String M24 = "kk:mm";
    // This extra is the raw Alarm object data. It is used in the
    // AlarmManagerService to avoid a ClassNotFoundException when filling in
    // the Intent extras.
    public static final String ALARM_RAW_DATA = "intent.extra.alarm_raw";
	private static PendingIntent mWakeupSender;
	private static PendingIntent mSleepSender;
    
    public static Alarm getAlarm(ContentResolver contentResolver, int alarmId) {
        Cursor cursor = contentResolver.query(
                ContentUris.withAppendedId(Alarm.Columns.CONTENT_URI, alarmId),
                Alarm.Columns.ALARM_QUERY_COLUMNS,
                null, null, null);
        Alarm alarm = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                alarm = new Alarm(cursor);
            }
            cursor.close();
        }
        
        return alarm;
    }
    
    private static ContentValues createContentValues(Alarm alarm) {
        ContentValues values = new ContentValues(8);

        // -1 means generate new id.
        if (alarm.id != -1) {
            values.put(Alarm.Columns._ID, alarm.id);
        }

        values.put(Alarm.Columns.ENABLED, alarm.enabled ? 1 : 0);
        values.put(Alarm.Columns.HOUR, alarm.hour);
        values.put(Alarm.Columns.MINUTES, alarm.minutes);
        values.put(Alarm.Columns.DAYS_OF_WEEK, alarm.daysOfWeek.getCoded());

        return values;
    }
    
    public static void setAlarm(Context context, Alarm alarm) {
        ContentValues values = createContentValues(alarm);
        ContentResolver resolver = context.getContentResolver();
        long rowsUpdated = resolver.update(
                ContentUris.withAppendedId(Alarm.Columns.CONTENT_URI, alarm.id),
                values, null, null);
        if (rowsUpdated < 1) {
        }

        if (alarm.id == Util.WAKEUP) {
            setNextAlertWakeup(context, alarm);
        } else if (alarm.id == Util.SLEEP) {
            setNextAlertSleep(context, alarm);
        } else {
        	throw new IllegalArgumentException("Unknown id");
		}

    }

    public static void setNextAlertWakeup(final Context context, Alarm alarm) {
        if (alarm.enabled) {
        	enableAlertWakeup(context, alarm);
        } else {
        	disableAlertWakeup(context);
        }
    }

    public static void enableAlertWakeup(Context context, final Alarm alarm) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long time = calculateAlarm(alarm);
        Intent intent = new Intent(context, ScheduleWakeupReceiver.class);
        Parcel out = Parcel.obtain();
        alarm.writeToParcel(out, 0);
        out.setDataPosition(0);
        intent.putExtra(ALARM_RAW_DATA, out.marshall());

        mWakeupSender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//		am.set(4, time, mWakeupSender);
        if (SDK_INT < Build.VERSION_CODES.KITKAT) {
//            am.set(4, time, mWakeupSender);
            am.setRepeating(4, time, 24 * 60 * 60 * 1000, mWakeupSender);// 24小时后再次执行
        } else {
            am.setExact(AlarmManager.RTC_WAKEUP, time, mWakeupSender);
        }
        
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date(time));
    }

    static void disableAlertWakeup(Context context) {
        Intent intent = new Intent(context, ScheduleWakeupReceiver.class);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		am.cancel(mWakeupSender);
    }
    
    public static void setNextAlertSleep(final Context context, Alarm alarm) {
        if (alarm.enabled) {
        	enableAlertSleep(context, alarm);
        } else {
            disableAlertSleep(context);
        }
    }

    public static void enableAlertSleep(Context context, final Alarm alarm) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long time = calculateAlarm(alarm);
        Intent intent = new Intent(context, ScheduleSleepReceiver.class);
        mSleepSender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//		am.set(AlarmManager.RTC_WAKEUP, time, mSleepSender);
        if (SDK_INT < Build.VERSION_CODES.KITKAT) {
//            am.set(AlarmManager.RTC_WAKEUP, time, mSleepSender);
            am.setRepeating(AlarmManager.RTC_WAKEUP, time, 24 * 60 * 60 * 1000, mSleepSender);
        } else {
            am.setExact(AlarmManager.RTC_WAKEUP, time, mSleepSender);
        }
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date(time));
    }

    static void disableAlertSleep(Context context) {
        Intent intent = new Intent(context, ScheduleSleepReceiver.class);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		am.cancel(mSleepSender);
    }
    
    public static void setNextAlert(final Context context) {
        Alarm alarm;
        alarm = getAlarm(context.getContentResolver(), Util.WAKEUP);
        if (alarm.enabled) {
        	enableAlertWakeup(context, alarm);
        } else {
            disableAlertWakeup(context);
        }

        alarm = getAlarm(context.getContentResolver(), Util.SLEEP);
        if (alarm.enabled) {
        	enableAlertSleep(context, alarm);
        } else {
            disableAlertSleep(context);
        }
    }
    
    private static long calculateAlarm(Alarm alarm) {
        return calculateAlarm(alarm.hour, alarm.minutes, alarm.daysOfWeek)
                .getTimeInMillis();
    }

    private static Calendar calculateAlarm(int hour, int minute, Alarm.DaysOfWeek daysOfWeek) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());

        int nowHour = c.get(Calendar.HOUR_OF_DAY);
        int nowMinute = c.get(Calendar.MINUTE);

        if (hour < nowHour  ||
            hour == nowHour && minute <= nowMinute) {
            c.add(Calendar.DAY_OF_YEAR, 1);
        }
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        int addDays = daysOfWeek.getNextAlarm(c);
        if (addDays > 0) c.add(Calendar.DAY_OF_WEEK, addDays);
        return c;
    }

    public static String formatTime(final Context context, int hour, int minute,
                                    Alarm.DaysOfWeek daysOfWeek) {
        Calendar c = calculateAlarm(hour, minute, daysOfWeek);
        return formatTime(context, c);
    }

    public static String formatTime(final Context context, Calendar c) {
        String format = get24HourMode(context) ? M24 : M12;
        return (c == null) ? "" : (String) DateFormat.format(format, c);
    }

    public static boolean get24HourMode(final Context context) {
    	return DateFormat.is24HourFormat(context);
    }
}