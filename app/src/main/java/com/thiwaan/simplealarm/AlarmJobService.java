package com.thiwaan.simplealarm;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

public class AlarmJobService extends JobService {

    private Ringtone ringtone;

    @Override
    public boolean onStartJob(JobParameters params) {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), alarmSound);
        ringtone.play();

        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.setAction("ACTION_ALARM");
        sendBroadcast(intent);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        if (ringtone != null && ringtone.isPlaying()) {
            ringtone.stop();
        }

        return false;
    }
}
