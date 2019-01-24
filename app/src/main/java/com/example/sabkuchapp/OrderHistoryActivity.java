package com.example.sabkuchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.adapter.OrderHistoryAdapter;
import com.example.sabkuchapp.model.OrdersHistory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderHistoryActivity extends AppCompatActivity {


    private Retrofit retrofitCart;
    private IApiClass apiCart;
    List<OrdersHistory> orders;
    private RecyclerView orderHistoryReycler;

    private OrderHistoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        retrofitCart=AppController.retrofitCart;
        apiCart=retrofitCart.create(IApiClass.class);
        orderHistoryReycler=findViewById(R.id.orderHistoryRecycler);
        orderHistoryReycler.setLayoutManager(new LinearLayoutManager(OrderHistoryActivity.this));


        orders=new ArrayList<>();
        loadData();





    }




    public void loadData()
    {
        Call<List<OrdersHistory>> call=apiCart.getAllOrder("4b726cbf-a5a1-4118-9d71-a239508b5172");
        call.enqueue(new Callback<List<OrdersHistory>>() {
            @Override
            public void onResponse(Call<List<OrdersHistory>> call, Response<List<OrdersHistory>> response) {

                for(OrdersHistory ordersHistory:response.body()) {
                    orders.add(ordersHistory);
                }

                System.out.println(orders);

                adapter=new OrderHistoryAdapter(orders,OrderHistoryActivity.this);
                orderHistoryReycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<OrdersHistory>> call, Throwable t) {

                System.out.println(t.getMessage());

            }
        });






    }
}
