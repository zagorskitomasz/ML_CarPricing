package com.zagorskidev.carpricing.service;

import java.util.Collection;

import com.zagorskidev.carpricing.domain.CarParams;

public interface CarDataService 
{
	public Collection<CarParams> load(Integer category);
}
