package com.iwuvhugs.knapsackproblem.knapsack;

import com.iwuvhugs.knapsackproblem.knapsack.GeneticAlgorithm.KnapsackSet;
import com.iwuvhugs.knapsackproblem.knapsack.GeneticAlgorithm.Population;
import com.iwuvhugs.knapsackproblem.model.ProductWrapper;


public class GeneticKnapsack extends Knapsack {

    public GeneticKnapsack(ProductWrapper rawData) {
        super(rawData);
    }

    @Override
    public void solve() {

        // create a byte array that represent Knapsack where 1 - item is taken, 0 - not
        KnapsackSet.setDefaultGeneLength(dataset.size());

        // Create fist population of 50 random sets
        Population population = new Population(50, true);

    }
}
