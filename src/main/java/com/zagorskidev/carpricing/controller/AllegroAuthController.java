package com.zagorskidev.carpricing.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zagorskidev.carpricing.service.AllegroAuthService;

@RestController
@RequestMapping("/auth")
public class AllegroAuthController 
{
	@Autowired
	private AllegroAuthService allegroAuthService;

	@RequestMapping("/allegroAuth")
	public void allegroAuth(@RequestParam(value="code") String code)
	{
		Logger.getGlobal().log(Level.INFO, "Code: " + code);
		allegroAuthService.processClientCode(code);
	}
}
