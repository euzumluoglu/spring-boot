package com.shopinle.remote.wsdl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.shopinle.remote.wsdl.generatedsrc.GetGeoIP;
import com.shopinle.remote.wsdl.generatedsrc.GetGeoIPResponse;

public class GeoIpClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(GeoIpClient.class);

	@Value("${geoip.wsdl.endpoint}")
	private String url;

	@Value("${geoip.wsdl.callback}")
	private String callBackUrl;

	public GetGeoIPResponse getGeoIP(String ip) {

		GetGeoIP request = new GetGeoIP();
		request.setIPAddress(ip);
		//
		log.info("Requesting ip for " + ip);

		GetGeoIPResponse response = (GetGeoIPResponse) getWebServiceTemplate().marshalSendAndReceive(url, request,
				new SoapActionCallback(callBackUrl));
		
		return response;
	}

}
