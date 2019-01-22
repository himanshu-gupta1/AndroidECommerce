package com.example.sabkuchapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sabkuchapp.R;

import org.w3c.dom.Text;

import java.util.List;


public class CartListAdapter extends RecyclerView.Adapter {
    private List<String> cartList;
    public CartListAdapter(List<String> cartList) {

        this.cartList=cartList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_list_item, viewGroup, false);
        return new CartViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    class CartViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName;
        private TextView productPrice;
        private ImageView plusButtton;
        private ImageView minusButton;
        private TextView productQuantity;
        private Button buttonRemove;

        private View view;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.productImage);
            productName=itemView.findViewById(R.id.productName);
            productPrice=itemView.findViewById(R.id.productPrice);
            plusButtton=itemView.findViewById(R.id.plusButton);
            minusButton=itemView.findViewById(R.id.minusButton);
            productQuantity=itemView.findViewById(R.id.productQuantity);
            buttonRemove=itemView.findViewById(R.id.productRemove);
        }

        public void bind(String urlAd)
        {

            Glide.with(imgView.getContext()).load(urlAd).into(imgView);
        }
    }
}
