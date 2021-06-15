package edu.byui.cit.sleamapp.model.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import edu.byui.cit.sleamapp.model.SonicEvent;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SonicEventDAO_Impl extends SonicEventDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SonicEvent> __insertionAdapterOfSonicEvent;

  private final EntityDeletionOrUpdateAdapter<SonicEvent> __deletionAdapterOfSonicEvent;

  private final EntityDeletionOrUpdateAdapter<SonicEvent> __updateAdapterOfSonicEvent;

  public SonicEventDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSonicEvent = new EntityInsertionAdapter<SonicEvent>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `SonicEvent` (`sonicEventID`,`sleepID`,`playOverPrevious`,`startTime`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SonicEvent value) {
        stmt.bindLong(1, value.getSonicEventID());
        stmt.bindLong(2, value.getSleepID());
        final int _tmp;
        _tmp = value.isPlayOverPrevious() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final String _tmp_1;
        _tmp_1 = Converters.toDateString(value.getStartTime());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp_1);
        }
      }
    };
    this.__deletionAdapterOfSonicEvent = new EntityDeletionOrUpdateAdapter<SonicEvent>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `SonicEvent` WHERE `sonicEventID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SonicEvent value) {
        stmt.bindLong(1, value.getSonicEventID());
      }
    };
    this.__updateAdapterOfSonicEvent = new EntityDeletionOrUpdateAdapter<SonicEvent>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `SonicEvent` SET `sonicEventID` = ?,`sleepID` = ?,`playOverPrevious` = ?,`startTime` = ? WHERE `sonicEventID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SonicEvent value) {
        stmt.bindLong(1, value.getSonicEventID());
        stmt.bindLong(2, value.getSleepID());
        final int _tmp;
        _tmp = value.isPlayOverPrevious() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final String _tmp_1;
        _tmp_1 = Converters.toDateString(value.getStartTime());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp_1);
        }
        stmt.bindLong(5, value.getSonicEventID());
      }
    };
  }

  @Override
  long insertH(final SonicEvent sonicEvent) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfSonicEvent.insertAndReturnId(sonicEvent);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteSoundSource(final SonicEvent sonicEvent) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfSonicEvent.handle(sonicEvent);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final SonicEvent sonicEvent) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfSonicEvent.handle(sonicEvent);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<SonicEvent> getAll() {
    final String _sql = "SELECT * FROM SonicEvent";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSonicEventID = CursorUtil.getColumnIndexOrThrow(_cursor, "sonicEventID");
      final int _cursorIndexOfSleepID = CursorUtil.getColumnIndexOrThrow(_cursor, "sleepID");
      final int _cursorIndexOfPlayOverPrevious = CursorUtil.getColumnIndexOrThrow(_cursor, "playOverPrevious");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
      final List<SonicEvent> _result = new ArrayList<SonicEvent>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final SonicEvent _item;
        _item = new SonicEvent();
        final long _tmpSonicEventID;
        _tmpSonicEventID = _cursor.getLong(_cursorIndexOfSonicEventID);
        _item.setSonicEventID(_tmpSonicEventID);
        final long _tmpSleepID;
        _tmpSleepID = _cursor.getLong(_cursorIndexOfSleepID);
        _item.setSleepID(_tmpSleepID);
        final boolean _tmpPlayOverPrevious;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfPlayOverPrevious);
        _tmpPlayOverPrevious = _tmp != 0;
        _item.setPlayOverPrevious(_tmpPlayOverPrevious);
        final LocalTime _tmpStartTime;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfStartTime);
        _tmpStartTime = Converters.toDate(_tmp_1);
        _item.setStartTime(_tmpStartTime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
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
  public SonicEvent getByKey(final long id) {
    final String _sql = "SELECT * FROM SonicEvent WHERE `sonicEventID` = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSonicEventID = CursorUtil.getColumnIndexOrThrow(_cursor, "sonicEventID");
      final int _cursorIndexOfSleepID = CursorUtil.getColumnIndexOrThrow(_cursor, "sleepID");
      final int _cursorIndexOfPlayOverPrevious = CursorUtil.getColumnIndexOrThrow(_cursor, "playOverPrevious");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
      final SonicEvent _result;
      if(_cursor.moveToFirst()) {
        _result = new SonicEvent();
        final long _tmpSonicEventID;
        _tmpSonicEventID = _cursor.getLong(_cursorIndexOfSonicEventID);
        _result.setSonicEventID(_tmpSonicEventID);
        final long _tmpSleepID;
        _tmpSleepID = _cursor.getLong(_cursorIndexOfSleepID);
        _result.setSleepID(_tmpSleepID);
        final boolean _tmpPlayOverPrevious;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfPlayOverPrevious);
        _tmpPlayOverPrevious = _tmp != 0;
        _result.setPlayOverPrevious(_tmpPlayOverPrevious);
        final LocalTime _tmpStartTime;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfStartTime);
        _tmpStartTime = Converters.toDate(_tmp_1);
        _result.setStartTime(_tmpStartTime);
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
