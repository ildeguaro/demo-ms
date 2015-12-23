
CREATE TABLE employee (
  id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  birth_date date DEFAULT NULL, 
  email varchar(70) NOT NULL,
  num_phone varchar(15) NOT NULL,
  PRIMARY KEY (id)
);
