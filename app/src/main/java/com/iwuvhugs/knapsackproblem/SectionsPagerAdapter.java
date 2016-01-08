package com.iwuvhugs.knapsackproblem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.iwuvhugs.knapsackproblem.knapsack.KnapsackSolution;


public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private static final String LOGTAG = SectionsPagerAdapter.class.getSimpleName();
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
        return KnapsackSolutionFragment.newInstance(solutions[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return solutions.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Greedy";
            case 1:
                return "Genetic";

        }
        return null;
    }

    public void setSolutions(KnapsackSolution[] solutions) {
        try {
            this.solutions = solutions;
            notifyDataSetChanged();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemPosition(Object object) {
        return SectionsPagerAdapter.POSITION_NONE;
    }
}

