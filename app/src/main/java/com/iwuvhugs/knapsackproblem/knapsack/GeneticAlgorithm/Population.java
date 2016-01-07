package com.iwuvhugs.knapsackproblem.knapsack.GeneticAlgorithm;


import android.util.Log;

public class Population {

    private static final String LOGTAG = Population.class.getSimpleName();
    KnapsackSet[] knapsackSets;

    public Population(int populationSize, boolean initialize){
        knapsackSets = new KnapsackSet[populationSize];
        if (initialize){
            for (int i = 0; i < size(); i++){
                KnapsackSet individual = new KnapsackSet();
                individual.generateSet();
                Log.d(LOGTAG, individual.toString());
                saveKnapsackSet(i, individual);

            }
        }
    }

    public int size(){
        return knapsackSets.length;
    }

    public KnapsackSet getKnapsackSet(int position){
        return knapsackSets[position];
    }

    public void saveKnapsackSet(int index, KnapsackSet set){
        knapsackSets[index] = set;
    }

    public KnapsackSet getFittest(){
        KnapsackSet fittest = knapsackSets[0];
        for(int i = 0; i < size(); i++){
            // TODO: check for compare operator when fittest will be invented
            if(fittest.getFitness() <= getKnapsackSet(i).getFitness()){
                fittest = getKnapsackSet(i);
            }
        }
        return fittest;
    }

}
