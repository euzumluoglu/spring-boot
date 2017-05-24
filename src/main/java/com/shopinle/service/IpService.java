package com.shopinle.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IpService {
	private static final Logger LOGGER = LoggerFactory.getLogger(IpService.class);

	public String getIp() {

		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			LOGGER.info("Current Host/IP is " + ip);
			return ip == null ? null : ip.getHostAddress();
		} catch (UnknownHostException e) {
			LOGGER.error("Ip resolve failure", e);
		}
		return null;
	}
}
