package com.example.sabkuchapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.adapter.CategoryDisplayAdapter;
import com.example.sabkuchapp.model.Category;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout ll_search;
    private Toolbar tb_search;
    private RecyclerView rv_category;
    private OkHttpClient client;
    private Retrofit retrofit;
    private IApiClass api;
    private List<Category> categoryList;
    private CategoryDisplayAdapter categoryDisplayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // temp=findViewById(R.id.temp);
        tb_search=findViewById(R.id.tb_search);
        rv_category=findViewById(R.id.rv_category);
        setSupportActionBar(tb_search);

        client = new OkHttpClient.Builder().build();

        retrofit=AppController.retrofitProduct;
        ll_search=findViewById(R.id.ll_search);

        api = retrofit.create(IApiClass.class);
        rv_category.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        addData();







//        temp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(HomeActivity.this,ProductDisplayActivity.class));
//            }
//        });

        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,ProductSearchActivity.class));
            }
        });


    }


    public void addData()
    {

        Call<List<Category>> call = api.getAllCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categoryList=response.body();
               // System.out.println(productResponseList);
                categoryDisplayAdapter=new CategoryDisplayAdapter(categoryList,HomeActivity.this);
//                productDisplayAdapter.notifyDataSetChanged();
                rv_category.setAdapter(categoryDisplayAdapter);

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                System.out.println("failure");

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("hello");
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }
}
