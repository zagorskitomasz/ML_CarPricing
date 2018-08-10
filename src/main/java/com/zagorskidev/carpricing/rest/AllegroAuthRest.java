package com.zagorskidev.carpricing.rest;

public interface AllegroAuthRest
{
	public SimpleToken requestToken(String code);
	public SimpleToken refreshToken(String refreshToken); 
}
