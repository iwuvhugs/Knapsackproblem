package com.iwuvhugs.knapsackproblem;


import retrofit.Call;
import retrofit.http.GET;

public interface ShopifyClient {

    @GET("/products.json")
    Call<ProductWrapper> getProducts();

}

