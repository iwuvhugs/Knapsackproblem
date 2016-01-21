package com.iwuvhugs.knapsackproblem.knapsack.GeneticAlgorithm;


import java.util.Random;

public class GeneticAlgorithm {

    // parameters
//    private static final double uniformRate = 0.5;
    private static final int uniformRate = 5;
    //    private static final double mutationRate = 0.025;
    private static final int mutationRate = 25;
    private static final int tournametSize = 5;
    private static final boolean elitism = true;


    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false);

        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveKnapsackSet(0, pop.getFittest());
            elitismOffset = 1;
        }

        for (int i = elitismOffset; i < newPopulation.size(); i++) {

            KnapsackSet parent1 = tournamentSelection(pop);
            KnapsackSet parent2 = tournamentSelection(pop);
            KnapsackSet child = crossover(parent1, parent2);
            newPopulation.saveKnapsackSet(i, child);
        }

        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getKnapsackSet(i));
        }

        return newPopulation;
    }

    private static KnapsackSet crossover(KnapsackSet individOne, KnapsackSet individTwo) {
        KnapsackSet newSet = new KnapsackSet();
        Random random = new Random();
        // Random genes from both parents
        for (int i = 0; i < individOne.size(); i++) {
//            if (Math.random() <= uniformRate) {
            if (random.nextInt(10) <= uniformRate) {
                newSet.setGene(i, individOne.getGene(i));
            } else {
                newSet.setGene(i, individTwo.getGene(i));
            }
        }

        // Replace part of parents' genome
        // P.S. less productive way of crossover
//        int startPos = (int) (Math.random() * individOne.size());
//        int endPos = (int) (Math.random() * individOne.size());
//        if (startPos == endPos) {
//            endPos += 1;
//        }
//
//
//        if(startPos < endPos){
//            for(int i = 0; i < newSet.size(); i++){
//                if(i > startPos && i < endPos){
//                    newSet.setGene(i, individOne.getGene(i));
//                } else {
//                    newSet.setGene(i, individTwo.getGene(i));
//                }
//            }
//        } else {
//            for(int i = 0; i < newSet.size(); i++){
//                if (!(i < startPos && i > endPos)) {
//                    newSet.setGene(i, individOne.getGene(i));
//                } else {
//                    newSet.setGene(i, individTwo.getGene(i));
//                }
//            }
//        }

        return newSet;
    }


    private static void mutate(KnapsackSet set) {
        Random random = new Random();
        for (int i = 0; i < set.size(); i++) {
//            if (Math.random() <= mutationRate) {
            if (random.nextInt(1000) <= mutationRate) {
//                byte gene = (byte) Math.round(Math.random());
                byte gene;
                if (random.nextBoolean()) {
                    gene = 0;
                } else {
                    gene = 1;
                }
                set.setGene(i, gene);
            }
        }
    }

    private static KnapsackSet tournamentSelection(Population pop) {
        Population tournament = new Population(tournametSize, false);
        Random random = new Random();
        for (int i = 0; i < tournametSize; i++) {
//            int randomId = (int) (Math.random() * pop.size());
            int randomId = random.nextInt(pop.size());
            tournament.saveKnapsackSet(i, pop.getKnapsackSet(randomId));
        }

        KnapsackSet fittest = tournament.getFittest();
        return fittest;
    }

}
