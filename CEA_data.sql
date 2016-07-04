/*Insert*/
INSERT INTO department VALUES('ECE','Electrical and Computer Engineering');
INSERT INTO department VALUES('CSC','Computer Science');
INSERT INTO department VALUES('MIE', 'Mechanical and Industrial Engineering');

INSERT INTO course VALUES('CSC',309,'Web Programming','engineering');
INSERT INTO course VALUES('ECE',1747,'Algorithm and Data Structure','sciences');
INSERT INTO course VALUES('ECE',1778,'Creative Application on Mobile Devices','engineering');
INSERT INTO course VALUES('ECE',568,'Computer Security','engineering');
INSERT INTO course VALUES('CSC',2515,'Introduction to Machine Learning', 'sciences');
INSERT INTO course VALUES('CSC',209,'Software Tools and Systems Programming', 'engineering');
INSERT INTO course VALUES('ECE',521,'Inference Algorithm and Machine Learning', 'engineering');
INSERT INTO course VALUES('ECE',344,'Operating System','engineering');
INSERT INTO course VALUES('ECE',302,'Probability and Application','sciences');
INSERT INTO course VALUES('ECE',1782,'Programming Massively', 'engineering');
INSERT INTO course VALUES('CSC',190,'Computer Algorithm and Data Structure', 'sciences');
INSERT INTO course VALUES('MIE',438,'Microprocessor Application', 'engineering');
INSERT INTO course VALUES('ECE',244,'Programming Fundamentals','engineering');

INSERT INTO student VALUES('zoutong1','Tong Zou','China','1993-10','2014-09','M');
INSERT INTO student VALUES('xiaoaipi','Aiping Xiao','China','1993-03','2014-09','F');

INSERT INTO instructor VALUES('Courtney Gibson','M', 38, 'freelancer', 'computer security, web securty'); /*568*/
INSERT INTO instructor VALUES('Cristiana Amza', 'F', 46, 'permanent', 'cloud computing'); /*1747*/
INSERT INTO instructor VALUES('Katie Seaborn','F',24,'freelancer','web development'); /*309*/
INSERT INTO instructor VALUES('Michelle Craig','F',43,'permanent','Online and Inverted Delivery of CS Education'); /*209*/
INSERT INTO instructor VALUES('David Liu','M',28,'permanent','tree evaluation'); /*209*/
INSERT INTO instructor VALUES('Brendan Frey','M', 45, 'permanent','Deep Learning'); /*521*/
INSERT INTO instructor VALUES('Jonathan Rose','M', 46, 'permanent','FPGA'); /*1778*/
INSERT INTO instructor VALUES('Richard Zemel','M', 41, 'permanent','Neural Computation'); /*2515*/
INSERT INTO instructor VALUES('Elizabeth Patitsas','F', 27, 'freelancer','Picture Computation'); /*190*/
INSERT INTO instructor VALUES('Matthew Mackay','M', 34, 'permanent', 'Active-vision and dynamic-camera systems'); /*438*/
INSERT INTO instructor VALUES('Andreas Moshovos','M', 42, 'permanent', 'processing performance'); /*1782*/

INSERT INTO topic VALUES(1,'Web Development');
INSERT INTO topic VALUES(2,'Security');
INSERT INTO topic VALUES(3,'Algorithm');
INSERT INTO topic VALUES(4,'Database');
INSERT INTO topic VALUES(5,'Machine Learning');
INSERT INTO topic VALUES(6,'Embedded System');
INSERT INTO topic VALUES(7,'Parallel Computing');
INSERT INTO topic VALUES(8,'Data Structure');
INSERT INTO topic VALUES(9,'Mobile App Development');
INSERT INTO topic VALUES(10,'GPU Programming');

