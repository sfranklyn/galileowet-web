create database if not exists galileowet;
create user 'galileowet'@'localhost' identified by 'galileowet';
grant all on galileowet.* to 'galileowet'@'localhost';