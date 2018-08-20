package com.zagorskidev.carpricing.service.loaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zagorskidev.carpricing.rest.parsing.Categories;

public class CategoriesProcessor 
{
	private static final String OSOBOWE_CATEGORY = "4029";
	
	private Categories categories;
	private Map<SimpleCategory, List<SimpleCategory>> categoriesMap;
	private Map<String, List<SimpleCategory>> categoriesIdMap;
	
	public synchronized Map<SimpleCategory, List<SimpleCategory>> process(Categories categories) 
	{
		initVariables(categories);
		
		selectOsoboweSubcategories();
		putCarsInSubcategories();
		
		return categoriesMap;
	}

	private void initVariables(Categories categories) 
	{
		this.categories = categories;
		categoriesMap = new HashMap<>();
		categoriesIdMap = new HashMap<>();
	}

	private void selectOsoboweSubcategories()
	{
		Arrays.asList(categories.getCategories()).forEach(element -> 
		{
			if(OSOBOWE_CATEGORY.equals(element.getParent().getId()))
			{
				List<SimpleCategory> subcategories = new ArrayList<>();
				categoriesIdMap.put(element.getId(), subcategories);
				categoriesMap.put(new SimpleCategory(element.getId(), element.getName()),subcategories);
			}
		});
	}
	
	private void putCarsInSubcategories() 
	{
		Arrays.asList(categories.getCategories()).forEach(element -> 
		{
			if(categoriesIdMap.containsKey(element.getParent().getId()))
				categoriesIdMap.get(element.getParent().getId()).add(new SimpleCategory(element.getId(), element.getName()));
		});
	}
}
