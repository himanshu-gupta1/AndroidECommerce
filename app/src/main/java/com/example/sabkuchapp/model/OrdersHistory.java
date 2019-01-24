package com.example.sabkuchapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrdersHistory {





    @SerializedName("orderId")
    private String orderId;

    @SerializedName("orderPlacedDate")
    private String orderPlacedDate;

    @SerializedName("orderProducts")
    private List<OrderProductsItem> orderProducts;

    @SerializedName("userId")
    private String userId;

    public void setOrderPlacedDate(String orderPlacedDate){
        this.orderPlacedDate = orderPlacedDate;
    }

    public String getOrderPlacedDate(){
        return orderPlacedDate;
    }

    public void setOrderProducts(List<OrderProductsItem> orderProducts){
        this.orderProducts = orderProducts;
    }

    public List<OrderProductsItem> getOrderProducts(){
        return orderProducts;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrdersHistory{" +
                "orderId='" + orderId + '\'' +
                ", orderPlacedDate='" + orderPlacedDate + '\'' +
                ", orderProducts=" + orderProducts +
                ", userId='" + userId + '\'' +
                '}';
    }
}
