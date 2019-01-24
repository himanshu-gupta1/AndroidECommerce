package com.example.sabkuchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.adapter.MerchantAdapter;
import com.example.sabkuchapp.model.MerchantProductResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AllMerchantActivity extends AppCompatActivity {

    private RecyclerView rv_allMerchant;
    private MerchantAdapter adapter;
    public Retrofit retrofit=AppController.retrofitMerchant;
    private IApiClass apiMerchant;
    private List<MerchantProductResponse> merchantProductResponseList;
    String prod_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_merchant);
        rv_allMerchant=findViewById(R.id.rv_allMerchant);
        prod_id=getIntent().getStringExtra("prod_id");
        apiMerchant=retrofit.create(IApiClass.class);
        merchantProductResponseList=new ArrayList<>();






        rv_allMerchant.setLayoutManager(new LinearLayoutManager(AllMerchantActivity.this));
        addData();




    }


    public void addData()
    {

        Call<List<MerchantProductResponse>> call=apiMerchant.getMerchantFromProduct(prod_id);
        call.enqueue(new Callback<List<MerchantProductResponse>>() {
            @Override
            public void onResponse(Call<List<MerchantProductResponse>> call, Response<List<MerchantProductResponse>> response) {
                merchantProductResponseList=response.body();
                System.out.println(merchantProductResponseList);
                adapter=new MerchantAdapter(merchantProductResponseList,AllMerchantActivity.this);
                rv_allMerchant.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<MerchantProductResponse>> call, Throwable t) {

            }
        });
    }
}
