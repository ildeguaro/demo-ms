
CREATE TABLE employee (
  id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  birth_date date DEFAULT NULL,  
  PRIMARY KEY (id)
);
