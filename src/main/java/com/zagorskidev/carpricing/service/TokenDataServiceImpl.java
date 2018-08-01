package com.zagorskidev.carpricing.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zagorskidev.carpricing.dao.ParameterRepository;
import com.zagorskidev.carpricing.domain.Parameter;
import com.zagorskidev.carpricing.domain.Parameters;
import com.zagorskidev.carpricing.rest.SimpleToken;

@Service
class TokenDataServiceImpl implements TokenDataService{

	@Autowired
	private ParameterRepository parameterRepository;
	
	@Override
	public void saveToken(SimpleToken authToken) 
	{
		saveParameter(Parameters.ACCESS_TOKEN, authToken.getAccessToken());
		saveParameter(Parameters.REFRESH_TOKEN, authToken.getRefreshToken());
	}
	
	private void saveParameter(Parameters parameter, String value)
	{
		List<Parameter> paramEntity = parameterRepository.findParametersByName(parameter.name());
		if(paramEntity == null || paramEntity.size() != 1)
		{
			Logger.getGlobal().log(Level.INFO, "Problem while reading parameter " + parameter.name());
			return;
		}
		paramEntity.get(0).setValue(value);
		parameterRepository.save(paramEntity.get(0));

		Logger.getGlobal().log(Level.INFO, "Saved parameter " + parameter.name());
	}

	@Override
	public SimpleToken loadToken() 
	{
		String accessTokenValue = loadParamValue(Parameters.ACCESS_TOKEN);
		String refreshTokenValue = loadParamValue(Parameters.REFRESH_TOKEN);
		
		return new SimpleToken(accessTokenValue, refreshTokenValue);
	}

	private String loadParamValue(Parameters parameter) 
	{
		List<Parameter> paramEntity = parameterRepository.findParametersByName(parameter.name());
		if(paramEntity == null || paramEntity.size() != 1)
		{
			Logger.getGlobal().log(Level.INFO, "Problem while reading parameter " + parameter.name());
			return null;
		}
		return paramEntity.get(0).getValue();
	}
}
