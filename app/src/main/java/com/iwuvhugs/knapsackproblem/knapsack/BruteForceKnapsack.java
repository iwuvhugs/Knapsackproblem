package com.iwuvhugs.knapsackproblem.knapsack;


import android.util.Log;

import com.iwuvhugs.knapsackproblem.model.ProductWrapper;

public class BruteForceKnapsack extends Knapsack {

    /*
        Oops. Amount of combinations for bruteforce is Math.pow(2, rawData.lenght).
        The lenght of rawData is more than 100 so it's way too much and poor phone
        will die trying. I'll try something else.
     */

    public BruteForceKnapsack(ProductWrapper rawData) {
        super(rawData);
    }

    @Override
    public void solve() {

    }
}
