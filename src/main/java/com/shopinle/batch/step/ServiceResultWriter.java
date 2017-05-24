package com.shopinle.batch.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopinle.db.entity.CountryIp;
import com.shopinle.db.jpa.CountryIpRepository;

@Service(value="serviceResultWriter")
public class ServiceResultWriter implements ItemWriter<CountryIp> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceResultWriter.class);
    
    @Autowired
    private CountryIpRepository countryIpRepository;

    @Override
    public void write(List<? extends CountryIp> items) throws Exception {
        LOGGER.info("Received the information of CountryIp{} ", items.size());

        items.forEach(i -> LOGGER.debug("Received the information of : CountryIp{}", i));
        Iterable<? extends CountryIp> countries = countryIpRepository.save(items);
        LOGGER.info("Saved the information of CountryIp{} ", items.size());
    }
}
