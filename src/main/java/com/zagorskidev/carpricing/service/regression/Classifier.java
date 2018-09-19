package com.zagorskidev.carpricing.service.regression;

import java.math.BigDecimal;

public interface Classifier 
{
	public BigDecimal predict() throws Exception;
}
