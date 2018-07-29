package com.zagorskidev.carpricing.rest;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AllegroAuthRestClient implements AllegroAuthRest 
{
	private static final String TOKEN_REQUEST_URL = "https://allegro.pl/auth/oauth/token?grant_type=authorization_code&code=#CODE#&redirect_uri=#REDIRECT_URL#";
	
	private String redirectURL = "";
	private String clientId;
	private String clientSecret;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private RestTemplate allegroRestTemplate;
	
	@Override
	public SimpleToken requestToken(String code) 
	{
		prepareVariables();
		
        ResponseEntity<?> response = postForToken(code);
		System.out.println("Response body: " + response.getBody());
		System.out.println("Response status: " + response.getStatusCode().name());
        
		return null; //processResponse(response);
	}	

	private ResponseEntity<?> postForToken(String code) 
	{
		HttpEntity<String> entity = createHeadersEntity();
        
		ResponseEntity<?> response = 
				allegroRestTemplate.postForEntity(TOKEN_REQUEST_URL.replaceAll("#CODE#", code).replaceAll("#REDIRECT_URL#", redirectURL), entity, Object.class);
		return response;
	}

	private HttpEntity<String> createHeadersEntity() 
	{
		HttpHeaders headers = createAuthCodeHeader();
        
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		return entity;
	}
	
	private void prepareVariables()
	{
		if(redirectURL == null)
			redirectURL = environment.getProperty("redirectURL");
		if(clientId == null)
			clientId = environment.getProperty("clientId");
		if(clientSecret == null)
			clientSecret = environment.getProperty("clientSecret");
	}

	private HttpHeaders createAuthCodeHeader() 
	{
		String authHeader = createAuthString();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authHeader);
		
		return headers;
	}

	private String createAuthString() 
	{
		String auth = clientId + ":" + clientSecret;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String( encodedAuth );
        
		return authHeader;
	}

	private SimpleToken processResponse(ResponseEntity<AccessToken> response) 
	{
		if(HttpStatus.OK.equals(response.getStatusCode()))
			return new SimpleToken(response.getBody().getAccess_token(),response.getBody().getRefresh_token());
		
		Logger.getGlobal().log(Level.WARNING, "Allegro auth response status not OK");
		return null;
	}
}
