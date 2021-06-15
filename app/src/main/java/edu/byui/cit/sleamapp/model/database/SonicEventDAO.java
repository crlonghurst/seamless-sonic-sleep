package edu.byui.cit.sleamapp.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.byui.cit.sleamapp.model.SonicEvent;
import edu.byui.cit.sleamapp.model.SoundSource;

@Dao
public abstract class SonicEventDAO {
    @Query("SELECT * FROM SonicEvent")
    public abstract List<SonicEvent> getAll();

    @Query("SELECT COUNT(*) FROM SoundSource")
    public abstract long count();

    @Query("SELECT * FROM SonicEvent WHERE `sonicEventID` = :id")
    public abstract SonicEvent getByKey(long id);

    public long insert (SonicEvent sonicEvent){
        long id = insertH(sonicEvent);
        sonicEvent.setSonicEventID(id);
        return id;
    }

    @Insert
    abstract long insertH(SonicEvent sonicEvent);

    @Update
    public abstract void update(SonicEvent sonicEvent);

    @Delete
    public abstract void deleteSoundSource(SonicEvent sonicEvent);
}
