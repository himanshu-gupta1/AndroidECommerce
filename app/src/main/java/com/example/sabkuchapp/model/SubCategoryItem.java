package com.example.sabkuchapp.model;


import com.google.gson.annotations.SerializedName;

public class SubCategoryItem{

	@SerializedName("subcategoryName")
	private String subcategoryName;

	@SerializedName("subcategoryImage")
	private String subcategoryImage;

	@SerializedName("subcategoryId")
	private String subcategoryId;

	@SerializedName("category")
	private Category category;

	public void setSubcategoryName(String subcategoryName){
		this.subcategoryName = subcategoryName;
	}

	public String getSubcategoryName(){
		return subcategoryName;
	}

	public void setSubcategoryImage(String subcategoryImage){
		this.subcategoryImage = subcategoryImage;
	}

	public String getSubcategoryImage(){
		return subcategoryImage;
	}

	public void setSubcategoryId(String subcategoryId){
		this.subcategoryId = subcategoryId;
	}

	public String getSubcategoryId(){
		return subcategoryId;
	}

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	@Override
 	public String toString(){
		return 
			"SubCategoryItem{" + 
			"subcategoryName = '" + subcategoryName + '\'' + 
			",subcategoryImage = '" + subcategoryImage + '\'' + 
			",subcategoryId = '" + subcategoryId + '\'' + 
			",category = '" + category + '\'' + 
			"}";
		}
}