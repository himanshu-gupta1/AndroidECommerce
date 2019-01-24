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
import com.example.sabkuchapp.model.Orders;
import com.example.sabkuchapp.model.OrdersHistory;

import java.util.List;


public class OrderHistoryAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {





    private List<OrdersHistory> orders;
    private Context context;
    //private IRvAdapterCommunicator communicator;
//    int flag=0;
//    int cnt=0;

    public OrderHistoryAdapter(List<OrdersHistory> orders, Context context) {
        this.orders = orders;
        this.context = context;
        //communicator= (IRvAdapterCommunicator) context;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_history_list, viewGroup, false);
        RecyclerView.ViewHolder viewHolder= new OrderHistoryViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


        ((OrderHistoryViewHolder)viewHolder).bind(orders.get(i));

    }

    @Override
    public int getItemCount() {
        return orders.size();

    }


    class OrderHistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView orderNumber;

        public OrderHistoryViewHolder(View itemView) {
            super(itemView);
            orderNumber=itemView.findViewById(R.id.orderNumber);

        }

        public void bind(OrdersHistory orders) {


            orderNumber.setText(orders.getOrderId());


        }


    }

}
