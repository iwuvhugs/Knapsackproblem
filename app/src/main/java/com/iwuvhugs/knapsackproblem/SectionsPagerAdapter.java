package com.iwuvhugs.knapsackproblem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.iwuvhugs.knapsackproblem.knapsack.KnapsackSolution;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private KnapsackSolution[] solutions;

    public SectionsPagerAdapter(FragmentManager fm, KnapsackSolution[] solutions) {
        super(fm);
        this.solutions = new KnapsackSolution[solutions.length];
        for (int i = 0; i < solutions.length; i++) {
            this.solutions[i] = solutions[i];
        }
    }

    @Override
    public Fragment getItem(int position) {
        if(solutions[position]!= null){
            return KnapsackSolutionFragment.newInstance(solutions[position]);
        } else {
            return KnapsackSolutionFragment.newInstance();
        }

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return solutions.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Greedy";
            case 1:
                return "Brute-Force";
            case 2:
                return "Genetic";
        }
        return null;
    }
}

