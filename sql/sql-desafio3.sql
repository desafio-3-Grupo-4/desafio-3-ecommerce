create database ecommerceEngrenagem;

create user 'developer'@'localhost' IDENTIFIED BY '1234567';

GRANT ALL privileges ON * . * TO 'developer'@'localhost';

FLUSH PRIVILEGES;