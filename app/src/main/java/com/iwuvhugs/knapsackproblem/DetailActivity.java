package com.iwuvhugs.knapsackproblem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iwuvhugs.knapsackproblem.model.Product;
import com.iwuvhugs.knapsackproblem.model.Variants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    private Product product;

    private Toolbar toolbar;
    private ImageView productImage;
    private TextView productDescription;
    private Spinner spinner;
    private TextView priceTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String data = extras.getString(Product.class.getSimpleName());
            product = new Gson().fromJson(data, Product.class);
            Log.e(LOG_TAG, "Extra is " + product.getTitle());
        } else {
            Log.e(LOG_TAG, "Extra is null");
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        productImage = (ImageView) findViewById(R.id.product_image);
        productDescription = (TextView) findViewById(R.id.product_description);
        priceTextView = (TextView) findViewById(R.id.price_textView);

        spinner = (Spinner) findViewById(R.id.spinner);


        if (product != null) {
            getSupportActionBar().setTitle(product.getTitle());
            Picasso.with(this)
                    .load(product.getImages()[0].getSrc())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .resizeDimen(R.dimen.product_detail_image_size, R.dimen.product_detail_image_size)
                    .centerInside()
                    .tag(this)
                    .into(productImage);
            productDescription.setText(product.getBody_html());

            priceTextView.setText("$ "+product.getVariants()[0].getPrice());

            ArrayList<String> variants = new ArrayList<>();
            for (Variants v : product.getVariants()) {
                variants.add(v.getTitle());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item,
                    variants);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    priceTextView.setText("$ "+product.getVariants()[position].getPrice());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }


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
