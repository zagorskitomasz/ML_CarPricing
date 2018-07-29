package com.zagorskidev.carpricing.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zagorskidev.carpricing.rest.AllegroAuthRest;
import com.zagorskidev.carpricing.rest.SimpleToken;

@Service
public class AllegroAuthServiceImpl implements AllegroAuthService
{
	@Autowired
	private AllegroAuthRest allegroRest;
	
	@Autowired
	private DataService dataService;
	
	@Override
	public void processClientCode(String code) 
	{
		SimpleToken authToken = allegroRest.requestToken(code);
		
		if(authToken != null)
			dataService.saveToken(authToken);
		else
			Logger.getGlobal().log(Level.WARNING, "Allegro auth token is null.");
	}
}
