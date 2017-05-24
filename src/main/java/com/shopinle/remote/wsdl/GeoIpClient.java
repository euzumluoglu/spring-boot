package com.shopinle.remote.wsdl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.shopinle.remote.wsdl.generatedsrc.GetGeoIP;
import com.shopinle.remote.wsdl.generatedsrc.GetGeoIPResponse;

public interface GeoIpClient {
	
	public GetGeoIPResponse getGeoIP(String ip);

}
