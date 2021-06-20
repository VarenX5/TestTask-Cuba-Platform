-- begin TESTTASK2_DVD
create table TESTTASK2_DVD (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    OWNER_ID varchar(32) not null,
    --
    primary key (ID)
)^
-- end TESTTASK2_DVD
-- begin TESTTASK2_DVD_OWNER
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
)^
-- end TESTTASK2_DVD_OWNER
-- begin TESTTASK2_ITEM_TAKEN
create table TESTTASK2_ITEM_TAKEN (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    CURRENT_OWNER_ID varchar(32) not null,
    TAKEN_DVD_ID varchar(32) not null,
    --
    primary key (ID)
)^
-- end TESTTASK2_ITEM_TAKEN
