package com.zagorskidev.carpricing.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zagorskidev.carpricing.rest.SimpleToken;

@Component
public class TokenRefresher 
{
	private static final long EIGHT_HOURS = 8 * 60 * 60 * 1000;
	private static final long ONE_HOUR = 60 * 60 * 1000;
	
	@Autowired
	private AllegroAuthService authService;
	
	@Autowired
	private TokenDataService tokenService;
	
	@Scheduled(fixedDelay=ONE_HOUR)
	public void checkToken()
	{
		SimpleToken currentToken = tokenService.loadToken();
		
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		long time = cal.getTimeInMillis();
		
		if(currentToken.getRefreshed().before(new Timestamp(time - EIGHT_HOURS)))
			refreshToken(currentToken);
		else
			Logger.getGlobal().log(Level.INFO, "Token up to date");
	}
	
	private void refreshToken(SimpleToken currentToken)
	{
		try
		{
			authService.refreshToken(currentToken);
			Logger.getGlobal().log(Level.INFO, "Token refreshed");
		}
		catch(Exception ex)
		{
			Logger.getGlobal().log(Level.INFO, "Error occured while refreshing token");
		}
	}
}
