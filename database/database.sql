create database practice_db;
use practice_db;

create user 'practice_user'@'localhost' identified by 'password';
grant all on *.* to 'practice_user'@'localhost';