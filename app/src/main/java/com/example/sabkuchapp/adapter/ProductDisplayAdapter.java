package com.example.sabkuchapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sabkuchapp.AppController;
import com.example.sabkuchapp.ProductListingActivity;
import com.example.sabkuchapp.R;
import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.model.ContentItem;
import com.example.sabkuchapp.model.MerchantProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ProductDisplayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {




    private List<ContentItem> productResponseList;
    private Context context;
    private Retrofit retrofitMerchant=AppController.retrofitMerchant;
    private IApiClass api=retrofitMerchant.create(IApiClass.class);;



    public ProductDisplayAdapter(List<ContentItem> productResponseList, Context context) {
        this.productResponseList = productResponseList;
        this.context=context;
       // retrofitMerchant= AppController.retrofitMerchant;

        //communicator= (IRvAdapterCommunicator) context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_item, viewGroup, false);
            RecyclerView.ViewHolder viewHolder=new ProductViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


        ((ProductViewHolder)viewHolder).bind(productResponseList.get(i));

    }

    @Override
    public int getItemCount() {
        return productResponseList.size();

    }






    class ProductViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_prodImage;
        private TextView tv_prodName;
        private TextView tv_price;
        private TextView tv_salePrice;
        private LinearLayout ll_productListing;

        public ProductViewHolder(View itemView) {
            super(itemView);
            iv_prodImage=itemView.findViewById(R.id.iv_prodImage);
            tv_prodName=itemView.findViewById(R.id.tv_prodName);
            tv_price=itemView.findViewById(R.id.tv_price);
            tv_salePrice=itemView.findViewById(R.id.tv_salePrice);
            ll_productListing=itemView.findViewById(R.id.ll_productListing);


            //iv_image=itemView.findViewById(R.id.iv_image);
        }

        public void bind(final ContentItem productResponse)
        {
            tv_prodName.setText(productResponse.getProductName());

           // tv_price.setText("120");
            ll_productListing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,ProductListingActivity.class);
                    intent.putExtra("prod_id",productResponse.getProductId());
                    intent.putExtra("prod_name",productResponse.getProductName());
                    intent.putExtra("prod_image",productResponse.getProductImage());

                    context.startActivity(intent);
                }
            });

            addPriceData(productResponse.getProductId());

//            Glide.with(context)
//                    .load(imageUrl)
//                    .into(iv_image);

        }


        public void addPriceData(String prod_id)
        {

            Call<MerchantProductResponse> call=api.getPriorityMerchant(prod_id);
            call.enqueue(new Callback<MerchantProductResponse>() {
                @Override
                public void onResponse(Call<MerchantProductResponse> call, Response<MerchantProductResponse> response) {
                    System.out.println(response.body());
                    tv_price.setText(response.body().getPrice()+"");
                    tv_salePrice.setText(response.body().getSalePrice()+"");
                  // tv_prodName.setText(productResponse.);
                    // price.add(response.body().getPrice());

                }

                @Override
                public void onFailure(Call<MerchantProductResponse> call, Throwable t) {
                    System.out.println(t.getMessage());

                }
            });


        }


    }











}
