package com.iwuvhugs.knapsackproblem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.iwuvhugs.knapsackproblem.knapsack.GreedyKnapsack;
import com.iwuvhugs.knapsackproblem.model.ProductWrapper;

public class ResultActivity extends AppCompatActivity {

    private static final String LOG_TAG = ResultActivity.class.getSimpleName();
    private ProductWrapper productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String data = extras.getString(ProductWrapper.class.getSimpleName());
            productList = new Gson().fromJson(data, ProductWrapper.class);
        } else {
            Log.e(LOG_TAG, "Extra is null");
        }

        setContentView(R.layout.activity_result);

        if(productList!=null){
            GreedyKnapsack greedyKnapsack = new GreedyKnapsack(productList);
            greedyKnapsack.solve();
            double cost = greedyKnapsack.getGreedyKnapsackPrice();
            Log.i(LOG_TAG, "Cost of knapsack is " + cost);
        }
    }
}
