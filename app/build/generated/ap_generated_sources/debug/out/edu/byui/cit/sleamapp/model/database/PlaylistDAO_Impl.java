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
import edu.byui.cit.sleamapp.model.Playlist;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PlaylistDAO_Impl extends PlaylistDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Playlist> __insertionAdapterOfPlaylist;

  private final EntityDeletionOrUpdateAdapter<Playlist> __deletionAdapterOfPlaylist;

  private final EntityDeletionOrUpdateAdapter<Playlist> __updateAdapterOfPlaylist;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public PlaylistDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlaylist = new EntityInsertionAdapter<Playlist>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Playlist` (`playlistID`,`shuffle`,`crossfade`,`crossfadeLength`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Playlist value) {
        stmt.bindLong(1, value.getPlaylistID());
        final int _tmp;
        _tmp = value.isShuffle() ? 1 : 0;
        stmt.bindLong(2, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isCrossfade() ? 1 : 0;
        stmt.bindLong(3, _tmp_1);
        stmt.bindLong(4, value.getCrossfadeLength());
      }
    };
    this.__deletionAdapterOfPlaylist = new EntityDeletionOrUpdateAdapter<Playlist>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Playlist` WHERE `playlistID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Playlist value) {
        stmt.bindLong(1, value.getPlaylistID());
      }
    };
    this.__updateAdapterOfPlaylist = new EntityDeletionOrUpdateAdapter<Playlist>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Playlist` SET `playlistID` = ?,`shuffle` = ?,`crossfade` = ?,`crossfadeLength` = ? WHERE `playlistID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Playlist value) {
        stmt.bindLong(1, value.getPlaylistID());
        final int _tmp;
        _tmp = value.isShuffle() ? 1 : 0;
        stmt.bindLong(2, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isCrossfade() ? 1 : 0;
        stmt.bindLong(3, _tmp_1);
        stmt.bindLong(4, value.getCrossfadeLength());
        stmt.bindLong(5, value.getPlaylistID());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Playlist";
        return _query;
      }
    };
  }

  @Override
  long insertH(final Playlist playlist) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfPlaylist.insertAndReturnId(playlist);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deletePlaylist(final Playlist playlist) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPlaylist.handle(playlist);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Playlist playlist) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPlaylist.handle(playlist);
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
    final String _sql = "SELECT COUNT(*) FROM Playlist";
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
  public List<Playlist> getAll() {
    final String _sql = "SELECT * FROM Playlist";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPlaylistID = CursorUtil.getColumnIndexOrThrow(_cursor, "playlistID");
      final int _cursorIndexOfShuffle = CursorUtil.getColumnIndexOrThrow(_cursor, "shuffle");
      final int _cursorIndexOfCrossfade = CursorUtil.getColumnIndexOrThrow(_cursor, "crossfade");
      final int _cursorIndexOfCrossfadeLength = CursorUtil.getColumnIndexOrThrow(_cursor, "crossfadeLength");
      final List<Playlist> _result = new ArrayList<Playlist>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Playlist _item;
        _item = new Playlist();
        final long _tmpPlaylistID;
        _tmpPlaylistID = _cursor.getLong(_cursorIndexOfPlaylistID);
        _item.setPlaylistID(_tmpPlaylistID);
        final boolean _tmpShuffle;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfShuffle);
        _tmpShuffle = _tmp != 0;
        _item.setShuffle(_tmpShuffle);
        final boolean _tmpCrossfade;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfCrossfade);
        _tmpCrossfade = _tmp_1 != 0;
        _item.setCrossfade(_tmpCrossfade);
        final int _tmpCrossfadeLength;
        _tmpCrossfadeLength = _cursor.getInt(_cursorIndexOfCrossfadeLength);
        _item.setCrossfadeLength(_tmpCrossfadeLength);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Playlist getByKey(final long id) {
    final String _sql = "SELECT * FROM Playlist WHERE `playlistID` = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPlaylistID = CursorUtil.getColumnIndexOrThrow(_cursor, "playlistID");
      final int _cursorIndexOfShuffle = CursorUtil.getColumnIndexOrThrow(_cursor, "shuffle");
      final int _cursorIndexOfCrossfade = CursorUtil.getColumnIndexOrThrow(_cursor, "crossfade");
      final int _cursorIndexOfCrossfadeLength = CursorUtil.getColumnIndexOrThrow(_cursor, "crossfadeLength");
      final Playlist _result;
      if(_cursor.moveToFirst()) {
        _result = new Playlist();
        final long _tmpPlaylistID;
        _tmpPlaylistID = _cursor.getLong(_cursorIndexOfPlaylistID);
        _result.setPlaylistID(_tmpPlaylistID);
        final boolean _tmpShuffle;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfShuffle);
        _tmpShuffle = _tmp != 0;
        _result.setShuffle(_tmpShuffle);
        final boolean _tmpCrossfade;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfCrossfade);
        _tmpCrossfade = _tmp_1 != 0;
        _result.setCrossfade(_tmpCrossfade);
        final int _tmpCrossfadeLength;
        _tmpCrossfadeLength = _cursor.getInt(_cursorIndexOfCrossfadeLength);
        _result.setCrossfadeLength(_tmpCrossfadeLength);
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
