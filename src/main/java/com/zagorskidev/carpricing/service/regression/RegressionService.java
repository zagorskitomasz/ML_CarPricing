package com.zagorskidev.carpricing.service.regression;

import java.math.BigDecimal;

import com.zagorskidev.carpricing.domain.CarParameters;

public interface RegressionService 
{
	public BigDecimal predict(CarParameters params);
}
