package com.zagorskidev.carpricing.service.loaders;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zagorskidev.carpricing.rest.AllegroRest;
import com.zagorskidev.carpricing.rest.parsing.Categories;

@Component
public class CarTypesLoaderImpl implements CarTypesLoader 
{
	@Autowired
	private AllegroRest allegroRest;
	
	@Override
	public Map<SimpleCategory, List<SimpleCategory>> load() 
	{
		Categories categories = allegroRest.processCarTypesRequest();
		return new CategoriesProcessor().process(categories);
	}
}
