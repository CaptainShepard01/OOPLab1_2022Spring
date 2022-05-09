--liquibase formatted sql

--changeset balanton:1

INSERT into teachers (name) VALUES('Karashuk M. G.');
INSERT into teachers (name) VALUES('Galkin O. V.');
INSERT into teachers (name) VALUES('Shkilnyak O. S.');

INSERT into students (name) VALUES('Anton');
INSERT into students (name) VALUES('Kyryl');
INSERT into students (name) VALUES('Ruslan');
INSERT into students (name) VALUES('Nazarii');

INSERT into courses (name, max_grade, teacher_id) VALUES('Social-Politics Studies', 10, 1);
INSERT into courses (name, max_grade, teacher_id) VALUES('Object Oriented Programming', 20, 2);
INSERT into courses (name, max_grade, teacher_id) VALUES('Basics of Cryptology', 30, 2);
INSERT into courses (name, max_grade, teacher_id) VALUES('Geometric Algorithms', 30, 3);

INSERT into student_course_relations(student_id, course_id, grade, review) VALUES(1,1,7,'Wonderful!');
INSERT into student_course_relations(student_id, course_id, grade, review) VALUES(1,2,17,'Good!');
INSERT into student_course_relations(student_id, course_id, grade, review) VALUES(2,1,8,'Amazing!');
INSERT into student_course_relations(student_id, course_id, grade, review) VALUES(3,1,9,'Finally!');
INSERT into student_course_relations(student_id, course_id, grade, review) VALUES(4,2,17,'Nice work!');
INSERT into student_course_relations(student_id, course_id, grade, review) VALUES(4,3,26,'Cryptolog!');
