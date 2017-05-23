package com.shopinle.remote.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shopinle.remote.rest.json.Quote;

@Service
public class ApiRandomClient {
	
	private static final Logger log = LoggerFactory.getLogger(ApiRandomClient.class);

	@Value("${api.random.rest.endpoint}")
	private String url;
	
	private final RestTemplate restTemplate;
	
	@Autowired
	public ApiRandomClient(RestTemplate restTemplate){
		this.restTemplate = restTemplate;
	}
	
	public Quote getRandomValue(){
		Quote quote = restTemplate.getForObject(
				url, Quote.class);
		log.info(quote.toString());
		return quote;
	}
}
