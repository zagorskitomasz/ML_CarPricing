package com.zagorskidev.carpricing.rest.parsing.offers;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemsResponse 
{
	Items items;
	
	public ItemsResponse()
	{
		
	}

	public Items getItems() 
	{
		return items;
	}

	public void setItems(Items items) 
	{
		this.items = items;
	}
}
