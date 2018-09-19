package com.zagorskidev.carpricing.service.regression;

import java.util.Collection;

import com.zagorskidev.carpricing.domain.CarParams;

import weka.core.Instances;

public interface DataMapper 
{
	public Instances map(Collection<CarParams> cars);
	public Instances map(CarParams params);
}
