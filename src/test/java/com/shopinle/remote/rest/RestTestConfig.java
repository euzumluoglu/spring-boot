package com.shopinle.remote.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTestConfig {

	@Bean
	public RestTemplate restTemplate(){//RestTemplateBuilder builder) {
		return new RestTemplate();//return builder.build();
	}
}
