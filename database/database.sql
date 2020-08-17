CREATE DATABASE IF NOT EXISTS e_yearbook_db;

create user 'e_yearbook_user'@'localhost' IDENTIFIED BY 'codeup';
GRANT ALL ON e_yearbook_db.* TO 'e_yearbook_user'@'localhost';

