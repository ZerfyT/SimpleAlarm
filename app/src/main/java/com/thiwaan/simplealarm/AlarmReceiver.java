package com.thiwaan.simplealarm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "AlarmChannel";
    private static final String STOP_ACTION = "STOP_ALARM";

    private Ringtone ringtone;
    private NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Alarm Ringing...", Toast.LENGTH_SHORT).show();

//        String action = intent.getAction();
//        if (action != null && action.equals(STOP_ACTION)) {
//            stopRingtone();
//            return;
//        }

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//        if (alarmSound == null) {
        // Default alarm sound is not available, use another sound
//            alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        }
//        if (alarmSound != null) {
        ringtone = RingtoneManager.getRingtone(context, alarmSound);
//            if (ringtone != null) {
        ringtone.play();
        showNotification(context);
//            }
//        }
    }

    private void stopRingtone() {
        if (ringtone != null && ringtone.isPlaying()) {
            ringtone.stop();
        }
        cancelNotification();
    }

    private void showNotification(Context context) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Alarm Channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Alarm")
                .setContentText("Alarm is ringing!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
//                .setOngoing(true);

        // Create the stop action for the notification
//        Intent stopIntent = new Intent(context, AlarmReceiver.class);
//        stopIntent.setAction(STOP_ACTION);
//        PendingIntent stopPendingIntent = PendingIntent.getBroadcast(context, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "Stop", stopPendingIntent);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void cancelNotification() {
        if (notificationManager != null) {
            notificationManager.cancel(NOTIFICATION_ID);
        }
    }
}
