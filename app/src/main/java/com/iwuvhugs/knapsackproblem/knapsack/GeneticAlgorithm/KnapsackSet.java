package com.iwuvhugs.knapsackproblem.knapsack.GeneticAlgorithm;


public class KnapsackSet {

    static int defaultLength = 110;
    private byte[] genes = new byte[defaultLength];
    private int fitness = 0;

    public void generateSet(){
        for (int i = 0; i < size(); i++){
            byte gene = (byte) Math.round(Math.random());
            genes[i] = gene;
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

    public int getFitness(){

        // TODO: calculate fitness using price and weight

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
