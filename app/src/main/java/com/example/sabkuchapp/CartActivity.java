package com.example.sabkuchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.adapter.CartAdapter;
import com.example.sabkuchapp.model.CartProductResponse;
import com.example.sabkuchapp.model.Category;
import com.example.sabkuchapp.model.MerchantProductResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private IApiClass api;
    private List<CartProductResponse> cartProductResponseList;
    private RecyclerView recycler_cart;
    private CartAdapter cartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        retrofit=AppController.retrofitCart;
        api = retrofit.create(IApiClass.class);
        recycler_cart=findViewById(R.id.recycler_cart);
        cartProductResponseList=new ArrayList<>();


        recycler_cart.setLayoutManager(new LinearLayoutManager(this));

        addData();


    }


    public void addData()
    {
        cartProductResponseList.clear();
        Call<List<CartProductResponse>> call=api.getAllProductFromCart("4b726cbf-a5a1-4118-9d71-a239508b5172");
        call.enqueue(new Callback<List<CartProductResponse>>() {
            @Override
            public void onResponse(Call<List<CartProductResponse>> call, Response<List<CartProductResponse>> response) {
                for (CartProductResponse cartProductResponse:response.body())
                {
                    cartProductResponseList.add(cartProductResponse);
                }

                cartAdapter=new CartAdapter(cartProductResponseList,CartActivity.this);
                recycler_cart.setAdapter(cartAdapter);



            }

            @Override
            public void onFailure(Call<List<CartProductResponse>> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });

    }

    public  void deleteData(String productId)
    {
        System.out.println(productId);
        System.out.println(cartProductResponseList);
        for(CartProductResponse cartProductResponse:cartProductResponseList)
        {
            System.out.println(cartProductResponse);
            if(cartProductResponse.getProductId().equals(productId))
            {
                cartProductResponseList.remove(cartProductResponse);
                System.out.println("removed");

            }
        }
        cartAdapter.notifyDataSetChanged();
    }


    public void updateProduct(String productId)
    {

        System.out.println(productId);
        System.out.println(cartProductResponseList);
        for(int i=0;i<cartProductResponseList.size();i++)
        {
            System.out.println(cartProductResponseList.get(i));
            if(cartProductResponseList.get(i).getProductId().equals(productId))
            {

                CartProductResponse cartProductResponse=cartProductResponseList.get(i);
                cartProductResponse.setProductCount(cartProductResponse.getProductCount()+1);
                cartProductResponseList.set(i,cartProductResponse);
                System.out.println("updated");

            }
        }
        cartAdapter.notifyDataSetChanged();



    }




}
