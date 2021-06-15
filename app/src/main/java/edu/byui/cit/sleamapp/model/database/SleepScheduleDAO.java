package edu.byui.cit.sleamapp.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.byui.cit.sleamapp.model.SleepSchedule;

@Dao
public abstract class SleepScheduleDAO {
    @Query("SELECT * FROM SleepSchedule")
    public abstract List<SleepSchedule> getAll();

    @Query("SELECT COUNT(*) FROM SleepSchedule")
    public abstract long count();

    @Query("SELECT * FROM SleepSchedule WHERE `sleepID` = :id")
    public abstract SleepSchedule getByKey(long id);

    public long insert (SleepSchedule sleepschedule){
        long id = insertH(sleepschedule);
        sleepschedule.setSleepID(id);
        return id;
    }

    @Insert
    abstract long insertH(SleepSchedule sleepSchedule);

    @Update
    public abstract void update(SleepSchedule sleepSchedule);

    @Delete
    public abstract void deleteSoundSource(SleepSchedule sleepSchedule);

}
