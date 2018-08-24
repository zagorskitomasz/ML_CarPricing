package com.zagorskidev.carpricing.service.loaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zagorskidev.carpricing.rest.parsing.Categories;

public class CategoriesProcessor 
{
	public static final Integer OSOBOWE_CATEGORY = 4029;
	private static final String POZOSTALE = "Pozostałe";
	
	private Categories categories;
	private Map<SimpleCategory, List<SimpleCategory>> categoriesMap;
	private Map<Integer, List<SimpleCategory>> categoriesIdMap;
	
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
			if(String.valueOf(OSOBOWE_CATEGORY).equals(element.getParent().getId()))
			{
				List<SimpleCategory> subcategories = new ArrayList<>();
				categoriesIdMap.put(Integer.valueOf(element.getId()), subcategories);
				categoriesMap.put(new SimpleCategory(Integer.valueOf(element.getId()), element.getName(), Integer.valueOf(OSOBOWE_CATEGORY)),subcategories);
			}
		});
	}
	
	private void putCarsInSubcategories() 
	{
		Arrays.asList(categories.getCategories()).forEach(element -> 
		{
			if(!element.getName().equals(POZOSTALE)
					&& element.getId().chars().allMatch(Character::isDigit) 
					&& element.getParent().getId().chars().allMatch(Character::isDigit)
					&& categoriesIdMap.containsKey(Integer.valueOf(element.getParent().getId())))
				categoriesIdMap.get(Integer.valueOf(element.getParent().getId())).add(new SimpleCategory(Integer.valueOf(element.getId()), element.getName(), Integer.valueOf(element.getParent().getId())));
		});
	}
}
