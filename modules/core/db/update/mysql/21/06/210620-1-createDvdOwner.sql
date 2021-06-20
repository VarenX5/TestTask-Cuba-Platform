create table TESTTASK2_DVD_OWNER (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    FIRST_NAME varchar(255),
    LAST_NAME varchar(255),
    EMAIL varchar(255),
    SYSTEM_USER_ID varchar(32) not null,
    --
    primary key (ID)
);