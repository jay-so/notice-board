


create table users(
   user_id varchar2(10) primary key,	
   pwd varchar2(10), 
   name varchar2(10)
)

select * from users;

select user_id , pwd, name from users where user_id='jang' and pwd= '1234'

insert into users values('jang', '1234', '장희정');
insert into users values('lee', '1234', '이가현');
insert into users values('jaehoon','1234','소재훈');
insert into users values('youngse','1234','전영서');


commit