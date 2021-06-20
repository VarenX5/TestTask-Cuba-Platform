package com.company.testtask2.web.screens.itemtaken;

import com.haulmont.cuba.gui.screen.*;
import com.company.testtask2.entity.ItemTaken;

@UiController("testtask2_ItemTaken.browse")
@UiDescriptor("item-taken-by-user-browse.xml")
@LookupComponent("itemTakensTable")
@LoadDataBeforeShow
public class ItemTakenByUserBrowse extends StandardLookup<ItemTaken> {
}