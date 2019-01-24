package com.example.sabkuchapp.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class OrderResponse{

	@SerializedName("orderPlacedDate")
	private String orderPlacedDate;

	@SerializedName("orderId")
	private String orderId;

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

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
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

	@Override
 	public String toString(){
		return 
			"OrderResponse{" + 
			"orderPlacedDate = '" + orderPlacedDate + '\'' + 
			",orderId = '" + orderId + '\'' + 
			",orderProducts = '" + orderProducts + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}