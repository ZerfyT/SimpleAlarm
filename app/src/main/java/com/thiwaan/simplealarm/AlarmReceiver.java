package com.thiwaan.simplealarm;

import static androidx.core.content.ContextCompat.startForegroundService;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.legacy.content.WakefulBroadcastReceiver;

public class AlarmReceiver extends WakefulBroadcastReceiver {

    private static final int JOB_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(context, new Intent(context, AlarmService.class));
        } else {
            startWakefulService(context, new Intent(context, AlarmService.class));
        }

//        scheduleAlarmJob(context);
    }

//    private void scheduleAlarmJob(Context context) {
//        ComponentName serviceComponent = new ComponentName(context, AlarmJobService.class);
//        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceComponent);
//
//        // Set additional JobInfo options if needed
//        // For example, set network requirements or job execution constraints
//
//        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        if (jobScheduler != null) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                jobScheduler.schedule(builder.setMinimumLatency(0).build());
//            } else {
//                jobScheduler.schedule(builder.setOverrideDeadline(0).build());
//            }
//        }
//    }
}
