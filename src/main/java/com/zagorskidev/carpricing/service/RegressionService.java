package com.zagorskidev.carpricing.service;

import com.zagorskidev.carpricing.domain.CarParams;

public interface RegressionService 
{
	void trainClassifier();
	String predict(CarParams params);
}
