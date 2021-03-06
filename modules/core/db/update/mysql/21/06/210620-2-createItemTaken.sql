alter table TESTTASK2_ITEM_TAKEN add constraint FK_TESTTASK2_ITEM_TAKEN_ON_CURRENT_OWNER foreign key (CURRENT_OWNER_ID) references TESTTASK2_DVD_OWNER(ID);
alter table TESTTASK2_ITEM_TAKEN add constraint FK_TESTTASK2_ITEM_TAKEN_ON_TAKEN_DVD foreign key (TAKEN_DVD_ID) references TESTTASK2_DVD(ID);
create index IDX_TESTTASK2_ITEM_TAKEN_ON_CURRENT_OWNER on TESTTASK2_ITEM_TAKEN (CURRENT_OWNER_ID);
create index IDX_TESTTASK2_ITEM_TAKEN_ON_TAKEN_DVD on TESTTASK2_ITEM_TAKEN (TAKEN_DVD_ID);
