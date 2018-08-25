package com.zagorskidev.carpricing.service;

import java.math.BigDecimal;

import com.zagorskidev.carpricing.domain.CarParameters;

public interface RegressionService 
{
	BigDecimal predict(CarParameters params);
}