INSERT INTO skills VALUES(1,'HTML5');
INSERT INTO skills VALUES(2,'CSS');
INSERT INTO skills VALUES(3,'NodeJS');
INSERT INTO skills VALUES(4,'Algorithm');
INSERT INTO skills VALUES(5,'Unix');
INSERT INTO skills VALUES(6,'Command Shell');
INSERT INTO skills VALUES(7,'Neural Networks');
INSERT INTO skills VALUES(8,'CUDA Programming');
INSERT INTO skills VALUES(9,'JavaScript');
INSERT INTO skills VALUES(10,'Android');
INSERT INTO skills VALUES(11,'Assembly Coding');
INSERT INTO skills VALUES(12,'Program Vulnerability');

INSERT INTO company VALUES('Google', 'C++');
INSERT INTO company VALUES('Amazon', 'Java');
INSERT INTO company VALUES('Oracle', 'PHP');

INSERT INTO job VALUES('Software Developer','Google');
INSERT INTO job VALUES('Web Developer','Amazon');
INSERT INTO job VALUES('Backend','Oracle');

INSERT INTO faculty VALUES('Cristiana Amza','research',1990);
INSERT INTO faculty VALUES('Michelle Craig','research',1996);
INSERT INTO faculty VALUES('David Liu','teaching',2009);
INSERT INTO faculty VALUES('Brendan Frey','research',1991);
INSERT INTO faculty VALUES('Jonathan Rose','research',1986);
INSERT INTO faculty VALUES('Richard Zemel','research',1996);
INSERT INTO faculty VALUES('Matthew Mackay','teaching',2003);
INSERT INTO faculty VALUES('Andreas Moshovos','research',1995);

INSERT INTO research_faculty VALUES('Cristiana Amza','Cloud Computing');
INSERT INTO research_faculty VALUES('Michelle Craig','Online and Inverted Delivery of CS Education');
INSERT INTO research_faculty VALUES('Brendan Frey','Deep Learning');
INSERT INTO research_faculty VALUES('Jonathan Rose','FPGA');
INSERT INTO research_faculty VALUES('Richard Zemel','Neural Computation');
INSERT INTO research_faculty VALUES('Andreas Moshovos','Processor Performance');
INSERT INTO teaching_faculty VALUES('David Liu',10);
INSERT INTO teaching_faculty VALUES('Matthew Mackay',15);

INSERT INTO courseed VALUES(1,'ECE',1747,'2015-09-01','2015-12-06',50,'day');
INSERT INTO courseed VALUES(2,'CSC',309,'2016-01-15','2016-04-30',120,'evening');
INSERT INTO courseed VALUES(3,'ECE',1778,'2016-01-15','2016-04-30',150,'morning');
INSERT INTO courseed VALUES(4,'ECE',568,'2016-01-15','2016-04-30',80,'morning');
INSERT INTO courseed VALUES(5,'CSC',2515,'2015-09-01','2015-12-15',120,'evening');
INSERT INTO courseed VALUES(6,'ECE',521,'2016-01-15','2016-04-30',150,'day');
INSERT INTO courseed VALUES(7,'ECE',1782,'2016-01-15','2016-04-30',150,'evening');
INSERT INTO courseed VALUES(8,'CSC',190,'2015-01-15','2015-04-30',200,'day');
INSERT INTO courseed VALUES(9,'MIE',438,'2015-01-15','2015-04-30',50,'evening');

/*
INSERT INTO course_experience VALUES(1,'zoutong1',89.34,NULL);
INSERT INTO course_experience VALUES(2,'zoutong1',89.34,NULL);
INSERT INTO course_experience VALUES(2,'xiaoaipi',89.34,NULL);
INSERT INTO course_experience VALUES(1,'xiaoaipi',89.34,NULL);
INSERT INTO course_experience VALUES(3,'xiaoaipi',89.34,NULL);
*/

INSERT INTO course_skills VALUES ('CSC',309,1);
INSERT INTO course_skills VALUES ('CSC',309,2);
INSERT INTO course_skills VALUES ('CSC',309,3);
INSERT INTO course_skills VALUES ('ECE',1747,4);

