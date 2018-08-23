package com.zagorskidev.carpricing.service;

import java.util.List;
import java.util.Map;

import com.zagorskidev.carpricing.domain.CarType;
import com.zagorskidev.carpricing.rest.SimpleToken;
import com.zagorskidev.carpricing.service.loaders.SimpleCategory;

public interface DataService 
{
	public Map<SimpleCategory, List<SimpleCategory>> getCarTypes(String parent);
	public boolean loadCarTypeData(CarType type);
	public void saveToken(SimpleToken authToken);
	SimpleToken loadToken();
}
