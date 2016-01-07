package com.iwuvhugs.knapsackproblem.knapsack.GeneticAlgorithm;


import android.util.Log;

import com.iwuvhugs.knapsackproblem.knapsack.Knapsack;
import com.iwuvhugs.knapsackproblem.model.Variants;

import java.util.ArrayList;

public class FitnessCalculator {

    private static final String LOGTAG = FitnessCalculator.class.getSimpleName();
    static KnapsackSet solution;
    static double maxFitness = 0;
    static int maxWeight = 0;
    static double maxCost = 0;
    private static ArrayList<Variants> dataset = new ArrayList<>();


    public static void setDataset(ArrayList<Variants> dataset) {
        FitnessCalculator.dataset = dataset;
    }

    static double getFitness(KnapsackSet set){
        double fitness = 0;

        int weight = 0;
        double cost = 0;
        for(int i = 0; i < set.size(); i ++){
            if(set.getGene(i) == 1){
                weight += dataset.get(i).getGrams();
                cost += Double.valueOf(dataset.get(i).getPrice());
            }
        }

        if(weight > 100000){
            fitness = 0;
        } else {
            fitness = cost;
            if(maxFitness < fitness){
                maxFitness = fitness;
                maxCost = Math.round(maxFitness * 100.0) / 100.0;
                maxWeight = weight;
                setSolution(set);
            }
        }

        return fitness;
    }

    private static void setSolution(KnapsackSet set) {
        FitnessCalculator.solution = set;
    }

    public static KnapsackSet getSolution() {
        return solution;
    }

    public static double getMaxCost() {
        return maxCost;
    }

    public static int getMaxWeight() {
        return maxWeight;
    }
}
