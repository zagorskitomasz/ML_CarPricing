package com.zagorskidev.carpricing.service.regression;

import com.zagorskidev.carpricing.domain.CarParameters;

public interface RegressionService 
{
	public Double predict(CarParameters params);
}
