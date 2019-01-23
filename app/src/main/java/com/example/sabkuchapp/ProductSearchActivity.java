package com.example.sabkuchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.adapter.ProductSearchAdapter;
import com.example.sabkuchapp.model.ContentItem;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductSearchActivity extends AppCompatActivity {

    private RecyclerView rv_search_list;
    private AutoCompleteTextView tv_searchSuggestions;
    private ProductSearchAdapter productSearchAdapter;
    private OkHttpClient client;
    private Retrofit retrofit;
    private IApiClass api;
    private List<ContentItem> productResponseList;
    private List<ContentItem> searchResponseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);
        rv_search_list=findViewById(R.id.rv_search_list);
        tv_searchSuggestions=findViewById(R.id.tv_searchSuggestions);

        client = new OkHttpClient.Builder().build();
        searchResponseList=new ArrayList<ContentItem>();

        retrofit=AppController.retrofitSearch;

        api = retrofit.create(IApiClass.class);

        rv_search_list.setLayoutManager(new LinearLayoutManager(ProductSearchActivity.this));
        productSearchAdapter = new ProductSearchAdapter(searchResponseList, ProductSearchActivity.this);
        rv_search_list.setAdapter(productSearchAdapter);
        //addData();

        tv_searchSuggestions.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                searchResponseList.clear();
//                productSearchAdapter.notifyDataSetChanged();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.toString().trim().equals("")) {
                    searchResponseList.clear();

                    productSearchAdapter.notifyDataSetChanged();
                }
                else
               {

                    String searchParam = charSequence.toString().trim();
                    System.out.println(searchParam);

                    Call<List<ContentItem>> call = api.getInSearch(searchParam);
                    call.enqueue(new Callback<List<ContentItem>>() {
                        @Override
                        public void onResponse(Call<List<ContentItem>> call, Response<List<ContentItem>> response) {
                            System.out.println(response.body().size());
                            searchResponseList.clear();
                            productSearchAdapter.notifyDataSetChanged();
                            for(ContentItem contentItem:response.body())
                            {
                                searchResponseList.add(contentItem);
                            }

                            productSearchAdapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onFailure(Call<List<ContentItem>> call, Throwable t) {
                            System.out.println(t.getMessage());

                        }
                    });

                }

//
//                if(!charSequence.toString().equals("")) {
//
//                    for (ContentItem contentItem : productResponseList) {
//                        if (contentItem.getProductName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
//                            searchResponseList.add(contentItem);
//                        }
//
//
//                    }
//                }
//                productSearchAdapter.notifyDataSetChanged();


            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchResponseList.clear();
               // productSearchAdapter.notifyDataSetChanged();

//                searchResponseList.clear();
//                if(editable.toString().equals("")) {
//
//                    productSearchAdapter.notifyDataSetChanged();
//                }
//                else
//                {
//
//                    String searchParam = editable.toString();
//                    System.out.println(searchParam);
//
//                    Call<List<ContentItem>> call = api.getInSearch(searchParam);
//                    call.enqueue(new Callback<List<ContentItem>>() {
//                        @Override
//                        public void onResponse(Call<List<ContentItem>> call, ProductResponse<List<ContentItem>> response) {
//                            for(ContentItem contentItem:response.body())
//                            {
//                                searchResponseList.add(contentItem);
//                            }
//                            productSearchAdapter = new ProductSearchAdapter(searchResponseList, ProductSearchActivity.this);
//                            rv_search_list.setAdapter(productSearchAdapter);
//                            productSearchAdapter.notifyDataSetChanged();
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<ContentItem>> call, Throwable t) {
//                            System.out.println(t.getMessage());
//
//                        }
//                    });
//
//                }

            }
        });


    }

//    public void addData()
//    {
//
//        Call<SearchResponse> call = api.getAllProducts();
//        call.enqueue(new Callback<SearchResponse>() {
//            @Override
//            public void onResponse(Call<SearchResponse> call, ProductResponse<SearchResponse> response) {
//                productResponseList=response.body().getContent();
//                productSearchAdapter=new ProductSearchAdapter(searchResponseList,ProductSearchActivity.this);
//                rv_search_list.setAdapter(productSearchAdapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<SearchResponse> call, Throwable t) {
//
//            }
//        });
//
//
//
//
//    }

}
