package edu.byui.cit.sleamapp.model.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import edu.byui.cit.sleamapp.model.Schedule;
import edu.byui.cit.sleamapp.model.SleepSchedule;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SleepScheduleDAO_Impl extends SleepScheduleDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SleepSchedule> __insertionAdapterOfSleepSchedule;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<SleepSchedule> __deletionAdapterOfSleepSchedule;

  private final EntityDeletionOrUpdateAdapter<SleepSchedule> __updateAdapterOfSleepSchedule;

  public SleepScheduleDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSleepSchedule = new EntityInsertionAdapter<SleepSchedule>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `SleepSchedule` (`sleepID`,`name`,`daysActive`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SleepSchedule value) {
        stmt.bindLong(1, value.getSleepID());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        final Schedule _tmpSchedule = value.getSchedule();
        if(_tmpSchedule != null) {
          final String _tmp;
          _tmp = __converters.booleanArrayToString(_tmpSchedule.getDaysActive());
          if (_tmp == null) {
            stmt.bindNull(3);
          } else {
            stmt.bindString(3, _tmp);
          }
        } else {
          stmt.bindNull(3);
        }
      }
    };
    this.__deletionAdapterOfSleepSchedule = new EntityDeletionOrUpdateAdapter<SleepSchedule>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `SleepSchedule` WHERE `sleepID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SleepSchedule value) {
        stmt.bindLong(1, value.getSleepID());
      }
    };
    this.__updateAdapterOfSleepSchedule = new EntityDeletionOrUpdateAdapter<SleepSchedule>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `SleepSchedule` SET `sleepID` = ?,`name` = ?,`daysActive` = ? WHERE `sleepID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SleepSchedule value) {
        stmt.bindLong(1, value.getSleepID());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        final Schedule _tmpSchedule = value.getSchedule();
        if(_tmpSchedule != null) {
          final String _tmp;
          _tmp = __converters.booleanArrayToString(_tmpSchedule.getDaysActive());
          if (_tmp == null) {
            stmt.bindNull(3);
          } else {
            stmt.bindString(3, _tmp);
          }
        } else {
          stmt.bindNull(3);
        }
        stmt.bindLong(4, value.getSleepID());
      }
    };
  }

  @Override
  long insertH(final SleepSchedule sleepSchedule) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfSleepSchedule.insertAndReturnId(sleepSchedule);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteSoundSource(final SleepSchedule sleepSchedule) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfSleepSchedule.handle(sleepSchedule);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final SleepSchedule sleepSchedule) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfSleepSchedule.handle(sleepSchedule);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<SleepSchedule> getAll() {
    final String _sql = "SELECT * FROM SleepSchedule";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSleepID = CursorUtil.getColumnIndexOrThrow(_cursor, "sleepID");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDaysActive = CursorUtil.getColumnIndexOrThrow(_cursor, "daysActive");
      final List<SleepSchedule> _result = new ArrayList<SleepSchedule>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final SleepSchedule _item;
        final Schedule _tmpSchedule;
        if (! (_cursor.isNull(_cursorIndexOfDaysActive))) {
          _tmpSchedule = new Schedule();
          final ArrayList<Boolean> _tmpDaysActive;
          final String _tmp;
          _tmp = _cursor.getString(_cursorIndexOfDaysActive);
          _tmpDaysActive = __converters.fromString(_tmp);
          _tmpSchedule.setDaysActive(_tmpDaysActive);
        }  else  {
          _tmpSchedule = null;
        }
        _item = new SleepSchedule();
        final long _tmpSleepID;
        _tmpSleepID = _cursor.getLong(_cursorIndexOfSleepID);
        _item.setSleepID(_tmpSleepID);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        _item.setSchedule(_tmpSchedule);
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
    final String _sql = "SELECT COUNT(*) FROM SleepSchedule";
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
  public SleepSchedule getByKey(final long id) {
    final String _sql = "SELECT * FROM SleepSchedule WHERE `sleepID` = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSleepID = CursorUtil.getColumnIndexOrThrow(_cursor, "sleepID");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDaysActive = CursorUtil.getColumnIndexOrThrow(_cursor, "daysActive");
      final SleepSchedule _result;
      if(_cursor.moveToFirst()) {
        final Schedule _tmpSchedule;
        if (! (_cursor.isNull(_cursorIndexOfDaysActive))) {
          _tmpSchedule = new Schedule();
          final ArrayList<Boolean> _tmpDaysActive;
          final String _tmp;
          _tmp = _cursor.getString(_cursorIndexOfDaysActive);
          _tmpDaysActive = __converters.fromString(_tmp);
          _tmpSchedule.setDaysActive(_tmpDaysActive);
        }  else  {
          _tmpSchedule = null;
        }
        _result = new SleepSchedule();
        final long _tmpSleepID;
        _tmpSleepID = _cursor.getLong(_cursorIndexOfSleepID);
        _result.setSleepID(_tmpSleepID);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
        _result.setSchedule(_tmpSchedule);
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
