drop database conference;
create database conference character set utf8;

use conference;

CREATE TABLE presentation
(
    id BIGINT AUTO_INCREMENT,
    presentation_name VARCHAR(50),
    schedule DATE,
    room INT(50),
    PRIMARY KEY (id)
) ENGINE=INNODB CHARACTER SET=utf8;



