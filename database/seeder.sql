USE eyearbook_db;

insert into student_records (first_name, image, last_name,  student_id) values
('Leroy', 'https://cdn.filestackcontent.com/BT3RYnEMTap6rZAJb9k6', 'Grant', '1010'),
('Bobbie', 'https://cdn.filestackcontent.com/BT3RYnEMTap6rZAJb9k6', 'Archambault', '2020'),
('Augustine', 'https://cdn.filestackcontent.com/BT3RYnEMTap6rZAJb9k6', 'Cervantes', '3030'),
('Armando', 'https://cdn.filestackcontent.com/BT3RYnEMTap6rZAJb9k6', 'Roman', '4040');

INSERT into users(is_parent, owns_yearbook, parent_id, password, username, student_id) values
( 1 , 0, null, 'test', 'test', null),
( 0 , 0, 1, 'leroy', 'leroy', 1010),
( 0 , 0, 1, 'bobbie', 'bobbie', 2020),
( 0 , 0, 1, 'augustine', 'augustine', 3030),
( 0 , 0, 1, 'mando', 'mando', 4040);
