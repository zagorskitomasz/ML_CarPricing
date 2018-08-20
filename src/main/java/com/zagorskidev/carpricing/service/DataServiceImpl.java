package com.zagorskidev.carpricing.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zagorskidev.carpricing.domain.CarType;
import com.zagorskidev.carpricing.rest.SimpleToken;
import com.zagorskidev.carpricing.service.loaders.CarTypesLoader;
import com.zagorskidev.carpricing.service.loaders.SimpleCategory;

@Service
public class DataServiceImpl implements DataService 
{
	@Autowired
	private TokenDataService tokenDataService;
	
	@Autowired
	private CarTypesLoader typesLoader;
	
	@Override
	public Map<SimpleCategory, List<SimpleCategory>> getCarTypes() 
	{
		return typesLoader.load();
	}

	@Override
	public boolean loadCarTypeData(CarType type) 
	{
		return false;
	}

	@Override
	public void saveToken(SimpleToken authToken) 
	{
		tokenDataService.saveToken(authToken);
	}

	@Override
	public SimpleToken loadToken() 
	{
		return tokenDataService.loadToken();
	}
}
