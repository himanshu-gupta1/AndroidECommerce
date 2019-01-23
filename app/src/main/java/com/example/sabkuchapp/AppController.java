package com.example.sabkuchapp;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppController extends Application {

    public static Retrofit retrofitSearch=null;
    public static Retrofit retrofitProduct=null;
    public static Retrofit retrofitCategory=null;
    public static Retrofit retrofitMerchant=null;
    public static Retrofit retrofitCart=null;


    @Override
    public void onCreate() {
        super.onCreate();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client=new OkHttpClient.Builder().build();
        if(null==retrofitSearch)
        {


            retrofitSearch=new Retrofit.Builder().baseUrl("http://10.177.7.131:8080/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        }
        if(null==retrofitProduct)
        {
            //OkHttpClient client=new OkHttpClient.Builder().build();

            retrofitProduct=new Retrofit.Builder().baseUrl("http://10.177.7.131:8003/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        }
        if(null==retrofitCategory)
        {
           // OkHttpClient client=new OkHttpClient.Builder().build();

            retrofitCategory=new Retrofit.Builder().baseUrl("http://10.177.7.131:8003/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        }
        if(null==retrofitMerchant)
        {
            retrofitMerchant=new Retrofit.Builder().baseUrl("http://10.177.7.120:8080/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        }
        if(null==retrofitCart)
        {
            retrofitCart=new Retrofit.Builder().baseUrl("http://10.177.7.137:8080/").addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();
        }

    }



}
