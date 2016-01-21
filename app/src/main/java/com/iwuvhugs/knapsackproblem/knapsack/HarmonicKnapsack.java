package com.iwuvhugs.knapsackproblem.knapsack;

import android.util.Log;

import com.iwuvhugs.knapsackproblem.knapsack.HarmonyAlgorithm.AwesomenessCalculator;
import com.iwuvhugs.knapsackproblem.knapsack.HarmonyAlgorithm.HarmonyAlgorithm;
import com.iwuvhugs.knapsackproblem.knapsack.HarmonyAlgorithm.Playlist;
import com.iwuvhugs.knapsackproblem.knapsack.HarmonyAlgorithm.Song;
import com.iwuvhugs.knapsackproblem.model.ProductWrapper;
import com.iwuvhugs.knapsackproblem.model.Variants;

import java.util.ArrayList;


public class HarmonicKnapsack extends Knapsack {


    private static final String LOGTAG = HarmonicKnapsack.class.getSimpleName();

    public HarmonicKnapsack(ProductWrapper rawData) {
        super(rawData);
    }

    @Override
    public void solve() {
        Song.setDefaultNotesLength(dataset.size());
        AwesomenessCalculator.setDataset(dataset);

        // Create fist population of 75 random sets
        Playlist playlist = new Playlist(2500);

        // Print final results
        Log.e(LOGTAG, "Initial");
        Log.e(LOGTAG, "Initial cost: " + playlist.getTheMostAwesomeSong().getAwesomness());

        HarmonyAlgorithm.improvise(playlist);
        for (int i = 0; i < 10000; i++) {
            HarmonyAlgorithm.improvise(playlist);
        }

        // Print final results


        knapsackCost = AwesomenessCalculator.getMaxCost();
        knapsackWeight = AwesomenessCalculator.getMaxWeight();
        setHarmonicKnapsack(AwesomenessCalculator.getSolution());

        Log.e(LOGTAG, "Finished");
        Log.e(LOGTAG, "Final cost: " + knapsackCost);
    }

    private void setHarmonicKnapsack(Song solution) {
        for (int i = 0; i < solution.size(); i++) {
            if (solution.getNote(i) == 1) {
                resultDataset.add(dataset.get(i));
            }
        }
    }


    public double getHarmonicKnapsackPrice() {
        return knapsackCost;
    }

    public int getHarmonicKnapsackWeight() {
        return knapsackWeight;
    }

    public ArrayList<Variants> getHarmonicKnapsack() {
        return resultDataset;
    }


}
