package com.zagorskidev.carpricing.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zagorskidev.carpricing.domain.CarParams;
import com.zagorskidev.carpricing.rest.AllegroRest;
import com.zagorskidev.carpricing.rest.parsing.offers.Item;
import com.zagorskidev.carpricing.rest.parsing.offers.ItemsResponse;

@Component
public class CarDataServiceImpl implements CarDataService
{
	@Autowired
	private AllegroRest allegroRest;
	
	@Override
	public Collection<CarParams> load(Integer category) 
	{
		Set<Long> offersIds = loadAllOffersIds(category);
		return loadOffersDetails(offersIds, category);
	}

	private Set<Long> loadAllOffersIds(Integer category) 
	{
		ItemsResponse items = allegroRest.processOffersRequest(category);
		return extractIds(items);
	}

	private Set<Long> extractIds(ItemsResponse items) 
	{
		Set<Long> ids = new HashSet<>();
		
		for(Item item : items.getItems().getPromoted())
			ids.add(item.getId());
		
		for(Item item : items.getItems().getRegular())
			ids.add(item.getId());
		
		return ids;
	}

	private Collection<CarParams> loadOffersDetails(Set<Long> offersIds, Integer category) 
	{
		Map<Long, CarParams> carsMap = createCarsMap(offersIds);
		
		for(CarParamType type : CarParamType.values())
			updateCarsParams(category, carsMap, type);
		
		return carsMap.values();
	}

	private void updateCarsParams(Integer category, Map<Long, CarParams> carsMap, CarParamType type) 
	{
		for(int i = 0; i < type.getParameterValues().length - 1; i++)
		{
			ItemsResponse items = 
					allegroRest.processCarSearchRequest(category, type.getParameterName(), type.getParameterValues()[i], type.getParameterValues()[i+1]);
			
			Set<Long> ids = extractIds(items);
			for(Long id : ids)
			{
				if(carsMap.containsKey(id))
					carsMap.get(id).setParam(type.name(), i);
			}
		}
	}

	private Map<Long, CarParams> createCarsMap(Set<Long> offersIds) 
	{
		Map<Long, CarParams> carsMap = new HashMap<>();
		for(Long id : offersIds)
			carsMap.put(id, new CarParams());
		
		return carsMap;
	}
}
