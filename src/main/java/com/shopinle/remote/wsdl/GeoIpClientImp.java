package com.shopinle.remote.wsdl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.shopinle.remote.wsdl.generatedsrc.GetGeoIP;
import com.shopinle.remote.wsdl.generatedsrc.GetGeoIPResponse;

public class GeoIpClientImp extends WebServiceGatewaySupport implements GeoIpClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeoIpClient.class);

	@Value("${geoip.wsdl.endpoint}")
	private String url;

	@Value("${geoip.wsdl.callback}")
	private String callBackUrl;

	public GetGeoIPResponse getGeoIP(String ip) {

		if(ip==null || ip.isEmpty()){
			LOGGER.info("Requesting ip empty ");
			return null;
		}
		GetGeoIP request = new GetGeoIP();
		request.setIPAddress(ip);

		LOGGER.info("Requesting ip for " + ip);

		GetGeoIPResponse response = (GetGeoIPResponse) getWebServiceTemplate().marshalSendAndReceive(url, request,
				new SoapActionCallback(callBackUrl));
		if (response == null) {
			LOGGER.info("Requesting ip " + ip + "could not resolved ");
		}
		return response;
	}

}
