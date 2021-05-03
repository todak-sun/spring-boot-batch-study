create table PAGE_WARN
(
    PAGE_WARN_ID   bigint identity (1, 1) not null,
    AMOUNT         bigint,
    SUCCESS_STATUS tinyint,
    primary key (PAGE_WARN_ID)
);