package com.iwuvhugs.knapsackproblem.knapsack;


import android.util.Log;

import com.iwuvhugs.knapsackproblem.model.ProductWrapper;

public class BruteForceKnapsack extends Knapsack {

    /*
        Oops. Amount of combinations for bruteforce is Math.pow(2, rawData.lenght).
        The lenght of rawData is more than 100 so it's way too much and poor phone
        will die trying. I'll try something else.

        Also amount of combinations in the branches-and-bounds algorithm
        can be up to Math.pow(2, rawData.lenght)
        ( <= Math.pow(2, rawData.lenght))
        in the worst case so it's not the best way to solve that.

        There is one more way to solve. Dynamic programming. Maybe later.
     */

    public BruteForceKnapsack(ProductWrapper rawData) {
        super(rawData);
    }

    @Override
    public void solve() {

    }
}
