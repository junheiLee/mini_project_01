create table student (
	studentId int auto_increment,
	studentName varchar(20) not null,
	mbti varchar(20),
	isSmoker boolean default false,
	isInProgress boolean default true,
	primary key(studentId)
);

create table seat (
	seatId int auto_increment,
	seatRow int,
	seatColumn int,
	isUsed boolean default true,
	primary key(seatId)
);

create table arrangement (
	id int auto_increment,
	arrangeDate date,
	seatId int,
	studentId int,
	primary key(id)
);