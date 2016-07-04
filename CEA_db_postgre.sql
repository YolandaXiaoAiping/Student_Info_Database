DROP SCHEMA IF EXISTS a1 CASCADE;
CREATE SCHEMA a1;
SET search_path TO a1;

/* Delete the tables if they already exist */
drop table if exists eskill;
drop table if exists etopic;
drop table if exists course_experience;
DROP TABLE IF EXISTS courseed;
drop table if exists instructor_ranking;
DROP TABLE IF EXISTS prereq;
DROP TABLE IF EXISTS exclusion;
drop table if exists research_faculty;
drop table if exists teaching_faculty;
drop table if exists faculty;
drop table if exists student_employment;
drop table if exists job_skill;
DROP TABLE IF EXISTS course_skills;
DROP TABLE IF EXISTS course_topic;
drop table if exists instructor;
drop table if exists student;
drop table if exists job;
DROP TABLE IF EXISTS course;
drop table IF EXISTS department;
DROP TABLE IF EXISTS topic;
DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS company;

/* Create enum types */
CREATE TYPE coursearea AS ENUM ('humanities', 'sciences', 'arts', 'business', 'engineering');
CREATE TYPE coursetime AS ENUM ('morning', 'day', 'evening');
CREATE TYPE instype AS ENUM ('permanent', 'freelancer');
CREATE TYPE factype AS ENUM ('research', 'teaching');
CREATE TYPE pgender AS ENUM ('F', 'M');

/* Create the tables */
CREATE TABLE instructor(name VARCHAR(50) PRIMARY KEY, gender pgender, age INT, type instype, area VARCHAR(255));
CREATE TABLE department(deptcode VARCHAR(5) PRIMARY KEY, name VARCHAR(255));
CREATE TABLE course(deptcode VARCHAR(5),cNum INT,name VARCHAR(255),area coursearea, PRIMARY KEY(deptcode,cNum));
CREATE TABLE student(userid VARCHAR(20) PRIMARY KEY, name VARCHAR(50), country VARCHAR(20), birth CHAR(7), begin_year CHAR(7), gender pgender);
CREATE TABLE topic(tid INT, name VARCHAR(255), PRIMARY KEY(tid));
CREATE TABLE skills(sid INT, name VARCHAR(255),PRIMARY KEY(sid));

