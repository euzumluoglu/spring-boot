package com.shopinle.batch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shopinle.db.entity.CountryIp;
import com.shopinle.db.jpa.CountryIpRepository;
import com.shopinle.remote.rest.ApiRandomClient;
import com.shopinle.remote.rest.json.Quote;
import com.shopinle.remote.rest.json.Value;
import com.shopinle.remote.wsdl.GeoIpClient;
import com.shopinle.remote.wsdl.generatedsrc.GeoIP;
import com.shopinle.remote.wsdl.generatedsrc.GetGeoIPResponse;
import com.shopinle.service.IpService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MockConfig.class, ServiceJobConfig.class, JobLauncherTestUtils.class })
public class ServiceJobTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private IpService ipService;

	@Autowired
	private ApiRandomClient apiRandomClient;

	@Autowired
	private GeoIpClient geoIpClient;

	@Autowired
	private CountryIpRepository countryIpRepository;

	@Before
	public void setup() throws Exception {
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
		CountryIp countryIp = new CountryIp(geoIP, value);
		when(ipService.getIp()).thenReturn("192.168.2.1");
		when(apiRandomClient.getRandomValue()).thenReturn(quote);
		GetGeoIPResponse response = new GetGeoIPResponse();
		response.setGetGeoIPResult(geoIP);
		when(geoIpClient.getGeoIP("192.168.2.1")).thenReturn(response);
		when(countryIpRepository.save(countryIp)).thenReturn(countryIp);
		ArrayList<CountryIp> countryIps = new ArrayList<CountryIp>();
		countryIps.add(countryIp);
		when(countryIpRepository.findAll()).thenReturn(countryIps);
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

	}
	
	   @Test
	    public void testJob() throws Exception {
	        commonAssertions(jobLauncherTestUtils.launchJob());
	    }
	 
	    @Test
	    public void serviceStep() throws Exception {
	        commonAssertions(jobLauncherTestUtils.launchStep("serviceStep"));
	    }
	 
	    private void commonAssertions(JobExecution jobExecution) {
	    	
	    	assertThat(jobExecution);
	 
	        BatchStatus batchStatus = jobExecution.getStatus();
	        assertThat(batchStatus).isEqualTo(BatchStatus.COMPLETED);
	        assertThat(batchStatus.isUnsuccessful()).isFalse();
	 
	        ExitStatus exitStatus = jobExecution.getExitStatus();
	        assertThat(exitStatus.getExitCode()).isEqualTo("COMPLETED");
	        assertThat(exitStatus.getExitDescription()).isEqualTo("");

	        List<Throwable> failureExceptions = jobExecution.getFailureExceptions();
	        assertThat(failureExceptions).isNotNull();
	        assertThat(failureExceptions.isEmpty()).isTrue();

	    }
}
