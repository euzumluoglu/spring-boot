package com.shopinle.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.shopinle.remote.rest.json.Value;
import com.shopinle.remote.wsdl.generatedsrc.GeoIP;

@Entity
@Table(name = "country_ip")
public class CountryIp {

	@Id
	@TableGenerator(name = "EVENT_GEN", table = "SEQUENCES", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_NUMBER", pkColumnValue = "IP_SEQUENCE", initialValue = 4, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EVENT_GEN")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	//
	@Column(name = "returncode")
	private Integer returnCode;
	@Column(name = "ip")
	private String ip;
	@Column(name = "returncodedetails")
	private String returnCodeDetails;
	@Column(name = "countryname")
	private String countryName;
	@Column(name = "countrycode")
	private String countryCode;
	@Column(name = "ipid")
	private Long ipId;
	@Column(name = "quote")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getReturnCodeDetails() {
		return returnCodeDetails;
	}

	public void setReturnCodeDetails(String returnCodeDetails) {
		this.returnCodeDetails = returnCodeDetails;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Long getIpId() {
		return ipId;
	}

	public void setIpId(Long ipId) {
		this.ipId = ipId;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	@Override
	public String toString() {
		return String.format(
				"CountyIp[id=%d, returnCode='%s', ip='%s, returnCodeDetails='%s, countryName='%s, countryCode='%s, ipId='%d, quote='%s']",
				id, returnCode, ip, returnCodeDetails, countryName, countryCode, ipId, quote);
	}

}
