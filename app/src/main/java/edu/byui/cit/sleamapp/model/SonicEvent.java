package edu.byui.cit.sleamapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import edu.byui.cit.sleamapp.model.database.AppDatabase;
import edu.byui.cit.sleamapp.model.database.SonicEventDAO;

/**
 * Author: Joel Jossie
 * Created: 12 November 2020
 *
 */
@Entity
public class SonicEvent {

    @PrimaryKey(autoGenerate = true)
    private long sonicEventID;

    private long sleepID;

    @Ignore
    private Playlist playlist; // TODO figure out the database relationship between Playlists and SonicEvents

    private boolean playOverPrevious; // This attribute might not be that useful
    private LocalTime startTime;

    @Ignore
    public SonicEvent(LocalTime startTime, boolean playOverPrevious) {
        this.playOverPrevious = playOverPrevious;
        this.startTime = startTime;
        this.playlist = new Playlist();
    }

    public SonicEvent() {
        playlist = new Playlist();
        startTime = LocalTime.now();
    }

    public long getSonicEventID() {
        return sonicEventID;
    }

    public void setSonicEventID(long sonicEventID) {
        this.sonicEventID = sonicEventID;
    }

    public long getSleepID() {
        return sleepID;
    }

    public void setSleepID(long sleepID) {
        this.sleepID = sleepID;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public boolean isPlayOverPrevious() {
        return playOverPrevious;
    }

    public void setPlayOverPrevious(boolean playOverPrevious) {
        this.playOverPrevious = playOverPrevious;
    }

    public String formatStartTime(){
        String pattern = "hh:mma";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String output =  startTime.format(formatter);
        if (output.charAt(0) == '0') // omit the leading zero if there is one
            return output.substring(1);
        return output;
    }

    public void save(AppDatabase db, long sleepID) {
        SonicEventDAO dao = db.getSonicEventDAO();
        if (dao.getByKey(this.sonicEventID) != null) { // If this SonicEvent exists in the database
            dao.update(this); // update it
        } else {
            this.sleepID = sleepID;
            dao.insert(this);
        }
        playlist.save(db);
    }

// This method can be implemented someday, but we're skipping it for now.
// It would be to enable a "play for " button that determines length of time for a sonic rather than explicit start times.
/*    public String formatTimeSince(SonicEvent previousEvent){
        previousEvent.getStartTime().compareTo(startTime)
    }*/

}
