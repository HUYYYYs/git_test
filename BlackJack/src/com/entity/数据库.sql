create database blackjack;
use blackjack;
create table users(
	userId int primary key auto_increment,
	name varchar(50) unique,
	icon varchar(200),
	money int
);
create table records(
	id int primary key auto_increment,
	playName varchar(50),
	numbers int,
	money int,
	state varchar(30),
	againUser varchar(50),
	addTime timestamp
);