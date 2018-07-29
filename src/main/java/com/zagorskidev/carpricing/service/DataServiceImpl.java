package com.zagorskidev.carpricing.service;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.zagorskidev.carpricing.domain.CarType;
import com.zagorskidev.carpricing.rest.SimpleToken;

@Service
public class DataServiceImpl implements DataService 
{
	@Override
	public Map<String, List<String>> getCarTypes() 
	{
		return null;
	}

	@Override
	public boolean loadCarTypeData(CarType type) 
	{
		return false;
	}

	@Override
	public void saveToken(SimpleToken authToken) 
	{
		Logger.getGlobal().log(Level.INFO, "Access token: " + authToken.getAccessToken());
		Logger.getGlobal().log(Level.INFO, "Refresh token: " + authToken.getRefreshToken());
	}
}
