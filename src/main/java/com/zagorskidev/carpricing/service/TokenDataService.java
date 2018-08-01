package com.zagorskidev.carpricing.service;

import com.zagorskidev.carpricing.rest.SimpleToken;

interface TokenDataService 
{
	public void saveToken(SimpleToken authToken);
	SimpleToken loadToken();
}
