package com.zagorskidev.carpricing.service.loaders;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zagorskidev.carpricing.rest.AllegroRest;

@Component
public class CarTypesLoaderImpl implements CarTypesLoader 
{
	@Autowired
	private AllegroRest allegroRest;
	
	@Override
	public Map<String, List<String>> load() 
	{
		Object response = allegroRest.processCarTypesRequest();
		return null;
	}
}
