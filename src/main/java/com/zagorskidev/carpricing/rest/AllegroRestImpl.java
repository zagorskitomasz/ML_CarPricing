package com.zagorskidev.carpricing.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.zagorskidev.carpricing.rest.parsing.Categories;
import com.zagorskidev.carpricing.service.DataService;

@Component
public class AllegroRestImpl implements AllegroRest
{
	private static final String CAR_TYPES_URL = "https://api.allegro.pl/categories";
	private static final String ACCEPT_HEADER = "application/vnd.allegro.public.v1+json";

	@Autowired
	private RestTemplate allegroRestTemplate;
	
	@Autowired
	private DataService dataService;
	
	@Override
	public Categories processCarTypesRequest() 
	{
        ResponseEntity<Categories> response = getCarTypes();
		return processResponse(response);
	}

	private ResponseEntity<Categories> getCarTypes() 
	{
		HttpEntity<String> entity = createHeadersEntity();
        
		ResponseEntity<Categories> response = 
				allegroRestTemplate.exchange(CAR_TYPES_URL, HttpMethod.GET, entity, Categories.class);
		return response;
	}

	private HttpEntity<String> createHeadersEntity() 
	{
		HttpHeaders headers = createHeader();
        
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		return entity;
	}

	private HttpHeaders createHeader() 
	{
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", createAuthString());
		headers.set("Accept", ACCEPT_HEADER);
		
		return headers;
	}

	private String createAuthString() 
	{
		SimpleToken token = dataService.loadToken();
		String auth = "Bearer " + token.getAccessToken();
        
		return auth;
	}

	private Categories processResponse(ResponseEntity<Categories> response) 
	{
		if(HttpStatus.OK.equals(response.getStatusCode()))
		{
			return response.getBody();
		}
		Logger.getGlobal().log(Level.WARNING, "Allegro response status not OK");
		return null;
	}
}
