package com.example.sabkuchapp.model;


import com.google.gson.annotations.SerializedName;

public class UserReviewsItem{

	@SerializedName("userComment")
	private String userComment;

	@SerializedName("productId")
	private String productId;

	@SerializedName("userRatingOnProduct")
	private int userRatingOnProduct;

	public void setUserComment(String userComment){
		this.userComment = userComment;
	}

	public String getUserComment(){
		return userComment;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setUserRatingOnProduct(int userRatingOnProduct){
		this.userRatingOnProduct = userRatingOnProduct;
	}

	public int getUserRatingOnProduct(){
		return userRatingOnProduct;
	}

	@Override
 	public String toString(){
		return 
			"UserReviewsItem{" + 
			"userComment = '" + userComment + '\'' + 
			",productId = '" + productId + '\'' + 
			",userRatingOnProduct = '" + userRatingOnProduct + '\'' + 
			"}";
		}
}