package com.example.sabkuchapp.model;


import com.google.gson.annotations.SerializedName;


public class CartDisplay{

	@SerializedName("cartProductId")
	private String cartProductId;

	@SerializedName("productImage")
	private String productImage;

	@SerializedName("productId")
	private String productId;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("price")
	private String price;

	@SerializedName("productCount")
	private int productCount;

	@SerializedName("productName")
	private String productName;

	public void setCartProductId(String cartProductId){
		this.cartProductId = cartProductId;
	}

	public String getCartProductId(){
		return cartProductId;
	}

	public void setProductImage(String productImage){
		this.productImage = productImage;
	}

	public String getProductImage(){
		return productImage;
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

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setProductCount(int productCount){
		this.productCount = productCount;
	}

	public int getProductCount(){
		return productCount;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	@Override
 	public String toString(){
		return 
			"CartDisplay{" + 
			"cartProductId = '" + cartProductId + '\'' + 
			",productImage = '" + productImage + '\'' + 
			",productId = '" + productId + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",price = '" + price + '\'' + 
			",productCount = '" + productCount + '\'' + 
			",productName = '" + productName + '\'' + 
			"}";
		}
}