package com.example.sabkuchapp.model;


import com.google.gson.annotations.SerializedName;


public class MerchantProductResponse{

	@SerializedName("merchantProductId")
	private String merchantProductId;

	@SerializedName("productId")
	private String productId;

	@SerializedName("salePrice")
	private int salePrice;

	@SerializedName("price")
	private int price;

	@SerializedName("merchant")
	private Merchant merchant;

	@SerializedName("stock")
	private int stock;

	public void setMerchantProductId(String merchantProductId){
		this.merchantProductId = merchantProductId;
	}

	public String getMerchantProductId(){
		return merchantProductId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setSalePrice(int salePrice){
		this.salePrice = salePrice;
	}

	public int getSalePrice(){
		return salePrice;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setMerchant(Merchant merchant){
		this.merchant = merchant;
	}

	public Merchant getMerchant(){
		return merchant;
	}

	public void setStock(int stock){
		this.stock = stock;
	}

	public int getStock(){
		return stock;
	}

	@Override
 	public String toString(){
		return 
			"MerchantProductResponse{" + 
			"merchantProductId = '" + merchantProductId + '\'' + 
			",productId = '" + productId + '\'' + 
			",salePrice = '" + salePrice + '\'' + 
			",price = '" + price + '\'' + 
			",merchant = '" + merchant + '\'' + 
			",stock = '" + stock + '\'' + 
			"}";
		}
}