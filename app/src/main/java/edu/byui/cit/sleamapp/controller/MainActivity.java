package edu.byui.cit.sleamapp.controller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import edu.byui.cit.sleamapp.R;
import edu.byui.cit.sleamapp.system.SystemResources;
import edu.byui.cit.sleamapp.system.audio.AudioFromDevice;


/**
 * Author: Christian Longhurst
 * Created: 27 October
 * Last Edited: 19 November by Joel Jossie
 * The Class for the Main Activity.
 */
public class MainActivity extends AppCompatActivity {
    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            SystemResources.getInstance(this).createNotificationChannels();
            SystemResources.getInstance(this).setAlarm(9, 46);

            if (savedInstanceState == null){
                // Create the main fragment and place it as the first fragment in this activity
                Fragment firstFrag = new SchedulesFrag();
                FragmentTransaction trans =
                        getSupportFragmentManager().beginTransaction();
                trans.add(R.id.fragContainer, firstFrag);
                trans.commit();
            }
        }
        catch(Exception e){
            Log.e(TAG, e.toString());
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AudioFromDevice.checkPermission(this);
        }
        AudioFromDevice.getAllAudioFromDevice(this);

    }


    /**
     * The On pause function is called when the user backs out of the app to the home screen, this allows for the user to close
     * their screen and going back to their home screen.
     */
    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * The stopPlayback function will stop playback of the audio, and won't allow it to continue.
     */
    @Override
    public void onVisibleBehindCanceled(){
        super.onVisibleBehindCanceled();
    }

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1; // A constant for some request code for permissions or something idk

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            for (int index = permissions.length - 1; index >= 0; --index) {
                if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                    // exit the app if permission is not granted
                    Toast.makeText(this, "Required permission '" + permissions[index] + "' not granted, exiting",
                            Toast.LENGTH_LONG).show();
                    finish();
                    return;
                } else {
                    Toast.makeText(this, "Thanks for granting " + permissions[index] + " permissions ", Toast.LENGTH_LONG).show();
                }
            }
            // all permissions were granted
        }
    }
}