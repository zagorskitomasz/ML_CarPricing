package com.zagorskidev.carpricing.service.loaders;

import java.util.List;
import java.util.Map;

public interface CarTypesLoader 
{
	public Map<SimpleCategory, List<SimpleCategory>> load();
}
