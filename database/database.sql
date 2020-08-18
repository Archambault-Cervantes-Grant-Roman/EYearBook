
CREATE DATABASE IF NOT EXISTS eyearbook_db;
DROP DATABASE eyearbook_db;

USE eyearbook_db;
show databases ;
create user 'eyearbook_user'@'localhost' IDENTIFIED BY 'codeup';
GRANT ALL ON eyearbook_db.* TO 'eyearbook_user'@'localhost';
