package com.example.sabkuchapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sabkuchapp.AppController;
import com.example.sabkuchapp.CartActivity;
import com.example.sabkuchapp.R;
import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.model.Cart;
import com.example.sabkuchapp.model.CartProduct;
import com.example.sabkuchapp.model.CartProductResponse;
import com.example.sabkuchapp.model.CartProductUpdate;
import com.example.sabkuchapp.model.ContentItem;
import com.example.sabkuchapp.model.MerchantProductResponse;
import com.example.sabkuchapp.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private List<CartProductResponse> cartProductResponseList;
    private Context context;
    private Retrofit retrofitProduct= AppController.retrofitProduct;
    private Retrofit retrofitMerchant= AppController.retrofitMerchant;
    private IApiClass api1=retrofitProduct.create(IApiClass.class);
    private IApiClass api2=retrofitMerchant.create(IApiClass.class);
    private IApiClass api3=AppController.retrofitCart.create(IApiClass.class);
    public static ClickListener clickListener;


    //private IRvAdapterCommunicator communicator;
//    int flag=0;
//    int cnt=0;


    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }



    public void setOnItemClickListener(ClickListener clickListener) {
        CartAdapter.clickListener = clickListener;
    }

    public CartAdapter(List<CartProductResponse> cartProductResponseList, Context context) {
        this.cartProductResponseList = cartProductResponseList;
        this.context = context;
        //communicator= (IRvAdapterCommunicator) context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_list_item, viewGroup, false);
        RecyclerView.ViewHolder viewHolder= new CartViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((CartViewHolder)viewHolder).bind(cartProductResponseList.get(i));

        //((ProductSearchAdapter.ProductViewHolder)viewHolder).bind(productResponseList.get(i));

    }

    @Override
    public int getItemCount() {
        return cartProductResponseList.size();

    }


    class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView productImage;
        private TextView productName;
        private TextView productPrice;
        private ImageView minusButton;
        private TextView productQuantity;
        private ImageView plusButton;
        private Button productRemove;


        public CartViewHolder(View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.productImage);
            productName=itemView.findViewById(R.id.productName);
            productPrice=itemView.findViewById(R.id.productPrice);
            minusButton=itemView.findViewById(R.id.minusButton);
            productQuantity=itemView.findViewById(R.id.productQuantity);
            plusButton=itemView.findViewById(R.id.plusButton);
            productRemove=itemView.findViewById(R.id.productRemove);
        }

        public void bind(final CartProductResponse cartProductResponse) {
           // tv_seachProdName.setText(contentItem.getProductName());
//            Glide.with(context)
//                    .load(imageUrl)
//                    .into(productImage);

           // productName.setText(cartProductResponse.getProductId());
            productQuantity.setText(cartProductResponse.getProductCount()+"");

            addProductData(cartProductResponse.getProductId());
            addPriceData(cartProductResponse.getProductId(),cartProductResponse.getMerchantId(),cartProductResponse.getProductCount());

            productRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    removeFromCart(cartProductResponse.getProductId());

                }
            });

            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // cartProductResponse.setProductCount(cartProductResponse.getProductCount()+1);
                    final Cart cart=new Cart();
                    final CartProductUpdate cartProductUpdate=new CartProductUpdate();
                    cartProductUpdate.setProductId(cartProductResponse.getProductId());
                    cartProductUpdate.setProductCount(cartProductResponse.getProductCount()+1+"");
                    cartProductUpdate.setMerchantId(cartProductResponse.getMerchantId());
                    cartProductUpdate.setCartProductId(cartProductResponse.getCartProductId());
                    Call<String> call=api3.getCartId("4b726cbf-a5a1-4118-9d71-a239508b5172");
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            cart.setCartId(response.body());
                            cartProductUpdate.setCart(cart);
                            Call<Void> call1=api3.updateCart(cartProductUpdate);
                            call1.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    System.out.println("product updated");
                                    ((CartActivity)context).updateProduct1(cartProductResponse.getProductId());
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });


                    api3.updateCart(cartProductUpdate);
                }
            });


            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(cartProductResponse.getProductCount()>1) {
                        final Cart cart = new Cart();
                        final CartProductUpdate cartProductUpdate = new CartProductUpdate();
                        cartProductUpdate.setProductId(cartProductResponse.getProductId());
                        cartProductUpdate.setProductCount(cartProductResponse.getProductCount() - 1 + "");
                        cartProductUpdate.setMerchantId(cartProductResponse.getMerchantId());
                        cartProductUpdate.setCartProductId(cartProductResponse.getCartProductId());
                        Call<String> call = api3.getCartId("4b726cbf-a5a1-4118-9d71-a239508b5172");
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                cart.setCartId(response.body());
                                cartProductUpdate.setCart(cart);
                                Call<Void> call1 = api3.updateCart(cartProductUpdate);
                                call1.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        System.out.println("product updated");
                                        ((CartActivity) context).updateProduct2(cartProductResponse.getProductId());
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {

                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });


                        api3.updateCart(cartProductUpdate);
                    }
                    else
                    {
                        Toast.makeText(context,"you cant, decrease the quantity furthur",Toast.LENGTH_SHORT).show();
                    }

                }
            });






        }





        public void addProductData(String productId)
        {
            Call<ProductResponse> call=api1.getProduct(productId);
            call.enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    System.out.println(response.body());
                    productName.setText(response.body().getProductName());

//                    tv_price.setText(response.body().getPrice()+"");
//                    tv_salePrice.setText(response.body().getSalePrice()+"");
                    // tv_prodName.setText(productResponse.);
                    // price.add(response.body().getPrice());

                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    System.out.println(t.getMessage());

                }
            });

        }


        public void addPriceData(String productId,String merchant_id,final int count)
        {
            Call<MerchantProductResponse> call=api2.getMerchantProduct(productId,merchant_id);
            call.enqueue(new Callback<MerchantProductResponse>() {
                @Override
                public void onResponse(Call<MerchantProductResponse> call, Response<MerchantProductResponse> response) {
                    productPrice.setText((count*response.body().getSalePrice())+"");

                }

                @Override
                public void onFailure(Call<MerchantProductResponse> call, Throwable t) {

                }
            });

        }


        @Override
        public void onClick(View view) {
              clickListener.onItemClick(getAdapterPosition(),view);

        }
    }


    public void removeFromCart(final String productId)
    {

        System.out.println(productId);
        Call<Void> call=api3.deleteFromCart("4b726cbf-a5a1-4118-9d71-a239508b5172",productId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("deleted");
                ((CartActivity)context).deleteData(productId);



            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }


}
