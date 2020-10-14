package com.journaldev.androidalarmbroadcastservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class PrayerAlarmManger {

    private PendingIntent pendingIntent = null;

    public void setAlarm(Context context, Long alarmTime, String prayerName, String prayerTime, int requestCode) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, MyService.class);
        intent.setAction(MyService.ACTION_SEND_NOTIFICATION_PARAM);
        intent.putExtra(MyService.EXTRA_PRAYER_NAME_PARAM, prayerName);
        intent.putExtra(MyService.EXTRA_PRAYER_TIME_PARAM, prayerTime);

        pendingIntent = PendingIntent.getService(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);
        }
    }
}