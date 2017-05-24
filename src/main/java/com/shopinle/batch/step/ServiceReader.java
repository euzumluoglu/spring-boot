package com.shopinle.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shopinle.db.entity.CountryIp;
import com.shopinle.remote.rest.ApiRandomClient;
import com.shopinle.remote.rest.json.Quote;
import com.shopinle.remote.wsdl.GeoIpClient;
import com.shopinle.remote.wsdl.generatedsrc.GetGeoIPResponse;
import com.shopinle.service.IpService;

@Service(value = "serviceReader")
public class ServiceReader implements ItemReader<CountryIp> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceReader.class);
	private int counter = 0;

	private GeoIpClient geoIpClient;
	private ApiRandomClient apiRandomClient;
	private IpService ipService;

	@Autowired
	public ServiceReader(@Qualifier(value = "geoIpClient") GeoIpClient geoIpClient, ApiRandomClient apiRandomClient,
			IpService ipService) {
		this.geoIpClient = geoIpClient;
		this.apiRandomClient = apiRandomClient;
		this.ipService = ipService;
	}

	@Override
	public CountryIp read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (counter == 1) {
			counter = 0;
			return null;
		}
		counter = 1;
		Quote quote = apiRandomClient.getRandomValue();
		String ip = ipService.getIp();
		GetGeoIPResponse geoIpResponse = geoIpClient.getGeoIP(ip);
		if (geoIpResponse == null) {
			LOGGER.info("geoIpClient response is null");
			return null;
		}
		CountryIp countryIp = new CountryIp(geoIpResponse.getGetGeoIPResult(), quote.getValue());
		return countryIp;
	}

}
