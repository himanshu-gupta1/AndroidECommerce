package com.example.sabkuchapp.model;


import com.google.gson.annotations.SerializedName;


public class CartProductResponse{

	@SerializedName("cartProductId")
	private String cartProductId;

	@SerializedName("productId")
	private String productId;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("productCount")
	private int productCount;

	public void setCartProductId(String cartProductId){
		this.cartProductId = cartProductId;
	}

	public String getCartProductId(){
		return cartProductId;
	}

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

	public void setProductCount(int productCount){
		this.productCount = productCount;
	}

	public int getProductCount(){
		return productCount;
	}

	@Override
 	public String toString(){
		return 
			"CartProductResponse{" + 
			"cartProductId = '" + cartProductId + '\'' + 
			",productId = '" + productId + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",productCount = '" + productCount + '\'' + 
			"}";
		}
}