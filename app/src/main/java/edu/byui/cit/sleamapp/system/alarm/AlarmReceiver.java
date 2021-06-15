package edu.byui.cit.sleamapp.system.alarm;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import edu.byui.cit.sleamapp.controller.MainActivity;
import edu.byui.cit.sleamapp.system.SystemResources;
import edu.byui.cit.sleamapp.system.audio.BackgroundSoundService;
/**
 * Author: Christian Longhurst
 * Edited: 2 November 2020
 * This class is to handle the alarm and to allow the alarms that are set to work after a restart of the device.
 */

public class AlarmReceiver extends BroadcastReceiver {
    public final String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG, "onReceive");
        Intent backgroundIntent = new Intent(context, BackgroundSoundService.class);
        context.startService(backgroundIntent);

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());


    }

}
