package com.example.sabkuchapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.model.Cart;
import com.example.sabkuchapp.model.CartProduct;
import com.example.sabkuchapp.model.MerchantProductResponse;
import com.example.sabkuchapp.model.ProductResponse;
import com.example.sabkuchapp.model.UserReviewsItem;

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
    private IApiClass apiProduct;
    private Button bt_allSeller;
    private Button bt_allReview;
    private Retrofit retrofitProduct;
    private TextView tv_description;
    private TextView tv_urating;
    private TextView tv_ucomment;
    private TextView tv_uname;
    private TextView tv_specification;

    String prod_name;
    String prod_image;
    String prod_id;
    String merchant_id;
   // private OkHttpClient client = new OkHttpClient.Builder().build();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);
        retrofitProduct=AppController.retrofitProduct;
       // bt_allDetails=findViewById(R.id.bt_allDetails);
        tv_urating=findViewById(R.id.tv_urating);
        tv_uname=findViewById(R.id.tv_uname);
        tv_ucomment=findViewById(R.id.tv_ucomment);
       // tv_specification=findViewById(R.id.tv_specification);
        tv_description=findViewById(R.id.tv_description);
        bt_addToCart=findViewById(R.id.bt_addToCart);
        iv_prodImage=findViewById(R.id.iv_prodImage);
        retrofitMerchant=AppController.retrofitMerchant;
        retrofitCart=AppController.retrofitCart;
        api=retrofitMerchant.create(IApiClass.class);
        apiCart=retrofitCart.create(IApiClass.class);
        bt_allSeller=findViewById(R.id.bt_allSeller);
        prod_name=getIntent().getStringExtra("prod_name");
        prod_image=getIntent().getStringExtra("prod_image");
        prod_id=getIntent().getStringExtra("prod_id");
        tv_sellerName=findViewById(R.id.tv_sellerName);
        tv_sellerRating=findViewById(R.id.tv_sellerRating);
      //  tv_urating=findViewById(R.id.tv_urating);
        bt_allReview=findViewById(R.id.bt_allReview);
        apiProduct=retrofitProduct.create(IApiClass.class);

//        bt_allDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(ProductListingActivity.this,AllDetailsActivity.class);
//                intent.putExtra("productId",prod_id);
//                startActivity(intent);
//            }
//        });



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

        addProductData(prod_id);

        bt_allSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProductListingActivity.this,AllMerchantActivity.class);
                intent.putExtra("prod_id",prod_id);
                startActivity(intent);
            }
        });

        addData();

        bt_allReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProductListingActivity.this,userReviewActivity.class);
                intent.putExtra("prod_id",prod_id);
                startActivity(intent);
            }
        });

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


    public void addProductData(String productId){

        Call<ProductResponse> call=apiProduct.getProduct(productId);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                System.out.println(response.body());
                tv_description.setText(response.body().getDescription()+"");
                if(response.body().getUserReviews()!=null)
                {
                    UserReviewsItem userReviewsItem=response.body().getUserReviews().get(0);
                    tv_ucomment.setText(userReviewsItem.getUserComment());
                    tv_uname.setVisibility(View.INVISIBLE);
                   // tv_urating.setText(userReviewsItem.getUserRatingOnProduct());
                }

                //tv_specification.setText(response.body().getSpecification().toString());
                Glide.with(ProductListingActivity.this)
                        .load(response.body().getProductImage())
                        .into(iv_prodImage);




            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });


    }

}
