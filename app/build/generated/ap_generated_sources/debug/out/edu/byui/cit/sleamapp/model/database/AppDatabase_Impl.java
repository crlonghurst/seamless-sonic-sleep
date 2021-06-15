package edu.byui.cit.sleamapp.model.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile SleepScheduleDAO _sleepScheduleDAO;

  private volatile PlaylistDAO _playlistDAO;

  private volatile SonicEventDAO _sonicEventDAO;

  private volatile SoundSourceDAO _soundSourceDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `SleepSchedule` (`sleepID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `daysActive` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Playlist` (`playlistID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `shuffle` INTEGER NOT NULL, `crossfade` INTEGER NOT NULL, `crossfadeLength` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `SonicEvent` (`sonicEventID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `sleepID` INTEGER NOT NULL, `playOverPrevious` INTEGER NOT NULL, `startTime` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `SoundSource` (`soundID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `playlistID` INTEGER NOT NULL, `name` TEXT, `path` TEXT, `type` TEXT, FOREIGN KEY(`playlistID`) REFERENCES `Playlist`(`playlistID`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_SoundSource_playlistID` ON `SoundSource` (`playlistID`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '01fc2b960757ad02c8eb3c3c2b17271f')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `SleepSchedule`");
        _db.execSQL("DROP TABLE IF EXISTS `Playlist`");
        _db.execSQL("DROP TABLE IF EXISTS `SonicEvent`");
        _db.execSQL("DROP TABLE IF EXISTS `SoundSource`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsSleepSchedule = new HashMap<String, TableInfo.Column>(3);
        _columnsSleepSchedule.put("sleepID", new TableInfo.Column("sleepID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSleepSchedule.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSleepSchedule.put("daysActive", new TableInfo.Column("daysActive", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSleepSchedule = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSleepSchedule = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSleepSchedule = new TableInfo("SleepSchedule", _columnsSleepSchedule, _foreignKeysSleepSchedule, _indicesSleepSchedule);
        final TableInfo _existingSleepSchedule = TableInfo.read(_db, "SleepSchedule");
        if (! _infoSleepSchedule.equals(_existingSleepSchedule)) {
          return new RoomOpenHelper.ValidationResult(false, "SleepSchedule(edu.byui.cit.sleamapp.model.SleepSchedule).\n"
                  + " Expected:\n" + _infoSleepSchedule + "\n"
                  + " Found:\n" + _existingSleepSchedule);
        }
        final HashMap<String, TableInfo.Column> _columnsPlaylist = new HashMap<String, TableInfo.Column>(4);
        _columnsPlaylist.put("playlistID", new TableInfo.Column("playlistID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylist.put("shuffle", new TableInfo.Column("shuffle", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylist.put("crossfade", new TableInfo.Column("crossfade", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylist.put("crossfadeLength", new TableInfo.Column("crossfadeLength", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlaylist = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlaylist = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlaylist = new TableInfo("Playlist", _columnsPlaylist, _foreignKeysPlaylist, _indicesPlaylist);
        final TableInfo _existingPlaylist = TableInfo.read(_db, "Playlist");
        if (! _infoPlaylist.equals(_existingPlaylist)) {
          return new RoomOpenHelper.ValidationResult(false, "Playlist(edu.byui.cit.sleamapp.model.Playlist).\n"
                  + " Expected:\n" + _infoPlaylist + "\n"
                  + " Found:\n" + _existingPlaylist);
        }
        final HashMap<String, TableInfo.Column> _columnsSonicEvent = new HashMap<String, TableInfo.Column>(4);
        _columnsSonicEvent.put("sonicEventID", new TableInfo.Column("sonicEventID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSonicEvent.put("sleepID", new TableInfo.Column("sleepID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSonicEvent.put("playOverPrevious", new TableInfo.Column("playOverPrevious", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSonicEvent.put("startTime", new TableInfo.Column("startTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSonicEvent = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSonicEvent = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSonicEvent = new TableInfo("SonicEvent", _columnsSonicEvent, _foreignKeysSonicEvent, _indicesSonicEvent);
        final TableInfo _existingSonicEvent = TableInfo.read(_db, "SonicEvent");
        if (! _infoSonicEvent.equals(_existingSonicEvent)) {
          return new RoomOpenHelper.ValidationResult(false, "SonicEvent(edu.byui.cit.sleamapp.model.SonicEvent).\n"
                  + " Expected:\n" + _infoSonicEvent + "\n"
                  + " Found:\n" + _existingSonicEvent);
        }
        final HashMap<String, TableInfo.Column> _columnsSoundSource = new HashMap<String, TableInfo.Column>(5);
        _columnsSoundSource.put("soundID", new TableInfo.Column("soundID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSoundSource.put("playlistID", new TableInfo.Column("playlistID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSoundSource.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSoundSource.put("path", new TableInfo.Column("path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSoundSource.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSoundSource = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysSoundSource.add(new TableInfo.ForeignKey("Playlist", "CASCADE", "NO ACTION",Arrays.asList("playlistID"), Arrays.asList("playlistID")));
        final HashSet<TableInfo.Index> _indicesSoundSource = new HashSet<TableInfo.Index>(1);
        _indicesSoundSource.add(new TableInfo.Index("index_SoundSource_playlistID", false, Arrays.asList("playlistID")));
        final TableInfo _infoSoundSource = new TableInfo("SoundSource", _columnsSoundSource, _foreignKeysSoundSource, _indicesSoundSource);
        final TableInfo _existingSoundSource = TableInfo.read(_db, "SoundSource");
        if (! _infoSoundSource.equals(_existingSoundSource)) {
          return new RoomOpenHelper.ValidationResult(false, "SoundSource(edu.byui.cit.sleamapp.model.SoundSource).\n"
                  + " Expected:\n" + _infoSoundSource + "\n"
                  + " Found:\n" + _existingSoundSource);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "01fc2b960757ad02c8eb3c3c2b17271f", "e17c4d77e01926dd68b712d6956d2156");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "SleepSchedule","Playlist","SonicEvent","SoundSource");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `SleepSchedule`");
      _db.execSQL("DELETE FROM `Playlist`");
      _db.execSQL("DELETE FROM `SonicEvent`");
      _db.execSQL("DELETE FROM `SoundSource`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public SleepScheduleDAO getSleepScheduleDAO() {
    if (_sleepScheduleDAO != null) {
      return _sleepScheduleDAO;
    } else {
      synchronized(this) {
        if(_sleepScheduleDAO == null) {
          _sleepScheduleDAO = new SleepScheduleDAO_Impl(this);
        }
        return _sleepScheduleDAO;
      }
    }
  }

  @Override
  public PlaylistDAO getPlaylistDAO() {
    if (_playlistDAO != null) {
      return _playlistDAO;
    } else {
      synchronized(this) {
        if(_playlistDAO == null) {
          _playlistDAO = new PlaylistDAO_Impl(this);
        }
        return _playlistDAO;
      }
    }
  }

  @Override
  public SonicEventDAO getSonicEventDAO() {
    if (_sonicEventDAO != null) {
      return _sonicEventDAO;
    } else {
      synchronized(this) {
        if(_sonicEventDAO == null) {
          _sonicEventDAO = new SonicEventDAO_Impl(this);
        }
        return _sonicEventDAO;
      }
    }
  }

  @Override
  public SoundSourceDAO getSoundSourceDAO() {
    if (_soundSourceDAO != null) {
      return _soundSourceDAO;
    } else {
      synchronized(this) {
        if(_soundSourceDAO == null) {
          _soundSourceDAO = new SoundSourceDAO_Impl(this);
        }
        return _soundSourceDAO;
      }
    }
  }
}
