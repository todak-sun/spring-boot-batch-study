CREATE TABLE PERSON
(
    ID      bigint primary key AUTO_INCREMENT,
    NAME    varchar(255),
    AGE     varchar(255),
    ADDRESS varchar(255)
);

INSERT INTO PERSON(NAME, AGE, ADDRESS)
VALUES ('선용주', '31', '인천');

INSERT INTO PERSON(NAME, AGE, ADDRESS)
VALUES ('홍길동', '30', '서울');

INSERT INTO PERSON(NAME, AGE, ADDRESS)
VALUES ('아무개', '25', '강원');

INSERT INTO PERSON(NAME, AGE, ADDRESS)
VALUES ('홍길순', '40', '경기도');