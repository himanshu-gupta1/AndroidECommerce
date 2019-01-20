package com.example.sabkuchapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ProductResponse{

	@SerializedName("subCategory")
	private List<SubCategoryItem> subCategory;

	@SerializedName("usp")
	private String usp;

	@SerializedName("productImage")
	private String productImage;

	@SerializedName("productId")
	private String productId;

	@SerializedName("description")
	private String description;

	@SerializedName("specification")
	private Specification specification;

	@SerializedName("brand")
	private Brand brand;

	@SerializedName("productName")
	private String productName;

	public void setSubCategory(List<SubCategoryItem> subCategory){
		this.subCategory = subCategory;
	}

	public List<SubCategoryItem> getSubCategory(){
		return subCategory;
	}

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

	public void setSpecification(Specification specification){
		this.specification = specification;
	}

	public Specification getSpecification(){
		return specification;
	}

	public void setBrand(Brand brand){
		this.brand = brand;
	}

	public Brand getBrand(){
		return brand;
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
			"ProductResponse{" + 
			"subCategory = '" + subCategory + '\'' + 
			",usp = '" + usp + '\'' + 
			",productImage = '" + productImage + '\'' + 
			",productId = '" + productId + '\'' + 
			",description = '" + description + '\'' + 
			",specification = '" + specification + '\'' + 
			",brand = '" + brand + '\'' + 
			",productName = '" + productName + '\'' + 
			"}";
		}
}