package com.zagorskidev.carpricing.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zagorskidev.carpricing.dao.CategoriesRepository;
import com.zagorskidev.carpricing.domain.CarParams;
import com.zagorskidev.carpricing.rest.SimpleToken;
import com.zagorskidev.carpricing.service.loaders.SimpleCategory;

@Service
public class DataServiceImpl implements DataService 
{
	@Autowired
	private TokenDataService tokenDataService;
	
	@Autowired 
	private CategoriesRepository categoriesRepository;
	
	@Autowired 
	private CarDataService carDataService;
	
	@Override
	public Collection<SimpleCategory> getCarTypes(Integer parent) 
	{
		return categoriesRepository.findByParent(parent);
	}

	@Override
	public Collection<CarParams> loadCarTypeData(Integer category) 
	{
		return carDataService.load(category);
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

	@Override
	public void saveCarTypes(Collection<SimpleCategory> categories) 
	{
		categoriesRepository.saveAll(categories);
		categoriesRepository.flush();
	}
}
