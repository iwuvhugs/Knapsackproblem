package com.iwuvhugs.knapsackproblem.knapsack.GeneticAlgorithm;


public class KnapsackSet {

    static int defaultLength = 110;
    private static double geneChance = 2.8;
    private byte[] genes = new byte[defaultLength];
    private double fitness = 0;

    // create a byte array that represent Knapsack where 1 - item is taken, 0 - not
    public void generateSet(){
        for (int i = 0; i < size(); i++){

            // 9/5 chance of 0 and 1
            long chance =  Math.round(Math.random() * geneChance );
            if( chance < 1){
                genes[i] = 1;
            } else {
                genes[i] = 0;
            }
            // 1/1 chance of 0 and 1
//            byte gene = (byte) Math.round(Math.random() * 4 );
//            genes[i] = gene;
        }
    }

    // Use this if you want to create individuals with different gene lengths
    public static void setDefaultGeneLength(int length) {
        defaultLength = length;
    }

    public byte getGene(int position){
        return  genes[position];
    }

    public void setGene(int position, byte gene){
        genes[position] = gene;
        fitness = 0;
    }

    public double getFitness(){
        if(fitness == 0){
            fitness = FitnessCalculator.getFitness(this);
        }
        return  fitness;
    }

    public int size(){
        return genes.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size(); i++){
            sb.append(getGene(i));
        }
        return sb.toString();
    }



}
