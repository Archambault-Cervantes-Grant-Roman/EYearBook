
CREATE DATABASE IF NOT EXISTS eyearbook_db;

USE eyearbook_db;

create user 'eyearbook_user'@'localhost' IDENTIFIED BY 'codeup';
GRANT ALL ON eyearbook_db.* TO 'eyearbook_user'@'localhost';

