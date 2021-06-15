package edu.byui.cit.sleamapp.system.audio;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;
import androidx.annotation.Nullable;

import edu.byui.cit.sleamapp.R;


public class BackgroundSoundService extends Service {
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.starwars);
        //mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(2000, 2000);
    }
    public int onStartCommand(Intent intent, int flags, int startId) {

        mediaPlayer.start();
        Toast.makeText(getApplicationContext(), "Playing Bohemian Rashpody in the Background",    Toast.LENGTH_SHORT).show();
        return startId;
    }
    public void onResume() {
        mediaPlayer.start();
    }
    public void onPause(){
        mediaPlayer.pause();
    }
    public void onStart(Intent intent, int startId) {

    }
    @Override
    public void onDestroy() {
        mediaPlayer.pause();
//        mediaPlayer.stop();
//        mediaPlayer.release();
    }
    @Override
    public void onLowMemory() {
    }
}

