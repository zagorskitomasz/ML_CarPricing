package com.zagorskidev.carpricing.service;

import java.math.BigDecimal;

import com.zagorskidev.carpricing.domain.CarParams;

public interface RegressionService 
{
	void trainClassifier();
	BigDecimal predict(CarParams params);
}
