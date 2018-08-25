package com.zagorskidev.carpricing.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.zagorskidev.carpricing.domain.CarParameters;

@Service
public class RegressionServiceImpl implements RegressionService 
{
	@Override
	public BigDecimal predict(CarParameters params) 
	{
		return null;
	}
}
