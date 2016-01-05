package com.iwuvhugs.knapsackproblem.knapsack;

import com.iwuvhugs.knapsackproblem.model.Variants;

import java.util.ArrayList;
import java.util.Arrays;


public class KnapsackSolution {

    private ArrayList<Variants> contents;
    private int contentWeight;
    private double contentCost;

    public KnapsackSolution(ArrayList<Variants> contents, int contentWeight, double contentCost) {
        this.contents = contents;
        this.contentWeight = contentWeight;
        this.contentCost = contentCost;
    }

    public Variants[] getContents() {
        Variants[] v = new Variants[contents.size()];
        v =contents.toArray(v);
        return v;
    }

    public void setContents(ArrayList<Variants> contents) {
        this.contents = contents;
    }

    public int getContentWeight() {
        return contentWeight;
    }

    public void setContentWeight(int contentWeight) {
        this.contentWeight = contentWeight;
    }

    public double getContentCost() {
        return contentCost;
    }

    public void setContentCost(double contentCost) {
        this.contentCost = contentCost;
    }
}
