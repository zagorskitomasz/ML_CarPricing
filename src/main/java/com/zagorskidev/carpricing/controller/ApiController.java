package com.zagorskidev.carpricing.controller;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zagorskidev.carpricing.domain.CarParameters;
import com.zagorskidev.carpricing.domain.CarParams;
import com.zagorskidev.carpricing.service.CarParamType;
import com.zagorskidev.carpricing.service.DataService;
import com.zagorskidev.carpricing.service.RegressionService;
import com.zagorskidev.carpricing.service.loaders.CategoriesProcessor;
import com.zagorskidev.carpricing.service.loaders.SimpleCategory;

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
	
	@GetMapping("/process")
	public BigDecimal process(@RequestBody CarParameters carParameters)
	{
		CarParams params = new CarParams();
		params.setParam(CarParamType.CAPACITY.name(), 7);
		params.setParam(CarParamType.MILEAGE.name(), 11);
		params.setParam(CarParamType.POWER.name(), 7);
		params.setParam(CarParamType.YEAR.name(), 8);
		
		
		CarParameters car = new CarParameters();
		car.setCategory(12525);
		car.setParams(params);
		
		return regressionService.predict(car);
	}
	
	@GetMapping("/test/{id}")
	public Collection<CarParams> test(@PathVariable Integer id)
	{
		return dataService.loadCarTypeData(id);
	}
}
