package com.shopinle.db.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.shopinle.db.entity.CountryIp;
import com.shopinle.remote.rest.json.Value;
import com.shopinle.remote.wsdl.generatedsrc.GeoIP;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CountryIpRepository.class})
@EntityScan(basePackages = {"com.shopinle.db.entity"})
@DataJpaTest
@Sql(scripts = {"/schema-test.sql"})
public class CountryIpTest {

	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CountryIpRepository repository;

    @Test
    public void saveCountryIpTest() {
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
		countryIp = this.entityManager.persist(countryIp);
        List<CountryIp> countryIps = (List<CountryIp>) this.repository.findAll();
        assertThat(countryIps).isNotNull();
        assertThat(countryIps.size()).isEqualTo(1);

    }
}
