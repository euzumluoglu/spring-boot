
DROP TABLE IF EXISTS sequences;

CREATE TABLE sequences (
seq_name VARCHAR(20),
seq_number BIGINT NOT NULL
);
INSERT INTO sequences VALUES ('IP_SEQUENCE',3);

DROP TABLE IF EXISTS country_ip;

CREATE TABLE country_ip (
	id BIGINT PRIMARY KEY,
	returnCode INT NOT NULL,
    ip VARCHAR(20) NOT NULL,
    returnCodeDetails VARCHAR(50) NOT NULL,
    countryName VARCHAR(30) NOT NULL,
    countryCode VARCHAR(10) NOT NULL,
    ipId BIGINT NOT NULL,
    quote VARCHAR(200) NOT NULL
);



INSERT INTO country_ip(id,returncode, ip, returncodedetails, countryname,countrycode,  ipid, quote) VALUES (1, 404, '192.168.2.1','Not Found','France','FR',1,'YES');
INSERT INTO country_ip(id,returncode, ip, returncodedetails, countryname,countrycode,  ipid, quote) VALUES (2, 404, '192.168.2.1','Not Found','France','FR',1,'NO');
