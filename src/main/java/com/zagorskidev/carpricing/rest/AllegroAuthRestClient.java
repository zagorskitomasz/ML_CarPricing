package com.zagorskidev.carpricing.rest;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AllegroAuthRestClient implements AllegroAuthRest 
{
	private static final String TOKEN_REQUEST_URL = "https://allegro.pl/auth/oauth/token";
	
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
        ResponseEntity<AccessToken> response = postForToken(code);
		
		return processResponse(response);
	}

	private ResponseEntity<AccessToken> postForToken(String code) 
	{
		HttpEntity<String> entity = createHeadersEntity();
        Map<String, String> variables = createRequestVariables(code);
        
		ResponseEntity<AccessToken> response = 
				allegroRestTemplate.exchange(TOKEN_REQUEST_URL, HttpMethod.POST, entity, AccessToken.class, variables);
		return response;
	}

	private Map<String, String> createRequestVariables(String code) 
	{
		prepareVariables();
		
		Map<String,String> variables = new HashMap<>();
        variables.put("grant_type", "authorization_code");
        variables.put("code", code);
        variables.put("redirect_uri", redirectURL);
        
		return variables;
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
			redirectURL = environment.getProperty("systemURL");
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
