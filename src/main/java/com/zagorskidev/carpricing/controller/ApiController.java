package com.zagorskidev.carpricing.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zagorskidev.carpricing.domain.CarParameters;
import com.zagorskidev.carpricing.domain.CarParams;
import com.zagorskidev.carpricing.service.CarParamType;
import com.zagorskidev.carpricing.service.DataService;
import com.zagorskidev.carpricing.service.loaders.CategoriesProcessor;
import com.zagorskidev.carpricing.service.loaders.SimpleCategory;
import com.zagorskidev.carpricing.service.regression.RegressionService;

@RestController
@RequestMapping("/api")
public class ApiController 
{
	@Autowired
	private DataService dataService;
	
	@Autowired
	private RegressionService regressionService;
	
	@GetMapping("/sayHello")
	public String sayHello()
	{
		return "hello";
	}
	
	@GetMapping("/getFirstLevel")
	public @ResponseBody Collection<SimpleCategory> getCarTypes()
	{
		return dataService.getCarTypes(CategoriesProcessor.OSOBOWE_CATEGORY);
	}
	
	@GetMapping("/getSecondLevel/{id}")
	public @ResponseBody Collection<SimpleCategory> getCarTypes(@PathVariable Integer id)
	{
		return dataService.getCarTypes(id);
	}
	
	@GetMapping("/test/{id}/{capacity}/{year}/{mileage}/{power}")
	public Double test(
			@PathVariable("id") Integer id,
			@PathVariable("capacity") Double capacity,
			@PathVariable("year") Double year,
			@PathVariable("mileage") Double mileage,
			@PathVariable("power") Double power)
	{
		CarParams params = new CarParams();
		params.setParam(CarParamType.CAPACITY.name(), capacity);
		params.setParam(CarParamType.YEAR.name(), year);
		params.setParam(CarParamType.MILEAGE.name(), mileage);
		params.setParam(CarParamType.POWER.name(), power);
		params.setParam(CarParamType.PRICE.name(), 0.0);
		
		CarParameters parameters = new CarParameters();
		parameters.setCategory(id);
		parameters.setParams(params);
		
		return regressionService.predict(parameters);
	}
}
