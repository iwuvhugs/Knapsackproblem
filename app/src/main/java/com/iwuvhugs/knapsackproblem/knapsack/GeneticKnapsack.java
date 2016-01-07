package com.iwuvhugs.knapsackproblem.knapsack;

import android.util.Log;

import com.iwuvhugs.knapsackproblem.knapsack.GeneticAlgorithm.FitnessCalculator;
import com.iwuvhugs.knapsackproblem.knapsack.GeneticAlgorithm.GeneticAlgorithm;
import com.iwuvhugs.knapsackproblem.knapsack.GeneticAlgorithm.KnapsackSet;
import com.iwuvhugs.knapsackproblem.knapsack.GeneticAlgorithm.Population;
import com.iwuvhugs.knapsackproblem.model.ProductWrapper;
import com.iwuvhugs.knapsackproblem.model.Variants;

import java.util.ArrayList;


public class GeneticKnapsack extends Knapsack {

    private static final String LOGTAG = GeneticKnapsack.class.getSimpleName();

    public GeneticKnapsack(ProductWrapper rawData) {
        super(rawData);
    }

    @Override
    public void solve() {

        KnapsackSet.setDefaultGeneLength(dataset.size());
        FitnessCalculator.setDataset(dataset);

        // Create fist population of 50 random sets
        Population population = new Population(100, true);

//        Log.d(LOGTAG, "Fittest " + population.getFittest().getFitness());
//        Log.d(LOGTAG, ""+population.getFittest());
        population = GeneticAlgorithm.evolvePopulation(population);
        for (int i = 0; i < 250; i++) {
            population = GeneticAlgorithm.evolvePopulation(population);
        }
        // Print final results
//        Log.d(LOGTAG, "Finished");
//        Log.d(LOGTAG, "Final cost: " + population.getFittest().getFitness());
//        Log.d(LOGTAG, "Solution:");
//        Log.d(LOGTAG, "" + population.getFittest());
//        System.out.println(FitnessCalculator.getMaxCost());

        knapsackCost = FitnessCalculator.getMaxCost();
        knapsackWeight = FitnessCalculator.getMaxWeight();
        setGeneticKnapsack(FitnessCalculator.getSolution());
    }

    private void setGeneticKnapsack(KnapsackSet solution) {
        for (int i = 0; i < solution.size(); i++) {
            if (solution.getGene(i) == 1) {
                resultDataset.add(dataset.get(i));
            }
        }
    }

    public double getGeneticKnapsackPrice() {
        return knapsackCost;
    }

    public int getGeneticKnapsackWeight() {
        return knapsackWeight;
    }

    public ArrayList<Variants> getGeneticKnapsack() {
        return resultDataset;
    }
}
