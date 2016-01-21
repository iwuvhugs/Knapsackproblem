package com.iwuvhugs.knapsackproblem.knapsack.HarmonyAlgorithm;

import com.iwuvhugs.knapsackproblem.model.Variants;

import java.util.ArrayList;


public class AwesomenessCalculator {


    private static ArrayList<Variants> dataset = new ArrayList<>();


    static Song bestSong;
    static double maxAwesomeness = 0;
    static int maxWeight = 0;
    static double maxCost = 0;


    public static void setDataset(ArrayList<Variants> dataset) {
        AwesomenessCalculator.dataset = dataset;
    }


    static double getAwesomness(Song song) {

        double awesomeness = 0;

        int weight = 0;
        double cost = 0;
        for (int i = 0; i < song.size(); i++) {
            if (song.getNote(i) == 1) {
                weight += dataset.get(i).getGrams();
                cost += Double.valueOf(dataset.get(i).getPrice());
            }
        }

        if (weight > 100000) {
            awesomeness = 0;
        } else {
            awesomeness = cost;

            if (maxAwesomeness < awesomeness) {
                maxAwesomeness = awesomeness;
                maxCost = Math.round(maxAwesomeness * 100.0) / 100.0;
                maxWeight = weight;
                setSolution(song);
            }
        }

        return awesomeness;
    }

    private static void setSolution(Song song) {
        AwesomenessCalculator.bestSong = song;
    }

    public static Song getSolution() {
        return bestSong;
    }

    public static double getMaxCost() {
        return maxCost;
    }

    public static int getMaxWeight() {
        return maxWeight;
    }
}
