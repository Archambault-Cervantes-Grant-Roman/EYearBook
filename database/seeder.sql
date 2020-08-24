create database if not exists eyearbook_db;
use eyearbook_db;

insert into signatures ( yearbook_comment, signer_id) values
('Body 2 asdf asdf asd fasdf as dfa sdfa sdf', 4);


DROP TABLE student_records;
select * from student_records;

insert into student_records (first_name, image, last_name,  student_id) values

('Leroy', 'https://cdn.filestackcontent.com/BT3RYnEMTap6rZAJb9k6', 'Grant', '0010100'),
('Bobbie', 'https://cdn.filestackcontent.com/BT3RYnEMTap6rZAJb9k6', 'Archambault', '318808'),
('Augustine', 'https://cdn.filestackcontent.com/BT3RYnEMTap6rZAJb9k6', 'Cervantes', '5743829'),
('Armando', 'https://cdn.filestackcontent.com/BT3RYnEMTap6rZAJb9k6', 'Roman', '4234985');


UPDATE student_records
SET
    student_id = '1234567'
WHERE
        first_name = 'Leroy';


insert into users (id, parent_id, username, password) values
(2, 0, 'leroy', 'password');

insert into users_roles(user_id, role_id) values ( 1, 3)
