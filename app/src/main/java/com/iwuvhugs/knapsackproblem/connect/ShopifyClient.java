package com.iwuvhugs.knapsackproblem.connect;


import com.iwuvhugs.knapsackproblem.model.ProductWrapper;

import retrofit.Call;
import retrofit.http.GET;

public interface ShopifyClient {

    @GET("/products.json")
    Call<ProductWrapper> getProducts();

}

