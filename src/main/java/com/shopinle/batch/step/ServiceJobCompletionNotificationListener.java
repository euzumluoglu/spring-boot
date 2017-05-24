package com.shopinle.batch.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopinle.db.entity.CountryIp;
import com.shopinle.db.jpa.CountryIpRepository;

@Component(value ="serviceJobCompletionNotificationListener")
public class ServiceJobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(ServiceJobCompletionNotificationListener.class);

	private final CountryIpRepository countryIpRepository;

	@Autowired
	public ServiceJobCompletionNotificationListener(CountryIpRepository countryIpRepository) {
		this.countryIpRepository = countryIpRepository;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Status checking started");

			List<CountryIp> countryIps = (List<CountryIp>) countryIpRepository.findAll();
			log.info("Total count is  <" + countryIps.size() + "> in the database.");
			log.info("!!! JOB FINISHED! Status checking finished");
		}
	}
}
