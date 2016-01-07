package com.iwuvhugs.knapsackproblem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

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
        Log.e(LOGTAG, "getItem");
//        if(solutions[position]!= null){
            return KnapsackSolutionFragment.newInstance(solutions[position]);
//        } else {
//            return KnapsackSolutionFragment.newInstance();
//        }

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

    public void setSolutions(KnapsackSolution[] solutions, int page) {
        Log.i(LOGTAG, "setSolutions");
        if(SectionsPagerAdapter.this != null) {
            this.solutions = solutions;
            notifyDataSetChanged();
        }

    }

    @Override
    public int getItemPosition(Object object) {
        return SectionsPagerAdapter.POSITION_NONE;
    }
}

