package com.zagorskidev.carpricing.service;

import java.util.List;
import java.util.Map;

import com.zagorskidev.carpricing.domain.CarType;
import com.zagorskidev.carpricing.rest.SimpleToken;

public interface DataService 
{
	public Map<String, List<String>> getCarTypes();
	public boolean loadCarTypeData(CarType type);
	public void saveToken(SimpleToken authToken);
	SimpleToken loadToken();
}
