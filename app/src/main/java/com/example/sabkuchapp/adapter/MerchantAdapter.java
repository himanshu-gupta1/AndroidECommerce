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
import com.example.sabkuchapp.model.ContentItem;
import com.example.sabkuchapp.model.MerchantProductResponse;

import java.util.List;

public class MerchantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MerchantProductResponse> merchantProductResponseList;
    private Context context;
    //private IRvAdapterCommunicator communicator;
//    int flag=0;
//    int cnt=0;

    public MerchantAdapter(List<MerchantProductResponse> merchantProductResponseList, Context context) {
        this.merchantProductResponseList = merchantProductResponseList;
        this.context = context;
        //communicator= (IRvAdapterCommunicator) context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.each_merchant_item, viewGroup, false);
        RecyclerView.ViewHolder viewHolder= new MerchantViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((MerchantViewHolder)viewHolder).bind(merchantProductResponseList.get(i));

    }

    @Override
    public int getItemCount() {
        return merchantProductResponseList.size();

    }


    class MerchantViewHolder extends RecyclerView.ViewHolder {
        private TextView productName;
        private TextView productPrice;
        private TextView merchantRating;


        public MerchantViewHolder(View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.productName);
            productPrice=itemView.findViewById(R.id.productPrice);
            merchantRating=itemView.findViewById(R.id.merchantRating);

        }

        public void bind(MerchantProductResponse merchantProductResponse) {
           productName.setText(merchantProductResponse.getMerchant().getMerchantName()+"");
           productPrice.setText(merchantProductResponse.getSalePrice()+"");
           merchantRating.setText(merchantProductResponse.getMerchant().getMerchantRating()+"");
//            Glide.with(context)
//                    .load(imageUrl)
//                    .into(iv_image);

        }


    }
}
