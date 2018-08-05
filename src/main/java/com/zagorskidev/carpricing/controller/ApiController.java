package com.zagorskidev.carpricing.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zagorskidev.carpricing.domain.CarParameters;
import com.zagorskidev.carpricing.service.DataService;
import com.zagorskidev.carpricing.service.RegressionService;

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
		return dataService.loadToken().getAccessToken();
	}
	
	@GetMapping("/getCarTypes")
	public @ResponseBody Map<String, List<String>> getCarTypes()
	{
		return dataService.getCarTypes();
	}
	
	@GetMapping("/process")
	public BigDecimal process(@RequestBody CarParameters carParameters)
	{
		if(!dataService.loadCarTypeData(carParameters.getType()))
			return null;
		
		regressionService.trainClassifier();
		
		return regressionService.predict(carParameters.getParams());
	}
}
