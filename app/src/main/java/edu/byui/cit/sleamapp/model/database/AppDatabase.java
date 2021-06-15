package edu.byui.cit.sleamapp.model.database;

import android.content.Context;


import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Database;
import androidx.room.TypeConverters;

import edu.byui.cit.sleamapp.model.Playlist;
import edu.byui.cit.sleamapp.model.Schedule;
import edu.byui.cit.sleamapp.model.SleepSchedule;
import edu.byui.cit.sleamapp.model.SonicEvent;
import edu.byui.cit.sleamapp.model.SoundSource;


/**
 * Author: Joel Jossie, Christian Longhurst
 * Last Edited: 30 November, Joel Jossie
 * Defines basic parameters for the Room Database.
 */
@Database(entities = {SleepSchedule.class, Playlist.class,  SonicEvent.class, SoundSource.class},
version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase singleton;

    public static AppDatabase getInstance(Context appCtx){
        if (singleton == null){
            singleton = Room.databaseBuilder(appCtx, AppDatabase.class, "SleepSchedulesDB").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return singleton;
    }

    public abstract SleepScheduleDAO getSleepScheduleDAO();
    public abstract PlaylistDAO getPlaylistDAO();
    public abstract SonicEventDAO getSonicEventDAO();
    public abstract SoundSourceDAO getSoundSourceDAO();
}
