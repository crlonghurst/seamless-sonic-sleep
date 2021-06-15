package edu.byui.cit.sleamapp.model;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;

import edu.byui.cit.sleamapp.model.database.AppDatabase;
import edu.byui.cit.sleamapp.model.database.SoundSourceDAO;

/**
 * Author: Joel Jossie
 * Created: 12 November 2020
 * Last Edited: 28 November, Joel Jossie
 *
 */
@Entity(foreignKeys = @ForeignKey(entity=Playlist.class, parentColumns = "playlistID", childColumns = "playlistID", onDelete = ForeignKey.CASCADE))
public class SoundSource {

    @PrimaryKey(autoGenerate = true)
    private long soundID;

    @ColumnInfo(index = true)
    private long playlistID;

    String name;
    String path;
    String type;

    public SoundSource(String name, String path, String type) {
        this.name = name;
        this.path = path;
        this.type = type;
    }

    public long getSoundID() {
        return soundID;
    }

    public long getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(long playlistID) {
        this.playlistID = playlistID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSoundID(long soundID) {
        this.soundID = soundID;
    }

    public void save(AppDatabase db) {
        // TODO figure out the playlist-song relationship
        SoundSourceDAO dao = db.getSoundSourceDAO();
        if (dao.getByKey(soundID) != null){
            dao.update(this); // This might be pointless. Wait actually never mind. People move their song files all the time.
            // This way if they move the song file they might be able to keep it in all its playlists even if it moves.
        } else {
            dao.insert(this);
        }
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoundSource s = (SoundSource) o;
        return soundID == s.getSoundID() &&
                playlistID == s.getPlaylistID() &&
                Objects.equals(name, s.getName()) &&
                Objects.equals(path, s.getPath()) &&
                Objects.equals(type, s.getType());
    }
}
