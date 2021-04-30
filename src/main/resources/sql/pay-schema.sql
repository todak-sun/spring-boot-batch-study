create table PAY (
     PAY_ID     bigint not null,
     AMOUNT     bigint,
     TX_NAME     varchar(255),
     TX_DATE_TIME datetime,
     primary key (PAY_ID)
);

create table PAY2 (
     PAY_ID       bigint identity(1, 1) not null,
     AMOUNT       bigint,
     TX_NAME      varchar(255),
     TX_DATE_TIME datetime,
     primary key (PAY_ID)
);