package com.zagorskidev.carpricing.service.loaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zagorskidev.carpricing.rest.parsing.categories.Categories;
import com.zagorskidev.carpricing.rest.parsing.categories.Category;

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
			if(!isValidCategory(element))
				return;
			
			Integer id = Integer.valueOf(element.getId());
			Integer parent = Integer.valueOf(element.getParent().getId());
			
			if(OSOBOWE_CATEGORY.equals(parent))
			{
				List<SimpleCategory> subcategories = new ArrayList<>();
				categoriesIdMap.put(Integer.valueOf(element.getId()), subcategories);
				categoriesMap.put(new SimpleCategory(id, element.getName(), OSOBOWE_CATEGORY),subcategories);
			}
		});
	}
	
	private void putCarsInSubcategories() 
	{
		Arrays.asList(categories.getCategories()).forEach(element -> 
		{
			if(!isValidCategory(element))
				return;
			
			Integer id = Integer.valueOf(element.getId());
			Integer parent = Integer.valueOf(element.getParent().getId());
			
			if(categoriesIdMap.containsKey(parent))
				categoriesIdMap.get(parent).add(new SimpleCategory(id, element.getName(), parent));
		});
	}

	private boolean isValidCategory(Category element) 
	{
		return isDigit(element.getId()) && isDigit(element.getParent().getId()) && !element.getName().equals(POZOSTALE);
	}
	
	private boolean isDigit(String value)
	{
		return value == null || value.chars().allMatch(Character::isDigit);
	}
}
