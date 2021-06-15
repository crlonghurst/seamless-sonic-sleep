package edu.byui.cit.sleamapp.model;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

import edu.byui.cit.sleamapp.model.database.AppDatabase;
import edu.byui.cit.sleamapp.model.database.PlaylistDAO;

/**
 * Author: Joel Jossie
 * Created: 12 November 2020
 * Last Edited: 28 November, Joel Jossie
 */
@Entity
public class Playlist {

    @PrimaryKey(autoGenerate = true)
    private long playlistID;

    @Ignore
    private ArrayList<SoundSource> sounds;

    private boolean shuffle;
    private boolean crossfade;
    private int crossfadeLength;

    public Playlist(){
        sounds = null;
        crossfade = false;
        crossfadeLength = 0;
        sounds = new ArrayList<>();
    }

    @Ignore
    public Playlist(boolean shuffle, boolean crossfade, int crossfadeLength) {
        this.shuffle = shuffle;
        this.crossfade = crossfade;
        this.crossfadeLength = crossfadeLength;
        sounds = new ArrayList<>();
    }

    public long getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(long playlistID) {
        this.playlistID = playlistID;
    }

    public void addSound(SoundSource sound){
        sounds.add(sound);
    }
    public void removeSound(SoundSource sound){
        sounds.remove(sound);
    }
    public int getSoundIndex(SoundSource sound){
        return sounds.indexOf(sound);
    }
    public SoundSource getSound(int index){
        return sounds.get(index);
    }
    public ArrayList<SoundSource> getSounds(){
        return sounds;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

    public boolean isCrossfade() {
        return crossfade;
    }

    public void setCrossfade(boolean crossfade) {
        this.crossfade = crossfade;
    }

    public int getCrossfadeLength() {
        return crossfadeLength;
    }

    public void setCrossfadeLength(int crossfadeLength) {
        this.crossfadeLength = crossfadeLength;
    }

    public int getPlaylistLength() {
        return sounds.size();
    }

    public void save(AppDatabase db){
        PlaylistDAO dao = db.getPlaylistDAO();
        if (dao.getByKey(playlistID) != null) { // if this playlist exists in the database
            dao.update(this);
        } else {
            // Foreign key does not need to be set because this class doesn't need a foreign key. many-to-one, not one-to-many.
            dao.insert(this);
            for (SoundSource s : sounds){ // Save all the SoundSources in this playlist
                s.save(db);
            }
        }
    }

    /**
     * This is used for comparing objects in JUnit tests. The return statement might need to be updated if/when more data fields are added.
     * @param o the object being compared, likely another Playlist
     * @return if they contain identical data fields (right now just playlistID, crossfade, and crossfadeLength)
     */
    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist p = (Playlist) o;
        return playlistID == p.getPlaylistID() &&
                crossfade == p.isCrossfade() &&
                crossfadeLength == p.getCrossfadeLength();
    }
}
