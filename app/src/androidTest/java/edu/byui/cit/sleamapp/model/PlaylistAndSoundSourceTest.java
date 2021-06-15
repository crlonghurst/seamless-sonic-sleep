package edu.byui.cit.sleamapp.model;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import edu.byui.cit.sleamapp.model.database.AppDatabase;
import edu.byui.cit.sleamapp.model.database.PlaylistDAO;
import edu.byui.cit.sleamapp.model.database.SoundSourceDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class PlaylistAndSoundSourceTest {

    // Create test objects
    Playlist testPlaylistOne = new Playlist();
    SoundSource testSongOne = new SoundSource("Apollo", "/somePath/apollo.mp3", "LocalFile");

    // Initialize Database and stuff
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    AppDatabase db = AppDatabase.getInstance(appContext);
    PlaylistDAO pDao = db.getPlaylistDAO();
    SoundSourceDAO sDao = db.getSoundSourceDAO();

    @Before
    public void setUp(){
        pDao.deleteAll();
        sDao.deleteAll();

        // Define some data for testPlaylistOne
        testPlaylistOne.setCrossfade(true);
        testPlaylistOne.setCrossfadeLength(30);

        // Define some data for testSongOne
        testSongOne.setName("Apollo");
        testSongOne.setType("LocalFile");
    }

    @Test
    public void storeInDatabase(){
        // store the testPlaylist
        pDao.insert(testPlaylistOne);
        long playlistID = testPlaylistOne.getPlaylistID();

        // retrieve the testPlaylist
        Playlist retrievedPlaylist = pDao.getByKey(playlistID);

        // store the testSound
        testSongOne.setPlaylistID(testPlaylistOne.getPlaylistID()); // Attaches the sound to its parent playlist
        sDao.insert(testSongOne);
        long soundID = testSongOne.getSoundID();

        // retrieve the testSound
        SoundSource retrievedSong = sDao.getByKey(soundID);

        // assert that the stored and retrieved Playlists and SoundSources are the same
        assertEquals(testPlaylistOne, retrievedPlaylist);
        assertEquals(testSongOne, retrievedSong);

        // Update that song
        sDao.update(testSongOne);
        retrievedSong = sDao.getByKey(testSongOne.getSoundID()); // update the local copy based on what's in the DB
        assertEquals(testSongOne, retrievedSong);

        // Verify this song is attached to the correct playlist
        Playlist storedAttachedPlaylist = pDao.getByKey(retrievedSong.getPlaylistID());
        assertEquals(testPlaylistOne, storedAttachedPlaylist);
    }

    @Test
    public void deleteFromDatabase(){
        // Delete the playlist. This should also delete the Song attached to it.
        pDao.deletePlaylist(testPlaylistOne);

        // Confirm playlist was deleted
        assertNull(pDao.getByKey(testPlaylistOne.getPlaylistID()));
        // Confirm song attached to playlist was also deleted
        assertNull(sDao.getByKey(testSongOne.getSoundID()));
    }
}