-- begin TESTTASK2_DVD
create table TESTTASK2_DVD (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    OWNER_ID uuid not null,
    --
    primary key (ID)
)^
-- end TESTTASK2_DVD
-- begin TESTTASK2_DVD_OWNER
create table TESTTASK2_DVD_OWNER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FIRST_NAME varchar(255),
    LAST_NAME varchar(255),
    EMAIL varchar(255),
    SYSTEM_USER_ID uuid not null,
    --
    primary key (ID)
)^
-- end TESTTASK2_DVD_OWNER
-- begin TESTTASK2_ITEM_TAKEN
create table TESTTASK2_ITEM_TAKEN (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CURRENT_OWNER_ID uuid not null,
    TAKEN_DVD_ID uuid not null,
    --
    primary key (ID)
)^
-- end TESTTASK2_ITEM_TAKEN
