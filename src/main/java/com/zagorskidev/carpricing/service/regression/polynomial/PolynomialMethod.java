package com.zagorskidev.carpricing.service.regression.polynomial;

import java.util.Map;

public interface PolynomialMethod 
{
	public Double compute(Map<String, Double> carParams);
}
