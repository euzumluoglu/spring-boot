package com.shopinle.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduleConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleConfig.class);
	@Autowired
	JobLauncher jobLauncher;
	@Qualifier(value="serviceCallJob")
	@Autowired
	Job serviceCallJob;
	
	@Scheduled(fixedDelayString="${schedule.job.fixdelay}")
	public void runJob() throws Exception {
		LOGGER.info("schedule job remoteCallJob started");
		jobLauncher.run(serviceCallJob, newExecution());
		LOGGER.info("schedule job remoteCallJob finished");
		
	}
    private JobParameters newExecution() {
        Map<String, JobParameter> parameters = new HashMap<>();

        JobParameter parameter = new JobParameter(new Date());
        parameters.put("currentTime", parameter);

        return new JobParameters(parameters);
    }
}
