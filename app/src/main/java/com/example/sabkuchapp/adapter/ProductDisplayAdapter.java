package com.example.sabkuchapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sabkuchapp.R;
import com.example.sabkuchapp.model.ProductResponse;

import java.util.List;


public class ProductDisplayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<ProductResponse> productResponseList;
    private Context context;
    //private IRvAdapterCommunicator communicator;
//    int flag=0;
//    int cnt=0;

    public ProductDisplayAdapter(List<ProductResponse> productResponseList, Context context) {
        this.productResponseList = productResponseList;
        this.context=context;
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

        public ProductViewHolder(View itemView) {
            super(itemView);
            iv_prodImage=itemView.findViewById(R.id.iv_prodImage);
            tv_prodName=itemView.findViewById(R.id.tv_prodName);
            tv_price=itemView.findViewById(R.id.tv_price);
            //iv_image=itemView.findViewById(R.id.iv_image);
        }

        public void bind(ProductResponse productResponse)
        {
            tv_prodName.setText(productResponse.getProductName());
            tv_price.setText("120");
//            Glide.with(context)
//                    .load(imageUrl)
//                    .into(iv_image);

        }

    }





}
