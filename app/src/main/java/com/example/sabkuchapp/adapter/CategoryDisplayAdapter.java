package com.example.sabkuchapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sabkuchapp.AppController;
import com.example.sabkuchapp.R;
import com.example.sabkuchapp.Rest.IApiClass;
import com.example.sabkuchapp.model.Category;
import com.example.sabkuchapp.model.SubCategoryItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryDisplayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Category> categoryList;
    private Context context;
    private Retrofit retrofit;
    private IApiClass api;
    private List<SubCategoryItem> subCategoryItemList;
    private SubCategoryDisplayAdapter subCategoryDisplayAdapter;


    public CategoryDisplayAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context=context;
        retrofit= AppController.retrofitProduct;

        api = retrofit.create(IApiClass.class);

        //communicator= (IRvAdapterCommunicator) context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_list_item, viewGroup, false);
        RecyclerView.ViewHolder viewHolder=new CategoryViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


        ((CategoryViewHolder)viewHolder).bind(categoryList.get(i));

    }

    @Override
    public int getItemCount() {
        return categoryList.size();

    }






    class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_category;
        private RecyclerView rv_subCategory;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            tv_category=itemView.findViewById(R.id.tv_category);
            rv_subCategory=itemView.findViewById(R.id.rv_subCategory);
           // rv_subCategory.setLayoutManager(new GridLayoutManager(context,4));


        }

        public void bind(Category category)
        {
            tv_category.setText(category.getCategoryName());
            rv_subCategory.setLayoutManager(new GridLayoutManager(context,4));
            subCategoryItemList=new ArrayList<>();

            addData(category.getCategoryName());




        }

        public void addData(String categoryName)
        {

            Call<List<SubCategoryItem>> call = api.getSubCategoryByCategory(categoryName);
            call.enqueue(new Callback<List<SubCategoryItem>>() {
                @Override
                public void onResponse(Call<List<SubCategoryItem>> call, Response<List<SubCategoryItem>> response) {
                    subCategoryItemList=response.body();
                    System.out.println(subCategoryItemList);
                    System.out.println(subCategoryItemList.size());

                    // System.out.println(productResponseList);
                    //SubCategoryDisplayAdapter=new SubCategoryDisplayAdapter(subCategoryItemList,context);
//                productDisplayAdapter.notifyDataSetChanged();
                    subCategoryDisplayAdapter=new SubCategoryDisplayAdapter(subCategoryItemList,context);
                    rv_subCategory.setAdapter(subCategoryDisplayAdapter);
                    //subCategoryDisplayAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<List<SubCategoryItem>> call, Throwable t) {
                    System.out.println("failure");

                }
            });


        }

    }

}
