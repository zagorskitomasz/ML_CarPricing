package com.zagorskidev.carpricing.rest;

import com.zagorskidev.carpricing.rest.parsing.categories.Categories;
import com.zagorskidev.carpricing.rest.parsing.offers.ItemsResponse;

public interface AllegroRest 
{
	public Categories processCarTypesRequest();
	public ItemsResponse processOffersRequest(Integer id);
	public ItemsResponse processCarSearchRequest(Integer category, String parameterName, Integer minValue,
			Integer maxValue);
}
