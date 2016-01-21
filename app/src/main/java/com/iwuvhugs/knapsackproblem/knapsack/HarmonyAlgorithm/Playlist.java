package com.iwuvhugs.knapsackproblem.knapsack.HarmonyAlgorithm;


import java.util.Random;

public class Playlist {

    Song[] songs;

    public Playlist(int playlistSize) {
        songs = new Song[playlistSize];
        for (int i = 0; i < size(); i++) {
            Song song = new Song();
            do {
                song.generateSong();
            } while (song.getAwesomness() == 0);
            saveSong(i, song);
        }
    }


    public int size() {
        return songs.length;
    }

    public Song getSong(int position) {
        return songs[position];
    }

    public void saveSong(int index, Song song) {
        songs[index] = song;
    }


    public Song getTheMostAwesomeSong() {
        Song theMostAwesomeSong = songs[0];
        for (int i = 0; i < size(); i++) {
            if (theMostAwesomeSong.getAwesomness() <= getSong(i).getAwesomness()) {
                theMostAwesomeSong = getSong(i);
            }
        }
        return theMostAwesomeSong;
    }

    public Song writeNewSong() {

        Song song = new Song();
        for (int i = 0; i < Song.defaultLength; i++) {

            Random random = new Random();
            int notePosition = random.nextInt(songs.length);

            // Get note from random song from playlist
            song.setNote(i, songs[notePosition].getNote(i));

        }

        return song;
    }
}
