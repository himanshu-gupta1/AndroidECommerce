package com.example.sabkuchapp.model;


import com.google.gson.annotations.SerializedName;


public class CartProduct{

	@SerializedName("productId")
	private String productId;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("productCount")
	private String productCount;

	@SerializedName("cart")
	private Cart cart;

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public void setProductCount(String productCount){
		this.productCount = productCount;
	}

	public String getProductCount(){
		return productCount;
	}

	public void setCart(Cart cart){
		this.cart = cart;
	}

	public Cart getCart(){
		return cart;
	}

	@Override
 	public String toString(){
		return 
			"CartProduct{" + 
			"productId = '" + productId + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",productCount = '" + productCount + '\'' + 
			",cart = '" + cart + '\'' + 
			"}";
		}
}