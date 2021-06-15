package edu.byui.cit.sleamapp.system.alarm;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;

import edu.byui.cit.sleamapp.R;

/**
 * Author: Christian Longhurst
 * Last Update Date: 14 December 2020
 * Notification Helper is used when AlarmReceiver receives an alarm. Notification helper creates a drop down notification.
 * ToDo: make notifications dynamic to allow for user notifications, have text be name of sleep schedule or specifically what the user wants.
 * ToDo: Make sure notification allows sound to fire as well.
 */
public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    private NotificationManager mManager;
    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }
    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
    public NotificationCompat.Builder getChannelNotification() {
//        Uri sound = Uri.parse("android.resource://edu.byui.cit.sleamapp/"+R.raw.starwars);
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Alarm!")
                .setContentText("Your AlarmManager is working.")
                .setSmallIcon(R.drawable.ic_notification)
                .setSound(Uri.parse("android.resource://edu.byui.cit.sleamapp/"+R.raw.starwars));
    }
}
