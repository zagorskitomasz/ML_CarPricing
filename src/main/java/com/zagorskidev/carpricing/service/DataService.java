package com.zagorskidev.carpricing.service;

import java.util.Collection;

import com.zagorskidev.carpricing.domain.CarType;
import com.zagorskidev.carpricing.rest.SimpleToken;
import com.zagorskidev.carpricing.service.loaders.SimpleCategory;

public interface DataService 
{
	public void saveCarTypes(Collection<SimpleCategory> categories);
	public Collection<SimpleCategory> getCarTypes(Integer parent);
	public boolean loadCarTypeData(CarType type);
	public void saveToken(SimpleToken authToken);
	SimpleToken loadToken();
}
