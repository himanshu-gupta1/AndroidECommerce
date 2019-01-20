package com.example.sabkuchapp.Rest;

import com.example.sabkuchapp.model.ProductResponse;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiClass {

    @GET("products/productByName/iPhone X")
    public Call<List<ProductResponse>> getProductsByName();
}
