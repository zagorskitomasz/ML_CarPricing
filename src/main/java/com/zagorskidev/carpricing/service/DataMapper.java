package com.zagorskidev.carpricing.service;

import java.util.Collection;

import com.zagorskidev.carpricing.domain.CarParameters;
import com.zagorskidev.carpricing.domain.CarParams;

import weka.core.Instances;

public interface DataMapper 
{
	public Instances map(Collection<CarParams> cars);
	public Instances map(CarParameters params);
}
