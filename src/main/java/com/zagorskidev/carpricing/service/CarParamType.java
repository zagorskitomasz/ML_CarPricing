package com.zagorskidev.carpricing.service;

public enum CarParamType 
{
	YEAR("parameter.1", new Integer[] {1990, 1992, 1994, 1996, 1998, 2000, 2002, 2004, 2006, 2008, 2010, 2012, 2014, 2016, 2018, 2020}),
	CAPACITY("parameter.5", new Integer[] {600, 800, 1000, 1200, 1400, 1600, 1800, 2000, 2200, 2400, 2600, 2800, 3000, 3200, 3400, 3600, 3800, 4000, 4200, 4400}),
	MILEAGE("parameter.4", new Integer[] {0, 25000, 50000, 75000, 100000, 125000, 150000, 175000, 200000, 225000, 250000, 275000, 300000, 325000, 350000, 375000, 400000}),
	POWER("parameter.14", new Integer[] {45, 60, 75, 90, 105, 120, 135, 150, 165, 180, 195, 210, 225, 240, 255, 270, 285, 300, 315, 330, 345, 360, 390, 405, 420, 435, 450, 465, 480, 495, 510, 525, 540, 550, 565, 580}),
	PRICE("price", new Integer[] {0, 4000, 8000, 12000, 16000, 20000, 24000, 28000, 32000, 36000, 40000, 44000, 48000, 52000, 56000, 60000, 64000, 68000, 72000, 76000, 80000, 84000, 88000, 92000, 96000, 100000, 104000, 108000, 112000, 116000, 120000, 124000, 128000, 132000, 136000, 140000});
	
	private String parameterName;
	private Integer[] parameterValues;
	
	private CarParamType(String paramName, Integer[] paramValues)
	{
		parameterName = paramName;
		parameterValues = paramValues;
	}

	public String getParameterName() 
	{
		return parameterName;
	}

	public Integer[] getParameterValues() 
	{
		return parameterValues;
	}
}
