package com.zagorskidev.carpricing.rest.parsing.offers;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Items 
{
	private Item[] promoted;
	private Item[] regular;
	
	public Items()
	{
		
	}

	public Item[] getPromoted() 
	{
		return promoted;
	}

	public void setPromoted(Item[] promoted) 
	{
		this.promoted = promoted;
	}

	public Item[] getRegular() 
	{
		return regular;
	}

	public void setRegular(Item[] regular) 
	{
		this.regular = regular;
	}
}
