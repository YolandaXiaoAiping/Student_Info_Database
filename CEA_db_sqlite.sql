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

CREATE TABLE department(deptcode text PRIMARY KEY, name text, check(deptcode = UPPER(deptcode)));
CREATE TABLE instructor(name text PRIMARY KEY, gender text check(gender = 'F' or gender = 'M'), age int, type text check(type = 'permanent' or type = 'freelancer'), area text);
CREATE TABLE course(deptcode text, cNum int check(cNum between 100 and 3000), name text, area check(area = 'humanities' or area = 'sciences' or area = 'business' or area = 'arts' or area = 'engineering'), PRIMARY KEY(deptcode,cNum), FOREIGN KEY(deptcode) REFERENCES department(deptcode));
CREATE TABLE student(userid text PRIMARY KEY, name text, country text, birth text, begin_year text, gender text check(gender = 'F' or gender = 'M'));
CREATE TABLE topic(tid int PRIMARY KEY, name text);
CREATE TABLE skills(sid int PRIMARY KEY, name text);

CREATE TABLE faculty(name text PRIMARY KEY, type text check(type = 'research' or type = 'teaching'), start_year int, FOREIGN KEY(name) REFERENCES instructor(name) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE research_faculty(name text PRIMARY KEY, research_interests text, FOREIGN KEY(name) REFERENCES faculty(name) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE teaching_faculty(name text PRIMARY KEY, year_of_experience int, FOREIGN KEY(name) REFERENCES faculty(name) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE courseed(eid int PRIMARY KEY, deptcode text, cNum int, starttime text, endtime text, enroll_num int, time text check(time = 'morning' or time = 'day' or time = 'evening'), FOREIGN KEY (deptcode,cNum) REFERENCES course(deptcode,cNum) ON DELETE CASCADE ON UPDATE CASCADE, check(starttime < endtime));
CREATE TABLE course_experience(eid int, userid text, grade decimal(5,2) check(grade between 0 and 100), satisfaction int check(satisfaction between 1 and 5), rank int check(rank between 1 and 5), PRIMARY KEY(eid, userid), FOREIGN KEY(eid) REFERENCES courseed(eid) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(userid) REFERENCES student(userid) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE instructor_ranking(eid int, instructor text, PRIMARY KEY(eid, instructor), FOREIGN KEY(eid) REFERENCES courseed(eid)ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(instructor) REFERENCES instructor(name)ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE eskill(eid int, userid text, sid int, deptcode text, cNum int, level_before int check(level_before between 1 and 5), level_after int check(level_after between 1 and 5), PRIMARY KEY(eid, userid, sid), FOREIGN KEY(eid, userid) REFERENCES course_experience(eid, userid)ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(deptcode, cNum, sid) REFERENCES course_skills(deptcode, cNum, sid)ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE etopic(eid int, userid text, tid int, deptcode text, cNum int, interest_before int check(interest_before between 1 and 5), interest_after int check(interest_after between 1 and 5), PRIMARY KEY(eid, userid, tid), FOREIGN KEY(eid, userid) REFERENCES course_experience(eid, userid)ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(deptcode, cNum, tid) REFERENCES course_topic(deptcode, cNum, tid)ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE company(name text PRIMARY KEY, expertise text);
CREATE TABLE job(title text NOT NULL, company text, PRIMARY KEY(title, company), FOREIGN KEY(company) REFERENCES company(name) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE student_employment(userid text, title text, company text, start_time text, end_time text, PRIMARY KEY(userid, title, company, start_time, end_time), FOREIGN KEY(userid) REFERENCES student(userid) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(title, company) REFERENCES job(title, company) ON DELETE CASCADE ON UPDATE CASCADE, check(start_time < end_time));
CREATE TABLE job_skill(title text, company text, sid int, scale int check(scale between 2 and 5), PRIMARY KEY(title, company, sid), FOREIGN KEY(title, company) REFERENCES job(title, company) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(sid) REFERENCES skills(sid) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE prereq(deptcode text, cNum int, reqDeptcode text, reqCNum int, PRIMARY KEY(deptcode, cNum, reqDeptcode, reqCNum), FOREIGN KEY(deptcode, cNum) REFERENCES course(deptcode, cNum) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(reqDeptcode,reqCNum) REFERENCES course(deptcode,cNum) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE exclusion(cNum int, deptcode text, exCNum int, exDeptcode text, PRIMARY KEY(cNum,deptcode,exCNum,exdeptcode), FOREIGN KEY(cnum, deptcode) REFERENCES course(cnum, deptcode) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(exCNum,exDeptcode) REFERENCES course(cNum,deptcode) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE course_topic(deptcode text, cNum int, tid int, PRIMARY KEY(deptcode, cNum, tid), FOREIGN KEY(deptcode, cNum) REFERENCES course(deptcode, cNum) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(tid) REFERENCES topic(tid) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE course_skills(deptcode text, cNum int, sid int, PRIMARY KEY(deptcode, cNum, sid), FOREIGN KEY(deptcode, cNum) REFERENCES course(deptcode, cNum) ON DELETE CASCADE ON UPDATE CASCADE,FOREIGN KEY(sid) REFERENCES skills(sid) ON DELETE CASCADE ON UPDATE CASCADE);

