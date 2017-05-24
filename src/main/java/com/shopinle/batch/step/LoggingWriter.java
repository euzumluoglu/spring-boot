package com.shopinle.batch.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;

import com.shopinle.db.entity.CountryIp;

@Service(value="loggingWriter")
public class LoggingWriter implements ItemWriter<CountryIp> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingWriter.class);

    @Override
    public void write(List<? extends CountryIp> items) throws Exception {
        LOGGER.info("Received the information of CountryIp{} ", items.size());

        items.forEach(i -> LOGGER.debug("Received the information of : CountryIp{}", i));
    }
}
