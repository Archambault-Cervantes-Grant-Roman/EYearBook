# use eyearbook_db;
create database if not exists eyearbook_db;
use practice_db;

insert into signatures ( yearbook_comment, signer_id) values
('Body 2 asdf asdf asd fasdf as dfa sdfa sdf', 4);

<<<<<<< HEAD
select * from student_records;

insert into student_records (first_name, last_name, image,  student_id) values
=======
insert into student_records (first_name, image, last_name,  student_id) values
>>>>>>> be3b64d46cb5327e3253e4a252bace1ba027cf7d
('Leroy', 'https://cdn.filestackcontent.com/BT3RYnEMTap6rZAJb9k6', 'Grant', '0010100'),
('Bobbie', 'https://cdn.filestackcontent.com/BT3RYnEMTap6rZAJb9k6', 'Archambault', '8957945'),
('Augustine', 'https://cdn.filestackcontent.com/BT3RYnEMTap6rZAJb9k6', 'Cervantes', '5743829'),
('Armando', 'https://cdn.filestackcontent.com/BT3RYnEMTap6rZAJb9k6', 'Roman', '4234985');

UPDATE student_records
SET
    student_id = '1234567'
WHERE
        first_name = 'Leroy';
