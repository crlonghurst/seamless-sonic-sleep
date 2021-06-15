package edu.byui.cit.sleamapp.model.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import edu.byui.cit.sleamapp.model.SoundSource;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SoundSourceDAO_Impl extends SoundSourceDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SoundSource> __insertionAdapterOfSoundSource;

  private final EntityDeletionOrUpdateAdapter<SoundSource> __deletionAdapterOfSoundSource;

  private final EntityDeletionOrUpdateAdapter<SoundSource> __updateAdapterOfSoundSource;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public SoundSourceDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSoundSource = new EntityInsertionAdapter<SoundSource>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `SoundSource` (`soundID`,`playlistID`,`name`,`path`,`type`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SoundSource value) {
        stmt.bindLong(1, value.getSoundID());
        stmt.bindLong(2, value.getPlaylistID());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getPath() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPath());
        }
        if (value.getType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getType());
        }
      }
    };
    this.__deletionAdapterOfSoundSource = new EntityDeletionOrUpdateAdapter<SoundSource>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `SoundSource` WHERE `soundID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SoundSource value) {
        stmt.bindLong(1, value.getSoundID());
      }
    };
    this.__updateAdapterOfSoundSource = new EntityDeletionOrUpdateAdapter<SoundSource>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `SoundSource` SET `soundID` = ?,`playlistID` = ?,`name` = ?,`path` = ?,`type` = ? WHERE `soundID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SoundSource value) {
        stmt.bindLong(1, value.getSoundID());
        stmt.bindLong(2, value.getPlaylistID());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getPath() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPath());
        }
        if (value.getType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getType());
        }
        stmt.bindLong(6, value.getSoundID());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM SoundSource";
        return _query;
      }
    };
  }

  @Override
  long insertH(final SoundSource soundSource) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfSoundSource.insertAndReturnId(soundSource);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteSoundSource(final SoundSource soundSource) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfSoundSource.handle(soundSource);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final SoundSource soundSource) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfSoundSource.handle(soundSource);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public long count() {
    final String _sql = "SELECT COUNT(*) FROM SoundSource";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final long _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getLong(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<SoundSource> getAll() {
    final String _sql = "SELECT * FROM SoundSource";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSoundID = CursorUtil.getColumnIndexOrThrow(_cursor, "soundID");
      final int _cursorIndexOfPlaylistID = CursorUtil.getColumnIndexOrThrow(_cursor, "playlistID");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPath = CursorUtil.getColumnIndexOrThrow(_cursor, "path");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final List<SoundSource> _result = new ArrayList<SoundSource>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final SoundSource _item;
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpPath;
        _tmpPath = _cursor.getString(_cursorIndexOfPath);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _item = new SoundSource(_tmpName,_tmpPath,_tmpType);
        final long _tmpSoundID;
        _tmpSoundID = _cursor.getLong(_cursorIndexOfSoundID);
        _item.setSoundID(_tmpSoundID);
        final long _tmpPlaylistID;
        _tmpPlaylistID = _cursor.getLong(_cursorIndexOfPlaylistID);
        _item.setPlaylistID(_tmpPlaylistID);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public SoundSource getByKey(final long id) {
    final String _sql = "SELECT * FROM SoundSource WHERE `soundID` = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSoundID = CursorUtil.getColumnIndexOrThrow(_cursor, "soundID");
      final int _cursorIndexOfPlaylistID = CursorUtil.getColumnIndexOrThrow(_cursor, "playlistID");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPath = CursorUtil.getColumnIndexOrThrow(_cursor, "path");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final SoundSource _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpPath;
        _tmpPath = _cursor.getString(_cursorIndexOfPath);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _result = new SoundSource(_tmpName,_tmpPath,_tmpType);
        final long _tmpSoundID;
        _tmpSoundID = _cursor.getLong(_cursorIndexOfSoundID);
        _result.setSoundID(_tmpSoundID);
        final long _tmpPlaylistID;
        _tmpPlaylistID = _cursor.getLong(_cursorIndexOfPlaylistID);
        _result.setPlaylistID(_tmpPlaylistID);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
