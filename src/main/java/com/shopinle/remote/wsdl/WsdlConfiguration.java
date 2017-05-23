package com.shopinle.remote.wsdl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WsdlConfiguration {
	
	@Value("${geoip.wsdl.endpoint }")
	private String geoipUrl;
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.shopinle.remote.wsdl.generatedsrc");
		return marshaller;
	}


	@Bean
	public GeoIpClient geoIpClient(Jaxb2Marshaller marshaller) {
		GeoIpClient client = new GeoIpClient();
		client.setDefaultUri(geoipUrl);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}


}
