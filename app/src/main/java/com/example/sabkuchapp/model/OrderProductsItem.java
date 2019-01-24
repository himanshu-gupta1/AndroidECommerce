package com.example.sabkuchapp.model;


import com.google.gson.annotations.SerializedName;


public class OrderProductsItem{

	@SerializedName("productId")
	private String productId;

	@SerializedName("price")
	private String price;

	@SerializedName("orderProductId")
	private String orderProductId;

	@SerializedName("productCount")
	private int productCount;

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setOrderProductId(String orderProductId){
		this.orderProductId = orderProductId;
	}

	public String getOrderProductId(){
		return orderProductId;
	}

	public void setProductCount(int productCount){
		this.productCount = productCount;
	}

	public int getProductCount(){
		return productCount;
	}

	@Override
 	public String toString(){
		return 
			"OrderProductsItem{" + 
			"productId = '" + productId + '\'' + 
			",price = '" + price + '\'' + 
			",orderProductId = '" + orderProductId + '\'' + 
			",productCount = '" + productCount + '\'' + 
			"}";
		}
}