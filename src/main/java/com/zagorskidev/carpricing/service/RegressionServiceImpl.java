package com.zagorskidev.carpricing.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.zagorskidev.carpricing.domain.CarParams;

@Service
public class RegressionServiceImpl implements RegressionService 
{
	@Override
	public void trainClassifier() 
	{

	}

	@Override
	public BigDecimal predict(CarParams params) 
	{
		return null;
	}
}
