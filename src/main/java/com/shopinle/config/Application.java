package com.shopinle.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.shopinle.db.entity.CountryIp;
import com.shopinle.db.jpa.CountryIpRepository;
import com.shopinle.remote.rest.ApiRandomClient;
import com.shopinle.remote.rest.json.Value;
import com.shopinle.remote.wsdl.GeoIpClient;
import com.shopinle.remote.wsdl.generatedsrc.GeoIP;

@SpringBootApplication
//@PropertySource({ 
//	  "classpath:application.properties"
//	})
@ComponentScan({"com.shopinle.remote.rest","com.shopinle.remote.wsdl","com.shopinle.batch"})
@EntityScan(basePackages = {"com.shopinle.db.entity"})
@EnableJpaRepositories(basePackages={"com.shopinle.db.jpa"})
public class Application {
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	


	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
//	@Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//            GeoIpClient geoIpClient = (GeoIpClient) ctx.getBean("geoIpClient");
//            ApiRandomClient apiRandomClient = ctx.getBean(ApiRandomClient.class);
//
//        };
//    }
	
	@Bean
  public CommandLineRunner commandLineRunner(CountryIpRepository countryIpRepository) {
		return args -> {
			Value value = new Value();
	    	value.setId(1L);
	    	value.setQuote("Working with Spring Boot");
			GeoIP geoIP = new GeoIP();
			geoIP.setCountryCode("TR");
			geoIP.setCountryName("Turkey");
			geoIP.setIP("192.168.2.1");
			geoIP.setReturnCode(200);
			geoIP.setReturnCodeDetails("success");
			CountryIp countryIp = new CountryIp(geoIP, value);
			countryIp = countryIpRepository.save(countryIp);
			countryIp = countryIpRepository.save(new CountryIp(geoIP, value));
			countryIp = countryIpRepository.save(new CountryIp(geoIP, value));
			countryIp = countryIpRepository.save(new CountryIp(geoIP, value));
		};
	}
	
	

}