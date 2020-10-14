//package com.journaldev.androidalarmbroadcastservice;
//
//import android.app.PendingIntent;
//
//public class PrayersActivity extends BaseActivity{
//
//   private AlarmManager managerFajr, managerShrouq, managerZuhr, managerAsr, managerMaghrib, managerEshaa;
//   private PendingIntent pendingIntentFajr, pendingIntentShrouq, pendingIntentZuhr, pendingIntentAsr,
//            pendingIntentMaghrib, pendingIntentEshaa;
//
// @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        startAlarm();
//}
//    public void startAlarm() {
//        Time timeFajr, timeShrouq, timeZuhr, timeAsr, timeMaghrib, timeEshaa;
//        Intent prayerIntentFajr, prayerIntentShrouq, prayerIntentZuhr, prayerIntentAsr, prayerIntentMaghrib, prayerIntentEshaa;
//        Calendar calendarFajr, calendarShrouq, calendarZuhr, calendarAsr, calendarMaghrib, calendarEshaa;
//        String[] prayersName = {getString(R.string.fajr), getString(R.string.shrouq), getString(R.string.zohr),
//                getString(R.string.asr), getString(R.string.maghrb), getString(R.string.esha)};
//                managerFajr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                timeFajr = PrayerTimesHelper.getFajrPrayerTime(this, PrayerTimesHelper.getCurrentSimpleDate());
//
//                calendarFajr = Calendar.getInstance();
//                calendarFajr.set(Calendar.HOUR_OF_DAY, timeFajr.getHour());
//                calendarFajr.set(Calendar.MINUTE, timeFajr.getMinute());
//
//                if (calendarFajr.before(Calendar.getInstance())) {
//                    calendarFajr.add(Calendar.DATE, 1);
//                }
//
//                if (notificationsPreferences.getFajrNotification()) {
//
//                    new PrayerAlarmManger().setAlarm(this, calendarFajr.getTimeInMillis(), prayersName[0], AppUtils.convertTimeAMandPM("" +
//                            PrayerTimesHelper.getFajrPrayerTime(this, PrayerTimesHelper.getCurrentSimpleDate())), 100);
//                }
//
//                managerShrouq = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                timeShrouq = PrayerTimesHelper.getShrouqPrayerTime(this, PrayerTimesHelper.getCurrentSimpleDate());
//
//                calendarShrouq = Calendar.getInstance();
//                calendarShrouq.set(Calendar.HOUR_OF_DAY, timeShrouq.getHour());
//                calendarShrouq.set(Calendar.MINUTE, timeShrouq.getMinute());
//
//                if (calendarShrouq.before(Calendar.getInstance())) {
//                    calendarShrouq.add(Calendar.DATE, 1);
//                }
//
//                new PrayerAlarmManger().setAlarm(this, calendarShrouq.getTimeInMillis(), prayersName[1], AppUtils.convertTimeAMandPM("" +
//                        PrayerTimesHelper.getShrouqPrayerTime(this, PrayerTimesHelper.getCurrentSimpleDate())), 101);
//
//
//                managerZuhr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                timeZuhr = PrayerTimesHelper.getZuhrPrayerTime(this, PrayerTimesHelper.getCurrentSimpleDate());
//
//                calendarZuhr = Calendar.getInstance();
//                calendarZuhr.set(Calendar.HOUR_OF_DAY, timeZuhr.getHour());
//                calendarZuhr.set(Calendar.MINUTE, timeZuhr.getMinute());
//
//                if (calendarZuhr.before(Calendar.getInstance())) {
//                    calendarZuhr.add(Calendar.DATE, 1);
//                }
//
//                new PrayerAlarmManger().setAlarm(this, calendarZuhr.getTimeInMillis(), prayersName[2], AppUtils.convertTimeAMandPM("" +
//                        PrayerTimesHelper.getZuhrPrayerTime(this, PrayerTimesHelper.getCurrentSimpleDate())), 102);
//
//
//                managerAsr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                timeAsr = PrayerTimesHelper.getAsrPrayerTime(this, PrayerTimesHelper.getCurrentSimpleDate());
//
//                calendarAsr = Calendar.getInstance();
//                calendarAsr.set(Calendar.HOUR_OF_DAY, timeAsr.getHour());
//                calendarAsr.set(Calendar.MINUTE, timeAsr.getMinute());
//
//                if (calendarAsr.before(Calendar.getInstance())) {
//                    calendarAsr.add(Calendar.DATE, 1);
//                }
//
//                new PrayerAlarmManger().setAlarm(this, calendarAsr.getTimeInMillis(), prayersName[3], AppUtils.convertTimeAMandPM("" +
//                        PrayerTimesHelper.getAsrPrayerTime(this, PrayerTimesHelper.getCurrentSimpleDate())), 103);
//
//                managerMaghrib = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                timeMaghrib = PrayerTimesHelper.geMaghribhrouqPrayerTime(this, PrayerTimesHelper.getCurrentSimpleDate());
//
//                calendarMaghrib = Calendar.getInstance();
//                calendarMaghrib.set(Calendar.HOUR_OF_DAY, timeMaghrib.getHour());
//                calendarMaghrib.set(Calendar.MINUTE, timeMaghrib.getMinute());
//
//                if (calendarMaghrib.before(Calendar.getInstance())) {
//                    calendarMaghrib.add(Calendar.DATE, 1);
//                }
//
//                new PrayerAlarmManger().setAlarm(this, calendarMaghrib.getTimeInMillis(), prayersName[4], AppUtils.convertTimeAMandPM("" +
//                        PrayerTimesHelper.geMaghribhrouqPrayerTime(this, PrayerTimesHelper.getCurrentSimpleDate())), 104);
//
//
//                managerEshaa = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                timeEshaa = PrayerTimesHelper.getEshaaPrayerTime(this, PrayerTimesHelper.getCurrentSimpleDate());
//
//                calendarEshaa = Calendar.getInstance();
//                calendarEshaa.set(Calendar.HOUR_OF_DAY, timeEshaa.getHour());
//                calendarEshaa.set(Calendar.MINUTE, timeEshaa.getMinute());
//
//                if (calendarEshaa.before(Calendar.getInstance())) {
//                    calendarEshaa.add(Calendar.DATE, 1);
//                }
//
//                new PrayerAlarmManger().setAlarm(this, calendarEshaa.getTimeInMillis(), prayersName[5], AppUtils.convertTimeAMandPM("" +
//                        PrayerTimesHelper.getEshaaPrayerTime(this, PrayerTimesHelper.getCurrentSimpleDate())), 105);
//
//
//    }
//}