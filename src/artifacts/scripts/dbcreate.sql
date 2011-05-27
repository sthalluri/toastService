drop database if exists toast
go

create database toast
go

use toast
go

drop table if exists user
go

CREATE TABLE user
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    userId    		VARCHAR(50),
    firstName    	VARCHAR(50),
    lastName    	VARCHAR(50),
    phone   		VARCHAR(15),
    email         	VARCHAR(50),
    password        VARCHAR(25),
    defaultClubId	INT,
    updated			TIMESTAMP,
    created     	TIMESTAMP
)
go

drop table if exists club
go

CREATE TABLE club
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    clubId			VARCHAR(50),
    clubName		VARCHAR(200),
    passCode 		VARCHAR(50),
    updated			TIMESTAMP,
    created     	TIMESTAMP
)
go

drop table if exists clubMember
go

CREATE TABLE clubMember
(
    clubId          INT ,
    memberId		INT ,
    PRIMARY KEY (clubId, memberId)
)
go

drop table if exists meeting
go

CREATE TABLE meeting
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    clubId			INT,
    inProgress 		VARCHAR(1),
	wordOfTheDay	VARCHAR(200),
	themeOfTheDay	VARCHAR(250),
	meetingDate		TIMESTAMP,
    updated			TIMESTAMP,
    created     	TIMESTAMP
)
go

drop table if exists meetingRole
go

CREATE TABLE meetingRole
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    meetingId 		INT,
	roleId			VARCHAR(50),
	timeSpent		INT,
	amCount			VARCHAR(100),
    updated			TIMESTAMP,
    created     	TIMESTAMP
)
go


drop table if exists clubRole
go

CREATE TABLE clubRole
(
    id          	VARCHAR(50) PRIMARY KEY ,
    description 	VARCHAR(250)
)
go
