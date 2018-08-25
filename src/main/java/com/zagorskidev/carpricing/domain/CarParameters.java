package com.zagorskidev.carpricing.domain;

public class CarParameters 
{
	private Integer category;
	private CarParams carParams;
	
	public CarParameters()
	{
		
	}
	
	public Integer getCategory() 
	{
		return category;
	}
	
	public void setCategory(Integer category)
	{
		this.category = category;
	}

	public CarParams getParams() 
	{
		return carParams;
	}
	
	public void setParams(CarParams carParams)
	{
		this.carParams = carParams;
	}
}
