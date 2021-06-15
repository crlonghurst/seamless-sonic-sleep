package edu.byui.cit.sleamapp.system;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Calendar;

import edu.byui.cit.sleamapp.controller.MainActivity;
import edu.byui.cit.sleamapp.system.alarm.AlarmReceiver;
import edu.byui.cit.sleamapp.system.alarm.AlarmService;
import edu.byui.cit.sleamapp.system.audio.BackgroundSoundService;

import static androidx.core.content.ContextCompat.getSystemService;

/**
* Author: Christian Longhurst
* Edited: 11 December 2020
* Class SystemResources will have all the code to interact with the android devices resources.
 * This class is the intermediary between alarms, audio managers, and eventually file storage.
 *
 * Implemented: alarms, and audio manager
 * Neither one of those are integrated into each other
 * Todo: integrate audio manager into playing when the alarms fires, also file storage for user chosen audio files to be used on alarms
 **/
public class SystemResources{
    private static String TAG = "SystemResources";
    private static final String CHANNELID = "Notification";

    /**
     *  The SystemResources class implements the singleton design pattern with the attribute of singleton checking if it's null or not
     *  in the getInstance function, this decides if the construct is needing to be called or not.
     */
    private static SystemResources singleton = null;
    public static SystemResources getInstance(Context context) {
        if (singleton == null) {
            singleton = new SystemResources(context);
        }
        return singleton;
    }

    private AlarmManager alarmManager;
    private Calendar calendar;
    private AudioAttributes mPlaybackAttributes;
    private Intent intent;
    private Context context;
        private SystemResources(Context passedContext){
            context = passedContext;
                mPlaybackAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();


            calendar = Calendar.getInstance();

        }

    public void setAlarm(int hour, int min){
        try {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, min);
            calendar.set(Calendar.SECOND, 0);

            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(context, AlarmReceiver.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0);
            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DATE, 1);
            }

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            /*Intent i = new Intent(context, AlarmService.class);
            i.putExtra("NOTIFICATION_TYPE", "ALARM");
            context.startService(i);*/

        }catch(Exception e){
            Log.e(TAG, e.toString());
        }

    }


    @TargetApi(Build.VERSION_CODES.O)
    public void createNotificationChannels() {
            Log.i(TAG, "Create Notification Channel function");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNELID,
                    "Notification Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("This is Channel 1");
            NotificationManager manager = getSystemService(context,NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }


}
