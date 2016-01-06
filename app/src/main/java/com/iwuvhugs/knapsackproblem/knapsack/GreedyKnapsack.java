package com.iwuvhugs.knapsackproblem.knapsack;


import android.util.Log;

import com.iwuvhugs.knapsackproblem.model.ProductWrapper;
import com.iwuvhugs.knapsackproblem.model.Variants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
    It's not the most accurate way but the easiest one
 */

public class GreedyKnapsack extends Knapsack {

    private static final String LOGTAG = GreedyKnapsack.class.getSimpleName();

    public GreedyKnapsack(ProductWrapper rawData) {
        super(rawData);
    }

    @Override
    public void solve() {


        Collections.sort(dataset, new ValueComparator());
        int remainingCapacity = knapsackCapasity;
        for (Variants variant : dataset) {
            if (remainingCapacity - variant.getGrams() >= 0) {
                resultDataset.add(variant);
                knapsackCost += Double.valueOf(variant.getPrice());
                knapsackWeight += variant.getGrams();
                remainingCapacity -= variant.getGrams();
//                Log.d(LOGTAG, "Value: " + variant.getValue() + " \t Price " + variant.getPrice() + " \tWeight " + variant.getGrams());
            }
        }
    }

    public double getGreedyKnapsackPrice() {
        return knapsackCost;
    }

    public int getGreedyKnapsackWeight() {
        return knapsackWeight;
    }

    public ArrayList<Variants> getGreedyKnapsack() {
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
