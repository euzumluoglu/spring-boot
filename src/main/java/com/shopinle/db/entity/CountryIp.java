package com.shopinle.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.shopinle.remote.rest.json.Value;
import com.shopinle.remote.wsdl.generatedsrc.GeoIP;

@Entity
public class CountryIp {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//
	private Integer returnCode;
	private String ip;
	private String returnCodeDetails;
	private String countryName;
	private String countryCode;
	private Long ipId;
	private String quote;

	protected CountryIp() {
	}

	public CountryIp(GeoIP geoIP, Value value) {
		this.returnCode = geoIP.getReturnCode();
		this.ip = geoIP.getIP();
		this.returnCodeDetails = geoIP.getReturnCodeDetails();
		this.countryName = geoIP.getCountryName();
		this.countryCode = geoIP.getCountryCode();
		ipId = value.getId();
		quote = value.getQuote();
	}

	@Override
	public String toString() {
		return String.format(
				"CountyIp[id=%d, returnCode='%s', ip='%s, returnCodeDetails='%s, countryName='%s, countryCode='%s, ipId='%d, quote='%s']",
				ip, returnCode, returnCodeDetails, countryName, countryCode,ipId, quote);
	}

}
