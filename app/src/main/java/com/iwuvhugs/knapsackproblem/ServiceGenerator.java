package com.iwuvhugs.knapsackproblem;


import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ServiceGenerator {

    private static final String API_URL = "http://shopicruit.myshopify.com";

    private static OkHttpClient httpClient = new OkHttpClient();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> setviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(setviceClass);
    }
}
