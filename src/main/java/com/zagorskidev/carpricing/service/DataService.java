package com.zagorskidev.carpricing.service;

import java.util.List;
import java.util.Map;

import com.zagorskidev.carpricing.domain.CarType;

public interface DataService 
{
	Map<String, List<String>> getCarTypes();
	boolean loadCarTypeData(CarType type);
}
