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

import com.bumptech.glide.Glide;
import com.example.sabkuchapp.ProductListingActivity;
import com.example.sabkuchapp.R;
import com.example.sabkuchapp.model.ContentItem;

import java.util.List;

public class ProductSearchAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<ContentItem> productResponseList;
    private Context context;
    //private IRvAdapterCommunicator communicator;
//    int flag=0;
//    int cnt=0;

    public ProductSearchAdapter(List<ContentItem> productResponseList, Context context) {
        this.productResponseList = productResponseList;
        this.context = context;
        //communicator= (IRvAdapterCommunicator) context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_list_item, viewGroup, false);
        RecyclerView.ViewHolder viewHolder= new ProductViewHolder(view);

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


    class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_searchProdImage;
        private TextView tv_seachProdName;
        private LinearLayout ll_clickon;

        public ProductViewHolder(View itemView) {
            super(itemView);
            tv_seachProdName = itemView.findViewById(R.id.tv_searchProdName);
            ll_clickon=itemView.findViewById(R.id.ll_clickon);
            iv_searchProdImage=itemView.findViewById(R.id.iv_searchProdImage);
            //iv_image=itemView.findViewById(R.id.iv_image);


        }

        public void bind(final ContentItem contentItem) {
            tv_seachProdName.setText(contentItem.getProductName());
            Glide.with(context)
                    .load(contentItem.getProductImage())
                    .into(iv_searchProdImage);
            ll_clickon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, ProductListingActivity.class);
                    intent.putExtra("prod_id",contentItem.getProductId());
                    intent.putExtra("prod_name",contentItem.getProductName());
                    intent.putExtra("prod_image",contentItem.getProductImage());

                    context.startActivity(intent);
                }
            });

        }


    }


}
