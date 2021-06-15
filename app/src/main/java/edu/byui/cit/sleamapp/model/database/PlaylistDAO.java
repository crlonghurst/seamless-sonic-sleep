package edu.byui.cit.sleamapp.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.byui.cit.sleamapp.model.Playlist;
import edu.byui.cit.sleamapp.model.SonicEvent;

@Dao
public abstract class PlaylistDAO {
    @Query("SELECT COUNT(*) FROM Playlist")
    public abstract long count();

    @Query("SELECT * FROM Playlist")
    public abstract List<Playlist> getAll();

    @Query("SELECT * FROM Playlist WHERE `playlistID` = :id")
    public abstract Playlist getByKey(long id);

    public long insert(Playlist playlist) {
        long id = insertH(playlist);
        playlist.setPlaylistID(id);
        return id;
    }

    @Insert
    abstract long insertH(Playlist playlist);

    @Update
    public abstract void update(Playlist playlist);

    @Delete
    public abstract void deletePlaylist(Playlist playlist);

    @Query("DELETE FROM Playlist")
    public abstract void deleteAll();
}
