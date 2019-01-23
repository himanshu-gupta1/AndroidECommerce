package com.example.sabkuchapp.Rest;

import com.example.sabkuchapp.CartActivity;
import com.example.sabkuchapp.model.CartProduct;
import com.example.sabkuchapp.model.CartProductResponse;
import com.example.sabkuchapp.model.CartProductUpdate;
import com.example.sabkuchapp.model.Category;
import com.example.sabkuchapp.model.ContentItem;
import com.example.sabkuchapp.model.MerchantProductResponse;
import com.example.sabkuchapp.model.ProductResponse;
import com.example.sabkuchapp.model.SearchResponse;
import com.example.sabkuchapp.model.SubCategoryItem;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IApiClass {



    @GET("getInSearch/{searchParam}")
    public Call<List<ContentItem>> getInSearch(@Path("searchParam") String searchParam);

    @GET("findAllProductsFromSearch")
    public Call<SearchResponse> getAllProducts();

    @GET("products/getSubCategoriesByCategory/{category}")
    public Call<List<SubCategoryItem>> getSubCategoryByCategory(@Path("category") String categoryName);

    @GET("products/getAllCategories")
    public Call<List<Category>> getAllCategories();

    @GET("getPriorityMerchant/{productId}")
    public Call<MerchantProductResponse> getPriorityMerchant(@Path("productId") String productId);

    @GET("cart/getAllproduct/{userId}")
    public Call<List<CartProductResponse>> getAllProductFromCart(@Path("userId") String userId);


    @GET("products/getProduct/{productId}")
    public Call<ProductResponse> getProduct(@Path("productId") String productId);


    @GET("getMerchantProduct/{productId}/{merchantId}")
    public Call<MerchantProductResponse> getMerchantProduct(@Path("productId") String productId,@Path("merchantId") String merchantId);

    @GET("cart/getcartId/{userId}")
    public Call<String> getCartId(@Path("userId") String userId);

    @POST("/cartproduct/add")
    public Call<Void> addToCart(@Body CartProduct cartProduct);


    @DELETE("/cartproduct/delete/{uid}/{pid}")
    public Call<Void> deleteFromCart(@Path("uid") String uid,@Path("pid") String pid);

    @PUT("/cartproduct/update")
    public Call<Void> updateCart(@Body CartProductUpdate cartProductUpdate);


}
