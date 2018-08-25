package com.zagorskidev.carpricing.rest.parsing.categories;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Categories 
{	
	private Category[] categories;
	
	public Categories()
	{
		
	}

	public Category[] getCategories() 
	{
		return categories;
	}

	public void setCategories(Category[] categories) 
	{
		this.categories = categories;
	}
}
