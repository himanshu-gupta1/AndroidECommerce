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
import com.example.sabkuchapp.ProductDisplayActivity;
import com.example.sabkuchapp.R;
import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.model.Category;
import com.example.sabkuchapp.model.ContentItem;
import com.example.sabkuchapp.model.SubCategoryItem;

import java.util.List;

import retrofit2.Retrofit;

public class SubCategoryDisplayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SubCategoryItem> subCategoryItemList;
    private Context context;

    //private IRvAdapterCommunicator communicator;
//    int flag=0;
//    int cnt=0;

    public SubCategoryDisplayAdapter(List<SubCategoryItem> subCategoryItemList, Context context) {
        this.subCategoryItemList = subCategoryItemList;
        this.context = context;
        System.out.println("called");
        //communicator= (IRvAdapterCommunicator) context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_list_item, viewGroup, false);
        RecyclerView.ViewHolder viewHolder= new SubCategoryViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //System.out.println("on BindViewHolder"+subCategoryItemList);

        ((SubCategoryViewHolder)viewHolder).bind(subCategoryItemList.get(i));

        //((ProductSearchAdapter.ProductViewHolder)viewHolder).bind(subCategoryItemList.get(i));

    }

    @Override
    public int getItemCount() {
       // System.out.println("called "+subCategoryItemList.size());
        return subCategoryItemList.size();

    }


    class SubCategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_subCategory;
        private TextView tv_subCategory;
        private LinearLayout ll_click;

        public SubCategoryViewHolder(View itemView) {
            super(itemView);
            iv_subCategory=itemView.findViewById(R.id.iv_subCategory);
            tv_subCategory=itemView.findViewById(R.id.tv_subCategory);
            ll_click=itemView.findViewById(R.id.ll_click);
            //tv_seachProdName = itemView.findViewById(R.id.tv_searchProdName);
            //iv_image=itemView.findViewById(R.id.iv_image);
        }

        public void bind(final SubCategoryItem subCategoryItem) {
            tv_subCategory.setText(subCategoryItem.getSubcategoryName());
            ll_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,ProductDisplayActivity.class);
                    intent.putExtra("subCategory",subCategoryItem.getSubcategoryName());
                    context.startActivity(intent);
                }
            });
            Glide.with(context)
                    .load(subCategoryItem.getSubcategoryImage())
                    .into(iv_subCategory);

        }


    }





}
