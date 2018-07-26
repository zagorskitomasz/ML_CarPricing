package com.zagorskidev.carpricing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zagorskidev.carpricing.service.AllegroAuthService;

@RestController
@RequestMapping("/auth")
public class AllegroAuthController 
{
	@Autowired
	private AllegroAuthService allegroAuthService;

	@RequestMapping("/allegroAuth")
	public void allegroAuth(@PathVariable(value="code") String code)
	{
		allegroAuthService.processClientCode(code);
	}
}
