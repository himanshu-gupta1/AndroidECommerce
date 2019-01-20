package com.example.sabkuchapp.model;


import com.google.gson.annotations.SerializedName;


public class Brand{

	@SerializedName("brandName")
	private String brandName;

	@SerializedName("brandId")
	private String brandId;

	public void setBrandName(String brandName){
		this.brandName = brandName;
	}

	public String getBrandName(){
		return brandName;
	}

	public void setBrandId(String brandId){
		this.brandId = brandId;
	}

	public String getBrandId(){
		return brandId;
	}

	@Override
 	public String toString(){
		return 
			"Brand{" + 
			"brandName = '" + brandName + '\'' + 
			",brandId = '" + brandId + '\'' + 
			"}";
		}
}