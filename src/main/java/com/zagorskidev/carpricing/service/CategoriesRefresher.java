package com.zagorskidev.carpricing.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zagorskidev.carpricing.service.loaders.CarTypesLoader;
import com.zagorskidev.carpricing.service.loaders.SimpleCategory;

@Component
public class CategoriesRefresher 
{
	private static final long TWELVE_HOURS = 12 * 60 * 60 * 1000;

	@Autowired
	private CarTypesLoader typesLoader;
	
	@Autowired
	private DataService dataService;
	
	@Scheduled(fixedDelay=TWELVE_HOURS)
	public void refreshCategories()
	{
		Logger.getGlobal().log(Level.INFO, "Refreshing categories...");
		
		try
		{
			Map<SimpleCategory,List<SimpleCategory>> categories = typesLoader.load();
			persist(categories);
			Logger.getGlobal().log(Level.INFO, "Categories refreshed");
		}
		catch(Exception ex)
		{
			Logger.getGlobal().log(Level.INFO, "Error occured during refreshing categories");
			ex.printStackTrace();
		}
	}

	private void persist(Map<SimpleCategory, List<SimpleCategory>> categories) 
	{
		persist(categories.keySet());
		for(List<SimpleCategory> subcategories : categories.values())
			persist(subcategories);
	}

	private void persist(Collection<SimpleCategory> categories) 
	{
		dataService.saveCarTypes(categories);
	}
}
