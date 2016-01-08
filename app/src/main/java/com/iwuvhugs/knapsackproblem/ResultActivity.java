package com.iwuvhugs.knapsackproblem;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.iwuvhugs.knapsackproblem.knapsack.GeneticKnapsack;
import com.iwuvhugs.knapsackproblem.knapsack.GreedyKnapsack;
import com.iwuvhugs.knapsackproblem.knapsack.KnapsackSolution;
import com.iwuvhugs.knapsackproblem.model.ProductWrapper;

public class ResultActivity extends AppCompatActivity {

    private static final String LOG_TAG = ResultActivity.class.getSimpleName();

    private ProductWrapper productList;
    private KnapsackSolution[] solutions = new KnapsackSolution[2];

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String data = extras.getString(ProductWrapper.class.getSimpleName());
            productList = new Gson().fromJson(data, ProductWrapper.class);
        } else {
            Log.e(LOG_TAG, "Extra is null");
        }

        if (productList != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    GreedyKnapsack greedyKnapsack = new GreedyKnapsack(productList);
                    greedyKnapsack.solve();

                    KnapsackSolution greedySolution = new KnapsackSolution(
                            greedyKnapsack.getGreedyKnapsack(),
                            greedyKnapsack.getGreedyKnapsackWeight(),
                            greedyKnapsack.getGreedyKnapsackPrice());
                    solutions[0] = greedySolution;
                    Log.i(LOG_TAG, "Greedy solution found");
                    updatePager();

                }
            }).start();


            // That is too costly
//            BruteForceKnapsack bruteForceKnapsack = new BruteForceKnapsack(productList);
//            bruteForceKnapsack.solve();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    GeneticKnapsack geneticKnapsack = new GeneticKnapsack(productList);
                    geneticKnapsack.solve();

                    KnapsackSolution geneticSolution = new KnapsackSolution(
                            geneticKnapsack.getGeneticKnapsack(),
                            geneticKnapsack.getGeneticKnapsackWeight(),
                            geneticKnapsack.getGeneticKnapsackPrice());

                    solutions[1] = geneticSolution;
                    Log.i(LOG_TAG, "Genetic solution found");
                    updatePager();

                }
            }).start();


        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(
                getSupportFragmentManager(),
                solutions);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void updatePager() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mSectionsPagerAdapter != null) {
                    mSectionsPagerAdapter.setSolutions(solutions);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
