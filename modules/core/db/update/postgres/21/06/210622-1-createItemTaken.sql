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
);