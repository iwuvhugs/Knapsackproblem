package com.iwuvhugs.knapsackproblem.knapsack.HarmonyAlgorithm;


import java.util.Random;

public class HarmonyAlgorithm {

    //    private static final double talent = 0.9;
    private static final double talent = 85;

    private static final String LOGTAG = HarmonyAlgorithm.class.getSimpleName();

    public static void improvise(Playlist playlist) {

        Song newSong;
        do {
            newSong = playlist.writeNewSong();
            // MAKE SOME NOISE
            dropTheBass(newSong);
        } while (newSong.getAwesomness() == 0);

        double newSongAwesomenes = newSong.getAwesomness();

        int lamestSongPosition = -1;
        double lamestSongAwesomeness = playlist.getSong(0).getAwesomness();
        for (int i = 0; i < playlist.size(); i++) {
            if (lamestSongAwesomeness > playlist.getSong(i).getAwesomness()) {
                lamestSongAwesomeness = playlist.getSong(i).getAwesomness();
                lamestSongPosition = i;
            }
        }
        if (lamestSongPosition != (-1)) {
            if (newSongAwesomenes > lamestSongAwesomeness) {
                playlist.saveSong(lamestSongPosition, newSong);
            }
        }

    }

    private static void dropTheBass(Song song) {
//        double randomNumber = Math.random();
        Random random = new Random();
        int position = random.nextInt(song.size());
        if (random.nextInt(100) <= talent) {
            byte note;
            if (random.nextBoolean()) {
                note = 0;
            } else {
                note = 1;
            }
            song.setNote(position, note);
        }
    }

}
