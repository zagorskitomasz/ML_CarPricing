package com.zagorskidev.carpricing.domain;

import java.util.HashMap;
import java.util.Map;

public class CarParams
{	
	private Map<String, Integer> params;
	
	public CarParams()
	{
		params = new HashMap<>();
	}
	
	public void setParam(String name, Integer value) 
	{
		params.put(name, value);
	}
	
	public void setParams(Map<String, Integer> params) 
	{
		this.params = params;
	}
	
	public Map<String, Integer> getParams()
	{
		return params;
	}
}
