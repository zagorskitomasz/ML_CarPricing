package com.zagorskidev.carpricing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller 
{
	@RequestMapping("/")
	public String sayHello()
	{
		return "hello";
	}
	
	@RequestMapping("/process")
	public String process()
	{
		/*TODO
		 * 1. get car data from request
		 * 2. prepare data object with data service (use car data to select car type)
		 * 3. train algorithm (use data object)
		 * 4. use algorithm to price user's car
		 */
		return null;
	}
}
