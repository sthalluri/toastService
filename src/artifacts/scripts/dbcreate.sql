drop database if exists toast;

create database toast;

use toast;

drop table if exists user;

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
    aboutMe     VARCHAR(256),
    updated			TIMESTAMP,
    created     	TIMESTAMP
);

ALTER TABLE user ADD UNIQUE INDEX email_UNIQUE (email ASC) ;

drop table if exists club;

CREATE TABLE club
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    clubId			VARCHAR(50),
    clubName		VARCHAR(200),
    passCode 		VARCHAR(50),
    clubSettings	VARCHAR(2000),
    updated			TIMESTAMP,
    created     	TIMESTAMP
);

drop table if exists clubMember;

CREATE TABLE clubMember
(
    clubId          INT ,
    memberId		INT ,
    PRIMARY KEY (clubId, memberId)
);

ALTER TABLE clubMember ADD CONSTRAINT userMember
  FOREIGN KEY (memberId)
  REFERENCES user (id)
  ON DELETE CASCADE
  ON UPDATE NO ACTION
, ADD INDEX userMember (memberId ASC) ;


drop table if exists meeting;

CREATE TABLE meeting
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    clubId			INT,
    inProgress 		VARCHAR(1),
	wordOfTheDay	VARCHAR(200),
	themeOfTheDay	VARCHAR(250),
	meetingDate		TIMESTAMP,
	location		VARCHAR(250),
	gramLog			VARCHAR(2000),
    updated			TIMESTAMP,
    created     	TIMESTAMP
);

drop table if exists meetingRole;

CREATE TABLE meetingRole
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    meetingId 		INT,
	roleId			VARCHAR(50),
	timeSpent		INT,
	amCount			VARCHAR(100),
	timeLimits		VARCHAR(100),
	userId			INT,
    updated			TIMESTAMP,
    created     	TIMESTAMP
);


drop table if exists meetingRoleContent;

CREATE TABLE meetingRoleContent
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    meetingRoleId 	INT,
	content			VARCHAR(2500),
	userId			INT,
    updated			TIMESTAMP,
    created     	TIMESTAMP
);

drop table if exists clubRole;

CREATE TABLE clubRole
(
    id          	VARCHAR(50) PRIMARY KEY ,
    description 	VARCHAR(250),
    trackTime 		VARCHAR(1)
)
go
