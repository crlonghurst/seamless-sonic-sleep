package edu.byui.cit.sleamapp.system.alarm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import edu.byui.cit.sleamapp.R;

/**
 * Author: Christian Longhurst
 * Last Update Date: 14 December 2020
 * AlarmService is the class that allows for alarms to be still active even if the alarm is closed, since that is integral to alarm types apps.
 * ToDo: integrate with AlarmReceiver
 */
public class AlarmService extends IntentService {

    public AlarmService(String name) {
        super(name);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return Service.START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent){

        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);

        Context context = getApplicationContext();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                context);

        mBuilder.setContentTitle("Notification");
        mBuilder.setContentText("Alarm is working outside the app");
        mBuilder.setSmallIcon(R.drawable.ic_launcher_background);
        mBuilder.setAutoCancel(true);

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(2, mBuilder.build());
    }
}

