package com.shopinle.batch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.shopinle.db.entity.CountryIp;

@Service(value="loggingProcessor")
public class LoggingProcessor implements ItemProcessor<CountryIp, CountryIp> {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingProcessor.class);
	@Override
	public CountryIp process(CountryIp countryIp) throws Exception {
		LOGGER.info("Processing the information of CountryIp{} ", countryIp);
		
		return countryIp;
	}
 

}
