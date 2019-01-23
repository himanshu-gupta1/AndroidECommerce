package com.example.sabkuchapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ContentItem{

	@SerializedName("usp")
	private String usp;

	@SerializedName("productImage")
	private String productImage;

	@SerializedName("productId")
	private String productId;

	@SerializedName("description")
	private String description;

	@SerializedName("categories")
	private List<String> categorie;

	@SerializedName("brand")
	private List<String> brand;

	@SerializedName("productName")
	private String productName;

	@SerializedName("subCategories")
	private List<String> subCategories;

	public void setUsp(String usp){
		this.usp = usp;
	}

	public String getUsp(){
		return usp;
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

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public List<String> getCategorie() {
		return categorie;
	}

	public void setCategorie(List<String> categorie) {
		this.categorie = categorie;
	}

	public void setBrand(List<String> brand){
		this.brand = brand;
	}

	public List<String> getBrand(){
		return brand;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setSubCategories(List<String> subCategories){
		this.subCategories = subCategories;
	}

	public List<String> getSubCategories(){
		return subCategories;
	}

	@Override
 	public String toString(){
		return 
			"ContentItem{" + 
			"usp = '" + usp + '\'' + 
			",productImage = '" + productImage + '\'' + 
			",productId = '" + productId + '\'' + 
			",description = '" + description + '\'' + 
			",categories = '" + categorie + '\'' +
			",brand = '" + brand + '\'' + 
			",productName = '" + productName + '\'' + 
			",subCategories = '" + subCategories + '\'' + 
			"}";
		}
}