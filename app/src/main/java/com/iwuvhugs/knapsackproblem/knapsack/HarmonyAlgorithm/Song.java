package com.iwuvhugs.knapsackproblem.knapsack.HarmonyAlgorithm;


import java.util.Random;

public class Song {

    static int defaultLength = 110;
    private byte[] notes = new byte[defaultLength];
    //    private static double dj = 2.8;
    private static int dj = 22;
    private double awesomeness = 0;

    // create a byte array that represent Knapsack where 1 - item is taken, 0 - not
    public void generateSong() {
        Random random = new Random();
        for (int i = 0; i < size(); i++) {

            // 9/5 chance of 0 and 1
//            long chance = Math.round(Math.random() * dj);
            int chance = random.nextInt(100);
            if (chance < dj) {
                notes[i] = 1;
            } else {
                notes[i] = 0;
            }
            // 1/1 chance of 0 and 1
//            byte note = (byte) Math.round(Math.random() * 4 );
//            notes[i] = note;
        }
//        awesomeness = AwesomenessCalculator.getAwesomness(this);
    }

    // Use this if you want to create a song with different  lengths
    public static void setDefaultNotesLength(int length) {
        defaultLength = length;
    }

    public byte getNote(int position) {
        return notes[position];
    }

    public void setNote(int position, byte gene) {
        notes[position] = gene;
        awesomeness = 0;
    }

    public double getAwesomness() {
        if (awesomeness == 0) {
            awesomeness = AwesomenessCalculator.getAwesomness(this);
        }
        return awesomeness;
    }

    public int size() {
        return notes.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(getNote(i));
        }
        return sb.toString();
    }
}
