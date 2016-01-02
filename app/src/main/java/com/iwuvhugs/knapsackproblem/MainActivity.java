package com.iwuvhugs.knapsackproblem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.iwuvhugs.knapsackproblem.connect.ServiceGenerator;
import com.iwuvhugs.knapsackproblem.connect.ShopifyClient;
import com.iwuvhugs.knapsackproblem.model.ProductWrapper;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private ProductWrapper productList;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "FAB clicked");
            }
        });

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // actually set only context here, productList is null
        adapter = new RecyclerViewAdapter(productList, MainActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ShopifyClient client = ServiceGenerator.createService(ShopifyClient.class);

        Call<ProductWrapper> call = client.getProducts();
        call.enqueue(new Callback<ProductWrapper>() {
            @Override
            public void onResponse(Response<ProductWrapper> response, Retrofit retrofit) {
                productList = response.body();
                adapter.setNewDataset(productList);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(LOG_TAG, t.getLocalizedMessage());
            }
        });

    }


}
