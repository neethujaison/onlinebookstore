DROP TABLE IF EXISTS Classification;

CREATE TABLE Classification (
  classification_id INT NOT NULL AUTO_INCREMENT,
  category VARCHAR(250) NOT NULL,
  discount DOUBLE DEFAULT NULL,
  promotion VARCHAR(250) DEFAULT NULL,
  PRIMARY KEY (`classification_id`)
);

DROP TABLE IF EXISTS Book;

CREATE TABLE Book (
  book_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) DEFAULT NULL,
  author VARCHAR(250) DEFAULT NULL,
  classification_id INT NOT NULL,
  price DOUBLE DEFAULT NULL,
  isbn VARCHAR(250) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
);

