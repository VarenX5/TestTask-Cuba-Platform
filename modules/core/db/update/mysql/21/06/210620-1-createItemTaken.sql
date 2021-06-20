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
);