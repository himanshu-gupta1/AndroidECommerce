package com.example.sabkuchapp.Rest;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sabkuchapp.R;
import com.example.sabkuchapp.adapter.CartListAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String> CartList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
        for (int i = 0; i < 20; i++) {


            CartList.add(ItemView);
        }
        RecyclerView recyclerView = findViewById(R.id.recycler_cart);

        CartListAdapter cartListAdapter = new CartListAdapter(CartList);
        recyclerView.setAdapter(cartListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //we have to set the layout mnanager because it doesnt know ki kaise display
        // krna hai Linear mai krna hai ya grid mai ya kaise


    }


}
