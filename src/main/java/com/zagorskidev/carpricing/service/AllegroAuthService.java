package com.zagorskidev.carpricing.service;

import com.zagorskidev.carpricing.rest.SimpleToken;

public interface AllegroAuthService 
{
	public void processClientCode(String code);

	public void refreshToken(SimpleToken currentToken);
}
