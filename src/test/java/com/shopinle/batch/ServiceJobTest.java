package com.shopinle.batch;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import javax.batch.runtime.JobExecution;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shopinle.remote.rest.ApiRandomClient;
import com.shopinle.remote.rest.json.Quote;
import com.shopinle.remote.rest.json.Value;
import com.shopinle.remote.wsdl.GeoIpClient;
import com.shopinle.remote.wsdl.generatedsrc.GeoIP;
import com.shopinle.remote.wsdl.generatedsrc.GetGeoIPResponse;
import com.shopinle.service.IpService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MockConfig.class, ServiceJobConfig.class })

public class ServiceJobTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Test
	public void stepTest() throws Exception{
		Value value = new Value();
    	value.setId(1L);
    	value.setQuote("Working with Spring Boot");
    	Quote quote = new Quote();
    	quote.setValue(value);
    	GeoIP geoIP = new GeoIP();
		geoIP.setCountryCode("TR");
		geoIP.setCountryName("Turkey");
		geoIP.setIP("192.168.2.1");
		geoIP.setReturnCode(200);
		geoIP.setReturnCodeDetails("success");
    	when(any(IpService.class).getIp()).thenReturn("192.168.2.1");
		when(any(ApiRandomClient.class).getRandomValue()).thenReturn(quote);
		GetGeoIPResponse response = new GetGeoIPResponse();
		response.setGetGeoIPResult(geoIP);
		when(any(GeoIpClient.class).getGeoIP("192.168.2.1")).thenReturn(response );
		
		JobExecution jobExecution = (JobExecution) jobLauncherTestUtils.launchJob();
		
		assertEquals(BatchStatus.COMPLETED, jobExecution.getBatchStatus());

	}
}
