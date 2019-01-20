package com.example.sabkuchapp;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppController extends Application {

    public static Retrofit retrofit=null;

    @Override
    public void onCreate() {
        super.onCreate();
        if(null==retrofit)
        {
            OkHttpClient client=new OkHttpClient.Builder().build();

            retrofit=new Retrofit.Builder().baseUrl("https://www.blibli.com/backend/mobile/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        }
    }

}
