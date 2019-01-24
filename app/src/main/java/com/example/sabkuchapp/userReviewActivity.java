package com.example.sabkuchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.adapter.UserReviewAdapter;
import com.example.sabkuchapp.model.ProductResponse;
import com.example.sabkuchapp.model.UserReviewsItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class userReviewActivity extends AppCompatActivity {


    private RecyclerView  rv_eachReview;
    private UserReviewAdapter adapter;
    List<UserReviewsItem> userReviewsItemList;
    private Retrofit retrofitProduct;
    private IApiClass api;
    String prod_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_review);

        rv_eachReview=findViewById(R.id.rv_eachReview);
        prod_id=getIntent().getStringExtra("prod_id");

        retrofitProduct=AppController.retrofitProduct;
        api=retrofitProduct.create(IApiClass.class);
        rv_eachReview.setLayoutManager(new LinearLayoutManager(this));

        addData();



        userReviewsItemList=new ArrayList<>();


    }


    public void addData()
    {
       Call<ProductResponse> call=api.getProduct(prod_id);
       call.enqueue(new Callback<ProductResponse>() {
           @Override
           public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
               for(UserReviewsItem userReviewsItem:response.body().getUserReviews())
               {
                   userReviewsItemList.add(userReviewsItem);

               }

               adapter=new UserReviewAdapter(userReviewsItemList,userReviewActivity.this);
               rv_eachReview.setAdapter(adapter);

           }

           @Override
           public void onFailure(Call<ProductResponse> call, Throwable t) {

           }
       });

    }
}
