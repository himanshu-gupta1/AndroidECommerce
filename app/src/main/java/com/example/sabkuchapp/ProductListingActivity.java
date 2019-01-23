package com.example.sabkuchapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.model.Cart;
import com.example.sabkuchapp.model.CartProduct;
import com.example.sabkuchapp.model.MerchantProductResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductListingActivity extends AppCompatActivity {

    private Button bt_allDetails;
    private TextView tv_salePrice;
    private TextView tv_price;
    private ImageView iv_prodImage;
    private TextView tv_prodName;
    private Retrofit retrofitMerchant;
    private TextView tv_sellerRating;
    private TextView tv_sellerName;
    private IApiClass api;
    private FloatingActionButton bt_addToCart;
    private Retrofit retrofitCart;
    private IApiClass apiCart;

    String prod_name;
    String prod_image;
    String prod_id;
    String merchant_id;
   // private OkHttpClient client = new OkHttpClient.Builder().build();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);
        bt_allDetails=findViewById(R.id.bt_allDetails);
        bt_addToCart=findViewById(R.id.bt_addToCart);
        retrofitMerchant=AppController.retrofitMerchant;
        retrofitCart=AppController.retrofitCart;
        api=retrofitMerchant.create(IApiClass.class);
        apiCart=retrofitCart.create(IApiClass.class);
        prod_name=getIntent().getStringExtra("prod_name");
        prod_image=getIntent().getStringExtra("prod_image");
        prod_id=getIntent().getStringExtra("prod_id");
        tv_sellerName=findViewById(R.id.tv_sellerName);
        tv_sellerRating=findViewById(R.id.tv_sellerRating);

        bt_allDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProductListingActivity.this,AllDetailsActivity.class);
                intent.putExtra("productId",prod_id);
                startActivity(intent);
            }
        });



        tv_salePrice=findViewById(R.id.tv_salePrice);
        tv_price=findViewById(R.id.tv_price);
        iv_prodImage=findViewById(R.id.iv_prodImage);
        tv_prodName=findViewById(R.id.tv_prodName);

        bt_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("hello");

                getCartId();

            }
        });

        addData();

    }


    public void addData()
    {

        Call<MerchantProductResponse> call=api.getPriorityMerchant(prod_id);
        call.enqueue(new Callback<MerchantProductResponse>() {
            @Override
            public void onResponse(Call<MerchantProductResponse> call, Response<MerchantProductResponse> response) {
                System.out.println(response.body());
                tv_price.setText(response.body().getPrice()+"");
                tv_salePrice.setText(response.body().getSalePrice()+"");
                tv_prodName.setText(prod_name);
                tv_sellerName.setText(response.body().getMerchant().getMerchantName());
                tv_sellerRating.setText(response.body().getMerchant().getMerchantRating()+"");
                merchant_id=response.body().getMerchant().getMerchantId();

                // price.add(response.body().getPrice());

            }

            @Override
            public void onFailure(Call<MerchantProductResponse> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });


    }


    public void getCartId()
    {
        Call<String> call=apiCart.getCartId("4b726cbf-a5a1-4118-9d71-a239508b5172");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.body());
                addToCart(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });


    }


    public void addToCart(String cartId)
    {
        CartProduct cartProduct=new CartProduct();
        Cart cart=new Cart();
        cart.setCartId(cartId);
        cartProduct.setCart(cart);
        cartProduct.setMerchantId(merchant_id);
        cartProduct.setProductCount("1");
        cartProduct.setProductId(prod_id);
        Call<Void> call=apiCart.addToCart(cartProduct);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                startActivity(new Intent(ProductListingActivity.this,CartActivity.class));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

}
