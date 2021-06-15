package edu.byui.cit.sleamapp.model;

import android.content.Context;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import edu.byui.cit.sleamapp.model.database.AppDatabase;
import edu.byui.cit.sleamapp.model.database.PlaylistDAO;
import edu.byui.cit.sleamapp.model.database.SleepScheduleDAO;
import edu.byui.cit.sleamapp.model.database.SonicEventDAO;
import edu.byui.cit.sleamapp.model.database.SoundSourceDAO;

/**
 * Author: Joel Jossie
 * Created: 12 November 2020
 */

@Entity
public class SleepSchedule {
    @PrimaryKey(autoGenerate = true)
    private long sleepID;
    private String name;

    @Ignore
    private SonicEvent fallAsleepEvent;
    @Ignore
    private SonicEvent stayAsleepEvent;
    @Ignore
    private SonicEvent wakeUpEvent;
    @Embedded
    private Schedule schedule;


    public SleepSchedule() {
        // These are all hard-coded default values that should probably be stored somewhere else.
        name = "Weekday Routine";
        fallAsleepEvent = new SonicEvent(LocalTime.of(22, 30), false);
        stayAsleepEvent = new SonicEvent(LocalTime.of(23, 15), false);
        wakeUpEvent = new SonicEvent(LocalTime.of(6, 30), true);
        schedule = new Schedule();
        schedule.setDay(1, true);
        schedule.setDay(2, true);
        schedule.setDay(3, true);
        schedule.setDay(4, true);
        schedule.setDay(5, true);
    }


    public long getSleepID() {
        return sleepID;
    }

    public void setSleepID(long sleepID) {
        this.sleepID = sleepID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public SonicEvent getFallAsleepEvent() {
        return fallAsleepEvent;
    }

    public void setFallAsleepEvent(SonicEvent fallAsleepEvent) {
        this.fallAsleepEvent = fallAsleepEvent;
    }

    public SonicEvent getStayAsleepEvent() {
        return stayAsleepEvent;
    }

    public void setStayAsleepEvent(SonicEvent stayAsleepEvent) {
        this.stayAsleepEvent = stayAsleepEvent;
    }

    public SonicEvent getWakeUpEvent() {
        return wakeUpEvent;
    }

    public void setWakeUpEvent(SonicEvent wakeUpEvent) {
        this.wakeUpEvent = wakeUpEvent;
    }

    public void save(Context ctx){
        AppDatabase db = AppDatabase.getInstance(ctx);
        SleepScheduleDAO ssDao = db.getSleepScheduleDAO();

        // Sleep Schedule
        boolean exists = ssDao.getByKey(this.sleepID) != null;
        if (exists){ // If this sleep schedule is already in the database
            ssDao.update(this); // Update it
        } else {
            ssDao.insert(this); // Otherwise, insert it
        }
        fallAsleepEvent.save(db, this.sleepID);
        stayAsleepEvent.save(db, this.sleepID);
        wakeUpEvent.save(db, this.sleepID);
    }

}
