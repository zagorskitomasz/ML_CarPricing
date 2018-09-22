package com.zagorskidev.carpricing.service.regression.polynomial;

import java.time.Year;
import java.util.Map;

import com.zagorskidev.carpricing.service.CarParamType;

public enum PolynomialFeatures 
{
	POWER_DIV_CAPACITY((carParams) -> 
	{
		Double power = carParams.get(CarParamType.POWER.name());
		Double capacity = carParams.get(CarParamType.CAPACITY.name());
		
		return power / capacity;
	}),
	
	MILEAGE_DIV_AGE((carParams) -> 
	{
		Double mileage = carParams.get(CarParamType.MILEAGE.name());
		Double age = Year.now().getValue() - carParams.get(CarParamType.YEAR.name());
		
		return mileage / age;
	}),
	
	MILEAGE_LN((carParams) -> 
	{
		Double mileage = carParams.get(CarParamType.MILEAGE.name());
		
		return Math.log(mileage);
	}),
			
	AGE_POW_2((carParams) -> 
	{
		Double age = Year.now().getValue() - carParams.get(CarParamType.YEAR.name());
		
		return Math.pow(age, 2);
	}),
	
	AGE_POW_3((carParams) -> 
	{
		Double age = Year.now().getValue() - carParams.get(CarParamType.YEAR.name());
		
		return Math.pow(age, 3);
	}),
	
	AGE_LN((carParams) -> 
	{
		Double age = Year.now().getValue() - carParams.get(CarParamType.YEAR.name());
		
		return Math.log(age);
	});
	
	private PolynomialMethod method;
	
	private PolynomialFeatures(PolynomialMethod method)
	{
		this.method = method;
	}
	
	public Double compute(Map<String, Double> carParams)
	{
		return method.compute(carParams);
	}
}
