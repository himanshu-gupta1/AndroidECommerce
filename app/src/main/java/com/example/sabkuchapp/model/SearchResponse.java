package com.example.sabkuchapp.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class SearchResponse{

	@SerializedName("content")
	private List<ContentItem> content;

	public void setContent(List<ContentItem> content){
		this.content = content;
	}

	public List<ContentItem> getContent(){
		return content;
	}

	@Override
 	public String toString(){
		return 
			"SearchResponse{" + 
			"content = '" + content + '\'' + 
			"}";
		}
}