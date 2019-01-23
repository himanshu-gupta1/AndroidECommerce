package com.example.sabkuchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.adapter.ProductDisplayAdapter;
import com.example.sabkuchapp.model.ContentItem;

import java.util.ArrayList;
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
    private Retrofit retrofitMerchant;
    private IApiClass api;
    private List<ContentItem> productResponseList;
    private String subCategory;
    private Toolbar tb_subCategorySearch;

//    List<Integer> salePrice;
//    List<Integer> price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);
        rv_product_list=findViewById(R.id.rv_product_list);
        tb_subCategorySearch=findViewById(R.id.tb_subCategorySearch);
        TextView title=tb_subCategorySearch.findViewById(R.id.tv_title);


        client = new OkHttpClient.Builder().build();

        retrofitMerchant=AppController.retrofitMerchant;
        retrofit=AppController.retrofitSearch;

        api = retrofit.create(IApiClass.class);

        subCategory=getIntent().getStringExtra("subCategory");
        title.setText(subCategory);

        productResponseList=new ArrayList<ContentItem>();

//        productDisplayAdapter=new ProductDisplayAdapter(productResponseList,ProductDisplayActivity.this);
//        rv_product_list.setAdapter(productDisplayAdapter);

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

        Call<List<ContentItem>> call = api.getInSearch(subCategory);  //replace it with subcategory
        call.enqueue(new Callback<List<ContentItem>>() {
            @Override
            public void onResponse(Call<List<ContentItem>> call, Response<List<ContentItem>> response) {
               // System.out.println(response.body());
                productResponseList=response.body();
                //get the price from each product...
//                for(ContentItem contentItem:productResponseList)
//                {
//                   // addPriceData(contentItem.getProductId());
//
//                }






                System.out.println("hi "+productResponseList);
                productDisplayAdapter=new ProductDisplayAdapter(productResponseList,ProductDisplayActivity.this);
//                productDisplayAdapter.notifyDataSetChanged();
                rv_product_list.setAdapter(productDisplayAdapter);

            }

            @Override
            public void onFailure(Call<List<ContentItem>> call, Throwable t) {
                System.out.println("failure");

            }
        });




    }



}
