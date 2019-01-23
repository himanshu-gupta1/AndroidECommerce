package com.example.sabkuchapp.model;

import com.google.gson.annotations.SerializedName;


public class Merchant{

	@SerializedName("merchantCity")
	private String merchantCity;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("merchantRating")
	private double merchantRating;

	@SerializedName("merchantName")
	private String merchantName;

	public void setMerchantCity(String merchantCity){
		this.merchantCity = merchantCity;
	}

	public String getMerchantCity(){
		return merchantCity;
	}

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public void setMerchantRating(double merchantRating){
		this.merchantRating = merchantRating;
	}

	public double getMerchantRating(){
		return merchantRating;
	}

	public void setMerchantName(String merchantName){
		this.merchantName = merchantName;
	}

	public String getMerchantName(){
		return merchantName;
	}

	@Override
 	public String toString(){
		return 
			"Merchant{" + 
			"merchantCity = '" + merchantCity + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",merchantRating = '" + merchantRating + '\'' + 
			",merchantName = '" + merchantName + '\'' + 
			"}";
		}
}