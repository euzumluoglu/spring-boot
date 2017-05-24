package com.shopinle.batch;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.shopinle.db.jpa.CountryIpRepository;
import com.shopinle.remote.rest.ApiRandomClient;
import com.shopinle.remote.wsdl.GeoIpClient;
import com.shopinle.service.IpService;

@Configuration
@ComponentScan(basePackages="com.shopinle.batch.step")
public class MockConfig {

	@Bean(name="geoIpClient")
	public GeoIpClient geoIpClient(){
		return mock(GeoIpClient.class);
	}
	
	@Bean
	public IpService ipService(){
		return mock(IpService.class);
	}
	
	@Bean
	public ApiRandomClient apiRandomClient(){
		return mock(ApiRandomClient.class);
	}
	
	@Bean
	public CountryIpRepository countryIpRepository(){
		return mock(CountryIpRepository.class);
	}
}
