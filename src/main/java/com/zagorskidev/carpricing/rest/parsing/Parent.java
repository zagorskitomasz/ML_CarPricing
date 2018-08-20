package com.zagorskidev.carpricing.rest.parsing;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Parent 
{
	private String id;

	public Parent()
	{
		
	}
	
	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}
}
