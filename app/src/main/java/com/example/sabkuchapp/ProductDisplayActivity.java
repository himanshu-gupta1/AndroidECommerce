package com.example.sabkuchapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.adapter.ProductDisplayAdapter;
import com.example.sabkuchapp.model.ProductResponse;

import java.util.List;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductDisplayActivity extends AppCompatActivity {

    private RecyclerView rv_product_list;
    private ProductDisplayAdapter productDisplayAdapter;
    private OkHttpClient client;
    private Retrofit retrofit;
    private IApiClass api;
    private List<ProductResponse> productResponseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);
        rv_product_list=findViewById(R.id.rv_product_list);

        client = new OkHttpClient.Builder().build();


        api = AppController.retrofit.create(IApiClass.class);

        rv_product_list.setLayoutManager(new GridLayoutManager(ProductDisplayActivity.this,2));
        addData();

//        rv_product_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                // super.onScrollStateChanged(recyclerView, newState);
//                // addData();
//                if(!rv_product_list.canScrollVertically(1))
//                {
//                    addData();
//                    productDisplayAdapter.notifyDataSetChanged();
//
//                }
//            }
//        });

    }


    public void addData()
    {

        Call<List<ProductResponse>> call = api.getProductsByName();
        call.enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                productResponseList=response.body();
                productDisplayAdapter=new ProductDisplayAdapter(productResponseList,ProductDisplayActivity.this);
                rv_product_list.setAdapter(productDisplayAdapter);
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {

            }
        });




    }
}
