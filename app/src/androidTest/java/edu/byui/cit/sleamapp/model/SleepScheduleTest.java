package edu.byui.cit.sleamapp.model;

import org.junit.Before;

import java.time.LocalTime;

public class SleepScheduleTest {

    SleepSchedule testSleepSchedule;
    Schedule testSchedule;
    SonicEvent testSonicEventOne, testSonicEventTwo, testSonicEventThree;
    Playlist testPlaylistOne, testPlaylistTwo, testPlaylistThree;
    SoundSource testSoundSourceOne, testSoundSourceTwo, testSoundSourceThree, testSoundSourceFour, testSoundSourceFive;



    /**
     * This should simulate data being entered by the user as they setup their sleep schedule. All of this is stored locally and not in the database (yet).
     */
    @Before
    public void setupAllData(){
        // SleepSchedule and Schedule
        testSleepSchedule = new SleepSchedule();
        testSchedule = new Schedule();
        testSchedule.setDay(1, true);
        testSchedule.setDay(2, true);
        testSchedule.setDay(3, true);
        testSchedule.setDay(4, true);
        testSchedule.setDay(5, true);
        testSleepSchedule.setSchedule(testSchedule);

        // Sonic event one (fall asleep)
        testSonicEventOne = new SonicEvent(LocalTime.of(22, 30), false); // Start at 10:30pm
        testPlaylistOne = new Playlist(false, true, 30);
        testSoundSourceOne = new SoundSource("Africa", "/music/toto/africa.mp3", "LocalFile"); // create a new song
        testSoundSourceTwo = new SoundSource("Lullaby", "/music/notByToto/songNotAsGoodAsAfrica.mp3", "LocalFile"); // make anotha one
        testPlaylistOne.addSound(testSoundSourceOne); // add song to the first playlist
        testPlaylistOne.addSound(testSoundSourceTwo); // ^^ anotha one
        testSonicEventOne.setPlaylist(testPlaylistOne); // attach the Playlist to the SonicEvent
        testSleepSchedule.setFallAsleepEvent(testSonicEventOne); // Attach the Sonic Event to the Sleep Schedule as the Falling Asleep event.

        // Sonic event two (stay asleep)
        testSonicEventTwo = new SonicEvent(LocalTime.of(23,15), false); // Start at 11:15pm
        testPlaylistTwo = new Playlist(true, true, 20);
        testSoundSourceThree = new SoundSource("White Noise", "~/thisAppsResourceFolder/whitenoise.mp3", "Built-In Sound");
    }



}
