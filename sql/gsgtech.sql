CREATE DATABASE gsgtech DEFAULT CHARACTER SET latin1;

USE gsgtech;

CREATE TABLE employee (
  id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  birth_date varchar(255) DEFAULT NULL,  
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
