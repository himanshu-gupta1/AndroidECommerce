package com.example.sabkuchapp.model;


import com.google.gson.annotations.SerializedName;

public class Cart{

	@SerializedName("cartId")
	private String cartId;

	public void setCartId(String cartId){
		this.cartId = cartId;
	}

	public String getCartId(){
		return cartId;
	}

	@Override
 	public String toString(){
		return 
			"Cart{" + 
			"cartId = '" + cartId + '\'' + 
			"}";
		}
}