CREATE TABLE faculty(name VARCHAR(50) PRIMARY KEY, type factype, start_year INT, FOREIGN KEY(name) REFERENCES instructor(name) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE research_faculty(name VARCHAR(50) PRIMARY KEY, research_interests VARCHAR(255), FOREIGN KEY(name) REFERENCES faculty(name) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE teaching_faculty(name VARCHAR(50) PRIMARY KEY, year_of_experience INT, FOREIGN KEY(name) REFERENCES faculty(name) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE courseed(eid INT, deptcode VARCHAR(5), cNum INT, starttime DATE, endtime DATE, enroll_num INT, time coursetime, PRIMARY KEY(eid), FOREIGN KEY (deptcode,cNum) REFERENCES course(deptcode,cNum) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE course_experience(eid INT, userid VARCHAR(20), grade NUMERIC(5,2), satisfaction INT, rank INT, PRIMARY KEY(eid, userid), FOREIGN KEY(eid) REFERENCES courseed(eid) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(userid) REFERENCES student(userid) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE instructor_ranking(eid INT, instructor VARCHAR(50), PRIMARY KEY(eid, instructor),FOREIGN KEY(eid) REFERENCES courseed(eid)ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(instructor) REFERENCES instructor(name)ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE eskill(eid INT, userid VARCHAR(20), sid INT, deptcode VARCHAR(5), cNum INT, level_before INT, level_after INT, PRIMARY KEY(eid, userid, sid));
CREATE TABLE etopic(eid INT, userid VARCHAR(20), tid INT, deptcode VARCHAR(5), cNum INT, interest_before INT, interest_after INT, PRIMARY KEY(eid, userid, tid));
CREATE TABLE company(name VARCHAR(255), expertise VARCHAR(255), PRIMARY KEY(name));
CREATE TABLE job(title VARCHAR(100) NOT NULL, company VARCHAR(255), PRIMARY KEY(title, company), FOREIGN KEY(company) REFERENCES company(name) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE student_employment(userid VARCHAR(20), title VARCHAR(100), company VARCHAR(255), start_time DATE, end_time DATE, PRIMARY KEY(userid, title, company, start_time, end_time), FOREIGN KEY(userid) REFERENCES student(userid) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(title, company) REFERENCES job(title, company) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE job_skill(title VARCHAR(100), company VARCHAR(255), sid INT, scale INT, PRIMARY KEY(title, company, sid), FOREIGN KEY(title, company) REFERENCES job(title, company) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(sid) REFERENCES skills(sid) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE prereq(deptcode VARCHAR(5),cNum INT,reqDeptcode VARCHAR(5),reqCNum INT, PRIMARY KEY(deptcode,cNum,reqDeptcode,reqCNum), FOREIGN KEY(deptcode,cNum) REFERENCES course(deptcode,cNum) ON DELETE CASCADE ON UPDATE CASCADE,FOREIGN KEY(reqDeptcode,reqCNum) REFERENCES course(deptcode,cNum) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE exclusion(cNum INT, deptcode VARCHAR(5), exCNum INT, exDeptcode VARCHAR(5), PRIMARY KEY(cNum,deptcode,exCNum,exdeptcode), FOREIGN KEY(cnum,deptcode) REFERENCES course(cnum,deptcode) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(exCNum,exDeptcode) REFERENCES course(cNum,deptcode) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE course_topic(deptcode VARCHAR(5), cNum INT, tid INT, PRIMARY KEY(deptcode,cNum,tid),FOREIGN KEY(deptcode,cNum) REFERENCES course(deptcode,cNum) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE course_skills(deptcode VARCHAR(5), cNum INT, sid INT, PRIMARY KEY(deptcode,cNum,sid), FOREIGN KEY(deptcode,cNum) REFERENCES course(deptcode,cNum) ON DELETE CASCADE ON UPDATE CASCADE,FOREIGN KEY(sid) REFERENCES skills(sid) ON DELETE CASCADE ON UPDATE CASCADE);

ALTER TABLE course ADD FOREIGN KEY (deptcode) REFERENCES department(deptcode) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE course_topic ADD FOREIGN KEY(tid) REFERENCES topic(tid) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE eskill ADD FOREIGN KEY(eid,userid) REFERENCES course_experience(eid,userid) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE etopic ADD FOREIGN KEY(eid,userid) REFERENCES course_experience(eid,userid) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE eskill ADD FOREIGN KEY(deptcode, cNum, sid) REFERENCES course_skills(deptcode, cNum, sid) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE etopic ADD FOREIGN KEY(deptcode, cNum, tid) REFERENCES course_topic(deptcode, cNum, tid) ON DELETE CASCADE ON UPDATE CASCADE;

/* Add constriants to specific values */
ALTER TABLE department ADD CHECK(deptcode = UPPER(deptcode));
ALTER TABLE course_experience ADD CHECK(grade BETWEEN 0 AND 100);
ALTER TABLE course_experience ADD CHECK(rank BETWEEN 1 AND 5);
ALTER TABLE course_experience ADD CHECK(satisfaction BETWEEN 1 AND 5);
ALTER TABLE eskill ADD CHECK(level_before BETWEEN 1 AND 5);
ALTER TABLE eskill ADD CHECK(level_after BETWEEN 1 AND 5);
ALTER TABLE etopic ADD CHECK(interest_before BETWEEN 1 AND 5);
ALTER TABLE etopic ADD CHECK(interest_after BETWEEN 1 AND 5);
ALTER TABLE student_employment ADD CHECK(start_time < end_time);
ALTER TABLE job_skill ADD CHECK(scale BETWEEN 2 AND 5);
ALTER TABLE courseed ADD CHECK(starttime < endtime);
ALTER TABLE course ADD CHECK(cNum BETWEEN 100 AND 3000);







 


