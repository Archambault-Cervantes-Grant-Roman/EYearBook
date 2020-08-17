create DATABASE eyearbook_db;
use eyearbook_db;

create user 'eyearbook_user'@'localhost' identified by 'password';
grant all on *.* to 'eyearbook_user'@'localhost';

SHOW databases;
DROP TABLE users;