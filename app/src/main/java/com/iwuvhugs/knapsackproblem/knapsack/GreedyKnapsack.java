package com.iwuvhugs.knapsackproblem.knapsack;


import com.iwuvhugs.knapsackproblem.model.ProductWrapper;
import com.iwuvhugs.knapsackproblem.model.Variants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GreedyKnapsack {

    private static final String LOGTAG = GreedyKnapsack.class.getSimpleName();

    private final int knapsackCapasity = 100000;
    private ArrayList<Variants> dataset = new ArrayList<>();
    private ArrayList<Variants> resultDataset = new ArrayList<>();
    private double greedyKnapsackCost = 0;

    public GreedyKnapsack(ProductWrapper rawData) {

        for (int i = 0; i < rawData.getProducts().length; i++) {
            for (int j = 0; j < rawData.getProducts()[i].getVariants().length; j++) {
                Variants v = new Variants();
                v.setId(rawData.getProducts()[i].getVariants()[j].getId());
                v.setTitle(rawData.getProducts()[i].getVariants()[j].getTitle());
                v.setPrice(rawData.getProducts()[i].getVariants()[j].getPrice());
                v.setGrams(rawData.getProducts()[i].getVariants()[j].getGrams());
                v.setValue(Double.valueOf(rawData.getProducts()[i].getVariants()[j].getPrice()) / (rawData.getProducts()[i].getVariants()[j].getGrams() / 1000.0));
                dataset.add(v);
            }
        }

    }

    public void solve() {
        Collections.sort(dataset, new ValueComparator());
        int remainingCapacity = knapsackCapasity;
        for (Variants variant : dataset) {
            if (remainingCapacity - variant.getGrams() > 0) {
                resultDataset.add(variant);
                greedyKnapsackCost += Double.valueOf(variant.getPrice());
                remainingCapacity -= variant.getGrams();
//                Log.d(LOGTAG, "Value: " + variant.getValue() + " \t Price " + variant.getPrice() + " \tWeight " + variant.getGrams());
            }

        }
//        Log.d(LOGTAG, "Weight of knapsack is " + (100000 - remainingCapacity));
    }

    public double getGreedyKnapsackPrice() {
        return greedyKnapsackCost;
    }

    public ArrayList<Variants> getGreedyKnapsack(){
        return resultDataset;
    }

    // sort in descending order by value
    public class ValueComparator implements Comparator<Variants> {
        @Override
        public int compare(Variants lhs, Variants rhs) {
            return Double.compare(rhs.getValue(), lhs.getValue());
        }
    }

}
