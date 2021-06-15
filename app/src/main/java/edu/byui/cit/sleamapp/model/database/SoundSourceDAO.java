package edu.byui.cit.sleamapp.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.byui.cit.sleamapp.model.SoundSource;

@Dao
public abstract class SoundSourceDAO {
    @Query("SELECT COUNT(*) FROM SoundSource")
    public abstract long count();

    @Query("SELECT * FROM SoundSource")
    public abstract List<SoundSource> getAll();

    @Query("SELECT * FROM SoundSource WHERE `soundID` = :id")
    public abstract SoundSource getByKey(long id);

    public long insert (SoundSource soundSource){
        long id = insertH(soundSource);
        soundSource.setSoundID(id);
        return id;
    }

    @Insert
    abstract long insertH(SoundSource soundSource);

    @Update
    public abstract void update(SoundSource soundSource);

    @Delete
    public abstract void deleteSoundSource(SoundSource soundSource);

    @Query("DELETE FROM SoundSource")
    public abstract void deleteAll();
}
