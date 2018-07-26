package com.zagorskidev.carpricing.domain;

public class CarParameters 
{
	private CarType carType;
	private CarParams carParams;
	
	public CarType getType() 
	{
		return carType;
	}
	
	public void setType(CarType pmType)
	{
		carType = pmType;
	}

	public CarParams getParams() 
	{
		return carParams;
	}
	
	public void setParams(CarParams pmParams)
	{
		carParams = pmParams;
	}
}
