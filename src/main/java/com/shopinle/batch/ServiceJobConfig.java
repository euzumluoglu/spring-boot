package com.shopinle.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shopinle.db.entity.CountryIp;

@Configuration
@EnableBatchProcessing
public class ServiceJobConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job serviceCallJob(Step serviceStep, 
			@Qualifier(value="serviceJobCompletionNotificationListener") JobExecutionListener listener) {
		return (Job) jobBuilderFactory.get("serviceCallJob").
				incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(serviceStep).
				end().
				build();
	}

	@Bean
	public Step serviceStep(@Qualifier(value = "serviceReader") ItemReader reader,
			@Qualifier(value = "serviceResultWriter") ItemWriter writer,
			@Qualifier(value = "loggingProcessor") ItemProcessor processor) {
		return stepBuilderFactory.get("serviceStep").
				<CountryIp, CountryIp>chunk(1).
				reader(reader).
				processor(processor)
				.writer(writer).build();
	}

}
