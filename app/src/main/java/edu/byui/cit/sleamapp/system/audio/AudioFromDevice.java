package edu.byui.cit.sleamapp.system.audio;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.byui.cit.sleamapp.R;

/**
 * Author Tyler Israelsen
 * updated: 23 November 2020
 * AllAudioFromDevice class with getAllAudioFromDevice method
 * method reads all the audio files on the device
 * requesting and handling necessary android storage permissions
 */

public class AudioFromDevice{

    // Permission request code
    private final static int REQUEST_CODE_ASK_PERMISSION = 1;

    // Permission that need to be explicitly requested from end user.
    private static final String[] REQUIRED_SDK_PERMISSION = new String[] {Manifest.permission.READ_EXTERNAL_STORAGE};


    /**
     * Checks the dynamically-controlled permission and request missing permission from end user.
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.M) // Indicates that this method
    public static void checkPermission(Context activity) {
        final List<String> missingPermission = new ArrayList<>();
        // check all required dynamic permissions
        for (final String permission : REQUIRED_SDK_PERMISSION) {
            final int result = ContextCompat.checkSelfPermission(activity, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermission.add(permission);
            }
        }
        if (!missingPermission.isEmpty()) {
            // request all missing permissions
            //noinspection ToArrayCallWithZeroLengthArrayArgument // I'm honestly not sure why there's a warning but that's probably because i don't know that this next line is doing exactly
            final String[] permission = missingPermission.toArray(new String[missingPermission.size()]);
            ActivityCompat.requestPermissions((Activity)activity, permission, REQUEST_CODE_ASK_PERMISSION);
        }
        else {
            final int[] grantResults = new int[REQUIRED_SDK_PERMISSION.length];
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
            Activity parentAct = (Activity) activity;
            parentAct.onRequestPermissionsResult(REQUEST_CODE_ASK_PERMISSION, REQUIRED_SDK_PERMISSION, grantResults);
        }
    }


    /**
     * Method to read all the audio files
     * @param context The context from which this method is being called, typically an activity
     * @return a List of all audio files on the device in the form of AudioModel objects. (we might change that)
     */
    public static List<AudioModel> getAllAudioFromDevice(final Context context) {

        Log.d("AudioFileAccess", "Starting getAllAudioFromDevice()");

        final List<AudioModel> audioList = new ArrayList<>();
        // To interact with the media store abstraction, use a ContentResolver

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.AudioColumns.DATA,
                MediaStore.Audio.AudioColumns.TITLE,
                MediaStore.Audio.ArtistColumns.ARTIST,
                MediaStore.Audio.AudioColumns.ALBUM,};
        // Using a cursor to retrieve all audio files on device
        Cursor c = context.getContentResolver().query(uri, projection, null, null, null);

        if (c != null) {
            while (c.moveToNext()) {
                // Create a model object
                AudioModel audioFileAccess = new AudioModel();

                // Retrieve path
                String path = c.getString(0);
                // Retrieve name
                String name = c.getString(1);
                // Retrieve artist name
                String artist = c.getString(2);
                // Retrieve album name
                String album = c.getString(3);

                audioFileAccess.setaName(name);
                audioFileAccess.setaArtist(artist);
                audioFileAccess.setaAlbum(album);
                audioFileAccess.setaPath(path);

                Log.e("Name: " + name, " Artist: " + artist);
                Log.e("Path: " + path, " Album: " + album);

                audioList.add(audioFileAccess);
            }
            c.close();
        }
        return audioList;
    }
}
