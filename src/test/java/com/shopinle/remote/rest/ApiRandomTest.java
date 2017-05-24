package com.shopinle.remote.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.shopinle.remote.rest.json.Quote;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT ,classes = {RestTestConfig.class,ApiRandomClient.class})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@TestPropertySource(properties = {
		"rest.endpoint= http://gturnquist-quoters.cfapps.io/api/random",
		"api.random.rest.endpoint= http://gturnquist-quoters.cfapps.io/api/random"})
public class ApiRandomTest {
	
	@Autowired
	private ApiRandomClient apiRandomClient;
	public void testQuote(){
		
	}

	@Value("${rest.endpoint}")
	private String url;

	@Test
	public void getQuoteTest() {
		Quote quote = apiRandomClient.getRandomValue();
		assertThat(quote).isNotNull();
		assertThat(quote.getValue()).isNotNull();
		assertThat(quote.getValue().getId()).isNotNull();
		
	}

}
