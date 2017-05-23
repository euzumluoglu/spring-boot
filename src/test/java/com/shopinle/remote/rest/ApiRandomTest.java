package com.shopinle.remote.rest;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.shopinle.remote.rest.json.Quote;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ComponentScan({"com.shopinle.remote.rest"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class , classes = {RestTestConfig.class,ApiRandomClient.class})
@TestPropertySource(properties = {
		"rest.endpoint= http://gturnquist-quoters.cfapps.io/api/random",
		"api.random.rest.endpoint= http://gturnquist-quoters.cfapps.io/api/random"})
public class ApiRandomTest {
	
	@Autowired
	private ApiRandomClient apiRandomClient;
	public void testQuote(){
		
	}
//	
//	@Autowired
//	private TestRestTemplate restTemplate;

	@Value("${rest.endpoint}")
	private String url;

	@Test
	public void getQuoteTest() {
		Quote quote = apiRandomClient.getRandomValue();//restTemplate.getForObject(url, Quote.class);
		assertThat(quote,notNullValue());
		assertThat(quote.getValue(),notNullValue());
		assertThat(quote.getValue().getId(),notNullValue());
		
	}

}
