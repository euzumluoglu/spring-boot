package com.shopinle.remote.wsld;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.shopinle.remote.wsdl.GeoIpClient;
import com.shopinle.remote.wsdl.WsdlConfiguration;
import com.shopinle.remote.wsdl.generatedsrc.GetGeoIPResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { WsdlConfiguration.class })
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@TestPropertySource(properties = { "geoip.wsdl.endpoint = http://www.webservicex.net/geoipservice.asmx",
		"geoip.wsdl.callback = http://www.webservicex.net/GetGeoIP" })
public class GeoApiTest {

	@Autowired
	private GeoIpClient geoIpClient;
	
	@Test
	public void getIpInfo(){
		GetGeoIPResponse ipResponse = geoIpClient.getGeoIP("192.168.2.1");
		assertThat(ipResponse).isNotNull();
		assertThat(ipResponse.getGetGeoIPResult()).isNotNull();
		assertThat(ipResponse.getGetGeoIPResult().getCountryCode()).isEqualTo("ZZZ");

	}

}