INSERT INTO course_skills VALUES('ECE',568,12);
INSERT INTO course_skills VALUES('CSC',2515,7);
INSERT INTO course_skills VALUES('ECE',521,4);
INSERT INTO course_skills VALUES('ECE',1782,8);
INSERT INTO course_skills VALUES('CSC',190,4);
INSERT INTO course_skills VALUES('MIE',438,11);

INSERT INTO course_topic VALUES ('CSC',309,1);
INSERT INTO course_topic VALUES ('ECE',1747,3);
INSERT INTO course_topic VALUES ('ECE',1747,8);
INSERT INTO course_topic VALUES ('ECE',1778,9);
INSERT INTO course_topic VALUES('ECE',568,2); 
INSERT INTO course_topic VALUES('CSC',2515,5);              
INSERT INTO course_topic VALUES ('ECE',521,5);
INSERT INTO course_topic VALUES ('ECE',1782,10);
INSERT INTO course_topic VALUES ('CSC',190,3);
INSERT INTO course_topic VALUES ('CSC',190,8);
INSERT INTO course_topic VALUES ('MIE',438,6);

/*
INSERT INTO eskill VALUES(1,'zoutong1',1,NULL,NULL);
INSERT INTO eskill VALUES(2,'zoutong1',2,NULL,NULL);
INSERT INTO eskill VALUES(1,'xiaoaipi',1,NULL,NULL);
INSERT INTO eskill VALUES(2,'xiaoaipi',2,NULL,NULL);
INSERT INTO eskill VALUES(3,'xiaoaipi',4,NULL,NULL);
INSERT INTO etopic VALUES(1,'zoutong1',4,NULL,NULL);
INSERT INTO etopic VALUES(2,'zoutong1',1,NULL,NULL);
INSERT INTO etopic VALUES(2,'xiaoaipi',1,NULL,NULL);
INSERT INTO etopic VALUES(1,'xiaoaipi',4,NULL,NULL);
INSERT INTO etopic VALUES(3,'xiaoaipi',3,NULL,NULL);
*/


INSERT INTO instructor_ranking VALUES(1,'Cristiana Amza');
INSERT INTO instructor_ranking VALUES(2,'Katie Seaborn');
INSERT INTO instructor_ranking VALUES(3,'Jonathan Rose');
INSERT INTO instructor_ranking VALUES(4,'Courtney Gibson');
INSERT INTO instructor_ranking VALUES(5,'Richard Zemel');
INSERT INTO instructor_ranking VALUES(6,'Brendan Frey');
INSERT INTO instructor_ranking VALUES(7,'Andreas Moshovos');
INSERT INTO instructor_ranking VALUES(8,'Elizabeth Patitsas');
INSERT INTO instructor_ranking VALUES(9,'Matthew Mackay');


INSERT INTO job_skill VALUES('Software Developer','Google', 4, 4);
INSERT INTO job_skill VALUES('Software Developer','Google', 6, 3);
INSERT INTO job_skill VALUES('Web Developer','Amazon', 1, 4);
INSERT INTO job_skill VALUES('Web Developer','Amazon', 2, 3);
INSERT INTO job_skill VALUES('Web Developer','Amazon', 3, 2);
INSERT INTO job_skill VALUES('Backend','Oracle', 5, 5);

INSERT INTO student_employment VALUES('zoutong1','Web Developer','Amazon','2012-05-01','2012-08-01');
INSERT INTO student_employment VALUES('zoutong1','Backend','Oracle','2012-05-01','2012-08-01');
INSERT INTO student_employment VALUES('xiaoaipi','Software Developer','Google','2012-05-01','2012-08-01');

INSERT INTO prereq VALUES ('CSC',309,'CSC',209);
INSERT INTO prereq VALUES ('ECE',568,'ECE',344);
INSERT INTO prereq VALUES ('ECE',521,'ECE',302);

INSERT INTO exclusion VALUES (190,'CSC',244,'ECE');

