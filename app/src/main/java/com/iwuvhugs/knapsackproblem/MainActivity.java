package com.iwuvhugs.knapsackproblem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
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

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbar.setTitle(getResources().getString(R.string.app_name));
        collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(MainActivity.this, android.R.color.white));
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(MainActivity.this, android.R.color.transparent));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
        floatingActionButton.hide();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productList != null) {
                    Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
                    resultIntent.putExtra(ProductWrapper.class.getSimpleName(), new Gson().toJson(productList));
                    startActivity(resultIntent);
                }
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
                floatingActionButton.show();
                adapter.setNewDataset(productList);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(LOG_TAG, t.getLocalizedMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.send_email:
                Log.d(LOG_TAG, "send email to me");
                sendMeEmail();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendMeEmail() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "k.suslov@yahoo.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Developer Intern response");
        intent.putExtra(Intent.EXTRA_TEXT, "Hi Kirill! ...");
        startActivity(Intent.createChooser(intent, "Pick an Email provider"));

    }
}
