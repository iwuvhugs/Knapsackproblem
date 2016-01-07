package com.iwuvhugs.knapsackproblem.knapsack;

import android.util.Log;

import com.iwuvhugs.knapsackproblem.model.ProductWrapper;
import com.iwuvhugs.knapsackproblem.model.Variants;

import java.util.ArrayList;

public abstract class Knapsack {

    final int knapsackCapasity = 100000;
    ArrayList<Variants> dataset = new ArrayList<>();
    ArrayList<Variants> resultDataset = new ArrayList<>();
    double knapsackCost = 0;
    int knapsackWeight = 0;

    public Knapsack(ProductWrapper rawData) {

//        double totalPrice = 0;
//        int totalWeight = 0;

        for (int i = 0; i < rawData.getProducts().length; i++) {
            for (int j = 0; j < rawData.getProducts()[i].getVariants().length; j++) {
                Variants v = new Variants();
                v.setId(rawData.getProducts()[i].getVariants()[j].getId());
                v.setTitle(rawData.getProducts()[i].getVariants()[j].getTitle());
                v.setPrice(rawData.getProducts()[i].getVariants()[j].getPrice());
                v.setGrams(rawData.getProducts()[i].getVariants()[j].getGrams());
                v.setValue(Double.valueOf(rawData.getProducts()[i].getVariants()[j].getPrice()) / ((double) rawData.getProducts()[i].getVariants()[j].getGrams()));
                dataset.add(v);

//                totalPrice += Double.valueOf(rawData.getProducts()[i].getVariants()[j].getPrice());
//                totalWeight += rawData.getProducts()[i].getVariants()[j].getGrams();
            }
        }

//        Log.d("Knapsack", "total price " + totalPrice);
//        Log.d("Knapsack", "total weight " + totalWeight);

    }

    public void solve(){}
}
