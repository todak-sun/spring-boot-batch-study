CREATE TABLE PERSON
(
    ID      bigint primary key IDENTITY (1, 1),
    NAME    nvarchar(255),
    AGE     nvarchar(255),
    ADDRESS nvarchar(255)
);

INSERT INTO PERSON(NAME, AGE, ADDRESS)
VALUES (N'선용주', '30', N'동대문구');

INSERT INTO PERSON(NAME, AGE, ADDRESS)
VALUES (N'홍길동', '30', N'서울');

INSERT INTO PERSON(NAME, AGE, ADDRESS)
VALUES (N'아무개', '25', N'강원');

INSERT INTO PERSON(NAME, AGE, ADDRESS)
VALUES (N'홍길순', '40', N'경기도');

SELECT * FROM PERSON;
