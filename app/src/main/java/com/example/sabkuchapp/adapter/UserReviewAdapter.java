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
import com.example.sabkuchapp.model.UserReviewsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserReviewAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{



    private List<UserReviewsItem> userReviewsItemList;
    private Context context;




    public UserReviewAdapter(List<UserReviewsItem> userReviewItemsList, Context context) {
        this.userReviewsItemList = userReviewItemsList;
        this.context=context;
        // retrofitMerchant= AppController.retrofitMerchant;

        //communicator= (IRvAdapterCommunicator) context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_review, viewGroup, false);
        RecyclerView.ViewHolder viewHolder=new UserViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


        ((UserViewHolder)viewHolder).bind(userReviewsItemList.get(i));

    }

    @Override
    public int getItemCount() {
        return userReviewsItemList.size();

    }






    class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_userName;
        private TextView tv_userRating;
        private TextView tv_userComment;

        public UserViewHolder(View itemView) {
            super(itemView);
          tv_userName=itemView.findViewById(R.id.tv_userName);
          tv_userRating=itemView.findViewById(R.id.tv_userRating);
          tv_userComment=itemView.findViewById(R.id.tv_userComment);



            //iv_image=itemView.findViewById(R.id.iv_image);
        }

        public void bind( UserReviewsItem userReviewsItem)
        {
           tv_userRating.setText(userReviewsItem.getUserRatingOnProduct());
           tv_userComment.setText(userReviewsItem.getUserComment());
//            Glide.with(context)
//                    .load(imageUrl)
//                    .into(iv_image);

        }





    }


}
