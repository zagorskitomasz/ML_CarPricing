package com.zagorskidev.carpricing.rest;

import java.sql.Timestamp;

public class SimpleToken 
{
	private String accessToken;
	private String refreshToken;
	private Timestamp refreshed;
	
	public SimpleToken(String accessToken, String refreshToken, Timestamp refreshed)
	{
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.refreshed = refreshed;
	}

	public String getAccessToken() 
	{
		return accessToken;
	}

	public void setAccessToken(String accessToken) 
	{
		this.accessToken = accessToken;
	}

	public String getRefreshToken() 
	{
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) 
	{
		this.refreshToken = refreshToken;
	}

	public Timestamp getRefreshed() 
	{
		return refreshed;
	}

	public void setRefreshed(Timestamp refreshed) 
	{
		this.refreshed = refreshed;
	}
}
