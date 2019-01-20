package com.example.sabkuchapp.model;


import com.google.gson.annotations.SerializedName;


public class Specification{

	@SerializedName("specificationId")
	private String specificationId;

	@SerializedName("occasion")
	private String occasion;

	@SerializedName("color")
	private String color;

	@SerializedName("material")
	private String material;

	@SerializedName("pattern")
	private String pattern;

	@SerializedName("idealFor")
	private String idealFor;

	public void setSpecificationId(String specificationId){
		this.specificationId = specificationId;
	}

	public String getSpecificationId(){
		return specificationId;
	}

	public void setOccasion(String occasion){
		this.occasion = occasion;
	}

	public String getOccasion(){
		return occasion;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setMaterial(String material){
		this.material = material;
	}

	public String getMaterial(){
		return material;
	}

	public void setPattern(String pattern){
		this.pattern = pattern;
	}

	public String getPattern(){
		return pattern;
	}

	public void setIdealFor(String idealFor){
		this.idealFor = idealFor;
	}

	public String getIdealFor(){
		return idealFor;
	}

	@Override
 	public String toString(){
		return 
			"Specification{" + 
			"specificationId = '" + specificationId + '\'' + 
			",occasion = '" + occasion + '\'' + 
			",color = '" + color + '\'' + 
			",material = '" + material + '\'' + 
			",pattern = '" + pattern + '\'' + 
			",idealFor = '" + idealFor + '\'' + 
			"}";
		}
}