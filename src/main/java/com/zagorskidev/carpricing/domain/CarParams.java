package com.zagorskidev.carpricing.domain;

import java.util.HashMap;
import java.util.Map;

public class CarParams
{	
	private Map<String, Double> params;
	
	public CarParams()
	{
		params = new HashMap<>();
	}
	
	public void setParam(String name, Double value) 
	{
		params.put(name, value);
	}
	
	public void setParams(Map<String, Double> params) 
	{
		this.params = params;
	}
	
	public Map<String, Double> getParams()
	{
		return params;
	}
}
