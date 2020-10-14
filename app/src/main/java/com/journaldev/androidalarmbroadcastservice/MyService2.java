package com.journaldev.androidalarmbroadcastservice;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyService2 extends IntentService {

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    NotificationManager notificationManager;
    public static final String ACTION_SEND_NOTIFICATION_PARAM = "ACTION_SEND_NOTIFICATION";
    public static final String EXTRA_PRAYER_NAME_PARAM = "EXTRA_PRAYER_NAME";
    public static final String EXTRA_PRAYER_TIME_PARAM = "EXTRA_PRAYER_TIME";
    private String prayerName;
    private String prayerTime;


    public MyService2() {
        super("MyService");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        System.out.println("came in service 3");

//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent alarmIntent = new Intent(this, MyBroadCastReceiver2.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_ONE_SHOT);
//        Intent alarmIntent2 = new Intent(this, MyBroadCastReceiver.class);
//        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 0, alarmIntent2, PendingIntent.FLAG_ONE_SHOT);
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, 0, 10000, pendingIntent);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP,10000, pendingIntent);
        long alarmTriggerTime = System.currentTimeMillis() + 30000;
//        long alarmTriggerTime2 = System.currentTimeMillis() + 40000;
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP,alarmTriggerTime, pendingIntent);
        alarmManager2.setExact(AlarmManager.RTC_WAKEUP,alarmTriggerTime, pendingIntent);

//        prayerName = intent.getStringExtra(EXTRA_PRAYER_NAME_PARAM);
//        prayerTime = intent.getStringExtra(EXTRA_PRAYER_TIME_PARAM);
//        setPrayerNotificationListener(this);

    }

    private void setPrayerNotificationListener(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder b = new NotificationCompat.Builder(context);

            b.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setTicker(context.getString(R.string.app_name))
                    .setContentTitle("Notification Example")
                    .setContentText("Notification Text")

                    .setContentIntent(contentIntent);
            // .setContentInfo(context.getString(R.string.next_prayer));


            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, b.build());
        } else {

            String NOTIFICATION_CHANNEL_ID1 = NOTIFICATION_CHANNEL_ID;

            NotificationChannel notificationChannel =
                    new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Salah",
                            NotificationManager.IMPORTANCE_UNSPECIFIED);
            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(context.getColor(R.color.colorPrimary));
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);


            NotificationCompat.Builder notificationBuilder = new NotificationCompat.
                    Builder(context.getApplicationContext(), NOTIFICATION_CHANNEL_ID1);

            notificationBuilder.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setTicker(context.getString(R.string.app_name))
                    .setContentTitle("Notification Example")
                    .setContentText("Notification Text");

            // .setContentInfo(context.getString(R.string.next_prayer));
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            String now = dateFormat.format(date);
            notificationManager.notify(Integer.valueOf(now.substring(3, 6)), notificationBuilder.build());
        }
    }
}
