package com.zagorskidev.carpricing.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zagorskidev.carpricing.domain.CarParameters;
import com.zagorskidev.carpricing.service.DataService;
import com.zagorskidev.carpricing.service.RegressionService;
import com.zagorskidev.carpricing.service.loaders.SimpleCategory;

@RestController
@RequestMapping("/api")
public class ApiController 
{
	@Autowired
	private DataService dataService;
	
	@Autowired
	private RegressionService regressionService;
	
	private Map<SimpleCategory, List<SimpleCategory>> categories;
	
	@GetMapping("/sayHello")
	public String sayHello()
	{
		return "hello";
	}
	
	@GetMapping("/getFirstLevel")
	public @ResponseBody Set<SimpleCategory> getCarTypes()
	{
		categories = dataService.getCarTypes();
		return categories.keySet();
	}
	
	@GetMapping("/getSecondLevel/{id}")
	public @ResponseBody List<SimpleCategory> getCarTypes(@PathVariable String id)
	{
		if(categories == null)
			categories = dataService.getCarTypes();

		for(Entry<SimpleCategory, List<SimpleCategory>> category : categories.entrySet())
		{
			if(category.getKey().getId().equals(id))
				return category.getValue();
		}
		return null;
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
