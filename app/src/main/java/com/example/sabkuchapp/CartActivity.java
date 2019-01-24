package com.example.sabkuchapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.adapter.CartAdapter;
import com.example.sabkuchapp.model.CartDisplay;
import com.example.sabkuchapp.model.CartProduct;
import com.example.sabkuchapp.model.CartProductResponse;
import com.example.sabkuchapp.model.Category;
import com.example.sabkuchapp.model.MerchantProductResponse;
import com.example.sabkuchapp.model.Order;
import com.example.sabkuchapp.model.OrderProducts;
import com.example.sabkuchapp.model.OrderProductsItem;
import com.example.sabkuchapp.model.Orders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private Button btn_continueShopping;
    private Button btn_placeOrder;
    private Retrofit retrofitMerchant=AppController.retrofitMerchant;
    private IApiClass api2;
    String price;
    String order_id;
    List<String> prices;
    int count=0;
    List<OrderProducts> orderProductsList;
    List<CartDisplay> cartDisplayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        retrofit=AppController.retrofitCart;
        api = retrofit.create(IApiClass.class);
        recycler_cart=findViewById(R.id.recycler_cart);
        cartProductResponseList=new ArrayList<>();
        prices=new ArrayList<>();
        btn_continueShopping=findViewById(R.id.btn_continueShopping);
        api2=retrofitMerchant.create(IApiClass.class);
        orderProductsList=new ArrayList<>();
        btn_placeOrder=findViewById(R.id.btn_placeorder);
        btn_continueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,HomeActivity.class));
            }
        });


        recycler_cart.setLayoutManager(new LinearLayoutManager(this));

        btn_placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("place order clicked");
                  placeOrder();


            }
        });

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
                cartAdapter.setOnItemClickListener(new CartAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {

                    }

                    @Override
                    public void onItemLongClick(int position, View v) {

                    }
                });
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


    public void updateProduct1(String productId)
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

    public void updateProduct2(String productId)
    {

        System.out.println(productId);
        System.out.println(cartProductResponseList);
        for(int i=0;i<cartProductResponseList.size();i++)
        {
            System.out.println(cartProductResponseList.get(i));
            if(cartProductResponseList.get(i).getProductId().equals(productId))
            {

                CartProductResponse cartProductResponse=cartProductResponseList.get(i);
                cartProductResponse.setProductCount(cartProductResponse.getProductCount()-1);
                cartProductResponseList.set(i,cartProductResponse);
                System.out.println("updated");

            }
        }
        cartAdapter.notifyDataSetChanged();



    }


    public  void placeOrder()
    {


        System.out.println("place order called");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        final Orders orders=new Orders();
        orders.setOrderPlacedDate(dateFormat.format(date));
        orders.setUserId("4b726cbf-a5a1-4118-9d71-a239508b5172");
        System.out.println(orders);
        Call<List<CartDisplay>> call=api.getAllProductsInCart("4b726cbf-a5a1-4118-9d71-a239508b5172");
        cartDisplayList=new ArrayList<>();
        call.enqueue(new Callback<List<CartDisplay>>() {
            @Override
            public void onResponse(Call<List<CartDisplay>> call, Response<List<CartDisplay>> response) {

                for(CartDisplay cartDisplay:response.body())
                {
                    cartDisplayList.add(cartDisplay);
                }

                List<OrderProductsItem> orderProductsItemList=new ArrayList<>();
                for(int i=0;i<cartDisplayList.size();i++)
                {
                    OrderProductsItem orderProductsItem=new OrderProductsItem();
                    orderProductsItem.setPrice(cartDisplayList.get(i).getPrice());
                    orderProductsItem.setProductCount(cartDisplayList.get(i).getProductCount());
                    orderProductsItem.setProductId(cartDisplayList.get(i).getProductId());

                    orderProductsItemList.add(orderProductsItem);

                }


                System.out.println(orderProductsItemList);
                orders.setOrderProducts(orderProductsItemList);
                System.out.println(orders);
                Call<Void> call1=api.placeOrder(orders);
                call1.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        System.out.println("order placed");
                        startActivity(new Intent(CartActivity.this,ThankyouActivity.class));
                        Call<Void> call3=api.deleteProductsFromCart("4b726cbf-a5a1-4118-9d71-a239508b5172");
                        call3.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                System.out.println("cart empty now");

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });


            }

            @Override
            public void onFailure(Call<List<CartDisplay>> call, Throwable t) {

            }
        });







    }



    public String setPriceData(String productId,String merchant_id)
    {
        count++;
        Call<MerchantProductResponse> call=api2.getMerchantProduct(productId,merchant_id);
        call.enqueue(new Callback<MerchantProductResponse>() {
            @Override
            public void onResponse(Call<MerchantProductResponse> call, Response<MerchantProductResponse> response) {
                price=response.body().getPrice()+"";
                prices.add(price);

                if(count==cartProductResponseList.size())
                {
                    for(int i=0;i<orderProductsList.size();i++)
                    {
                        orderProductsList.get(i).setPrice(prices.get(i));
                        System.out.println(orderProductsList.get(i));

                    }


                }





            }

            @Override
            public void onFailure(Call<MerchantProductResponse> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });


        return price;
    }




}
