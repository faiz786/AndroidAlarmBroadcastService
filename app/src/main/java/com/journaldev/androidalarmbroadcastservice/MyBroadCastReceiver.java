package com.journaldev.androidalarmbroadcastservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBroadCastReceiver extends BroadcastReceiver {

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    NotificationManager notificationManager;
    Notification notification1;

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context.getApplicationContext(), "on receive == "+intent, Toast.LENGTH_LONG).show();
//        if (intent.getAction() != null && intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
//            Toast.makeText(context.getApplicationContext(), "on receive 2 == "+intent, Toast.LENGTH_LONG).show();
//            Intent serviceIntent = new Intent(context, MyService.class);
//            context.startService(serviceIntent);
//        } else {
            Toast.makeText(context.getApplicationContext(), "Alarm Manager just ran", Toast.LENGTH_LONG).show();

            notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);



            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importance = NotificationManager. IMPORTANCE_HIGH ;
                NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
                notificationManager.createNotificationChannel(notificationChannel) ;
            }
//
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class).putExtra("importance", notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID).getImportance()).putExtra("channel_id", NOTIFICATION_CHANNEL_ID), PendingIntent.FLAG_UPDATE_CURRENT);
//            }


            NotificationCompat.Builder notification = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                    .setContentTitle("Notification Example")
                    .setContentText("Notification Text")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setPriority(Notification.PRIORITY_MAX);

            notification1 = notification.build();

            notificationManager.notify(1, notification1);

//            setPrayerNotificationListener(context);

//        }

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
